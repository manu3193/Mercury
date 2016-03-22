package mercury;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Kevin
 */
public class GUIController implements Initializable {

    @FXML
    public Label assembleResultLbl;

    @FXML
    public Button newButton;

    @FXML
    public Button openButton;

    @FXML
    public Button saveButton;

    @FXML
    public Button logButton;

    @FXML
    public TextArea textArea;

    @FXML
    public MenuItem menuClose;

    @FXML
    public ComboBox<String> comboBox0;

    @FXML
    public ComboBox<String> comboBox1;

    @FXML
    public ComboBox<String> comboBox2;

    @FXML
    public ComboBox<String> comboBox3;

    @FXML
    public ComboBox<String> comboBox4;

    @FXML
    public ComboBox<String> comboBox5;

    @FXML
    public ComboBox<String> comboBox6;

    @FXML
    public ComboBox<String> comboBox7;

    @FXML
    public ComboBox<String> comboBox8;

    @FXML
    public ComboBox<String> comboBox9;

    @FXML
    public ComboBox<String> comboBox10;

    @FXML
    public ComboBox<String> comboBox11;

    @FXML
    public ComboBox<String> comboBox12;

    @FXML
    public ComboBox<String> comboBox13;

    @FXML
    public ComboBox<String> comboBoxLR;

    @FXML
    public ComboBox<String> comboBoxPC;

    ObservableList<String> options
            = FXCollections.observableArrayList(
                    "Dec",
                    "Bin",
                    "Hex"
            );

    @FXML
    public TextField textFieldR0;

    @FXML
    public TextField textFieldR1;

    @FXML
    public TextField textFieldR2;

    @FXML
    public TextField textFieldR3;

    @FXML
    public TextField textFieldR4;

    @FXML
    public TextField textFieldR5;

    @FXML
    public TextField textFieldR6;

    @FXML
    public TextField textFieldR7;

    @FXML
    public TextField textFieldR8;

    @FXML
    public TextField textFieldR9;

    @FXML
    public TextField textFieldR10;

    @FXML
    public TextField textFieldR11;

    @FXML
    public TextField textFieldR12;

    @FXML
    public TextField textFieldR13;

    @FXML
    public TextField textFieldLR;

    @FXML
    public TextField textFieldPC;
    
    @FXML
    public TextField CSPR_TextField;
   
    @FXML
    public Label Curr_Inst_Lbl;

    @FXML
    public Label Total_Lbl;

    @FXML
    public Label NFlag_Lbl;
    
    @FXML
    public Label ZFlag_Lbl;
    
    @FXML
    public Label CFlag_Lbl;
    
    @FXML
    public Label VFlag_Lbl; 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Loading user data...");
        
        /*Initialize combo boxes for all registers. */
        comboBox0.setItems(options);
        comboBox0.setValue("Hex");
        comboBox1.setItems(options);
        comboBox1.setValue("Hex");
        comboBox2.setItems(options);
        comboBox2.setValue("Hex");
        comboBox3.setItems(options);
        comboBox3.setValue("Hex");
        comboBox4.setItems(options);
        comboBox4.setValue("Hex");
        comboBox5.setItems(options);
        comboBox5.setValue("Hex");
        comboBox6.setItems(options);
        comboBox6.setValue("Hex");
        comboBox7.setItems(options);
        comboBox7.setValue("Hex");
        comboBox8.setItems(options);
        comboBox8.setValue("Hex");
        comboBox9.setItems(options);
        comboBox9.setValue("Hex");
        comboBox10.setItems(options);
        comboBox10.setValue("Hex");
        comboBox11.setItems(options);
        comboBox11.setValue("Hex");
        comboBox12.setItems(options);
        comboBox12.setValue("Hex");
        comboBox13.setItems(options);
        comboBox13.setValue("Hex");
        comboBoxLR.setItems(options);
        comboBoxLR.setValue("Hex");
        comboBoxPC.setItems(options);
        comboBoxPC.setValue("Hex");

