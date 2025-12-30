package SmartEtuSport.view;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

@Component
public class NiveauxView {
    public VBox getView() {
        VBox box = new VBox();
        box.getChildren().add(new Label("Vue Niveaux - En cours de développement"));
        return box;
    }
}
