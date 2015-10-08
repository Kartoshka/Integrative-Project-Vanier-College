package graphpackage;

import interfaces.Constants;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;

public class Graph implements Constants{
    
    private static boolean useGlow = USEGLOWLINES;
    
    private Canvas canvas;
    private GraphicsContext GC;
    private int gWidth;
    private int gHeight;
    private double xTickDistance;
    private double yTickDistance;
    
    private double lastx = UNUSEDVALUE;
    private double lasty = UNUSEDVALUE;
    
    private double xScale;
    private double yScale;
    
    private double xTUnits;
    private double yTUnits;
    
    private String xLabel;
    private String yLabel;
    
    private boolean showNegative;

    //xT and yT is the number of units per tick
    //xS and yS visually multiply the distance on the graph
    public Graph(int width, int height, double xT, double yT, double xS, double yS, String xUnits, String yUnits, boolean showNeg){
        canvas = new Canvas(width, height);
        GC = canvas.getGraphicsContext2D();
        gWidth = width;
        gHeight = height;
        xScale = xS;
        yScale = yS;
        xTickDistance = xT*xScale;
        yTickDistance = yT*yScale;
        showNegative = showNeg;
        
        xTUnits = xT;
        yTUnits = yT;
        
        xLabel = xUnits;
        yLabel = yUnits;
    }
    
    public void reScale(double desX, double desY){
        double xT = desX/GRAPH_APPROXLINES;
        double yT = desY/GRAPH_APPROXLINES;
        
        double fitX = gWidth/desX;
        double fitY = gHeight/desY;
        
        xT = roundTicks(xT);
        yT = roundTicks(yT);
        
        xScale = fitX;
        yScale = fitY;
        
        xTickDistance = xT*xScale;
        yTickDistance = yT*yScale;
        
        xTUnits = xT;
        yTUnits = yT;
        
        drawClearGraph();
    }
    
    public double roundTicks(double num){
        double limit = GRAPH_LIMIT;
        
        while(num > limit*TEN){
            limit = limit*TEN;
        }
        return num - (num % limit);
    }
    
    public void drawClearGraph(){
        
        lastx = UNUSEDVALUE;
        lasty = UNUSEDVALUE;
        
        GC.setFill(GRAPHBGCOLOR);
        GC.setStroke(GRAPHLINECOLOR);
        GC.setLineWidth(LINEWIDTH);
        
        GC.fillRect(ZERO, ZERO, gWidth, gHeight);
        
        //Axis Lines
        if(showNegative){
            GC.strokeLine(AXISWIDTH, gHeight/TWO, gWidth, gHeight/TWO);
            GC.strokeLine(AXISWIDTH, ZERO, AXISWIDTH, gHeight);
        }
        else{
            GC.strokeLine(AXISWIDTH, gHeight - AXISWIDTH, gWidth, gHeight - AXISWIDTH);
            GC.strokeLine(AXISWIDTH, ZERO, AXISWIDTH, gHeight - AXISWIDTH);
        }
        
        //X Axis
        for(int i = ZERO; i < gWidth/xTickDistance; i++){
            if(showNegative){
                //Lines
                GC.setStroke(GRAPHGRIDCOLOR);
                GC.strokeLine(i*xTickDistance + AXISWIDTH, gHeight,
                        i*xTickDistance + AXISWIDTH, ZERO);

                //Ticks
                GC.setStroke(GRAPHLINECOLOR);
                GC.strokeLine(i*xTickDistance + AXISWIDTH, gHeight/TWO,
                        i*xTickDistance + AXISWIDTH, gHeight/TWO + TICKWIDTH);
                
                //Numbers
                GC.strokeText(DF.format(i*xTUnits), i*xTickDistance + AXISWIDTH, gHeight/TWO + TEXTHEIGHT*TWO_D);
            }
            else{
                //Lines
                GC.setStroke(GRAPHGRIDCOLOR);
                GC.strokeLine(i*xTickDistance + AXISWIDTH, gHeight - AXISWIDTH,
                        i*xTickDistance + AXISWIDTH, ZERO);

                //Ticks
                GC.setStroke(GRAPHLINECOLOR);
                GC.strokeLine(i*xTickDistance + AXISWIDTH, gHeight - AXISWIDTH,
                        i*xTickDistance + AXISWIDTH, gHeight - AXISWIDTH + TICKWIDTH);
            
                //Numbers
                GC.strokeText(DF.format(i*xTUnits), i*xTickDistance + AXISWIDTH, 
                        gHeight - AXISWIDTH + TEXTHEIGHT*TWO_D);
            }
        }
        //Y Axis
        if(showNegative){
            for(int i = ZERO; i < gHeight/yTickDistance/TWO; i++){
                //Lines
                GC.setStroke(GRAPHGRIDCOLOR);
                GC.strokeLine(AXISWIDTH, gHeight/TWO - i*yTickDistance, gWidth, 
                        gHeight/TWO - i*yTickDistance);
                GC.strokeLine(AXISWIDTH, gHeight/TWO + i*yTickDistance, gWidth, 
                        gHeight/TWO + i*yTickDistance);
                
                //Ticks
                GC.setStroke(GRAPHLINECOLOR);
                //Positive Ticks
                GC.strokeLine(AXISWIDTH, gHeight/TWO - i*yTickDistance, 
                        AXISWIDTH - TICKWIDTH, gHeight/TWO - i*yTickDistance);
                
                //Negative Ticks
                GC.strokeLine(AXISWIDTH, gHeight/TWO + i*yTickDistance, 
                        AXISWIDTH - TICKWIDTH, gHeight/TWO + i*yTickDistance);
                
                //Numbers
                GC.strokeText(DF.format(i*yTUnits), TEXTPADDING, gHeight/TWO - i*yTickDistance);
                GC.strokeText(DF.format(-i*yTUnits), TEXTPADDING, gHeight/TWO + i*yTickDistance);
            }
        }
        else{
            for(int i = ZERO; i < gHeight/yTickDistance; i++){
                //Lines
                GC.setStroke(GRAPHGRIDCOLOR);
                GC.strokeLine(AXISWIDTH, gHeight - AXISWIDTH - i*yTickDistance, 
                        gWidth, gHeight - AXISWIDTH - i*yTickDistance);
                
                //Ticks
                GC.setStroke(GRAPHLINECOLOR);
                GC.strokeLine(AXISWIDTH, gHeight - AXISWIDTH - i*yTickDistance, 
                        AXISWIDTH - TICKWIDTH, gHeight - AXISWIDTH - i*yTickDistance);
            
                //Numbers
                GC.strokeText(DF.format(i*yTUnits), TEXTPADDING, gHeight - AXISWIDTH - i*yTickDistance);
            }
        }
        
        GC.setStroke(GRAPHLINECOLOR);
        GC.strokeRect(ZERO, ZERO, gWidth, gHeight);
        
        //Axis Labels
        if(showNegative){
            GC.strokeText(xLabel, AXISWIDTH + TICKWIDTH, gHeight/2 + TEXTHEIGHT*FOUR);
        }
        else{
            GC.strokeText(xLabel, AXISWIDTH + TICKWIDTH, gHeight - TEXTPADDING);
        }
        GC.strokeText(yLabel, AXISWIDTH + TICKWIDTH, TICKWIDTH*THREE);
    }
    
