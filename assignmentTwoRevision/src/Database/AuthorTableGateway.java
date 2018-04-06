package Database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Model.Author;
import View.Launcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AuthorTableGateway {
	
	private static Logger logger = LogManager.getLogger(Launcher.class);
	private Connection conn;
	
	public AuthorTableGateway(Connection conn) {
		this.conn = conn;
	}
	
	public void updateAuthor(Author author) throws AppException {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("update AuthorTable set first_name = ?, last_name = ?, dob = ?, gender = ?, web_site = ? where id = ?");
			st.setString(1, author.getAuthorFirstName());
			st.setString(2, author.getAuthorLastName());
			st.setDate(3, Date.valueOf(author.getDOB()));
			st.setString(4, author.getAuthorGender());
			st.setString(5, author.getAuthorWebsite());
			st.setInt(6, author.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e);
		} finally {
			try {
				if(st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new AppException(e);
			}
		}
	}
	public ObservableList<Author> getAuthors() throws AppException {
		ObservableList<Author> authors = FXCollections.observableArrayList();

		PreparedStatement st = null;

		try {
			st = conn.prepareStatement("select * from AuthorTable order by first_name");

			ResultSet rs = st.executeQuery();
			while(rs.next()) {

				Author author = new Author();
				author.setAuthorFirstName(rs.getString("first_name"));
				author.setAuthorLastName(rs.getString("last_name"));
				author.setAuthorGender(rs.getString("gender"));
				author.setDOB(rs.getDate("dob").toLocalDate());
				author.setAuthorWebsite(rs.getString("web_site"));
				author.setGateway(this);
				author.setId(rs.getInt("id"));
				authors.add(author);
			}
		} catch (SQLException e) {
		
			///e.printStackTrace();
			throw new AppException(e);
		} finally {
			try {
				if(st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new AppException(e);
			}
		}
		return authors;
	}
	
}
