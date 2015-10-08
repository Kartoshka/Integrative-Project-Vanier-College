package integrativeproject;

import graphpackage.*;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class DopEffect extends SimulationUI {

    private double freq0, freq, dist, lastDist, velocity, relativeVel, vertDist, xPos, obsPos;
    private int waveCounter, waveInterval;
    private ArrayList<Wave> waveList;

    public DopEffect(MainContainer UI, Pane root) {
        super(UI, root);
        addComponents();
    }

    @Override
    void initAnimation() {
        waveList = new ArrayList();
        GC.drawImage(background, ZERO, ANIMATIONH - background.getHeight());
    }

    @Override
    void initGraph() {
        Graph[] g = new Graph[]{new Graph(GRAPHSECTIONW / TWO, GRAPHSECTIONH, DEFAULTGRAPHTICK,
            DEFAULTGRAPHTICK, DEFAULTGRAPHSCALE, DEFAULTGRAPHSCALE, allUnits[0], allUnits[4], false),
            new Graph(GRAPHSECTIONW / TWO, GRAPHSECTIONH, DEFAULTGRAPHTICK, DEFAULTGRAPHTICK,
            DEFAULTGRAPHSCALE, DEFAULTGRAPHSCALE, allUnits[0], allUnits[5], false)};
        graphs = new GraphUI(g);
        graphs.drawGraphBase();
    }

    @Override
    void initControlPanel() {
        controls = new ContPanel(new String[]{allInputs[0], allInputs[1], allInputs[2], allInputs[3]}, helpText[FOUR], this);
        controls.setAlignment(Pos.CENTER);
        mainPane.add(controls, ZERO, ONE);
    }

    @Override
    void play() {
        timeline.play();
        if (!playing && controls.checkValues()) {
            velocity = controls.getValues(ONE);
            vertDist = controls.getValues(TWO);
            freq0 = controls.getValues(ZERO);
            obsPos = controls.getValues(THREE);
            lastDist = dist = Math.sqrt(Math.pow(obsPos, TWO) + Math.pow(vertDist, TWO));

            double newGraphX = (obsPos / velocity) * TWO;

            graphs.reScale(ZERO, newGraphX, freq0 * THREE);
            graphs.reScale(ONE, newGraphX, vertDist * FIVE);
            graphs.drawGraphBase();
            playing = true;
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
        graphs.drawLineSegment(ZERO, timePassed, freq);
        graphs.drawLineSegment(ONE, timePassed, dist);
    }

    @Override
    void updateAnimation() {
        waveCounter++;

        GC.drawImage(background, ZERO, ANIMATIONH - background.getHeight());

        GC.drawImage(person, obsPos - person.getWidth() / TWO, DOPPLER_BASELINE - person.getHeight() / TWO);

        waveInterval = (int) (DOPPLER_FREQDIVISOR / freq0) + ONE;

        if (waveCounter % waveInterval == ZERO) {
            waveList.add(new Wave(xPos + (velocity * TIMESTEP_S * waveInterval), vertDist));
        }

        int count = ZERO;
        while (count < waveList.size() - ONE) {
            if (waveList.get(count).expand() == false) {
                waveList.remove(count);
            } else {
                count++;
            }
        }

        for (int i = ZERO; i < waveList.size() - ONE; i++) {
            waveList.get(i).draw();
        }

        GC.drawImage(personSide, xPos - horn.getWidth() - DOPPLER_PERSONX, vertDist + DOPPLER_BASELINE - DOPPLER_PERSONY);
        GC.drawImage(horn, xPos - horn.getWidth(), vertDist + DOPPLER_BASELINE - horn.getHeight() / TWO);
    }

    @Override
    void updateVariables() {
        xPos += velocity * TIMESTEP_S;
        dist = Math.sqrt(Math.pow(obsPos - xPos, TWO) + Math.pow(vertDist, TWO));
        relativeVel = (dist - lastDist) / TIMESTEP_S;
        freq = freq0 * (SOSOUND / (SOSOUND + relativeVel));

        lastDist = dist;
    }

    @Override
    void initVariables() {
        freq0 = freq = dist = lastDist = velocity = relativeVel = vertDist = xPos = obsPos = timePassed = ZERO;
        waveCounter = waveInterval = ZERO;
    }

    private class Wave {

        private double x;
        private double y;
        private double rad;
        private int timeAlive;
        private Color col;

        public Wave(double xPos, double yPos) {
            x = xPos;
            y = yPos + DOPPLER_BASELINE;
            rad = ZERO;
            timeAlive = ZERO;
            col = DOPPLER_RINGCOLOUR;
        }

        public boolean expand() {
            col = col.deriveColor(ZERO, ONE, ONE, DOPPLER_OPACITYOFFSET);
            if (timeAlive > DOPPLER_RINGLIFE) {
                return false;
            } else {
                rad += SOSOUND * DOPPLER_SOUNDSCALE;
                timeAlive++;
                return true;
            }
        }

        public void draw() {
            GC.setStroke(col);
            GC.setLineWidth(TWO);
            GC.strokeOval(x - rad, y - rad, rad * TWO, rad * TWO);
        }
    }
}