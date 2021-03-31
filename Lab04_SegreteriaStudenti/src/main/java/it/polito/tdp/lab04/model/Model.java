package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private StudenteDAO studenteDao;

	public Model() {
		super();
		this.studenteDao = new StudenteDAO();
	}
	
	public List<String> getNomeCognome(Integer matricola) {
		return studenteDao.getNomeCognome(matricola);
	}
	
}
