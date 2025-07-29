package com.wg.common;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;



@Component
@DisallowConcurrentExecution
public class TestJob implements Job {

    // 누적 포인트 변수 (static 유지)
    private static int accumulatedPoints = 0;

    @Override
    public void execute(JobExecutionContext context) {
        accumulatedPoints++;
        System.out.println("Job 실행됨, 누적 포인트 : " + accumulatedPoints);
        // DB 업데이트는 하지 않음
    }

    // 외부에서 누적 포인트를 가져갈 수 있도록 getter
    public static int getAccumulatedPoints() {
        return accumulatedPoints;
    }

    // 외부에서 누적 포인트 초기화할 수 있도록 setter
    public static void resetAccumulatedPoints() {
        accumulatedPoints = 0;
    }
}
