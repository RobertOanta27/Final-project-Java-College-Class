import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import repository.EnrollmentRepo;
import repository.EnrollmentRepositoryFile;
import repository.WinterGameRepo;
import repository.WinterGameRepositoryFile;
import service.ServicesException;
import service.WinterSportsServices;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import ctrl.WinterSportsController;

public class MainFX extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("example.fxml"));
            Parent root = loader.load();
            WinterSportsController ctrl = loader.getController();
            ctrl.setService(getService());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Winter Sports");
            primaryStage.show();
        }catch(Exception e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setContentText("Error while starting app "+e);
            alert.showAndWait();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }

    static WinterSportsServices getService() throws ServicesException {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("WinterSports.properties"));
            String requestFileName=properties.getProperty("RequestsFile");
            if (requestFileName==null){
                requestFileName="WinterGamesFiles.txt.txt";
                System.err.println("WinterGames file not found. Using default "+requestFileName);
            }
            String formsFileName=properties.getProperty("RepairedFile");
            if (formsFileName==null){
                formsFileName="EnrollmentFiles.txt.txt";
                System.err.println("Enrollment file not found. Using default "+formsFileName);
            }
            WinterGameRepo winterGameRepo = new WinterGameRepositoryFile(requestFileName);
            EnrollmentRepositoryFile enrollmentRepositoryFile = new EnrollmentRepositoryFile(formsFileName, winterGameRepo);
            return new WinterSportsServices(winterGameRepo, enrollmentRepositoryFile);
        }catch (IOException ex){
            throw new ServicesException("Error starting app "+ex);
        }
    }
}

