package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private StudenteDAO studenteDao;
	private CorsoDAO corsoDao;

	public Model() {
		super();
		this.studenteDao = new StudenteDAO();
		this.corsoDao=new CorsoDAO();
		
	}
	
	public Studente getNomeCognome(Integer matricola) {
		return studenteDao.getNomeCognome(matricola);
	}
	
	public List<Corso> getTuttiICorsi(){
		 return corsoDao.getTuttiICorsi();
		
	}
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso){
		return corsoDao.getStudentiIscrittiAlCorso(corso);
	}
	
	public List<Corso> getCorsiPerStudente(Integer matricola){
		return studenteDao.getCorsiPerStudente(matricola);
	}
	
	public boolean StudenteIscrittoCorso(Corso corso,Integer matricola) {
		List<Studente> studenti= corsoDao.getStudentiIscrittiAlCorso(corso);
		List<Corso> corsi= studenteDao.getCorsiPerStudente(matricola);
		boolean trovatoStudente=false;
		boolean trovatoCorso=false;
		
		for(Studente s:studenti) {
			if(s!=null) {
				if(s.getMatricola().equals(matricola)) {
					trovatoStudente=true;
					break;
				}
			}
		}
		for(Corso c:corsi) {
			if(c!=null) {
				if(c.equals(corso)) {
					trovatoCorso=true;
					break;
				}
			}
		}
		if(trovatoStudente==true && trovatoCorso==true ) {
			return true;
		}
		return false;
	}
	public boolean isStudenteIscrittoACorso(Studente studente, Corso corso) {//uguale a sopra ma fatto cosi è meglio
		return studenteDao.isStudenteIscrittoACorso(studente,corso);
	}

	public boolean esisteMatricola(Integer matricola) {
		return studenteDao.esisteMatricola(matricola);
	}

	public boolean corsoConIscritti(String codins) {
		
		return corsoDao.corsoConIscritti(codins);
	}
	
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		return corsoDao.inscriviStudenteACorso(studente, corso);
	}

}