        /*Set text fields as not editable.*/
        textFieldR0.setEditable(false);
        textFieldR1.setEditable(false);
        textFieldR2.setEditable(false);
        textFieldR3.setEditable(false);
        textFieldR4.setEditable(false);
        textFieldR5.setEditable(false);
        textFieldR6.setEditable(false);
        textFieldR7.setEditable(false);
        textFieldR8.setEditable(false);
        textFieldR9.setEditable(false);
        textFieldR10.setEditable(false);
        textFieldR11.setEditable(false);
        textFieldR12.setEditable(false);
        textFieldR13.setEditable(false);
        textFieldLR.setEditable(false);
        textFieldPC.setEditable(false);
        CSPR_TextField.setEditable(false);
        
        
        
    }

    @FXML
    private void handleNewButtonAction(ActionEvent event) {
        System.out.println("Creating new file...");

    }

    @FXML
    private void handleOpenButtonAction(ActionEvent event) {
        System.out.println("Opening file...");

    }

    @FXML
    private void handleSaveButtonAction(ActionEvent event) {
        System.out.println("Saving program...");
    }

    @FXML
    private void handleAssembleButtonAction(ActionEvent event) {
        System.out.println("Assembling!");
        assembleResultLbl.setVisible(true);
        assembleResultLbl.setText("Assemble successful!");
    }

    @FXML
    private void handleSimulateButtonAction(ActionEvent event) {
        System.out.println("Simulating");
    }

    @FXML
    private void onActionMenuClose(ActionEvent event) {
        System.out.println("Program finished.");
        System.exit(0);
    }

    @FXML
    private void handleLogButtonAction(ActionEvent event) {
        System.out.println("Opening log...");
    }

    @FXML
    private void handleComboBox0(ActionEvent event) {
        String selectedValue = comboBox0.getValue();
        System.out.println("Selected value:" + selectedValue);

    }

    @FXML
    private void handleComboBox1(ActionEvent event) {
        String selectedValue = comboBox1.getValue();

    }

    @FXML
    private void handleComboBox2(ActionEvent event) {
        String selectedValue = comboBox2.getValue();

    }

    @FXML
    private void handleComboBox3(ActionEvent event) {
        String selectedValue = comboBox3.getValue();

    }

    @FXML
    private void handleComboBox4(ActionEvent event) {
        String selectedValue = comboBox0.getValue();

    }

    @FXML
    private void handleComboBox5(ActionEvent event) {
        String selectedValue = comboBox0.getValue();

    }

    @FXML
    private void handleComboBox6(ActionEvent event) {
        String selectedValue = comboBox6.getValue();
    }

    @FXML
    private void handleComboBox7(ActionEvent event) {
        String selectedValue = comboBox7.getValue();
    }

    @FXML
    private void handleComboBox8(ActionEvent event) {
        String selectedValue = comboBox8.getValue();
    }

    @FXML
    private void handleComboBox9(ActionEvent event) {
        String selectedValue = comboBox9.getValue();
    }

    @FXML
    private void handleComboBox10(ActionEvent event) {
        String selectedValue = comboBox10.getValue();
    }

    @FXML
    private void handleComboBox11(ActionEvent event) {
        String selectedValue = comboBox11.getValue();
    }

    @FXML
    private void handleComboBox12(ActionEvent event) {
        String selectedValue = comboBox12.getValue();
    }

    @FXML
    private void handleComboBox13(ActionEvent event) {
        String selectedValue = comboBox13.getValue();
    }

    @FXML
    private void handleComboBoxLR(ActionEvent event) {
        String selectedValue = comboBoxLR.getValue();
    }

    @FXML
    private void handleComboBoxPC(ActionEvent event) {
        String selectedValue = comboBoxPC.getValue();
    }

    @FXML
    private void retrieveContentR0(ActionEvent event) {
        String registerVal = textFieldR0.getText();
    }

    @FXML
    private void retrieveContentR1(ActionEvent event) {
        String registerVal = textFieldR1.getText();
    }
    
    @FXML
    private void retrieveContentR2(ActionEvent event) {
        String registerVal = textFieldR2.getText();
    }
    
    @FXML
    private void retrieveContentR3(ActionEvent event) {
        String registerVal = textFieldR3.getText();
    }
    @FXML
    private void retrieveContentR4(ActionEvent event) {
        String registerVal = textFieldR4.getText();
    }
    
    @FXML
    private void retrieveContentR5(ActionEvent event) {
        String registerVal = textFieldR5.getText();
    }
    
    @FXML
    private void retrieveContentR6(ActionEvent event) {
        String registerVal = textFieldR6.getText();
    }
    
    @FXML
    private void retrieveContentR7(ActionEvent event) {
        String registerVal = textFieldR7.getText();
    }
    
    @FXML
    private void retrieveContentR8(ActionEvent event) {
        String registerVal = textFieldR8.getText();
    }
    
    @FXML
    private void retrieveContentR9(ActionEvent event) {
        String registerVal = textFieldR9.getText();
    }
    
    @FXML
    private void retrieveContentR10(ActionEvent event) {
        String registerVal = textFieldR10.getText();
    }
    
    @FXML
    private void retrieveContentR11(ActionEvent event) {
        String registerVal = textFieldR11.getText();
    }
    
    @FXML
    private void retrieveContentR12(ActionEvent event) {
        String registerVal = textFieldR12.getText();
    }
    
    @FXML
    private void retrieveContentR13(ActionEvent event) {
        String registerVal = textFieldR13.getText();
    }
    @FXML
    private void retrieveContentLR(ActionEvent event) {
        String registerVal = textFieldLR.getText();
    }
    
    @FXML
    private void retrieveContentPC(ActionEvent event) {
        String registerVal = textFieldPC.getText();
    }
}
