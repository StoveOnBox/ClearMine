package com.senvenwood;
import java.util.TimerTask;

public class Time extends TimerTask {
    public static int num;
    FramMain f = new FramMain(2, 2, 2);

    Time(FramMain f) {
        this.f = f;//把窗体传近来
    }

    public void run() {
        num++;//记数
        String tmpn = Integer.toString(num);
        for (int i = 0; i < 5 - tmpn.length(); i++)
            tmpn = "0" + tmpn;
        f.jLabel4.setText(tmpn);
    }
}
