package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	/*
	 * Controllo se uno studente (matricola) è iscritto ad un corso (codins)
	 */
	public boolean isStudenteIscrittoACorso(Studente studente, Corso corso) {

		final String sql = "SELECT * FROM iscrizione where codins=? and matricola=?";
		boolean returnValue = false;

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());
			st.setInt(2, studente.getMatricola());

			ResultSet rs = st.executeQuery();

			if (rs.next())
				returnValue = true;

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}

		return returnValue;
	}
	
	/*
	 * Data una matricola ottengo lo studente.
	 */
	public Studente getNomeCognome(Integer matricola) {
		
		String sql="SELECT * "
				+ "FROM studente "
				+ "WHERE matricola=?";
		
		Studente studente=null;
		
		try {
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs=st.executeQuery();
			if(rs.next()) {
				studente=new Studente(matricola, rs.getString("cognome"), rs.getString("nome"), rs.getString("cds"));
			}
			rs.close();
			st.close();
			conn.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		} 
		
		return studente;
		
	}
	
	/*
	 * Data una matricola ottengo la lista dei corsi (codins) a cui è iscritto
	 */
	public List<Corso> getCorsiPerStudente(Integer matricola){
		
		String sql="SELECT c.codins,c.crediti,c.nome,c.pd "
				+ "FROM studente s, iscrizione i, corso c "
				+ "WHERE s.matricola=i.matricola AND i.codins=c.codins AND "
				+ "s.matricola=?";
		
		List<Corso> result = new ArrayList<Corso>();
		
		try {
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs=st.executeQuery();
			
			while(rs.next()) {			
				Corso c=new Corso(rs.getString("codins"),rs.getInt("crediti"),rs.getString("nome"),rs.getInt("pd"));
				result.add(c);
			}
			rs.close();
			st.close();
			conn.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return result;
		
	}
	
	/*
	 * Data una matricola guardo se esiste lo studente.
	 */
	public boolean esisteMatricola(Integer matricola) {
		String sql="SELECT * FROM studente WHERE matricola=?"; 
		
		try {
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs=st.executeQuery();
			
			if(rs.next()) {//vero se c'e' un prossimo risultato->al massimo ce ne sara 1 (rs punta a 0, se c'e' n'e' uno il next avra' qualcosa!
				rs.close();//REGOLA: chiudo tutto tutte le volte che c'e' un return!
				st.close();
				conn.close();
				return true;
			}
			else {
				rs.close();
				st.close();
				conn.close();
				return false;
			}
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
}
