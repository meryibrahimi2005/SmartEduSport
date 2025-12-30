package SmartEtuSport.config;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class ViewManager {

    private Stage primaryStage;

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    public void switchScene(Scene scene) {
        if (primaryStage != null) {
            primaryStage.setScene(scene);
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}