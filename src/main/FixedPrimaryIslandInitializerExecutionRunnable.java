package main;

import property.util.ActionsForOrganisms;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedPrimaryIslandInitializerExecutionRunnable implements Runnable {
    private final AtomicInteger runCount = new AtomicInteger();
    private static ActionsForOrganisms actionsForOrganisms = new ActionsForOrganisms();
    private final Runnable delegate;
    private ScheduledFuture<?> self;
    private final int maxRunCount;

    public FixedPrimaryIslandInitializerExecutionRunnable(Runnable delegate, int maxRunCount) {
        this.delegate = delegate;
        this.maxRunCount = maxRunCount;
    }

    @Override
    public void run() {
        delegate.run();
        if (runCount.incrementAndGet() == maxRunCount) {
            boolean interrupted = false;
            try {
                while (self == null) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        interrupted = true;
                    }
                }
                self.cancel(false);
            } finally {
                if (interrupted) {
                    Thread.currentThread().interrupt();
                    self.cancel(false);
                }
            }
        }
    }

    public void runNTimes(ScheduledExecutorService executor, long period, TimeUnit unit) {
        self = executor.scheduleAtFixedRate(this, 0, period, unit);
    }
}

