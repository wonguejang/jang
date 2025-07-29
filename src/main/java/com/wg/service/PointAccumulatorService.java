package com.wg.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PointAccumulatorService {

    private final AtomicInteger accumulatedPoints = new AtomicInteger(0);

    public void addPoints(int points) {
        accumulatedPoints.addAndGet(points);
    }

    public int getAndResetPoints() {
        return accumulatedPoints.getAndSet(0);
    }

    public int getAccumulatedPoints() {
        return accumulatedPoints.get();
    }
}
