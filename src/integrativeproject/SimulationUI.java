package integrativeproject;

import graphpackage.GraphUI;
import interfaces.Constants;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public abstract class SimulationUI extends Scene implements Constants {

    String name;
    String domain;
    private MainContainer mainUI;
    ContPanel controls;
    GraphUI graphs;
    boolean playing;
    Canvas animation;
    GraphicsContext GC;
    Timeline timeline;
    double timePassed;
    GridPane mainPane;

    SimulationUI(MainContainer UI, Pane root) {
        super(root, SCENEW, SCENEH);
        mainPane = (GridPane) root;
        animation = new Canvas(ANIMATIONW, ANIMATIONH);
        GC = animation.getGraphicsContext2D();
        mainUI = UI;

        timePassed = ZERO;

        timeline = new Timeline(new KeyFrame(Duration.seconds(TIMESTEP_S), new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                updateVariables();
                updateGraphs();
                updateAnimation();
                timePassed += TIMESTEP_S;
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);

        initGraph();
        initControlPanel();
        initVariables();
        initAnimation();
    }

    void exitToMain() {
        mainUI.resetToMain();
    }

    void addComponents() {
        mainPane.getColumnConstraints().addAll(COLCONSTRAINT, COLCONSTRAINT);
        mainPane.getRowConstraints().addAll(ROWCONSTRAINT, ROWCONSTRAINT);
        mainPane.add(graphs, ZERO, ZERO);
        mainPane.setColumnSpan(graphs, TWO);
        mainPane.add(animation, ONE, ONE);
    }

    abstract void initAnimation();

    abstract void initGraph();

    abstract void initControlPanel();

    abstract void initVariables();

    abstract void play();

    abstract void pause();

    abstract void stop();

    abstract void updateGraphs();

    abstract void updateAnimation();

    abstract void updateVariables();
}