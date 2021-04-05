package ctrl;

import Domain.Enrollment;
import Domain.WinterGame;
import javafx.fxml.FXML;
import service.ServicesException;
import service.WinterSportsServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class WinterSportsController {

    private WinterSportsServices winterSportsServices;

    public void setService(WinterSportsServices service) {
        this.winterSportsServices = service;
    }

    // for Winter Game
    @FXML
    private TextField name, type, minAge, maxAge, filterW;
    @FXML
    private DatePicker dateW;
    @FXML
    private TableView<WinterGame> winterGameTableView;

    //for Enrollment
    @FXML
    private TextField childName, age, parentName, wgID, filterE;
    @FXML
    private TableView<Enrollment> enrollmentTableView;

    @FXML
    public void addW() {
        String wName = name.getText();
        String wType = type.getText();
        String wMinAge = minAge.getText();
        String wMaxAge = maxAge.getText();
        String wDate = dateW.getValue().format(dateFormatter);
        if (checkString(wName) && checkString(wType) && checkString(wDate) && checkString(wMinAge) && checkString(wMaxAge)) {
            try {
                int id = winterSportsServices.addWinterGame(wName, wType, Integer.parseInt(wMinAge), Integer.parseInt(wMaxAge), wDate);
                showNotification("Winter game successfully added! ", Alert.AlertType.INFORMATION);
            } catch (ServicesException cEx) {
                showNotification(cEx.getMessage(), Alert.AlertType.ERROR);
            }
        }

    }

    @FXML
    public void addE() {
        String eChildName = childName.getText();
        String eAge = age.getText();
        String eParentName = parentName.getText();
        String eWinterGame = wgID.getText();
        if (checkString(eChildName) && checkString(eParentName)&&checkString(eAge)&&checkString(eWinterGame)) {
            try {
                WinterGame winterGame = winterSportsServices.getWinterGameRepo().findByID(Integer.parseInt(eWinterGame));
                if (Integer.parseInt(eAge) > winterGame.getMaxAge() || Integer.parseInt(eAge) < winterGame.getMinAge() ){
                    showNotification("Enrollement does not respect Winter Game age criteria",Alert.AlertType.INFORMATION);
                    return;
                }
                winterSportsServices.addEnrollment(eChildName,Integer.parseInt(eAge),eParentName,winterGame);
                showNotification("Enrollment succesfully added! ", Alert.AlertType.INFORMATION);
            } catch (ServicesException cEx) {
                showNotification(cEx.getMessage(), Alert.AlertType.ERROR);
            }
        }

    }

    @FXML
    public void refreshW(){
        winterGameTableView.getItems().clear();
        winterGameTableView.getItems().addAll(winterSportsServices.getAllWG());

    }

    @FXML
    public void refreshE(){
        enrollmentTableView.getItems().clear();
        enrollmentTableView.getItems().addAll(winterSportsServices.getAllEn());

    }
    @FXML
    public void clearW() {
        name.setText("");
        type.setText("");
        minAge.setText("");
        maxAge.setText("");
        filterW.setText("");
    }

    @FXML
    public void clearE() {
        childName.setText("");
        parentName.setText("");
        age.setText("");
        wgID.setText("");
        filterE.setText("");
    }

    private boolean checkString(String s) {
        return s != null && !s.isEmpty();
    }

    @FXML
    public void filterByW() {

    }

    @FXML
    public void filterByE() {
        String filterByWhatWinterGame = filterE.getText();
        List<Enrollment> resultList=winterSportsServices.getWinterFilter(filterByWhatWinterGame);
        enrollmentTableView.getItems().clear();
        enrollmentTableView.getItems().addAll(resultList);
    }

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private void showNotification(String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle("Notification");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
