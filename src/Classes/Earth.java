package Classes;

import javafx.scene.Group;
import javafx.scene.shape.Sphere;
import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;

import javafx.scene.transform.Rotate;
import javafx.animation.AnimationTimer;

import java.io.FileInputStream;

import javafx.scene.paint.Color;

public class Earth extends Group {

    private Sphere sph;
    private Rotate ry = new Rotate(0, Rotate.Y_AXIS);

    public Earth() {
        sph = new Sphere(300);

        try {
            PhongMaterial earthMaterial = new PhongMaterial();
            earthMaterial.setDiffuseMap(new Image(new FileInputStream("src/data/Image_Terre.png")));
            sph.setMaterial(earthMaterial);
        } catch (Exception e) {
            System.out.println("Image introuvable");
            e.printStackTrace();
        }

        this.getChildren().add(sph);

        // Rotation de la terre vers la gauche, pour changer le sens il faut rajouter un - dans le setAngle
        this.getTransforms().add(ry);

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long time) {
                ry.setAngle((time / 1_000_000_000.0) * 360 / 60);
            }
        };
        animationTimer.start();
    }

    public Sphere createSphere(Aeroport a, Color color) {
        double R = 300;
        //double lat = Math.toRadians(a.getLatitude());
        //double lon = Math.toRadians(a.getLongitude());

        // Conversion de la latitude en angle géometrique avec a sin
        // Parce que le point placé est décalé à la verticale par rapport au clique réel
        double lat = Math.asin(a.getLatitude() / 90.0);
        double lon = Math.toRadians(a.getLongitude());

        Sphere s = new Sphere(2);
        s.setMaterial(new PhongMaterial(color));
        s.setTranslateX(R * Math.cos(lat) * Math.sin(lon));
        s.setTranslateY(-R * Math.sin(lat));
        s.setTranslateZ(-R * Math.cos(lat) * Math.cos(lon));
        return s;
    }

    public void displayRedSphere(Aeroport a) {
        this.getChildren().add(createSphere(a, Color.RED));
    }
}
