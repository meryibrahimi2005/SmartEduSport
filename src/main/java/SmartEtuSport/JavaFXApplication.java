package SmartEtuSport;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class JavaFXApplication extends Application {

    private ConfigurableApplicationContext context;

    @Override
    public void init() {
        // Initialiser Spring Boot
        this.context = new SpringApplicationBuilder()
                .sources(SmartEduSportApplication.class)
                .run(getParameters().getRaw().toArray(new String[0]));
    }

    @Override
    public void start(Stage primaryStage) {
        // Récupérer le StageManager du contexte Spring
        context.publishEvent(new StageReadyEvent(primaryStage));
    }

    @Override
    public void stop() {
        // Fermer le contexte Spring
        this.context.close();
        Platform.exit();
    }
}
