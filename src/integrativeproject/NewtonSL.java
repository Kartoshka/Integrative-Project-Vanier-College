package integrativeproject;

import graphpackage.*;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;

public class NewtonSL extends SimulationUI{

    //Values
    private double mass;
    private double force;
    private double acceleration;
    private double position;
    private double velocity;
    private MediaPlayer mp = new MediaPlayer(hell);

    //Display
    private ContPanel controls;

    //Animation image
    public NewtonSL(MainContainer UI, Pane root) {
        super(UI, root);
        addComponents();
    }

    @Override
    void initVariables() {
        timePassed = ZERO;
        mass = DEFAULTMASS;
        force = DEFAULTFORCE;
        acceleration = ZERO;
        position = ZERO;
        velocity = ZERO;
    }

    @Override
    void initGraph() {
        Graph[] g = new Graph[]{new Graph((int) (GRAPHSECTIONW / THREE), GRAPHSECTIONH, DEFAULTGRAPHTICK, DEFAULTGRAPHTICK, DEFAULTGRAPHSCALE, DEFAULTGRAPHSCALE, allUnits[0], allUnits[1], false),
            new Graph((int) (GRAPHSECTIONW / THREE), GRAPHSECTIONH, DEFAULTGRAPHTICK, DEFAULTGRAPHTICK, DEFAULTGRAPHSCALE, DEFAULTGRAPHSCALE, allUnits[0], allUnits[2], false),
            new Graph((int) (GRAPHSECTIONW / THREE), GRAPHSECTIONH, DEFAULTGRAPHTICK, DEFAULTGRAPHTICK, DEFAULTGRAPHSCALE, DEFAULTGRAPHSCALE, allUnits[0], allUnits[3], false)};
        graphs = new GraphUI(g);
        graphs.drawGraphBase();
    }

    @Override
    void initControlPanel() {
        controls = new ContPanel(new String[]{allInputs[13], allInputs[14]}, helpText[ZERO], this);
        controls.setAlignment(Pos.CENTER);
        mainPane.add(controls, ZERO, ONE);
    }

    @Override
    public void play() {
        timeline.play();
        mp.play();
        if(!playing && controls.checkValues()){
            playing = true;
            mass = Math.abs(controls.getValues(ZERO));
            force = controls.getValues(ONE);
            acceleration = force / mass;
            graphs.reScale(ZERO, NSL_MAXTIMEGRAPH, acceleration * NSL_MAXTIMEGRAPH * NSL_MAXTIMEGRAPH * HALF);
            graphs.reScale(ONE, NSL_MAXTIMEGRAPH, acceleration * NSL_MAXTIMEGRAPH * TWO);
            graphs.reScale(TWO, NSL_MAXTIMEGRAPH, acceleration * FOUR);
        }
    }

    @Override
    public void pause() {
        timeline.pause();
        mp.pause();
    }

    @Override
    public void stop() {
        timeline.stop();
        playing = false;
        initVariables();
        initAnimation();
        mp.stop();
    }

    @Override
    void updateGraphs() {
        graphs.drawLineSegment(ZERO, timePassed, position);
        graphs.drawLineSegment(ONE, timePassed, velocity);
        graphs.drawLineSegment(TWO, timePassed, acceleration);
    }

    @Override
    void updateAnimation() {
        GC.drawImage(supermarket, ZERO, ZERO);
        GC.drawImage(godHand, position * NSL_ANIMATIONSCALE - godHand.getWidth(), SCENEH / TWO - NSL_ANIMATION_CART_HEIGHT_OFFSET - godHand.getHeight() + NSL_GODHAND_RATIO * godHand.getHeight());
        GC.drawImage(cart, position * NSL_ANIMATIONSCALE, SCENEH / TWO - NSL_ANIMATION_CART_HEIGHT_OFFSET);
    }

    @Override
    void updateVariables() {
        acceleration = force / mass;
        velocity = acceleration * timePassed;
        position = (velocity / TWO_D) * timePassed;
    }

    @Override
    void initAnimation() {
        GC.drawImage(supermarket, ZERO, ZERO);
        GC.drawImage(cart, ZERO, SCENEH / TWO - NSL_ANIMATION_CART_HEIGHT_OFFSET);
    }
}