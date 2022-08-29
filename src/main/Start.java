package main;

public class Start {

    public static void main(String[] args) {
        new ScheduledThreadTaskManager(ScheduledThreadTaskManager.Mode.INDEFINITE);
        new ScheduledThreadTaskManager(ScheduledThreadTaskManager.Mode.FIXED_NO_OF_TIMES);
    }
}

