package com.wg.service;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wg.common.TestJob;

@Service
public class SchedulerServiceImpl implements SchedulerService {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private MemberService memberService;

    @Override
    public void startScheduler() throws SchedulerException {
        System.out.println("스케줄러가 시작되었음");

        JobKey jobKey = new JobKey("pointJob");

        if (!scheduler.checkExists(jobKey)) {
            JobDetail jobDetail = JobBuilder.newJob(TestJob.class)
                    .withIdentity(jobKey)
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("pointTrigger")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInSeconds(20)  // ← 이 부분이 실행 간격 설정 위치입니다.
                            .repeatForever())
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);
        }

        if (!scheduler.isStarted()) {
            scheduler.start();
        }
    }


    @Override
    public void stopScheduler() throws SchedulerException {
        System.out.println("<< 스케줄러 실행 종료 >>");

        // 스케줄러 중지
        scheduler.shutdown();

        // 누적 포인트 가져오기
        int totalPoints = TestJob.getAccumulatedPoints();

        if (totalPoints > 0) {
            int updatedCount = memberService.increaseAllMemberPoints(totalPoints);
            System.out.println(updatedCount + "명에게 " + totalPoints + "점씩 포인트 부여 완료.");

            // 누적 초기화
            TestJob.resetAccumulatedPoints();
        } else {
            System.out.println("누적 포인트가 없어서 업데이트하지 않음.");
        }
    }

	@Override
	public void executeJob() {
		// TODO Auto-generated method stub
		
	}
}
