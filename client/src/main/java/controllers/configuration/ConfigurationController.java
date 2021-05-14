package controllers.configuration;

import application.NominalFX;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import view.ViewController;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConfigurationController extends ViewController implements Initializable {

    @FXML
    private TextField payrollDirectory;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void run() {
        this.payrollDirectory.setText(NominalFX.configuration.getProperty("payrollDirectory"));
    }

    @FXML
    private void setDirectory(){
        DirectoryChooser chooser = new DirectoryChooser();
        File file = chooser.showDialog(this.manager.getController().getStageManager().getPrimaryStage());
        if (file != null){
            NominalFX.configuration.put("payrollDirectory",file.getPath());
            try {
                NominalFX.fileAPI.saveToFile(NominalFX.configuration,NominalFX.configurationPath,"Nominal Directory");
            } catch (IOException e){
                System.out.println(e.getMessage());
            }
        } else {
            NominalFX.logger.add("No directory found.");
        }
        this.payrollDirectory.setText(NominalFX.configuration.getProperty("payrollDirectory"));
    }

    @Override
    public void shouldUpdate() {

    }

}
