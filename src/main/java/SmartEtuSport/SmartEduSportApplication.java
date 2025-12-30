package SmartEtuSport;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmartEduSportApplication {
    public static void main(String[] args) {
        // Lancer JavaFX au lieu de Spring Boot directement
        Application.launch(JavaFXApplication.class, args);
    }
}