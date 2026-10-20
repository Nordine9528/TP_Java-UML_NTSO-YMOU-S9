package Classes;
import javafx.application.Application;
import javafx.scene.Scene;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.PerspectiveCamera;

import javafx.scene.input.MouseEvent;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseButton;
import javafx.scene.input.PickResult;

public class Interface extends Application {
    private double mousePosX;
    private double mousePosY;

    private World w = new World ("src/data/airport-codes_no_comma.csv");

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Hello world");

        Earth root = new Earth();
        Pane pane = new Pane(root);
        pane.setPickOnBounds(false);
        Scene theScene = new Scene(pane, 600, 400, true);

        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-1000);
        camera.setNearClip(0.1);
        camera.setFarClip(2000.0);
        camera.setFieldOfView(35);
        theScene.setCamera(camera);

        theScene.addEventHandler(MouseEvent.ANY, event -> {
            if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                mousePosX = event.getSceneX();
                mousePosY = event.getSceneY();
                System.out.println("Clicked on : (" + event.getSceneX() + ", " + event.getSceneY() + ")");
            }
            if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                double deltaY = event.getSceneY() - mousePosY;
                camera.setTranslateZ(camera.getTranslateZ() - deltaY);
                mousePosX = event.getSceneX();
                mousePosY = event.getSceneY();
            }

            if (event.getButton() == MouseButton.SECONDARY
                    && event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                PickResult pickResult = event.getPickResult();
                System.out.println("Noeud touché : " + pickResult.getIntersectedNode());
                Point2D texCoord = pickResult.getIntersectedTexCoord();
                System.out.println("texCoord : " + texCoord);

                if (texCoord != null) {
                    double lat = 180 * (0.5 - texCoord.getY());
                    double lon = 360 * (texCoord.getX() - 0.5);
                    System.out.println("Lat : " + lat + ", Lon : " + lon);

                    Aeroport nearest = w.findNearestAirport(lon, lat);
                    System.out.println(nearest);
                    root.displayRedSphere(nearest);
                }
            }
        });

        primaryStage.setScene(theScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        System.out.println("bonjour");
        launch(args);
    }
}
