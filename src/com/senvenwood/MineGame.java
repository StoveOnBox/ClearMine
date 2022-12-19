package com.senvenwood;
import javax.swing.*;
import java.awt.*;

public class MineGame {
    boolean packFrame = false;

    /**
     * Construct and show the application.
     */
    public MineGame(int x, int y, int z) {
        FramMain frame = new FramMain(x, y, z);
        // Validate frames that have preset sizes
        // Pack frames that have useful preferred size info, e.g. from their layout
        if (packFrame) {
            frame.pack();
        } else {
            frame.validate();
        }
        // Center the window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        frame.setVisible(true);
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Application entry point.
     *
     * @param args String[]
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.
                            getSystemLookAndFeelClassName());
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                //-------------------------------雷区大小
                int x = 20;//行雷区大小
                int y = 20;//列雷区大小
                int z = 50;//雷区雷数目
                //-------------------------------
                new MineGame(x, y, z);
            }
        });
    }

    private void jbInit() throws Exception {
    }
}
