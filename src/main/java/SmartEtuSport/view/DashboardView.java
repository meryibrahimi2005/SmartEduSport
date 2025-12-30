package SmartEtuSport.view;

import SmartEtuSport.service.AuthService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DashboardView {

    private final AuthService authService;
    private final NiveauxView niveauxView;
    private final StatistiquesView statistiquesView;
    private final ProfesseurView professeurView;

    private BorderPane mainContent;
    private Label breadcrumbLabel;

    public Scene getScene() {
        BorderPane root = new BorderPane();

        // Sidebar gauche
        VBox sidebar = createSidebar();
        root.setLeft(sidebar);

        // Contenu principal
        BorderPane contentArea = new BorderPane();

        // Header
        HBox header = createHeader();
        contentArea.setTop(header);

        // Zone de contenu centrale
        mainContent = new BorderPane();
        mainContent.setPadding(new Insets(30));
        mainContent.setStyle("-fx-background-color: #ECEFF1;");

        // Afficher le dashboard par défaut
        showDashboard();

        contentArea.setCenter(mainContent);
        root.setCenter(contentArea);

        Scene scene = new Scene(root, 1400, 900);
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());

        return scene;
    }

    private VBox createSidebar() {
        VBox sidebar = new VBox();
        sidebar.getStyleClass().add("sidebar");
        sidebar.setPrefWidth(280);
        sidebar.setMinWidth(280);
        sidebar.setMaxWidth(280);

        // Header sidebar
        VBox header = new VBox(5);
        header.getStyleClass().add("sidebar-header");
        header.setAlignment(Pos.CENTER);

        Text logo = new Text("🏃 SmartEduSport");
        logo.getStyleClass().add("sidebar-logo");

        Text subtitle = new Text("Gestion Sportive");
        subtitle.getStyleClass().add("sidebar-subtitle");

        header.getChildren().addAll(logo, subtitle);

        // Menu de navigation
        VBox menu = new VBox();
        menu.setSpacing(5);
        menu.setPadding(new Insets(20, 0, 0, 0));

        Button dashboardBtn = createNavButton("📊", "Tableau de Bord", true);
        Button niveauxBtn = createNavButton("🎯", "Niveaux & Classes", false);
        Button etudiantsBtn = createNavButton("👥", "Étudiants", false);
        Button activitesBtn = createNavButton("⚽", "Activités", false);
        Button notesBtn = createNavButton("📝", "Notes & Évaluations", false);
        Button presencesBtn = createNavButton("✅", "Présences", false);
        Button planningBtn = createNavButton("📅", "Planning", false);
        Button statistiquesBtn = createNavButton("📈", "Statistiques", false);
        Button professeurBtn = createNavButton("👤", "Mon Profil", false);

        // Actions des boutons
        dashboardBtn.setOnAction(e -> {
            setActiveButton(dashboardBtn, menu);
            showDashboard();
        });

        niveauxBtn.setOnAction(e -> {
            setActiveButton(niveauxBtn, menu);
            mainContent.setCenter(niveauxView.getView());
            updateBreadcrumb("Niveaux & Classes");
        });

        statistiquesBtn.setOnAction(e -> {
            setActiveButton(statistiquesBtn, menu);
            mainContent.setCenter(statistiquesView.getView());
            updateBreadcrumb("Statistiques");
        });

        professeurBtn.setOnAction(e -> {
            setActiveButton(professeurBtn, menu);
            mainContent.setCenter(professeurView.getView());
            updateBreadcrumb("Mon Profil");
        });

        menu.getChildren().addAll(
                dashboardBtn, niveauxBtn, etudiantsBtn,
                activitesBtn, notesBtn, presencesBtn,
                planningBtn, statistiquesBtn,
                new Region(), // Spacer
                professeurBtn
        );

        VBox.setVgrow(menu.getChildren().get(menu.getChildren().size() - 2), Priority.ALWAYS);

        sidebar.getChildren().addAll(header, menu);

        return sidebar;
    }

    private Button createNavButton(String icon, String text, boolean active) {
        Button button = new Button(icon + "  " + text);
        button.getStyleClass().add("nav-button");
        if (active) {
            button.getStyleClass().add("active");
        }
        button.setMaxWidth(Double.MAX_VALUE);
        return button;
    }

    private void setActiveButton(Button activeBtn, VBox menu) {
        menu.getChildren().forEach(node -> {
            if (node instanceof Button) {
                ((Button) node).getStyleClass().remove("active");
            }
        });
        activeBtn.getStyleClass().add("active");
    }

    private HBox createHeader() {
        HBox header = new HBox(20);
        header.getStyleClass().add("header");
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(0, 30, 0, 30));

        // Titre et fil d'Ariane
        VBox titleBox = new VBox(5);
        Text title = new Text("Tableau de Bord");
        title.getStyleClass().add("header-title");

        breadcrumbLabel = new Label("Accueil");
        breadcrumbLabel.getStyleClass().add("header-breadcrumb");

        titleBox.getChildren().addAll(title, breadcrumbLabel);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Info utilisateur
        HBox userInfo = new HBox(10);
        userInfo.getStyleClass().add("user-info");
        userInfo.setAlignment(Pos.CENTER);

        var currentUser = authService.getCurrentUser();
        String initials = currentUser != null ?
                (currentUser.getNom().substring(0, 1) + currentUser.getPrenom().substring(0, 1)).toUpperCase() : "??";

        Label avatar = new Label(initials);
        avatar.getStyleClass().add("user-avatar");

        VBox userDetails = new VBox(2);
        Label userName = new Label(currentUser != null ?
                currentUser.getNom() + " " + currentUser.getPrenom() : "Utilisateur");
        userName.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        Label userRole = new Label(currentUser != null ? currentUser.getRole().toString() : "");
        userRole.setStyle("-fx-font-size: 12px; -fx-text-fill: #78909C;");

        userDetails.getChildren().addAll(userName, userRole);

        userInfo.getChildren().addAll(avatar, userDetails);

        header.getChildren().addAll(titleBox, spacer, userInfo);

        return header;
    }

    private void showDashboard() {
        updateBreadcrumb("Tableau de Bord");

        VBox dashboard = new VBox(30);
        dashboard.setPadding(new Insets(0));

        // Cartes statistiques
        HBox statsCards = createStatsCards();

        // Graphiques et infos
        HBox chartsRow = new HBox(20);
        VBox recentActivity = createRecentActivityCard();
        VBox upcomingSessions = createUpcomingSessionsCard();

        HBox.setHgrow(recentActivity, Priority.ALWAYS);
        HBox.setHgrow(upcomingSessions, Priority.ALWAYS);

        chartsRow.getChildren().addAll(recentActivity, upcomingSessions);

        dashboard.getChildren().addAll(statsCards, chartsRow);

        mainContent.setCenter(dashboard);
    }

    private HBox createStatsCards() {
        HBox cards = new HBox(20);

        VBox card1 = createStatCard("150", "Étudiants Actifs", "primary");
        VBox card2 = createStatCard("12", "Classes", "success");
        VBox card3 = createStatCard("8", "Activités", "warning");
        VBox card4 = createStatCard("95%", "Taux de Présence", "info");

        HBox.setHgrow(card1, Priority.ALWAYS);
        HBox.setHgrow(card2, Priority.ALWAYS);
        HBox.setHgrow(card3, Priority.ALWAYS);
        HBox.setHgrow(card4, Priority.ALWAYS);

        cards.getChildren().addAll(card1, card2, card3, card4);

        return cards;
    }

    private VBox createStatCard(String value, String label, String styleClass) {
        VBox card = new VBox(10);
        card.getStyleClass().addAll("stat-card", styleClass);
        card.setAlignment(Pos.CENTER);
        card.setPrefHeight(150);

        Text valueText = new Text(value);
        valueText.getStyleClass().add("stat-value");

        Text labelText = new Text(label);
        labelText.getStyleClass().add("stat-label");

        card.getChildren().addAll(valueText, labelText);

        return card;
    }

    private VBox createRecentActivityCard() {
        VBox card = new VBox(15);
        card.getStyleClass().add("card");
        VBox.setVgrow(card, Priority.ALWAYS);

        Label title = new Label("📋 Activités Récentes");
        title.getStyleClass().add("card-title");

        ListView<String> activityList = new ListView<>();
        activityList.getItems().addAll(
                "✅ Session Football - 3A terminée",
                "📝 Notes Basketball ajoutées",
                "👤 Nouvel étudiant inscrit",
                "📅 Planning semaine prochaine créé"
        );
        VBox.setVgrow(activityList, Priority.ALWAYS);

        card.getChildren().addAll(title, activityList);

        return card;
    }

    private VBox createUpcomingSessionsCard() {
        VBox card = new VBox(15);
        card.getStyleClass().add("card");
        VBox.setVgrow(card, Priority.ALWAYS);

        Label title = new Label("📅 Prochaines Sessions");
        title.getStyleClass().add("card-title");

        ListView<String> sessionsList = new ListView<>();
        sessionsList.getItems().addAll(
                "⚽ Football - 2B - 14:00",
                "🏀 Basketball - 1A - 15:30",
                "🏃 Athlétisme - 3C - 16:00",
                "🤸 Gymnastique - 2A - 10:00"
        );
        VBox.setVgrow(sessionsList, Priority.ALWAYS);

        card.getChildren().addAll(title, sessionsList);

        return card;
    }

    private void updateBreadcrumb(String path) {
        breadcrumbLabel.setText("Accueil > " + path);
    }
}
