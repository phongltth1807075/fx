package spring.hero.bank;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.AccountModel;


public class MainStage extends Stage {
    private static final int LOGIN_STAGE_WIDTH = 300;
    private static final int LOGIN_STAGE_HEIGHT = 200;

    private HBox titleBox;
    private VBox funtionBox;
    private VBox mainText;
    private HBox mainHbox;
    private VBox getAllVbox;


    private Label logo;
    private Label lblTitle;
    private Label lblText1;
    private Label lblText2;
    private Label lblText3;
    private Button btnWithdraw;
    private Button btnDeposit;
    private Button btnLogin;
    private Button btnRegister;

    private Scene scene;
    private Stage stage;
    private LoginStage loginStage;
    private MainApplication mainApplication;
    private AccountModel model;

    public MainStage(MainApplication mainApplication) {
        this.model = new AccountModel();
        this.mainApplication = mainApplication;
        this.initComponent();
        this.scene = new Scene(this.getAllVbox, LOGIN_STAGE_WIDTH, LOGIN_STAGE_HEIGHT);
        this.stage = this;
        this.stage.setScene(this.scene);
    }
    public void initComponent(){
        this.titleBox=new HBox();
        this.titleBox.setAlignment(Pos.TOP_LEFT);
        this.titleBox.setSpacing(10);
        this.logo=new Label("Logo");
        this.lblTitle = new Label("Spring Hero Bank");
        this.lblTitle.setFont(Font.font(17));
        this.lblTitle.setTextFill(Color.web("#f9e70b"));
        this.titleBox.getChildren().addAll(logo,lblTitle);

        this.btnWithdraw=new Button("Withdraw");
        this.btnDeposit=new Button("Deposit");
        this.btnLogin=new Button("Login");
        this.btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loginStage=new LoginStage(this);
                loginStage.show();
            }
        });
        this.btnRegister=new Button("Register");

        this.funtionBox=new VBox();
        this.funtionBox.setAlignment(Pos.BASELINE_LEFT);
        this.funtionBox.getChildren().addAll(btnDeposit, btnWithdraw,btnLogin,btnRegister);

        this.mainText=new VBox();
        this.lblText1=new Label("Spring-hero-bank");
        this.lblText2=new Label("Ngân Hàng Gửi Tiền Là Mất.");
        this.lblText3=new Label("Chúc Quý Khách May Mắn!");
        this.mainText.setAlignment(Pos.CENTER);
        this.mainText.getChildren().addAll(lblText1,lblText2,lblText3);

        this.mainHbox=new HBox();
        mainHbox.getChildren().addAll(funtionBox,mainText);

        this.getAllVbox=new VBox();
        getAllVbox.getChildren().addAll(this.titleBox,this.mainHbox);
    }
}
