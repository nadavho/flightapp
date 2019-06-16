package com.flightapp.common.utils.cache;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
@Log4j2
public class CacheEvacuator {


    private static final int MAX_SIZE = 30;
    private List<CacheExpireConfig> monitoredCaches = new LinkedList();

    @PostConstruct
    private void init() {
        Executors.newSingleThreadScheduledExecutor()
                .scheduleAtFixedRate(() -> {
                    try {
                        if (monitoredCaches.isEmpty()) {
                            log.debug("No caches to monitor yet..");
                            return;
                        }
                        log.debug("Started entries evacuation process for {} caches", monitoredCaches.size());
                        int poolSize = monitoredCaches.size() > MAX_SIZE ? MAX_SIZE : monitoredCaches.size();
                        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
                        monitoredCaches.forEach(cache -> executorService.submit(() -> clearExpiredEntries(cache)));
                        executorService.shutdown();
                        executorService.awaitTermination(1, TimeUnit.MINUTES);
                        log.debug("Done entries evacuation process for {} caches", monitoredCaches.size());
                    } catch (Throwable t) {
                        log.error("Failed to monitor caches", t);
                    }
                }, 0, 1, TimeUnit.SECONDS);
    }

    private void clearExpiredEntries(CacheExpireConfig cacheExpireConfig) {
        cacheExpireConfig.cache.entries().removeIf(cacheEntry ->
                System.currentTimeMillis() - cacheEntry.timestamp > cacheExpireConfig.experiation
        );
    }


    public void monitor(Cache<?> cache, long expiration, TimeUnit timeUnit) {
        monitoredCaches.add(new CacheExpireConfig(cache, expiration, timeUnit));
    }

    private static class CacheExpireConfig {

        private final Cache<?> cache;
        private final long experiation;

        CacheExpireConfig(Cache<?> cache, long experiation, TimeUnit timeUnit) {
            this.cache = cache;
            this.experiation = timeUnit.toMillis(experiation);

        }
    }
}
