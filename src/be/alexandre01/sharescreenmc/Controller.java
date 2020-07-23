package be.alexandre01.sharescreenmc;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;

public class Controller {
    @FXML
    private Button button;
    @FXML
    private ImageView imageClose;
    @FXML
    private ImageView imageMin;
    @FXML
    private double xOffset = 0;
    private double yOffset = 0;
    private MenuBar menuBar;
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

}


