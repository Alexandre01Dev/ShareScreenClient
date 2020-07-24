package be.alexandre01.sharescreenmc;

import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Controller {
    @FXML
    private Button button;
    @FXML
    private ImageView imageClose;
    @FXML
    private AnchorPane connection;
    @FXML
    private ImageView imageMin;


    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private MenuBar menuBar;
    @FXML
    private TextField ip;
    @FXML
    private TextField port;
    @FXML
    void onButton(ActionEvent event) {

    }

    @FXML
    public void initialize() {
        CornerRadii cornerRadii = new CornerRadii(0.01,true);
        BackgroundFill background_fill = new BackgroundFill(Color.rgb(61, 99, 254),
                cornerRadii, Insets.EMPTY);
        button.setBackground(new Background(background_fill));
        button.setTextFill(Color.rgb(255, 254, 253));
        button.setFont(new Font(button.getFont().getSize()+5));
        imageClose.setImage(new Image("/buttonClose.png"));
        imageClose.setPreserveRatio(false);
        imageMin.setImage(new Image("/buttonMin.png"));
        imageClose.setPreserveRatio(false);
        brightnessButton(imageClose,-0.4);
        brightnessButton(imageMin,-0.4);
        brightnessButton(button,-0.25);
    }


    @FXML
    void onButtonClick(MouseEvent event) {
    if(ip.getText().isEmpty()){
        addAnnotation();
        return;
    }
        if(port.getText().isEmpty()){

            addAnnotation();
            return;
        }
        if(!port.getText().matches("^[0-9]+$")){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Port area is invalid", ButtonType.OK);
            alert.showAndWait();
            return;
        }
                Node node = (Node) event.getSource();
                Parent root = null;

                    setConnection(true,connection);
                   /* root = FXMLLoader.load(Main.class.getResource("/ressources/loading.fxml"));
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.UTILITY);
                    stage.setTitle("Connection...");
                    stage.setScene(new Scene(root));
                    stage.show();*/



                return;
            }




    @FXML
    void onButtonHover(MouseEvent event) {

    }

    @FXML
    void onImageHover(MouseEvent event) {

       // g.drawImage(image, x, y);
    }

    @FXML
    void onMin(MouseEvent event) {
        ImageView imageView = (ImageView) event.getSource();
        Stage stage = (Stage) imageView.getScene().getWindow();
        stage.setIconified(true);
    }
    @FXML
    void onClose(MouseEvent event){
        Node node = (Node) event.getSource();
        node.getScene().getWindow().hide();
        System.exit(0);
    }
    @FXML
    void onDrag(MouseEvent event){
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }
    @FXML
    void onDragged(MouseEvent event){
        Node node= (Node)event.getSource();
       Stage stage = (Stage) node.getScene().getWindow();
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }
    public void brightnessButton(Node node,double value){
        node.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                ColorAdjust colorAdjust = new ColorAdjust();
                colorAdjust.setBrightness(value);
                node.setEffect(colorAdjust);
            } else {
                node.setEffect(null);
            }
        });
    }
    public void addAnnotation(){
        Alert alert = new Alert(Alert.AlertType.WARNING, "Complete Adress and port area", ButtonType.OK);
        alert.showAndWait();

    }
    public void setConnection(boolean isConnection,Node node){
        if(isConnection){
            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            scheduler.scheduleAtFixedRate(new Runnable() {

                double ms = 0.05;
                @Override
                public void run() {
                    if(ms >= 0.7){
                        System.out.println("yes");
                        scheduler.shutdown();
                    }
                    System.out.println(ms);
                    ms = ms + 0.01;
                   node.setOpacity(ms);
                }

            }, 0, 5, TimeUnit.MILLISECONDS);

        }else {
            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            scheduler.scheduleAtFixedRate(new Runnable() {

                double ms = 0.7;
                @Override
                public void run() {
                    if(ms <= 0){
                        System.out.println("yes");
                        scheduler.shutdown();
                    }
                    System.out.println(ms);
                    ms = ms - 0.01;
                    node.setOpacity(ms);
                }

            }, 0, 5, TimeUnit.MILLISECONDS);
        }
    }

    @FXML
    void debugClicked(ActionEvent event) {
        System.out.println("ok");
        Parent root = null;
        try {
             root = FXMLLoader.load(Main.class.getResource("/ressources/debug.fxml"));
            System.out.println("ok");
            Stage stage = new Stage();
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("DebugMode");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}


