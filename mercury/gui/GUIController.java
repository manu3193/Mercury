package mercury.gui;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import mercury.ErrorList;
import mercury.ErrorMessage;
import mercury.LexicalAnalysis;
import mercury.SyntaxAnalysis;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.StyleSpans;
import org.fxmisc.richtext.StyleSpansBuilder;


import java.awt.*;
import java.io.*;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Kevin
 */
public class GUIController implements Initializable {

    @FXML
    private static final int N = 1024;

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
    public Button assembleButton;

    @FXML
    public Button simulateButton;

    @FXML
    public TextArea textArea;

    @FXML
    public TextArea outputTextArea;

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

    @FXML
    private ObservableList<String> options
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
    public TextField txtFieldStartAddress;

    @FXML
    public TextField txtFieldEndAddress;

    @FXML
    public TableView<Memory> tableView;

    @FXML
    public TableColumn<Memory, String> colWordAddress;

    @FXML
    public TableColumn<Memory, String> colByte3;

    @FXML
    public TableColumn<Memory, String> colByte2;

    @FXML
    public TableColumn<Memory, String> colByte1;

    @FXML
    public TableColumn<Memory, String> colByte0;

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

    @FXML
    public ScrollPane scrollPane;

    @FXML
    public ScrollPane scroll2;

    @FXML
    public FileChooser openDialog;

    @FXML
    public FileChooser saveDialog;

    @FXML
    public Desktop desktop;

    @FXML
    public ArmFileReader reader = new ArmFileReader();

    @FXML
    private static final Logger LOG = Logger.getLogger(Mercury.class.getName());

    @FXML
    private Future<List<String>> future;

    @FXML
    private ExecutorService executorService;

    @FXML
    private File file;

    @FXML
    Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Loading user data...");
        desktop = Desktop.getDesktop();
        configureOpenDialog();
        configureSaveDialog();
        /*Initialize combo boxes for all registers. */
        initComboBoxes();
        /*Set text fields as not editable.*/
        initRegisterTextField();
        //tableView.setPlaceholder(new Label("No memory addresses to show."));
        initTable();
    }

    @FXML
    private void initComboBoxes() {
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
    }

    @FXML
    private void initRegisterTextField() {
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
    private void initTable() {
        tableView.setEditable(true);
        colWordAddress.setMinWidth(35);
        colWordAddress.setCellValueFactory(new PropertyValueFactory<>("wordAddress"));
        colWordAddress.setSortable(false);

        colByte3.setMinWidth(10);
        colByte3.setCellValueFactory(new PropertyValueFactory<>("byte3"));
        colByte3.setSortable(false);
        colByte3.setCellFactory(TextFieldTableCell.forTableColumn());


        colByte2.setMinWidth(10);
        colByte2.setCellValueFactory(new PropertyValueFactory<>("byte2"));
        colByte2.setSortable(false);
        colByte2.setCellFactory(TextFieldTableCell.forTableColumn());


        colByte1.setMinWidth(10);
        colByte1.setCellValueFactory(new PropertyValueFactory<>("byte1"));
        colByte1.setSortable(false);
        colByte1.setCellFactory(TextFieldTableCell.forTableColumn());

        colByte0.setMinWidth(10);
        colByte0.setCellValueFactory(new PropertyValueFactory<>("byte0"));
        colByte0.setSortable(false);
        colByte0.setCellFactory(TextFieldTableCell.forTableColumn());


        tableView.setPlaceholder(new Label("No addresses to show."));
        tableView.setItems(fillTable());

    }

    private ObservableList<Memory> fillTable() {
        ObservableList<Memory> memoryAddress = FXCollections.observableArrayList();
        Memory mem = new Memory();
        for (int i = 0; i < N; i++) {
            mem.setRow(i);
            memoryAddress.add(new Memory());

        }
        return memoryAddress;
    }

    @FXML
    private void handleNewButtonAction(ActionEvent event) {
        textArea.clear();
    }

    @FXML
    private void handleOpenButtonAction(ActionEvent event) {
        file = openDialog.showOpenDialog(newButton.getScene().getWindow());
        if (file != null) {
            try {
                setContentTextArea(file);
                assembleResultLbl.setVisible(true);
                assembleResultLbl.setText("File opened.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    private void setContentTextArea(File file) throws InterruptedException, ExecutionException {
        executorService = Executors.newSingleThreadExecutor();
        textArea.clear();
        future = executorService.submit(new Callable<List<String>>() {
            public List<String> call() throws Exception {
                return reader.read(file);
            }
        });
        List<String> lines = future.get();
        executorService.shutdownNow();
        textArea.clear();
        for (String line : lines) {
            textArea.appendText(line + "\n");
        }
        lines.clear();
    }

    @FXML
    private void configureOpenDialog() {
        openDialog = new FileChooser();
        openDialog.setTitle("Open ARMv4 program");

        openDialog.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        openDialog.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("ARMv4 files", "*.arm4")
        );
    }

    @FXML
    private void configureSaveDialog() {
        saveDialog = new FileChooser();
        saveDialog.setTitle("Save ARMv4 program");
        saveDialog.setInitialDirectory(
                new File(System.getProperty("user.home")));

        saveDialog.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("ARMv4 files", "*.arm4")
        );

    }

    @FXML
    private void handleSaveButtonAction(ActionEvent event) {
        file = saveDialog.showSaveDialog(newButton.getScene().getWindow());
        if (file != null) {
            SaveFile(textArea.getText(), file);
            assembleResultLbl.setVisible(true);
            assembleResultLbl.setText("File saved.");
        }
    }

    @FXML
    private void SaveFile(String content, File file) {
        try {
            FileWriter fileWriter;
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(
                    Mercury.class.getName()).log(
                    Level.SEVERE, null, ex
            );
        }
    }

    @FXML

    private void setTextArea(TextArea textarea, String content) {
        new Thread(new Runnable() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        textarea.appendText(content.concat("\n"));
                    }

                });
            }

        }).start();
    }

    @FXML
    private void handleAssembleButtonAction(ActionEvent event) {
        System.out.println("Assembling!");
        outputTextArea.setEditable(true);
        outputTextArea.clear();
        assembleResultLbl.setVisible(true);
        try {
            FileReader newFile = new FileReader(file);
            LexicalAnalysis lexicalAnalysis = new LexicalAnalysis(newFile);
            SyntaxAnalysis synAnalysis = new SyntaxAnalysis(lexicalAnalysis);

            Object result = synAnalysis.parse().value;
            ErrorList errorList = lexicalAnalysis.getErrorList();
            errorList.append(synAnalysis.getErrorList());
            if (errorList.getErrors().isEmpty()) {
                outputTextArea.appendText("No errors found.");
                assembleResultLbl.setText("Assemble Successful.");
            } else {
                for (ErrorMessage e : errorList.getErrors()
                        ) {
                    outputTextArea.appendText(e.getType().concat(" in line: ").
                            concat(String.valueOf(e.getLine())).concat(", position: ").concat(String.valueOf(e.getPos()))
                            .concat(".").concat("\n"));
                }
                assembleResultLbl.setText("Build failed.");
                outputTextArea.setEditable(false);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    @FXML
    private void handleOutput(ActionEvent event) {
        String output = textFieldPC.getText();
    }

    @FXML
    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(
                    Mercury.class.getName()).log(
                    Level.SEVERE, null, ex
            );
        }
    }
}


