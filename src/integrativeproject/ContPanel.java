package integrativeproject;

import interfaces.Constants;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;

public class ContPanel extends GridPane implements Constants {

    private Button exitButton, playButton, pauseButton, stopButton, helpButton;
    private TextField[] inputFields;
    private Text[] inputNames;
    private double[] inputValues;
    private SimulationUI parent;
    private String helpText;
    private String warningText;
    private Label warningLabel;
    private boolean error = false;

    ContPanel(String[] fields, String help, SimulationUI UI) {
        this.setBackground(UIBG);
        
        inputFields = new TextField[fields.length];
        inputNames = new Text[fields.length];
        inputValues = new double[fields.length];

        helpText = help;
        parent = UI;

        for (int i = ZERO; i < fields.length; i++) {
            inputNames[i] = new Text(fields[i]);
            inputNames[i].setFill(TEXTCOLOR);
            inputValues[i] = ZERO;
            inputFields[i] = new TextField("0");
        }

        ButtonHandler BH = new ButtonHandler();
        warningText = "";
        warningLabel = new Label(warningText);
        warningLabel.setTextFill(Color.RED);
        exitButton = new Button("Exit");
        exitButton.setOnAction(BH);
        playButton = new Button("Play");
        playButton.setOnAction(BH);
        pauseButton = new Button("Pause");
        pauseButton.setOnAction(BH);
        pauseButton.setDisable(true);
        stopButton = new Button("Stop");
        stopButton.setOnAction(BH);
        stopButton.setDisable(true);
        helpButton = new Button("Help me!");
        helpButton.setOnAction(BH);

        exitButton.setBackground(new Background(new BackgroundFill(BUTTONCOLOR, BUTTONRADII, BUTTONINSETS)));
        playButton.setBackground(new Background(new BackgroundFill(BUTTONCOLOR, BUTTONRADII, BUTTONINSETS)));
        pauseButton.setBackground(new Background(new BackgroundFill(BUTTONCOLOR, BUTTONRADII, BUTTONINSETS)));
        stopButton.setBackground(new Background(new BackgroundFill(BUTTONCOLOR, BUTTONRADII, BUTTONINSETS)));
        helpButton.setBackground(new Background(new BackgroundFill(BUTTONCOLOR, BUTTONRADII, BUTTONINSETS)));

        this.add(exitButton, ONE, inputFields.length);
        this.add(playButton, TWO, inputFields.length);
        this.add(pauseButton, THREE, inputFields.length);
        this.add(stopButton, FOUR, inputFields.length);
        this.add(helpButton, FIVE, inputFields.length);

        for (int i = ZERO; i < inputFields.length; i++) {
            this.add(inputNames[i], ZERO, i);
            this.add(inputFields[i], ONE, i);
            this.setColumnSpan(inputFields[i], FIVE);
        }
        this.add(warningLabel, ONE, inputFields.length + ONE);
        this.setColumnSpan(warningLabel, FIVE);
    }

    public double getValues(int index) {
        inputValues[index] = Double.parseDouble(inputFields[index].getText());
        return inputValues[index];
    }

    public boolean checkValues() {
        boolean isDouble = true;
        for (int i = ZERO; i < inputValues.length; i++) {
            try {
                inputValues[i] = Double.parseDouble(inputFields[i].getText());
            } catch (NumberFormatException e) {
                isDouble = false;
                System.out.println("hello " + inputNames[i].getText() + "is clearly not a number");                
                warningText += "\n You must enter a number for the " + inputNames[i].getText();
                stopButton.fire();
            }
        }
        warningLabel.setText(warningText);
        return isDouble;
    }

    public void resetValues() {
        for (int i = ZERO; i < inputValues.length; i++) {
            inputValues[i] = ZERO;
        }
    }

    private class ButtonHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {
            if (event.getSource() == playButton) {
                warningText = EMPTYS;
                playButton.setDisable(true);
                pauseButton.setDisable(false);
                stopButton.setDisable(false);
                for (int i = ZERO; i < inputFields.length; i++) {
                    inputFields[i].setDisable(true);
                }
                parent.play();
            } else if (event.getSource() == pauseButton) {
                parent.pause();
                playButton.setDisable(false);
                pauseButton.setDisable(true);
                stopButton.setDisable(false);
            } else if (event.getSource() == stopButton) {
                parent.stop();
                playButton.setDisable(false);
                pauseButton.setDisable(true);
                stopButton.setDisable(true);
                for (int i = ZERO; i < inputFields.length; i++) {
                    inputFields[i].setDisable(false);
                }
            } else if (event.getSource() == exitButton) {
                parent.stop();
                parent.exitToMain();
            } else if (event.getSource() == helpButton) {
                JOptionPane.showMessageDialog(null, helpText, "I'm helping!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}