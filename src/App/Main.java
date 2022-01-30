package App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        //load file from path
        Parent root = FXMLLoader.load(this.getClass().getResource("hanoi.fxml"));

        //creating scene
        Scene scene = new Scene(root);

        //disabling maximize button
        primaryStage.resizableProperty().setValue(Boolean.FALSE);

        //setting title
        primaryStage.setTitle("Ex Hanoi");

        //setting the size of window
        primaryStage.setWidth(970);
        primaryStage.setHeight(740);

        //setting icon
        primaryStage.getIcons().add(new Image("App/hanoi.jpg"));

        //connecting
        primaryStage.setScene(scene);

        //showing
        primaryStage.show();
    }
}