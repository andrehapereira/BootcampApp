package navigation;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import controller.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public final class Navigation {
    private final int MIN_WIDTH = 1024;
    private final int MIN_HEIGHT = 768;
    private LinkedList<Scene> scenes = new LinkedList<Scene>();
    private Map<String, Controller> controllers = new HashMap<>();
    private Stage stage;
    private static Navigation instance = null;
    private Scene scene;

    private Navigation() {
    }

    public static synchronized Navigation getInstance() {
        if(instance == null) {
            instance = new Navigation();
        }
        return instance;
    }

    public void loadScreen(String view) {
        try {

            // Instantiate the view and the controller
            FXMLLoader fxmlLoader;
            fxmlLoader = new FXMLLoader(getClass().getResource("/view/" + view + ".fxml"));
            Parent root = fxmlLoader.load();

            //Store the controller
            controllers.put(view, fxmlLoader.<Controller>getController());

            // Create a new scene and add it to the stack
            Scene scene = new Scene(root, MIN_WIDTH, MIN_HEIGHT);
            scenes.push(scene);
            // Put the scene on the stage
            setScene(scene);
            char ch = view.charAt(0);
            String newView = "<Academia de Codigo_>";
            stage.setTitle(newView);

        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
            System.out.println("Failure to load view " + view + " : " + e.getMessage());
        }
    }
    public void setScene(Scene scene) {
        stage.setScene(scene);
        //stage.setResizable(false);
        stage.show();
    }

    public void back() {
        if (scenes.isEmpty()) {
            return;
        }
        scenes.pop();
        setScene(scenes.peek());
    }

    public Controller getControllers(String key) {
        return controllers.get(key);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}