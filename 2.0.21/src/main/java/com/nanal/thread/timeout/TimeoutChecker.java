package com.nanal.thread.timeout;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import com.nanal.db.AppSendDataDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class TimeoutChecker implements Runnable {

    private final Logger rootLogger;
    private final AppSendDataDao appSendDataDao;

    @Override
    public void run() {
        rootLogger.trace("Timeout check thread start");

        while (true) {
            List<String> serialNumberList = appSendDataDao.selectTimeoutData();

            if (serialNumberList.isEmpty()) {
                break;
            }

            appSendDataDao.updateTimeoutDataError(serialNumberList);
        }

        rootLogger.trace("Timeout check thread end");
    }
}
