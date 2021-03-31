package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudenteDAO {
	
	public List<String> getNomeCognome(Integer matricola) {
		
		String sql="SELECT Nome,Cognome "
				+ "FROM studente "
				+ "WHERE matricola=?";
		
		List<String> result = new ArrayList<String>();
		
		try {
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs=st.executeQuery();
			rs.next();
			result.add(rs.getString("nome"));
			result.add(rs.getString("cognome"));
			rs.close();
			st.close();
			conn.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return result;
		
	}
	
	
}
