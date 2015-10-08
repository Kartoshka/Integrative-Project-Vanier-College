package integrativeproject;

import graphpackage.*;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;

public class NewSBike extends SimulationUI {

    ArrayList<Integer> xpos = new ArrayList<Integer>();
    ArrayList<Integer> moneyX = new ArrayList<Integer>();
    ArrayList<Integer> moneyY = new ArrayList<Integer>();

    double rateBike, rateCash, manufCost, bikeCost, totalCost, profit, moneySales, lastBike, lastCash, root1, root2, maxPricePoint, maxProfit;
    int initialDemand, unitSales, price, count;

    public NewSBike(MainContainer UI, Pane root) {
        super(UI, root);
        addComponents();
    }

    @Override
    void initVariables() {
        price = count = unitSales = ZERO;
        root1 = root2 = maxPricePoint = maxProfit = lastBike = lastCash = timePassed = manufCost = bikeCost = totalCost = moneySales = profit = initialDemand = ZERO;
        rateBike = rateCash = UNUSEDVALUE;
    }

    @Override
    void initAnimation() {
        GC.drawImage(cadTire, ZERO, ZERO);
    }

    @Override
    void initGraph() {
        Graph[] g = new Graph[]{new Graph(GRAPHSECTIONW / TWO, GRAPHSECTIONH, DEFAULTGRAPHTICK, DEFAULTGRAPHTICK, DEFAULTGRAPHSCALE, DEFAULTGRAPHSCALE, allUnits[8], allUnits[9], false),
            new Graph(GRAPHSECTIONW / TWO, GRAPHSECTIONH, DEFAULTGRAPHTICK, DEFAULTGRAPHTICK, DEFAULTGRAPHSCALE, DEFAULTGRAPHSCALE, allUnits[8], allUnits[10], true)
        };
        graphs = new GraphUI(g);
        graphs.drawGraphBase();
    }

    @Override
    void initControlPanel() {
        controls = new ContPanel(new String[]{allInputs[10], allInputs[11], allInputs[12]}, helpText[TWO], this);
        controls.setAlignment(Pos.CENTER);
        mainPane.add(controls, ZERO, ONE);
    }

    @Override
    void play() {
        timeline.play();
        if (!playing && controls.checkValues()) {
            bikeCost = Math.abs(controls.getValues(ZERO));
            manufCost = Math.abs(controls.getValues(ONE));
            initialDemand = Math.abs((int) controls.getValues(TWO));

            maxPricePoint = (NSB_CONSTANT * bikeCost + initialDemand) / (NSB_CONSTANT * TWO);
            maxProfit = maxPricePoint * maxPricePoint * (-ONE) * NSB_CONSTANT + (NSB_CONSTANT * bikeCost + initialDemand) * maxPricePoint - (manufCost + bikeCost * initialDemand);
            if (maxProfit < ZERO) {
                maxProfit = Math.abs(maxProfit);
            }
            graphs.reScale(ZERO, (initialDemand / NSB_CONSTANT) * NSB_GRAPHRATIO, initialDemand * NSB_GRAPHRATIO);
            graphs.reScale(ONE, maxPricePoint * TWO, maxProfit * FOUR);
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
    }

    @Override
    void updateGraphs() {
        graphs.drawLineSegment(ZERO, price, unitSales);
        graphs.drawLineSegment(ONE, price, profit);
    }

    @Override
    void updateAnimation() {
        GC.drawImage(cadTire, ZERO, ZERO);

        if ((timePassed - lastCash) > rateCash) {
            lastCash = timePassed;
            moneyX.add((int) (Math.random() * SCENEW / TWO));
            moneyY.add(ZERO);
        }
        if ((timePassed - lastBike) > rateBike) {
            lastBike = timePassed;
            xpos.add(ZERO);
        }
        for (int i = ZERO; i < xpos.size(); i++) {

            GC.drawImage(bike, xpos.get(i), NSB_CADTIREDOORS - bike.getHeight());
            xpos.set(i, xpos.get(i) + NSB_CASHFLOWPOSITIONINCREASE);

            if (xpos.get(i) > ANIMATIONW) {
                xpos.remove(i);
            }
        }
        for (int i = ZERO; i < moneyX.size(); i++) {
            GC.drawImage(dollar, moneyX.get(i), moneyY.get(i));
            moneyY.set(i, moneyY.get(i) + NSB_CASHFLOWPOSITIONINCREASE);
            if (moneyY.get(i) > ANIMATIONH) {
                moneyY.remove(i);
                moneyX.remove(i);
            }
        }
    }

    @Override
    void updateVariables() {

        unitSales = initialDemand - NSB_CONSTANT * price;
        moneySales = unitSales * price;
        totalCost = manufCost + bikeCost * unitSales;
        profit = moneySales - totalCost;

        if (profit > ZERO) {
            rateCash = (maxProfit) / (profit * NSB_RATEINCREASEVALUE);
        }
        if (unitSales <= ZERO) {
            rateBike = rateCash = UNUSEDVALUE;
        } else {
            rateBike = (initialDemand) / (unitSales * NSB_RATEINCREASEVALUE);
        }
        price++;
    }
}