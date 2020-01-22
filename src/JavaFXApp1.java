//package JavaFXApplication1;

import java.text.NumberFormat;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author WUStudent
 */
public class JavaFXApp1 extends Application {
    
    TextField investmentField;
    TextField interestField;
    TextField yearsField;
    TextField futureValueField;
    
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Future Value Calculator");
        
        GridPane grid = new GridPane();
        
       grid.add(new Label("Monthly Investment:"), 0, 0);
       grid.add(new Label("Monthly Interest Rate:"), 0, 1);
       grid.add(new Label("Years:"), 0, 2);
       grid.add(new Label("Future Value:"), 0, 3);
       
       Scene scene = new Scene(grid, 500, 300);
       
       grid.setAlignment(Pos.CENTER_LEFT);
       grid.setPadding(new Insets(10, 10, 10, 50));
       grid.setHgap(20);
       grid.setVgap(15);
       
       investmentField = new TextField();
       grid.add(investmentField, 1, 0);
       String investment = investmentField.getText();
       
       interestField = new TextField();
       grid.add(interestField, 1, 1);
       String interest = interestField.getText();
       
       yearsField = new TextField();
       grid.add(yearsField, 1, 2);
       String years = yearsField.getText();
       
       futureValueField = new TextField();
       futureValueField.setEditable(false);
       futureValueField.setDisable(true);
       grid.add(futureValueField, 1, 3);
       
       for (int i = 0; i < 2; i++) {
       ColumnConstraints col = new ColumnConstraints(150);
       grid.getColumnConstraints().add(col);
       }
       
       Button calculateButton = new Button("Calculate");
       calculateButton.setOnAction(event -> calculateButtonClicked());
      
       Button exitButton = new Button("Exit");
       exitButton.setOnAction(event -> exitButtonClicked());
       
       HBox buttonBox = new HBox(10);
       buttonBox.getChildren().add(calculateButton);
       buttonBox.getChildren().add(exitButton);
       buttonBox.setAlignment(Pos.CENTER_RIGHT);
       
       grid.add(buttonBox, 0, 4, 2, 1);
                        
      primaryStage.setScene(scene);
        primaryStage.show();
          
      }
   
    private void calculateButtonClicked(){
      
        Validation v = new Validation();
        String errorMsg = "";
        
        errorMsg += v.isDouble(investmentField.getText(), "Monthly Investment ");
        errorMsg += v.isDouble(interestField.getText(), "Annual Interest ");
        errorMsg += v.isInteger(yearsField.getText(), "Number of years ");
        
        if(errorMsg.isEmpty()){
            double investment = Double.parseDouble(investmentField.getText());
            double interest = Double.parseDouble(interestField.getText());
            int years = Integer.parseInt(yearsField.getText());
            double futureValue = (investment * years * 12) * Math.pow((1 + 
                    (interest/100)/12), years);
            
             NumberFormat currency = NumberFormat.getCurrencyInstance();
        futureValueField.setText(currency.format(futureValue));
        }
        
        else
        {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Invalid Data");
            alert.setContentText(errorMsg);
            alert.showAndWait();
        }
        /*
        double investment;
        try{
        investment = Double.parseDouble(investmentField.getText());
        } catch (NumberFormatException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Invalid entry!");
            alert.setContentText("Monthly investment must be a valid number");
            alert.showAndWait();
            return;
        }
        
        double interest;
        try{
        interest = Double.parseDouble(interestField.getText());        
        } catch (NumberFormatException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Invalid entry!");
            alert.setContentText("Interest rate must be a valid number");
            alert.showAndWait();
            return;
        }
        
        int years;
        try{
        years = Integer.parseInt(yearsField.getText());
        } catch (NumberFormatException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Invalid entry!");
            alert.setContentText("Years must be a valid number");
            alert.showAndWait();
            return;
        }
        double futureValue = (investment * years * 12) * Math.pow((1 + (interest/100)/12), years);
        
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        futureValueField.setText(currency.format(futureValue));
        */
    }
    
    private void exitButtonClicked()
    {
        System.exit(0);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private Insets Insets(int i, int i0, int i1, int i2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
