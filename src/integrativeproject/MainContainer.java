package integrativeproject;

import graphpackage.Graph;

import interfaces.Constants;
import static interfaces.Constants.menuView;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class MainContainer extends Application implements  Constants{
    
    MenuItem range;
    MenuItem nSLaw;
    MenuItem dop;
    MenuItem mSpring;
    MenuItem sBike;
    MenuItem iSeries;
    MenuItem exit;
    CheckMenuItem glowCheck;
    Scene current;
    
    GridPane NSLroot = new GridPane();
    NewtonSL NSL = new NewtonSL(this, NSLroot);
    
    GridPane MSProot = new GridPane();
    MassSpring MSP = new MassSpring(this, MSProot);
    
    GridPane NSBroot = new GridPane();
    NewSBike NSB = new NewSBike(this, NSBroot);
    
    GridPane ISroot = new GridPane();
    InfSeries IS = new InfSeries(this, ISroot);
    
    GridPane DProot = new GridPane();
    DopEffect DOP = new DopEffect(this, DProot);
    
    GridPane PMroot = new GridPane();
    ProjMotion PRM = new ProjMotion(this, PMroot);
    
    private Stage mainStage;
    private StackPane bodyDescription = new StackPane();
    private Text textDescription = new Text(introText);
    private Text textDescription2 = new Text(introText);
    private Rectangle textBackground = new Rectangle(SCENEW,SCENEH/FOUR);
    
    Graph controlGraph = new Graph(ONE, ONE, ONE, ONE, ONE, ONE, "", "", false);
    
    private BorderPane root = new BorderPane();
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        mainStage = primaryStage;
        MenuHandler MH = new MenuHandler();
        MenuBar mBar = new MenuBar();
        Menu mechanics = new Menu("Mechanics");
        range = new MenuItem("Range of a projectile");
        nSLaw = new MenuItem("Newton's Second Law");
        mechanics.getItems().addAll(range,nSLaw);
        
        Menu waves = new Menu("Waves");
        mSpring = new MenuItem("Simple Mass-Spring Oscillation");
        dop = new MenuItem("Doppler Effect");
        waves.getItems().addAll(mSpring,dop);
        
        Menu calculus = new Menu("Calculus");
        sBike = new MenuItem("New Sports Bike");
        iSeries = new MenuItem("Infinite Geometric Series");
        calculus.getItems().addAll(sBike,iSeries);
        
        Menu options = new Menu("Options");
        exit = new MenuItem("Exit");
        glowCheck = new CheckMenuItem("Ues Glow Lines");
        glowCheck.setSelected(USEGLOWLINES);
        options.getItems().addAll(exit, glowCheck);
        
        range.setOnAction(MH);
        nSLaw.setOnAction(MH);
        mSpring.setOnAction(MH);
        dop.setOnAction(MH);
        sBike.setOnAction(MH);
        iSeries.setOnAction(MH);
        exit.setOnAction(MH);
        glowCheck.setOnAction(MH);
        
        mBar.getMenus().addAll(mechanics, waves, calculus, options);
        
        root.setTop(mBar);
        textDescription.setFill(TEXTCOLOR);
        textDescription.setTextAlignment(TextAlignment.CENTER);
        textBackground.setFill(Color.rgb(ZERO, ZERO, ZERO, 0.85));
        textDescription2.setFill(Color.CYAN);
        textDescription2.setTextAlignment(TextAlignment.CENTER);
        textDescription2.setEffect(TEXTBLUR);
        bodyDescription.getChildren().addAll(menuView,textBackground,textDescription2,textDescription);
        root.setCenter(bodyDescription);
        
        current = new Scene(root, SCENEW,  SCENEH);
        
        mainStage.setTitle("Integrative Project!");
        mainStage.setScene(current);
        mainStage.show();
    }
    
    public void resetToMain(){
        mainStage.setScene(current);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    private class MenuHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            if(event.getSource() == range){
                mainStage.setScene(PRM);
            }
            else if(event.getSource() == nSLaw){
                mainStage.setScene(NSL);
            }
            else if(event.getSource() == mSpring){
                mainStage.setScene(MSP);
            }
            else if(event.getSource() == dop){
                mainStage.setScene(DOP);
            }
            else if(event.getSource() == sBike){
                mainStage.setScene(NSB);
            }
            else if(event.getSource() == iSeries){
                mainStage.setScene(IS);
            }
            else if(event.getSource() == exit){
                System.exit(0);
            }
            else if(event.getSource() == glowCheck){
                controlGraph.setGlow(glowCheck.isSelected());
            }
        }
    }
}