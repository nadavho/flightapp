package com.flightapp.common.utils.cache;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

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

    private void init() {
        Executors.newSingleThreadScheduledExecutor()
                .scheduleAtFixedRate(() -> {
                    try {
                        log.debug("Starting ");
                        int poolSize = monitoredCaches.size() > MAX_SIZE ? MAX_SIZE : monitoredCaches.size();
                        ExecutorService executorService = Executors.newFixedThreadPool(monitoredCaches.size());
                        monitoredCaches.forEach(cache -> executorService.submit(() -> clearExpiredEntries(cache)));
                        executorService.awaitTermination(1, TimeUnit.MINUTES);
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
