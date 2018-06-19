package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import javafx.collections.ObservableList;
import model.Atsiliepimai;

public class AtsiliepimaiDao {
	
	public void showElements(ObservableList<Atsiliepimai> data) {
		String query = "SELECT * FROM atsiliepimai";	
		
		try {
			
		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "");
		PreparedStatement add = myConn.prepareStatement(query);
		ResultSet rs = add.executeQuery();
		
		while(rs.next()) {
			data.add(new Atsiliepimai(
					rs.getInt("id"),
					rs.getString("miestas"),
					rs.getString("vardas"),
					rs.getString("elPastas"),
					rs.getString("atsiliepimas")				
					));
		}
		
		} catch(Exception exc) { 
			exc.printStackTrace();
		
		}
	}
	
	public void addElement(Atsiliepimai atsiliepimas) throws MySQLIntegrityConstraintViolationException {
		String sql = "INSERT INTO `atsiliepimai`"
				+ "(`miestas`, `vardas`, `elPastas`, `atsiliepimas`)"
				+ " VALUES (?, ?, ?, ?)";
		try {
		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "");
		PreparedStatement add = myConn.prepareStatement(sql);
		add.setString(1, atsiliepimas.getMiestas());
		add.setString(2, atsiliepimas.getVardas());
		add.setString(3, atsiliepimas.getElPastas());
		add.setString(4, atsiliepimas.getAtsiliepimas());
	
		add.execute();
		add.close();
		} catch (MySQLIntegrityConstraintViolationException e) {
			throw new MySQLIntegrityConstraintViolationException (); 
		} catch(Exception e) {
			e.printStackTrace();
		
		}
	}
	
	public void deleteAtsiliepimai(int id) {
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "");
			PreparedStatement del = myConn.prepareStatement("DELETE FROM atsiliepimai WHERE id = ?");
			del.setInt(1, id);
			del.executeUpdate();
			} catch(Exception e) {
				e.printStackTrace();
			
			}
	}
	
	public void updateElement(Atsiliepimai atsiliepimas) {
		try {
		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "");
		PreparedStatement upd = myConn.prepareStatement("UPDATE atsiliepimai SET miestas = ?, vardas = ?, elPastas = ?, atsiliepimas = ? WHERE id = ?");
		upd.setString(1, atsiliepimas.getMiestas());
		upd.setString(2, atsiliepimas.getVardas());
		upd.setString(3, atsiliepimas.getElPastas());
		upd.setString(4, atsiliepimas.getAtsiliepimas());
		upd.setInt(5, atsiliepimas.getId());
		upd.executeUpdate();
		upd.close();
		} catch(Exception e) {
			e.printStackTrace();
		
		}
	}
	
}
