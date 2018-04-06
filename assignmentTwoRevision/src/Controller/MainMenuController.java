package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Model.Author;
import View.Launcher;
import View.SingletonSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class MainMenuController implements Initializable{
	private static Logger logger = LogManager.getLogger(Launcher.class);

	@FXML private MenuBar menuBar;
	@FXML private MenuItem authorList;
	@FXML private MenuItem menuItemExit;
	@FXML private MenuItem bookList;
	@FXML private MenuItem addBook;
	@FXML private BorderPane rootPane;
	
	@FXML private void handleMenuAction(ActionEvent event) throws IOException {

		if(event.getSource() == authorList) {
	        logger.info("AuthorList button selected.");
	        SingletonSwitcher.getInstance().changeView(0, new Author());
		}else if(event.getSource() == menuItemExit) {
			logger.info("Exiting...");
			System.exit(0);
			logger.info("Exited.");
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		logger.info("Main Menu Loaded.");
	}
	
	

}
