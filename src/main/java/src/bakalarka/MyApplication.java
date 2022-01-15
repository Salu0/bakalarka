package src.bakalarka;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.bakalarka.db.Db;

import java.io.IOException;

public class MyApplication extends Application {

    public static boolean canRun = true;

    @Override
    public void start(Stage stage) throws IOException {
        Db.setUp();
        FXMLLoader fxmlLoader = new FXMLLoader(MyApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}