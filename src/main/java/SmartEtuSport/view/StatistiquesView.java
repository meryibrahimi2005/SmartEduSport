package SmartEtuSport.view;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

@Component
public class StatistiquesView {
    public VBox getView() {
        VBox box = new VBox();
        box.getChildren().add(new Label("Vue Statistiques - En cours de développement"));
        return box;
    }
}
