package com.example.miniproyecto_3_battlership.model.ships;

import javafx.scene.Group;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

/**
 * Represents a "Fragata" ship in the game.
 *
 * <p>The {@code Fragata} class is a specific type of ship that inherits from the
 * abstract {@link Ship} class. This class defines the visual representation of
 * the "Fragata" using polygons with specific textures, scales, and effects.
 * The "Fragata" has a size of 1 and is designed for gameplay as a small, agile
 * ship.</p>
 *
 * <p>The class includes the creation and styling of the ship's visual elements,
 * including texture application and shadow effects, to enhance the user's experience.</p>
 *
 * @author Nicolas Cordoba
 * @author Samuel Arenas
 * @author Juan Manuel Ampudia
 */
public class Fragata extends Ship {

    public Fragata() {

        Polygon fragataShape1 = new Polygon();
        Polygon fragataShape2 = new Polygon();
        Polygon fragataShape3 = new Polygon();
        Polygon fragataShape4 = new Polygon();
        fragataShape1.getPoints().addAll(0.0, 20.0,
                0.0, 40.0,
                3.0, 50.0,
                10.0,60.0,
                20.0,60.0,
                25.0,55.0,
                35.0,50.0,
                45.0,50.0,
                60.0,35.0,
                60.0,25.0,
                45.0,10.0,
                35.0,10.0,
                25.0,5.0,
                20.0,0.0,
                10.0,0.0,
                3.0,10.0,
                0.0,20.0
        );
        Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/textureFragata1.png")));
        ImagePattern imagePattern2 = new ImagePattern(image2);
        fragataShape1.setFill(imagePattern2);
        fragataShape2.setStroke(Color.BLACK);

        fragataShape2.getPoints().addAll(5.0,20.0,
                5.0,40.0,
                13.0,55.0,
                20.0,55.0,
                25.0,45.0,
                45.0,45.0,
                55.0,35.0,
                55.0,25.0,
                45.0,15.0,
                25.0,15.0,
                20.0,5.0,
                13.0,5.0,
                5.0,20.0

        );

        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/textureFragata3.png")));
        ImagePattern imagePattern = new ImagePattern(image);
        fragataShape2.setFill(imagePattern);

        fragataShape3.getPoints().addAll(20.0,20.0,
                40.0,20.0,
                45.0,25.0,
                45.0,35.0,
                40.0,40.0,
                20.0,40.0
        );
        Image image3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/textureFragata1.png")));
        ImagePattern imagePattern3 = new ImagePattern(image3);
        fragataShape3.setFill(imagePattern3);

        fragataShape4.getPoints().addAll(25.0,25.0,
                40.0,25.0,
                40.0,35.0,
                25.0,35.0
        );
        Image image4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/textureFragata4.png")));
        ImagePattern imagePattern4 = new ImagePattern(image4);
        fragataShape4.setFill(imagePattern4);
        fragataShape4.setScaleY(1.2);
        fragataShape4.setScaleX(1.2);

        DropShadow glow = new DropShadow();
        glow.setColor(Color.CADETBLUE);
        glow.setRadius(10);
        glow.setSpread(0.3);
        shapesGroup.setEffect(glow);
        shapesGroup.setStyle("-fx-cursor: hand");
        shapesGroup.setLayoutX(0);
        shapesGroup.setLayoutY(2);
        shapesGroup.setScaleX(1.1);
        shapesGroup.setScaleY(1.1);

        shapesGroup.getChildren().addAll(fragataShape1, fragataShape2, fragataShape3,fragataShape4 );

        this.getChildren().add(shapesGroup);
        isSelect = false;
        size = 1;
    }


}
