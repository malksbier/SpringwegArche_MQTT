package de.springwegarche.webpage.Util.Mqtt;

import de.springwegarche.webpage.Util.ConsolePrinter;

public class MqttApplication extends Thread {
    private final String TAG = "[MqttApplication] ";
    private ConsolePrinter consolePrinter = new ConsolePrinter(true);
    private boolean running = true;

    public boolean getRunning() {
        return this.running;
    }

    private void setRunning(boolean running) {
        this.running = running;
    }

    private void loop() throws InterruptedException {
        consolePrinter.println(TAG + "loop");

        Thread.sleep(5000);
    }

    public void run() {
        consolePrinter.println(TAG + "started");
        while(running) {
            try {
                loop();
            } catch (Exception e) {
                consolePrinter.println(TAG + "got Exception");
                e.printStackTrace();
                setRunning(false);
            }
        }
        consolePrinter.println(TAG + "stoped");
    }
    
}
