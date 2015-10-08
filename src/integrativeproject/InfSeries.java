package integrativeproject;

import graphpackage.*;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;

public class InfSeries extends SimulationUI {

    int currentN, maxN;
    double xBall, yBall, currentDistance, xVelocity, yVelocity, currentTime, base, last, b;
    double intervalTime;


    public InfSeries(MainContainer UI, Pane root) {
        super(UI, root);
        addComponents();
    }

    @Override
    void initAnimation() {
        GC.drawImage(golfBG, ZERO, ZERO);
        GC.drawImage(golfer, ZERO, SCENEH / TWO - golfer.getHeight());
        GC.drawImage(golfBall, xBall - golfBall.getWidth() / TWO + golfer.getWidth(), SCENEH / TWO - yBall - golfBall.getHeight());
        GC.drawImage(golfFlag, INF_GOALGOLF + golfer.getWidth() - golfFlag.getWidth() / TWO, SCENEH / TWO + INF_FLAGOFFSET - golfFlag.getHeight());
    }

    @Override
    void initGraph() {
        Graph[] g = new Graph[]{new Graph(GRAPHSECTIONW / TWO, GRAPHSECTIONH, DEFAULTGRAPHTICK, DEFAULTGRAPHTICK, DEFAULTGRAPHSCALE, DEFAULTGRAPHSCALE, allUnits[0], allUnits[1], false),
            new Graph(GRAPHSECTIONW / TWO, GRAPHSECTIONH, DEFAULTGRAPHTICK, DEFAULTGRAPHTICK, DEFAULTGRAPHSCALE, DEFAULTGRAPHSCALE, allUnits[6], allUnits[7], true)};
        graphs = new GraphUI(g);
        graphs.drawGraphBase();
    }

    @Override
    void initControlPanel() {
        controls = new ContPanel(new String[]{allInputs[4], allInputs[5], allInputs[6]}, helpText[THREE], this);
        controls.setAlignment(Pos.CENTER);
        mainPane.add(controls, ZERO, ONE);
    }

    @Override
    void play() {
        timeline.play();
        if (!playing && controls.checkValues()) {
            playing = true;
            maxN = Math.abs((int) controls.getValues(ZERO));
            b = controls.getValues(ONE);
            base = controls.getValues(TWO);
            currentDistance = b * Math.pow(base, currentN);
            xVelocity = currentDistance / intervalTime;
            yVelocity = Math.abs(xVelocity);
            double maxY = b * (base * (ONE - Math.pow(base, maxN)) / (ONE - base)) + b;
            graphs.reScale(ZERO,INF_TIMEGRAPH , maxY * TWO);
            graphs.reScale(ONE, maxN, maxY);
        }
    }

    @Override
    void pause() {
        timeline.pause();
    }

    @Override
    void stop() {
        timeline.stop();
        initVariables();
        initAnimation();
    }

    @Override
    void updateGraphs() {
        graphs.drawLineSegment(ZERO, timePassed, xBall);
        graphs.drawLineSegment(ONE, currentN, currentDistance);
    }

    
    @Override
    void updateAnimation() {
        GC.drawImage(golfBG, ZERO, ZERO);
        GC.drawImage(golfer2, ZERO, SCENEH / TWO - golfer.getHeight());
        GC.drawImage(golfBall, xBall - golfBall.getWidth() / TWO + golfer.getWidth(), SCENEH / TWO - yBall - golfBall.getHeight());
        GC.drawImage(golfFlag, INF_GOALGOLF + golfer.getWidth() - golfFlag.getWidth() / TWO, SCENEH / TWO + INF_FLAGOFFSET - golfFlag.getHeight());
        if (currentDistance < ZERO) {
            GC.drawImage(bubble, golfer.getWidth() / TWO, SCENEH / TWO - golfer.getHeight() - bubble.getHeight() / TWO);
        } else if (Math.abs(xBall - INF_GOALGOLF) < INF_PRECISION && currentN == maxN) {
            GC.drawImage(winBubble, golfer.getWidth() / TWO, SCENEH / TWO - golfer.getHeight() - bubble.getHeight() / TWO);
            GC.drawImage(confetti, ZERO, ZERO);
        }
    }

    @Override
    void updateVariables() {
        if (currentN < maxN) {
            if ((timePassed - last) >= intervalTime) {
                currentN++;
                last = timePassed;
                currentDistance = b * Math.pow(base, currentN);
                xVelocity = currentDistance / intervalTime;
                yVelocity = Math.abs(xVelocity);
            }
            if (timePassed - last >= (intervalTime / TWO_D)) {
                yVelocity = (-ONE) * Math.abs(xVelocity);
            }
            xBall += xVelocity * TIMESTEP_S;
            yBall += yVelocity * TIMESTEP_S;
        }
    }

    @Override
    void initVariables() {
        currentN = ZERO;
        maxN = ZERO;
        base = ZERO;
        xBall = ZERO;
        yBall = ZERO;
        yVelocity = xVelocity = INF_VELOCITY;
        currentDistance = ZERO;
        currentTime = ZERO;
        last = ZERO;
        intervalTime = ONE;
        timePassed = ZERO;
        playing = false;
    }
}