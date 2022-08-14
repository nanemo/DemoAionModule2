package main;

public class Start {

    public static void main(String[] args) {
        ScheduledThreadTaskManager firstInitializer = new ScheduledThreadTaskManager(ScheduledThreadTaskManager.Mode.INDEFINITE);
        ScheduledThreadTaskManager action = new ScheduledThreadTaskManager(ScheduledThreadTaskManager.Mode.FIXED_NO_OF_TIMES);
    }
}

