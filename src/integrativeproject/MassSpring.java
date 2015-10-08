package integrativeproject;

import graphpackage.*;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class MassSpring extends SimulationUI{

    private double omega, phi, A, xSpring, velocity;
    private double startPoint = ANIMATIONW/TWO;

    Image spring = new Image("/images/spring.png", startPoint, startPoint, true, true);
    
    public MassSpring(MainContainer UI, Pane root) {
        super(UI, root);
        addComponents();
    }

    @Override
    void initVariables() {
        timePassed = ZERO;
        omega = ZERO;
        phi = ZERO;
        A = ZERO;
    }

    @Override
    void initGraph() {
        Graph[] g = new Graph[]{new Graph(GRAPHSECTIONW / TWO, GRAPHSECTIONH, DEFAULTGRAPHTICK, DEFAULTGRAPHTICK, DEFAULTGRAPHSCALE, DEFAULTGRAPHSCALE, allUnits[0], allUnits[1], true),
            new Graph(GRAPHSECTIONW / TWO, GRAPHSECTIONH, DEFAULTGRAPHTICK, DEFAULTGRAPHTICK, DEFAULTGRAPHSCALE, DEFAULTGRAPHSCALE, allUnits[0], allUnits[2], true)};
        graphs = new GraphUI(g);
        graphs.drawGraphBase();
    }

    @Override
    void initControlPanel() {
        controls = new ContPanel(new String[]{allInputs[7],allInputs[8],allInputs[9]},helpText[ONE],this);
        controls.setAlignment(Pos.CENTER);
        mainPane.add(controls, ZERO,ONE);
    }

    @Override
    void play() {
        timeline.play();
        if(!playing && controls.checkValues()){
            playing = true;
            A = controls.getValues(ZERO);
            omega = controls.getValues(ONE);
            phi = controls.getValues(TWO);
            graphs.reScale(ZERO, MASS_GRAPHRANGEX*Math.PI/omega, A*MASS_GRAPHOFFSETY);
            graphs.reScale(ONE, MASS_GRAPHRANGEX*Math.PI/omega, A*omega*MASS_GRAPHOFFSETY);
        }
    }

    @Override
    void pause() {
        timeline.pause();
    }

    @Override
    void stop() {
        timeline.stop();
        playing = false;
        initVariables();
        initAnimation();
    }

    @Override
    void updateGraphs() {
        graphs.drawLineSegment(ZERO, timePassed, xSpring);
        graphs.drawLineSegment(ONE, timePassed, velocity);
    }

    @Override
    void updateAnimation() {
        GC.drawImage(background, ZERO, ANIMATIONH - background.getHeight());
        
        if(xSpring > -startPoint + MASS_MASSOFFSETX){
            spring = new Image("/images/spring.png",  startPoint + xSpring - MASS_SPRINGOFFSETX, MASS_SPRINGY, false, true);
        }
        else
        {
            spring = null;
        }
        GC.drawImage(spring, -FIVE, SCENEH/FOUR - spring.getHeight()/TWO);
        GC.drawImage(mass, startPoint + xSpring - MASS_MASSOFFSETX, ANIMATIONH/TWO - MASS_MASSOFFSETY);
    }

    @Override
    void updateVariables() {
        xSpring = A * Math.sin(omega * timePassed + phi);
        velocity = A * omega * Math.cos(omega * timePassed + phi);
    }

    @Override
    void initAnimation(){
        GC.drawImage(background, ZERO, ANIMATIONH - background.getHeight());
    }
}