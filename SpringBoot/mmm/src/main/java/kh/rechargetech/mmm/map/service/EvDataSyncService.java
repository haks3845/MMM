package kh.rechargetech.mmm.map.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Service
@RequiredArgsConstructor
public class EvDataSyncService {

    private final StationService stationService;

    @Value("${ev.api.base-url}")
    private String baseUrl;

    @Value("${ev.api.service-key}")
    private String serviceKey;

    private final AtomicBoolean isRunning = new AtomicBoolean(false);
    private final AtomicBoolean stopRequested = new AtomicBoolean(false);
    private final RestClient restClient = RestClient.create();

    /**
     * 동기화 시작 요청
     */
    public boolean triggerSync() {
        if (!isRunning.compareAndSet(false, true)) {
            log.warn("⚠️ 이미 데이터 동기화가 진행 중입니다.");
            return false;
        }
        stopRequested.set(false);
        executeSyncAsync();
        return true;
    }

    /**
     * 동기화 중단 요청
     */
    public boolean stopSync() {
        if (!isRunning.get()) {
            return false;
        }
        stopRequested.set(true);
        log.info("🛑 [중단 요청] 현재 작업 마무리 후 동기화를 즉시 중단합니다.");
        return true;
    }

    /**
     * 백그라운드 스레드에서 실제 공공데이터 API를 호출해 적재하는 메서드
     */
    @Async
    public void executeSyncAsync() {
        int pageNo = 1;
        int numOfRows = 1000;
        boolean hasMoreData = true;
        int retryCount = 0;
        final int MAX_RETRY = 10000;

        log.info("🔄 [수동 동기화 시작] 대용량 충전소 데이터 수집을 시작합니다.");

        try {
            while (hasMoreData && !stopRequested.get()) {
                try {
                    URI uri = UriComponentsBuilder.fromUriString(baseUrl)
                            .queryParam("serviceKey", serviceKey)
                            .queryParam("pageNo", pageNo)
                            .queryParam("numOfRows", numOfRows)
                            .queryParam("dataType", "JSON")
                            .build(true)
                            .toUri();

                    String jsonResult = restClient.get()
                            .uri(uri)
                            .header("Accept", "application/json")
                            .retrieve()
                            .body(String.class);

                    int insertedCount = stationService.insertStationList(jsonResult);
                    log.info("📦 [진행 안내] {} 페이지 완료 ({}건 처리됨)", pageNo, insertedCount);
                    retryCount = 0;

                    if (insertedCount < numOfRows) {
                        hasMoreData = false;
                        log.info("✨ [동기화 최종 완료] 데이터 적재 완료!");
                    } else {
                        pageNo++;
                        // 30초 대기 중 중단 요청이 들어오면 1초 내로 즉시 탈출
                        for (int i = 0; i < 30; i++) {
                            if (stopRequested.get()) break;
                            Thread.sleep(1000);
                        }
                    }
                } catch (Exception e) {
                    log.error("❌ API 호출/처리 중 오류 발생: {}", e.getMessage());
                    retryCount++;
                    if (retryCount <= MAX_RETRY && !stopRequested.get()) {
                        log.info("🔄 [재시도] 10초 후 {} 페이지를 다시 요청합니다. ({}/{})", pageNo, retryCount, MAX_RETRY);
                        // 재시도 10초 대기 중에도 중단 요청 시 즉시 탈출
                        for (int i = 0; i < 10; i++) {
                            if (stopRequested.get()) break;
                            Thread.sleep(1000);
                        }
                    } else {
                        hasMoreData = false;
                        if (!stopRequested.get()) {
                            log.error("🛑 최대 재시도 횟수를 초과하여 동기화를 중단합니다.");
                        }
                    }
                }
            }

            if (stopRequested.get()) {
                log.info("⏹️ [동기화 중단 완료] 사용자 요청으로 데이터 수집이 정지되었습니다.");
            }
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            log.warn("🛑 스레드 인터럽트로 동기화가 중단되었습니다.");
        } finally {
            isRunning.set(false);
            stopRequested.set(false);
        }
    }
    
    
}