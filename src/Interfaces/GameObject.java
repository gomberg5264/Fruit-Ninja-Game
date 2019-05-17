package Interfaces;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public interface GameObject {
    /**
     * @return X location of game object
     */
    double getXlocation();

    /**
     * @return Y location of game object
     */
    double getYlocation();

    /**
     * @return max Y location that the object can reach on the screen
     */
    double getMaxHeight();

    /**
     * @return velocity at which game object is thrown
     */
    int getInitialVelocity();

    /**
     * @return failing velocity of game object
     */
    int getFallingVelocity();

    /**
     * @return whether the object is sliced or not
     */
    Boolean isSliced();

    /**
     * @return whether the object is dropped off the screen or not
     */
    Boolean hasMovedOffScreen();

    /**
     * it is used to slice the object
     */
    void slice();

    Boolean isFalling();

    void setFalling(boolean falling);

    void setSliced(boolean sliced);

    void setMovedOffScreen(boolean movedOffScreen);

    void setX(double x);

    void setY(double y);

    void setMaxHeight(double maxHeight);

    double getStartPos();

    void setStartPos(double startPos);

    double getMidPoint();

    void setMidPoint(double midPoint);

    double getEndPos();

    void setEndPos(double endPos);

    double getA();

    void setA(double a);

    double getQ();

    void setQ(double q);

    double getP();

    void setP(double p);

    Image getImg1();

    void setImg1(Image img1);

    Image getImg2();

    void setImg2(Image img2);
    /**
     * it is used to move the object on the screen
     *
     * @param time: time elapsed since the object is thrown
     *              it is used calculate the new position of
     *              fruit object.
     */
    void move(double time);

    /**
     * @return the type of game object
     */
    enum getObjectType {}

    /**
     * used to render the image on the canvas
     */
    void render(GraphicsContext gc);
    /**
     * updates the movment of any fruit
     */
    void updatePosition();

    // TODO: 17-May-19 mostafa

}
