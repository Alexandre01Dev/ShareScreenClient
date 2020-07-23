package be.alexandre01.sharescreenmc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/ressources/sample.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("ScreenSharingMC");
        primaryStage.setScene(new Scene(root, 643, 400));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
