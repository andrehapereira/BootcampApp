package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.User;
import navigation.Navigation;
import services.UserService;

public class LoginController implements Controller{
    private UserService userService;
    private boolean loginMode = true;
    private boolean registerMode = false;
    private Navigation navigation;



    @FXML
    private Label UsernameLbl;

    @FXML
    private ImageView formimage;

    @FXML
    private Label passwordLbl;

    @FXML
    private Label emailLbl;

    @FXML
    private TextField username;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginBtn;

    @FXML
    private Hyperlink hyperlink;

    @FXML
    private Label message_field;

    @FXML
    private GridPane myGrid;

    @FXML
    void action(ActionEvent event) {
        if(loginMode) {
            login();
        } else {
            register();
        }
    }

    @FXML
    void modeSwitch(ActionEvent event) {
        if(!registerMode) {
            showRegister();
        } else {
            showLogin();
        }
    }

    public void login() {
        if(userService.autenthicate(username.getText(), password.getText())) {
            message_field.setText("Login com Sucesso");
            navigation.loadScreen("bootcamp");
            System.out.println("LOADED");
            message_field.setText("");
        } else {
            if (username.getText().isEmpty() || username.getText().matches("\\s+") ||
                    password.getText().isEmpty() || password.getText().matches("\\s+")) {
                message_field.setText("Tem campos vazios.");
            } else {
                message_field.setText("Username ou password errados.");
            }
        }
    }

    public void register() {
        User user = new User (username.getText(),password.getText(),email.getText());
        User checkIfExists = userService.findByName(user.getUsername());
        if(checkIfExists != null) {
            message_field.setText("Username already exists");
            return;
        }
        if (user.getUsername().equals("") || user.getUsername().matches("\\s+")) {
            message_field.setText("Username field empty.");
            return;
        }
        if (user.getEmail().equals("") || user.getEmail().matches("\\s+")) {
            message_field.setText("Email field empty.");
            return;
        }
        if (user.getPassword().equals("") || user.getPassword().matches("\\s+")) {
            message_field.setText("Password field empty.");
            return;
        }
        userService.addUser(user);
        loginMode = true;
        showLogin();
        username.setText("");
        password.setText("");
        message_field.setText("User Registered.");
    }

    public void showRegister() {
        email.setVisible(true);
        email.setDisable(false);
        emailLbl.setVisible(true);
        emailLbl.setDisable(false);
        loginBtn.setText("Register");
        hyperlink.setText("Cancel");
        message_field.setText("");
        registerMode = true;
        loginMode = false;
    }

    public void showLogin() {
        email.setVisible(false);
        email.setDisable(true);
        emailLbl.setVisible(false);
        emailLbl.setDisable(true);
        loginBtn.setText("Login");
        hyperlink.setText("Register");
        message_field.setText("");
        registerMode = false;
        loginMode = true;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setNavigation(Navigation navigation) {
        this.navigation = navigation;
    }
}
