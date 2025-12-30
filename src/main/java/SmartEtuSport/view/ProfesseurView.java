package SmartEtuSport.view;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

@Component
public class ProfesseurView {
    public VBox getView() {
        VBox box = new VBox();
        box.getChildren().add(new Label("Vue Professeur - En cours de développement"));
        return box;
    }
}