    public void drawLineSegment(double x, double y){
        GC.setLineCap(StrokeLineCap.BUTT);
        
        if(showNegative){
            if(lastx != UNUSEDVALUE){
                drawLines(lastx*xScale + AXISWIDTH, gHeight - lasty*yScale - gHeight/TWO, 
                        x*xScale + AXISWIDTH, gHeight - y*yScale - gHeight/TWO);
            }
            else{
                drawLines(x*xScale + AXISWIDTH, gHeight - y*yScale - gHeight/TWO, 
                        x*xScale + AXISWIDTH, gHeight - y*yScale - gHeight/TWO);
            }
        }
        else{
            if(lastx != UNUSEDVALUE){
                drawLines(lastx*xScale + AXISWIDTH, gHeight - lasty*yScale - AXISWIDTH, 
                        x*xScale + AXISWIDTH, gHeight - y*yScale - AXISWIDTH);
            }
            else{
                drawLines(x*xScale + AXISWIDTH, gHeight - y*yScale - AXISWIDTH, 
                        x*xScale + AXISWIDTH, gHeight - y*yScale - AXISWIDTH);
            }
        }
        lastx = x;
        lasty = y;
    }
    
    public void drawIndividualLine(double x1, double y1, double x2, double y2, Color c){
        GC.setStroke(c);
        GC.strokeLine(x1, y1, x2, y2);
    }
    
    public void drawLines(double x1, double y1, double x2, double y2){
        if(useGlow){
            GC.setLineWidth(GLOW1);
            drawIndividualLine(x1, y1, x2, y2, GRAPHGLOWCOLOR);
            GC.setLineWidth(GLOW2);
            drawIndividualLine(x1, y1, x2, y2, GRAPHGLOWCOLOR);
            GC.setLineWidth(GLOW3);
            drawIndividualLine(x1, y1, x2, y2, GRAPHGLOWCOLOR);
            GC.setLineWidth(GLOW4);
            drawIndividualLine(x1, y1, x2, y2, GRAPHGLOWCOLOR);
        }
        GC.setLineWidth(DATALINEWIDTH);
        drawIndividualLine(x1, y1, x2, y2, GRAPHDATACOLOR);
    }
    
    public void setGlow(boolean g){
        useGlow = g;
    }
    
    public Canvas getCanvas(){
        return canvas;
    }
}
