package com.example.demo.util;

import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class PoolService {

    public ExecutorService getPool(){
        /* no need to use multi-thread executor.

        BlockingDeque<Runnable> taskQueue = new LinkedBlockingDeque<>();
        ThreadFactory nameThreadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread("get like to go thread "+r);
            }
        };

        ExecutorService pool = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),100,60L, TimeUnit.SECONDS,taskQueue,nameThreadFactory);
        */
        ExecutorService pool = Executors.newSingleThreadExecutor();
        return pool;
    }
}
