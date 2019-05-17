package Gui.Controller;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class Classic implements Initializable {
    private Controller controller = Controller.getInstance();
    @FXML private Canvas canvas ;
    @FXML private ImageView pause;
    @FXML private ImageView resume;
    @FXML private  ImageView reset;
    @FXML private ImageView background;
    @FXML private javafx.scene.text.Text gameOver;
    @FXML private javafx.scene.text.Text score;
    @FXML private javafx.scene.text.Text bestScore;
    @FXML private javafx.scene.text.Text alltimeBestScore;
    public boolean stopall =true;
    private GraphicsContext gc;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bestScore.setText("best score:"+controller.personalHighscore);
        alltimeBestScore.setText("alltime best score:"+controller.highestScore);
        resume.setVisible(false);
        reset.setVisible(false);
        background.setImage(new Image("Resources/ConceptGreatWave1 (2).jpg"));
        gc=canvas.getGraphicsContext2D();

        Timeline timeline = new Timeline(new KeyFrame(new Duration(2000), actionEvent->{
            if(controller.throwables.size()<6) {
                controller.throwables.add(controller.getRandomThrowable());
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        Timeline clock= new Timeline(new KeyFrame(new Duration(1000), actionEvent1->{
            controller.updateTime_Difficulty(timeline);
        }));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

        canvas.setOnMouseMoved(event -> {
            if(stopall)
            controller.slice(event.getSceneX(),event.getSceneY());
        });

        AnimationTimer timer=new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(stopall)
                controller.drawAllThings(gc);
                controller.removeUnwantedThrowable();
                if(controller.gameOver()){
                    stopall =false;
                    timeline.stop();
                    clock.stop();
                    canvas.setEffect(new GaussianBlur(50));
                    background.setEffect(new GaussianBlur(50));
                    gameOver.setText("game over");
                    score.setText("score: " + controller.score);
                    pause.setVisible(false);
                }

            }
        };
        timer.start();

        pause.setOnMouseClicked(event -> {
            stopall=false;
            pause.setVisible(false);
            resume.setVisible(true);
            reset.setVisible(true);
            timeline.stop();
            timer.stop();
            clock.stop();
            canvas.setEffect(new GaussianBlur(50));
            background.setEffect(new GaussianBlur(50));
        });

        resume.setOnMouseClicked(event -> {
            if(resume.isVisible()){
                stopall=true;
                resume.setVisible(false);
                reset.setVisible(false);
                canvas.setEffect(new GaussianBlur(-50));
                background.setEffect(new GaussianBlur(-50));
                AtomicInteger seconds= new AtomicInteger();
                Timeline resume = new Timeline(new KeyFrame(new Duration(500), acttionEvent->{
                    controller.drawAllThings(gc);
                    seconds.getAndIncrement();
                    controller.getCountDown(gc,seconds);
                }));
                resume.setCycleCount(4);
                resume.play();
                resume.setOnFinished(event1 -> {
                    pause.setVisible(true);
                    timeline.play();
                    timer.start();
                    clock.play();
                });

            }
        });

        reset.setOnMouseClicked(event -> {
            if(reset.isVisible()){
                stopall=true;
                resume.setVisible(false);
                reset.setVisible(false);
                pause.setVisible(true);
                canvas.setEffect(new GaussianBlur(-50));
                background.setEffect(new GaussianBlur(-50));

                controller.ResetGame();
                AtomicInteger seconds= new AtomicInteger();
                Timeline resume = new Timeline(new KeyFrame(new Duration(500), acttionEvent->{
                    controller.drawAllThings(gc);
                    seconds.getAndIncrement();
                    controller.getCountDown(gc,seconds);
                }));
                resume.setCycleCount(4);
                resume.play();
                resume.setOnFinished(event1 -> {
                    pause.setVisible(true);
                    timeline.play();
                    timer.start();
                    clock.play();
                });

            }

        });


    }



}


