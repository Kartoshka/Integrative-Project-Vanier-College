package integrativeproject;

import graphpackage.*;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.transform.Rotate;

public class ProjMotion extends SimulationUI{

    private double velocity, angle, velX, velY, xPos, yPos, range,maxHeight;

    public ProjMotion(MainContainer UI, Pane root) {
        super(UI, root);
        addComponents();
    }

    @Override
    void initAnimation() {
        GC.drawImage(background, ZERO, SCENEH / TWO - PM_BACKGROUNDOFFSET);
        GC.drawImage(grass,ZERO,SCENEH/TWO -PM_GRASSOFFSET);
        GC.setLineWidth(PM_FLAGPOLEWIDTH);
        GC.setLineCap(StrokeLineCap.ROUND);
        GC.strokeLine(PM_FLAGPOLE_BASE, ANIMATIONH/TWO, PM_FLAGPOLE_BASE, SCENEH / TWO);
        GC.drawImage(cannon, PM_CANNON_X_OFFSET, ANIMATIONH - PM_CANNON_y_OFFSET);
        GC.drawImage(flag, PM_FLAG_X_OFFSET, ANIMATIONH/TWO);
        GC.drawImage(cannonBase, PM_CANNONBASE_X_OFFSET, ANIMATIONH +PM_CANNONBASE_Y_OFFSET);
    }

    @Override
    void initGraph() {
        Graph[] g = new Graph[]{new Graph(GRAPHSECTIONW / TWO, GRAPHSECTIONH, DEFAULTGRAPHTICK, DEFAULTGRAPHTICK, DEFAULTGRAPHSCALE, DEFAULTGRAPHSCALE, allUnits[0], allUnits[11], false),
            new Graph(GRAPHSECTIONW / TWO, GRAPHSECTIONH, DEFAULTGRAPHTICK, DEFAULTGRAPHTICK, DEFAULTGRAPHSCALE, DEFAULTGRAPHSCALE, allUnits[0], allUnits[12], true)};
        graphs = new GraphUI(g);
        graphs.drawGraphBase();
    }

    @Override
    void initControlPanel() {
        controls = new ContPanel(new String[]{allInputs[1], allInputs[15]}, helpText[FIVE], this);
        controls.setAlignment(Pos.CENTER);
        mainPane.add(controls, ZERO, ONE);
    }

    @Override
    void play() {
        timeline.play();
        if (!playing && controls.checkValues()) {
            velocity = controls.getValues(ZERO);
            angle = controls.getValues(ONE);
            velX = velocity * Math.cos(angle * DEG_TO_RAD);
            velY = velocity * Math.sin(angle * DEG_TO_RAD);
            //Math'd velocity squared over acceleration gives distance
            maxHeight = (velocity * Math.sin(PM_MAXANGLE)) * (velocity * Math.sin(PM_MAXANGLE)) / GRAVITY;
            //Range formula
            range = velocity * velocity * Math.sin(TWO * angle * DEG_TO_RAD) / GRAVITY;
            graphs.reScale(ZERO, (range / velX) * PM_GRAPH_RATIO, maxHeight * PM_GRAPH_RATIO); 
            graphs.reScale(ONE, (range / velX), velocity * Math.sin(PM_OPTIMALANGLE) * PM_GRAPH_RATIO*TWO_D);
            playing = true;
        }
    }

    @Override
    void pause() {
        timeline.pause();
    }

    @Override
    void stop() {
        playing = false;
        timeline.stop();
        timePassed = ZERO;
        initVariables();
    }

    @Override
    void updateGraphs() {
        graphs.drawLineSegment(ZERO, timePassed, yPos);
        graphs.drawLineSegment(ONE, timePassed, velY);
    }

    @Override
    void updateAnimation() {
        GC.drawImage(background, ZERO, SCENEH / TWO - PM_BACKGROUNDOFFSET);
        GC.drawImage(grass,ZERO,SCENEH/TWO -PM_GRASSOFFSET);
        GC.strokeLine(PM_FLAGPOLE_BASE, ANIMATIONH/TWO, PM_FLAGPOLE_BASE, SCENEH / TWO);
        GC.drawImage(flag, PM_FLAG_X_OFFSET, ANIMATIONH/TWO);
        
        double startY = -PM_CANNONWIDTH * Math.sin(angle * DEG_TO_RAD) + ANIMATIONH - PM_CANNON_Y_ROTATIONOFFSET;
        double startX = PM_CANNONWIDTH * Math.cos(angle * DEG_TO_RAD) + PM_CANNON_X_ROTATIONOFFSET;
        
        GC.drawImage(projectile, xPos * PM_SCALE +PM_CANNONBASE_X_OFFSET + startX, -yPos * PM_SCALE +PM_CANNONBASE_X_OFFSET + startY);
        GC.save();
        Rotate r = new Rotate(-angle, PM_CANNON_X_ROTATIONOFFSET, ANIMATIONH - PM_CANNON_Y_ROTATIONOFFSET);

        GC.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
        GC.drawImage(cannon, PM_CANNON_X_OFFSET, ANIMATIONH - PM_CANNON_y_OFFSET);

        GC.restore();
        GC.drawImage(cannonBase, PM_CANNONBASE_X_OFFSET, ANIMATIONH +PM_CANNONBASE_Y_OFFSET);
        GC.drawImage(reticule, PM_SCALE*range +startX-reticule.getHeight()/TWO , startY-reticule.getHeight()/TWO);
    }

    @Override
    void updateVariables() {
        velY -= GRAVITY * TIMESTEP_S;
        xPos += velX * TIMESTEP_S;
        yPos += velY * TIMESTEP_S;
    }

    @Override
    void initVariables() {
        velocity = angle = velX = velY = xPos = yPos = ZERO;
        playing = false;
    }
}