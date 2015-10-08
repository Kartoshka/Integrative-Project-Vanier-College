package graphpackage;

import interfaces.Constants;
import javafx.scene.layout.GridPane;

public class GraphUI extends GridPane implements Constants{
    
    Graph[] graphs;
    
    public GraphUI(Graph[] g)
    {
        graphs = g;
        for(int i = ZERO; i < graphs.length; i++){
            this.add(graphs[i].getCanvas(), i, ZERO);
        }
    }
    
    public void drawLineSegment(int n, double x, double y){
        graphs[n].drawLineSegment(x, y);
    }
    
    public void drawGraphBase(){
        for(Graph g: graphs)
            g.drawClearGraph();
    }
    
    public Graph getGraph(int i)
    {
        return graphs[i];
    }
    
    public void reScale(int i, double desX, double desY){
        graphs[i].reScale(desX, desY);
    }
    
    public void updateDisplaySettings(){}
}