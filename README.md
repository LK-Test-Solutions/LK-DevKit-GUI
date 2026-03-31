# OpenTDK_GUI

This repository contains classes to create JavaFX GUI applications and charts.

It allows a quick introduction to the implementation of desktop applications as well as the creation of various diagrams, such as bar charts or line charts.

## Chart Samples

See the test cases of the project.

## Base Application Sample

````java
import java.util.Locale;
import java.util.ResourceBundle;

import application.org.lk.devkit.gui.BaseApplication;
import controls.org.lk.devkit.gui.MessageDialog.MessageType;

import javafx.application.Platform;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import lombok.Getter;
import lombok.Setter;

public final class CustomApp extends BaseApplication {
    /**
     * The one and only instance of this class to get access from other classes without creating another
     * instance there.
     */
    @Getter
    private static CustomApp instance;
    /**
     * The controller class for the main layout.
     */
    @Getter
    private RootController rootController;

    /**
     * Initializes the {@link #instance}.
     */
    public CustomApp() {
        if (instance == null) {
            instance = this;
        }
    }

    /**
     * Entry point of the <code>CustomApp</code> application where the
     * <code>javafx.Application.launch()</code> method gets called which starts the JavaFX application
     * thread and triggers the {@link #showRootLayout()} method.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Initialization of the {@link #rootController} that shows the primary stage.
     * Has to be a valid FXML file (build with SceneBuilder)
     */
    @Override
    protected void showRootLayout() {
        super.getPrimaryStage().getIcons().add(new Image("file:conf/app.png"));
        super.setResourceBundle(ResourceBundle.getBundle("test.Bundle", new Locale("en")));
        super.setStyleSheet("app.css");
        super.getPrimaryStage().setResizable(true);
        super.getPrimaryStage().setMinHeight(200);
        super.getPrimaryStage().setMinWidth(200);
        super.getPrimaryStage().setOnCloseRequest(event -> {
            Platform.exit();
        });
        try {
            rootController = (RootController) super.showStage("view/Root.fxml", super.getBundle().getString("app.title"), super.getPrimaryStage());
            rootController.initRootLayout();
        } catch (IOException e) {
            e.printStackTrace();
            Platform.exit();
        }
    }

    public ButtonType showMessageDialog(MessageType type, String info, String message) {
        return getMessageDialog().showMessageBox(type, getBundle().getString(info), getBundle().getString(message));
    }
}
````

## Run JavaFX in Intellij

![image](https://github.com/user-attachments/assets/357de6b1-8b38-4f5c-9719-c33afeedf9b7)
