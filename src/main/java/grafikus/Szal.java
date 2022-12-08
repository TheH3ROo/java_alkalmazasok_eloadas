package grafikus;

import javafx.application.Platform;
import javafx.scene.control.Label;
import java.util.Random;

class Szal extends Thread{
    Label label;
    Integer sleep_time;
    public Szal(Label label, Integer sleep_time) {
        this.label = label;
        this.sleep_time = sleep_time;
    }

    @Override
    public void run(){
        while(true){
            Platform.runLater(() -> {
                label.setText(genString());
            });
            try {
                Thread.sleep(sleep_time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String genString(){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        int length = 7;

        for(int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }

}
