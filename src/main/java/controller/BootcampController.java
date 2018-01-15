package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.Bootcamp;
import navigation.Navigation;
import services.BootcampService;
import services.ServiceRegistry;

import java.util.Date;

public class BootcampController implements Controller {
    private BootcampService bootcampService;

    public void initialize() {
        bootcampService = (BootcampService) ServiceRegistry.getInstance().getService("jpabootcampservice");
       fillTable();
    }

    @FXML
    private GridPane myGrid;

    @FXML
    private Button backBtn;

    @FXML
    private TableView<Bootcamp> tableView;

    @FXML
    private TableColumn<Bootcamp, Integer> tableID;

    @FXML
    private TableColumn<Bootcamp, String> tableLocation;

    @FXML
    private TableColumn<Bootcamp, Date> tableStart;

    @FXML
    private TableColumn<Bootcamp, Date> tableEnd;

    @FXML
    void onBackBtn(ActionEvent event) {
        Navigation.getInstance().back();
    }

    @FXML
    void onClickTable(MouseEvent event) {
        Bootcamp row = tableView.getSelectionModel().getSelectedItem();
        if(row == null) {
            return;
        } else if (event.getClickCount() >= 2){
            Navigation.getInstance().loadScreen("bootcamp_details");
            ((bootcampDetailsController)Navigation.getInstance().getControllers("bootcamp_details")).setBootcamp(row);
        }
        tableView.getSelectionModel().clearSelection();
    }

    public void fillTable() {
        ObservableList<Bootcamp> observableList = FXCollections.observableArrayList();
        observableList.addAll(bootcampService.listAllBootcamps());
        tableView.setEditable(false);
        tableID.setCellValueFactory(new PropertyValueFactory<>("bootcampNumber"));
        tableLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        tableStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        tableEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        tableView.setItems(observableList);
    }

}
