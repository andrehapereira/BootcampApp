package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.Bootcamp;
import model.Codecadet;
import navigation.Navigation;
import services.BootcampService;
import services.ServiceRegistry;

import java.util.Date;

public class bootcampDetailsController implements Controller{
    private Bootcamp bootcamp;
    private BootcampService bootcampService = (BootcampService)ServiceRegistry.getInstance().getService("jpabootcampservice");

    @FXML
    private GridPane myGridCadets;

    @FXML
    private Button backBtn;

    @FXML
    private TableView<Codecadet> tableView;

    @FXML
    private TableColumn<Codecadet, String> colName;

    @FXML
    private TableColumn<Codecadet, Codecadet.Gender> colGender;

    @FXML
    private TableColumn<Codecadet, String> colAddress;

    @FXML
    private TableColumn<Codecadet, String> colCity;

    @FXML
    private TableColumn<Codecadet, String> colPhone;

    @FXML
    private TableColumn<Codecadet, Date> colBirthday;

    @FXML
    private TableColumn<Bootcamp, Integer> colBootcamp;

    @FXML
    private Label labelID;

    @FXML
    private Label idLabel;

    @FXML
    private Label locationLabel;

    @FXML
    private Label startLbl;

    @FXML
    private Label endLbl;

    @FXML
    void onBackBtn(ActionEvent event) {
        Navigation.getInstance().back();
        tableView.refresh();
    }

    public void setBootcamp(Bootcamp bootcamp) {
        this.bootcamp = bootcamp;
        idLabel.setText(String.valueOf(bootcamp.getBootcampNumber()));
        locationLabel.setText(bootcamp.getLocation());
        startLbl.setText("" + bootcamp.getStart());
        endLbl.setText("" + bootcamp.getEnd());
        fillTable();
    }


    public void fillTable() {
        ObservableList<Codecadet> observableList = FXCollections.observableArrayList();
        observableList.addAll(bootcampService.listAllCadets(bootcamp));
        tableView.setEditable(false);
        colName.setCellValueFactory(new PropertyValueFactory<Codecadet, String>("name"));
        colGender.setCellValueFactory(new PropertyValueFactory<Codecadet, Codecadet.Gender>("gender"));
        colAddress.setCellValueFactory(new PropertyValueFactory<Codecadet, String>("address"));
        colCity.setCellValueFactory(new PropertyValueFactory<Codecadet, String>("city"));
        colPhone.setCellValueFactory(new PropertyValueFactory<Codecadet, String>("phone"));
        colBirthday.setCellValueFactory(new PropertyValueFactory<Codecadet, Date>("birthday"));
        tableView.setItems(observableList);
    }
}
