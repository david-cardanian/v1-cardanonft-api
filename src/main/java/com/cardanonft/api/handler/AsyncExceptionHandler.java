package com.cardanonft.api.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

/**
 * @Title      : AsyncConfig Exception Handler
 * @Filename   : AsyncExceptionHandler.java
 */
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(AsyncExceptionHandler.class);
    /**
     * AsyncTask 에서 오류 발생 시 실행
     */
    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
        logger.error("==============>>>>>>>>>>>> THREAD ERROR");
        logger.error("Exception Message :: " + throwable.getMessage());
        logger.error("Method Name :: " + method.getName());
        for (Object param : obj) {
            logger.error("Parameter Value :: " + param);
        }
        // JOB_LOG : 종료 입력
        logger.error("==============>>>>>>>>>>>> THREAD ERROR END");
    }

}
