package com.wg.service;

import org.quartz.SchedulerException;

public interface SchedulerService {

	void executeJob();
	
	void startScheduler() throws SchedulerException;
    void stopScheduler() throws SchedulerException;

}
