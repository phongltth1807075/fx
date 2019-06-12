package spring.hero.bank;

import entity.Transaction;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.AccountModel;

public class TransactionStage extends Stage {
    private String accountName;

    private VBox vBox; // 3 tầng.
    private HBox hBox; // label action.
    private GridPane gridPane; // số lượng và nút bấm.
    private HBox btnHbox; // chứa 2 nút bấm.

    private int action; // 1. withdraw | 2. deposit
    private Label lblAction;
    private Label lblAmount;
    private Label labelSender;
    private Label labelReceiver;
    private Label labelMessage;
    private Label lblAccountName;
    private TextField txtAmount;
    private TextField txtSender;
    private TextField txtReceiver;
    private TextField txtMessage;
    private Button btnSubmit;
    private Button btnCancel;
    private TextField id;
    private TextField createdat;
    private TextField updateat;
    private TextField status;
    private AccountModel model;

    private Scene scene;
    private Stage stage;
    private MainApplication mainApplication;

    public TransactionStage() {

    }

    public TransactionStage(int action, MainApplication mainApplication) {
        this.action = action;
        this.stage = this;
        this.mainApplication = mainApplication;
        initComponent();
        this.scene = new Scene(this.vBox, 570, 370);
        this.setScene(this.scene);
        this.initModality(Modality.APPLICATION_MODAL);
    }

    private void initComponent() {
        if (this.action == 1) {
            this.lblAction = new Label("Withdraw");
        } else {
            this.lblAction = new Label("Deposit");
        }
        this.hBox = new HBox();
        this.hBox.setSpacing(10);
        this.hBox.setPadding(new Insets(10));
        this.hBox.setAlignment(Pos.TOP_LEFT);
        this.hBox.getChildren().add(this.lblAction);

        this.gridPane = new GridPane();
        this.gridPane.setHgap(10);
        this.gridPane.setVgap(10);
        this.gridPane.setPadding(new Insets(10));
        this.gridPane.setAlignment(Pos.CENTER);
        this.labelSender = new Label("Sender");
        this.txtSender = new TextField();
        this.lblAccountName = new Label(this.accountName);
        this.labelReceiver=new Label("Receiver");
        this.txtReceiver=new TextField();
        this.labelMessage=new Label("Message");
        this.txtMessage=new TextField();
        this.lblAmount = new Label("Amount");
        this.txtAmount = new TextField();
        this.btnSubmit = new Button("Submit");
        this.btnCancel = new Button("Cancel");
        this.btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int amount = 0;
                try {
                    amount = Integer.parseInt(txtAmount.getText());
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
                int currentBalance = (action == 1) ? (mainApplication.getAccountBalance() - amount) : (mainApplication.getAccountBalance() + amount);
                mainApplication.getLblBalanceValue().setText(String.valueOf(currentBalance));
                mainApplication.setAccountBalance(currentBalance);
                stage.close();
                Transaction transaction=new Transaction(id.getText(),txtSender.getText(),txtReceiver.getText(),currentBalance,txtMessage.getText(),createdat.getText(),updateat.getText(),status.getText());
                model.savetransaction(transaction);
            }
        });
        this.btnCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });
        this.gridPane.add(this.labelSender,0,0);
        this.gridPane.add(this.txtSender,1,0);
        this.gridPane.add(this.labelReceiver, 0, 1);
        this.gridPane.add(this.txtReceiver, 1, 1);
        this.gridPane.add(this.lblAmount, 0,2);
        this.gridPane.add(this.txtAmount, 1, 2);
        this.gridPane.add(this.labelMessage, 0, 3);
        this.gridPane.add(this.txtMessage, 1, 3);

        this.btnHbox = new HBox();
        this.btnHbox.setPadding(new Insets(10));
        this.btnHbox.setSpacing(10);
        this.btnHbox.getChildren().addAll(this.btnSubmit, this.btnCancel);
        this.gridPane.add(this.btnHbox, 1, 4);

        this.vBox = new VBox();
        this.vBox.setPadding(new Insets(10));
        this.vBox.setSpacing(10);
        this.vBox.getChildren().addAll(this.hBox, this.gridPane);
        this.vBox.setAlignment(Pos.CENTER);
    }
}
