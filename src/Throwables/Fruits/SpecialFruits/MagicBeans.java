package Throwables.Fruits.SpecialFruits;

import javafx.scene.image.Image;

/**
 * @author Mostafa Talaat
 */
public class MagicBeans extends SpecialFruit {


    public MagicBeans(double x, double y, double maxHeight, int initialVelocity, int fallingVelocity, boolean falling, boolean sliced, boolean movedOffScreen) {
        super(x, y, maxHeight, initialVelocity, fallingVelocity, falling, sliced, movedOffScreen);
    }

    @Override
    public void slice() {
        super.slice();
    }

    @Override
    public Image[] getImages() {
        Image[] images= new Image[2];
        images[0]= new Image("Resources/Magic_Bean.png",75,75,true,true);
        images[1]= new Image("Resources/Magic_Bean.png",75,75,true,true);
        return images;
    }
}
