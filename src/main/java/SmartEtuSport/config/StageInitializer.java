package SmartEtuSport.config;

import SmartEtuSport.StageReadyEvent;
import SmartEtuSport.view.LoginView;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StageInitializer implements ApplicationListener<StageReadyEvent> {

    private final LoginView loginView;

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        Stage stage = event.getStage();

        // Configuration de la fenêtre principale
        stage.setTitle("SmartEduSport - Gestion Sportive");
        stage.setWidth(1400);
        stage.setHeight(900);
        stage.setMinWidth(1200);
        stage.setMinHeight(700);

        // Icône de l'application
        try {
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/logo.png")));
        } catch (Exception e) {
            System.out.println("Logo non trouvé");
        }

        // Afficher la page de connexion
        Scene scene = loginView.getScene();
        stage.setScene(scene);
        stage.show();
    }
}
