package SmartEtuSport.view;

import SmartEtuSport.config.ViewManager;
import SmartEtuSport.service.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginView {

    private final AuthService authService;
    private final ViewManager viewManager;
    private final DashboardView dashboardView;

    public Scene getScene() {
        // Conteneur principal
        StackPane root = new StackPane();
        root.getStyleClass().add("login-container");

        // Boîte de connexion
        VBox loginBox = createLoginBox();

        root.getChildren().add(loginBox);

        Scene scene = new Scene(root, 1400, 900);
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());

        return scene;
    }

    private VBox createLoginBox() {
        VBox box = new VBox(20);
        box.getStyleClass().add("login-box");
        box.setMaxWidth(450);
        box.setAlignment(Pos.CENTER);

        // Logo et titre
        Text title = new Text("🏃 SmartEduSport");
        title.getStyleClass().add("login-title");

        Text subtitle = new Text("Gestion des Étudiants Sportifs");
        subtitle.getStyleClass().add("login-subtitle");

        // Champs de formulaire
        TextField emailField = new TextField();
        emailField.setPromptText("📧 Email");
        emailField.getStyleClass().add("login-field");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("🔒 Mot de passe");
        passwordField.getStyleClass().add("login-field");

        // Bouton de connexion
        Button loginButton = new Button("SE CONNECTER");
        loginButton.getStyleClass().add("btn-login");
        loginButton.setMaxWidth(Double.MAX_VALUE);

        // Message d'erreur
        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: #EF5350; -fx-font-size: 14px;");
        errorLabel.setVisible(false);

        // Action de connexion
        loginButton.setOnAction(e -> {
            String email = emailField.getText();
            String password = passwordField.getText();

            if (email.isEmpty() || password.isEmpty()) {
                showError(errorLabel, "Veuillez remplir tous les champs");
                return;
            }

            try {
                boolean success = authService.login(email, password);
                if (success) {
                    viewManager.switchScene(dashboardView.getScene());
                } else {
                    showError(errorLabel, "Email ou mot de passe incorrect");
                }
            } catch (Exception ex) {
                showError(errorLabel, "Erreur de connexion: " + ex.getMessage());
            }
        });

        // Lien d'inscription
        Hyperlink registerLink = new Hyperlink("Créer un nouveau compte");
        registerLink.getStyleClass().add("link-text");
        registerLink.setOnAction(e -> {
            // TODO: Ouvrir la vue d'inscription
            System.out.println("Ouvrir inscription");
        });

        // Assembler la boîte
        box.getChildren().addAll(
                title,
                subtitle,
                new VBox(10), // Spacer
                emailField,
                passwordField,
                errorLabel,
                loginButton,
                registerLink
        );

        return box;
    }

    private void showError(Label errorLabel, String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }
}
