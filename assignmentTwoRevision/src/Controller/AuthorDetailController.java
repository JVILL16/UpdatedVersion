package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Database.AlertHelper;
import Model.Author;
import View.MyController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class AuthorDetailController implements Initializable, MyController, GeneralController {
	
private static Logger logger = LogManager.getLogger();
	
    @FXML private TextField FirstName;
    @FXML private TextField LastName;
    @FXML private TextField Website;
    @FXML private RadioButton Male;
    @FXML private RadioButton Female;
    @FXML private DatePicker datePicker;
    @FXML private Button Save;
   
    private Author author;

    
    public AuthorDetailController() {
    	
    }
    
    public AuthorDetailController(Author author) {
    	this();
        this.author = author;
        logger.info("Now showing: " + author.toString());
    }
    
    @FXML
    public void saveAuthorClicked() {
    	
    	logger.info("Author's info is saved");
    	
    	if(!author.isValidAuthorName(author.getAuthorFullName())) {
    		logger.error("Invalid Author name " + author.getAuthorFullName());
    		
    		AlertHelper.showWarningMessage("ERROR", "Author's Name Invalid", "The name that you inputed is invalid, try again.");
    	return;
    	}
    	author.save();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {

        FirstName.textProperty().bindBidirectional(author.authorFirstNameProperty());
        LastName.textProperty().bindBidirectional(author.authorLastNameProperty());
        Website.textProperty().bindBidirectional(author.authorWebsiteProperty());
        setGender();
        datePicker.valueProperty().bindBidirectional(author.authorBirthDateProperty());
	}
private void setGender(){
   if(author.getAuthorGender().equals("M")) {
       Male.setSelected(true);
       Female.setSelected(false);
       
       Male = new RadioButton("M");
       
   }else if(author.getAuthorGender().equals("F")){
       Male.setSelected(false);
       Female.setSelected(true);
       
       
   }
}
}
