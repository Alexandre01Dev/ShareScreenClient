package be.alexandre01.sharescreenmc.window;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import javax.swing.text.Element;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DebugWindow {
    @FXML
    private ImageView img;

    @FXML
    public void initialize(){
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new Runnable() {

            double ms = 0.7;
            @Override
            public void run() {
                setScreen();
            }

        }, 0, 50, TimeUnit.MILLISECONDS);


    }

    public void setScreen(){
        try {
            Robot robot = new Robot();

            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
           // ImageIO.write(screenFullImage, format, new File(fileName));


           img.setImage(SwingFXUtils.toFXImage(screenFullImage, null));
        } catch (AWTException ex) {
            System.err.println(ex);
        }
    }
}
