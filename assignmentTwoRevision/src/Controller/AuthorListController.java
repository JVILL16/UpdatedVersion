package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Database.AuthorTableGateway;
import Model.Author;
import View.MyController;
import View.SingletonSwitcher;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class AuthorListController implements Initializable, MyController, GeneralController {
	
	 private static Logger logger = LogManager.getLogger();
	
	 @FXML private ListView<Author> authorList;
	 private ObservableList<Author> authors;

	private AuthorTableGateway gateway;

	 
	 public AuthorListController(AuthorTableGateway gateway) {
	    	this.gateway = gateway;
	    	authors = this.gateway.getAuthors();
	    }
	    
	 @FXML void switchToAuthorDetailView(MouseEvent event) throws IOException {
			try {
				if(event.getClickCount()==2) {
					logger.info("Author double clicked.");
					SingletonSwitcher.getInstance().changeView(1,authorList.getSelectionModel().getSelectedItem());
				}
			}catch(Exception e) {
				
			}
	    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
			this.authorList.setItems(authors);
	}

}
