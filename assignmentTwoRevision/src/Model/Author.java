package Model;

import java.time.LocalDate;

import Database.AppException;
import Database.AuthorTableGateway;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;


/**
 * 
 * @author CS 4743 Assignment 2 by Jheremi Villarreal and Manuel Gomez
 *
 */
public class Author {
	
	private int id;
	//private String authorGender;
	private SimpleStringProperty authorFirstName, authorLastName, authorWebsite, authorGender;	//Not all of these are great as StringProperties, but for now it'll have to do.
	private ObjectProperty<LocalDate> authorBirthDate;
	private AuthorTableGateway gateway;
	
	public enum Gender{	//Having an enum as a public field should be safe I think, since it's a constant.
			M,
			F
		}

	public Author() {
		authorFirstName = new SimpleStringProperty();
		authorLastName = new SimpleStringProperty();
		authorWebsite = new SimpleStringProperty();
		authorGender = new SimpleStringProperty();
		authorBirthDate = new SimpleObjectProperty<LocalDate>();
		id = 0;
		
	}

	public Author(String authorFirstName, String authorLastName) {
		this();
		if(!isValidAuthorName(authorFirstName) || !isValidAuthorName(authorLastName))
			throw new IllegalArgumentException("Author's name does not exist");
		
		setAuthorFirstName(authorFirstName);
		setAuthorLastName(authorLastName);
	}

	public Author(String authorFirstName, String authorLastName, String authorGender) {
		this(authorFirstName, authorLastName);
		if(!isValidGender(authorGender))
			throw new IllegalArgumentException("Author's gender does not exist");
		 setAuthorGender(authorGender.toString());
	}
	public Author(String authorFirstName, String authorLastName, String authorGender, String authorWebsite) {
		this(authorFirstName, authorLastName, authorGender);
		this.authorWebsite.setValue(authorWebsite);
	}
	public Author(String authorFirstName, String authorLastName, String authorGender, String authorWebsite, String authorDOBString) {
		this(authorFirstName, authorLastName, authorGender, authorWebsite);
		this.authorBirthDate.setValue(LocalDate.parse(authorDOBString));
	}
	
	public void save() throws AppException {
		gateway.updateAuthor(this);
	}
	
	public boolean isValidID(int id) {		
		if(!(id >= 0))
			return false;
		return true;
	}
	
	public boolean isValidAuthorName(String authorName) {
		if(authorName.length() < 0 || authorName.length() > 100)	//This runs for both first and last making max chars 100, idk if you want to change that.
			return false;
		return true;
	}
	
	public boolean isValidDateOfBirth(String authorDOBString) {
		return true; // couldn't figure this out 
	}
	
	public boolean isValidGender(String authorGender) {
		if (!authorGender.equals("M") || !authorGender.equals("F"))
			return false;
		return true;
	}
	
	public boolean isValidWebsite(String authorWebsite) {
		if(authorWebsite.length() > 100)	//This runs for both first and last making max chars 100, idk if you want to change that.
			return false;
		return true;
	}
	

	@Override
	public String toString() {
		return "Full Name: " + getAuthorFirstName() + " " + getAuthorLastName();
	}

	/**
	 * Everything Below Here should be mutators/accesors/SimpleStringProperties, please feel free to ignore all of it.
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public AuthorTableGateway getGateway() {
		return gateway;
	}

	public void setGateway(AuthorTableGateway gateway) {
		this.gateway = gateway;
	}

	public String getAuthorFirstName() {
		return authorFirstName.get();
	}

	public SimpleStringProperty authorFirstNameProperty() {
		return authorFirstName;
	}

	public String getAuthorWebsite() {
		return authorWebsite.get();
	}

	public SimpleStringProperty authorWebsiteProperty() {
		return authorWebsite;
	}

	public String getAuthorGender() {
		return authorGender.get();
	}

	public SimpleStringProperty authorGenderProperty() {
		return authorGender;
	}
	

	public void setAuthorFirstName(String authorFirstName) {
		this.authorFirstName.set(authorFirstName);
	}

	public void setAuthorLastName(String authorLastName) {
		this.authorLastName.set(authorLastName);
	}

	public void setAuthorWebsite(String authorWebsite) {
		this.authorWebsite.set(authorWebsite);
	}

	public void setAuthorGender(String authorGender) {
		this.authorGender.set(authorGender);
	}

	public String getAuthorLastName() {

		return authorLastName.get();
	}

	public ObjectProperty<LocalDate> authorBirthDateProperty() { 
		
		return authorBirthDate; 
	}
	
	public LocalDate getDOB() {
		return authorBirthDate.get();
	}
	
	public void setDOB(LocalDate date) {
		this.authorBirthDate.set(date);
	}
	public SimpleStringProperty authorLastNameProperty() {
		
		return authorLastName;  
	}
	public String getAuthorFullName() {
		
		return authorFirstName.get() + " " + authorLastName.get();
	}

	public void setAuthorName(String authorFirstName, String authorLastName) {
		
		this.authorFirstName.set(authorFirstName);
		this.authorLastName.set(authorLastName);
	}
}
