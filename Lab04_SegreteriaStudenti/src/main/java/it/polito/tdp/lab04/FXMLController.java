/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.sql.SQLDataException;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class FXMLController {
	
	private Model model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> cmbCorsi;

    @FXML
    private TextField txtMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextArea txtRisultato;

    @FXML
    private Button btnReset;
 
    @FXML
    void doCercaCorsi(ActionEvent event) {
    	txtRisultato.clear();
    	String matricolaString=txtMatricola.getText();
    	Integer matricola = null;
    	
    	
    	try {
    		
    		matricola=Integer.parseInt(matricolaString);
    		
    	} catch(NumberFormatException nfe) {
    		txtRisultato.setText("Inserire un numero intero per la matricola!");
    		txtMatricola.clear();
    		return;
    	} catch(NullPointerException npe) { //non entra mai qui!
    		txtRisultato.setText("Matricola non presente!");
    		txtMatricola.clear();
    		return;
    	}
    	//Se la matricola non e' presente visualizzare un messaggio di errore 
    	if(model.esisteMatricola(matricola)==false) {
    		txtRisultato.setText("Studente non presente!");
    		txtMatricola.clear();
    		return;
    	}
    		
    	List<Corso> corsi=this.model.getCorsiPerStudente(matricola);
    	
    	for(Corso c:corsi) {
    		txtRisultato.appendText(c.toString());
    	}
    	
    }
    /*@FXML
    void doCercaCorsi(ActionEvent event) {

		txtResult.clear();

		try {

			int matricola = Integer.parseInt(txtMatricola.getText());

			Studente studente = model.getStudente(matricola);
			if (studente == null) {
				txtResult.appendText("Nessun risultato: matricola inesistente");
				return;
			}

			List<Corso> corsi = model.cercaCorsiDatoStudente(studente);

			StringBuilder sb = new StringBuilder();

			for (Corso corso : corsi) {
				sb.append(String.format("%-8s ", corso.getCodins()));
				sb.append(String.format("%-4s ", corso.getCrediti()));
				sb.append(String.format("%-45s ", corso.getNome()));
				sb.append(String.format("%-4s ", corso.getPd()));
				sb.append("\n");
			}
			txtResult.appendText(sb.toString());

		} catch (NumberFormatException e) {
			txtResult.setText("Inserire una matricola nel formato corretto.");
		} catch (RuntimeException e) {
			txtResult.setText("ERRORE DI CONNESSIONE AL DATABASE!");
		}

    }*/

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	txtRisultato.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	
    	Corso c=null;
    	
    	try {
    		c=cmbCorsi.getValue();
    		if(c.getNome()=="") { //if(c==null)
        		txtRisultato.setText("Selezionare un corso!"); //new Corso("Nessun corso", null, "", null); -->"" e' il nome
        		return;
        	}
    		
    		/*List<Studente> studenti = model.studentiIscrittiAlCorso(corso);

			StringBuilder sb = new StringBuilder();

			for (Studente studente : studenti) {

				sb.append(String.format("%-10s ", studente.getMatricola()));
				sb.append(String.format("%-20s ", studente.getCognome()));
				sb.append(String.format("%-20s ", studente.getNome()));
				sb.append(String.format("%-10s ", studente.getCds()));
				sb.append("\n");
			}

			txtResult.appendText(sb.toString());
			*/
    	}catch(NullPointerException npe) {
    		txtRisultato.setText("Selezionare un corso!");
    		return;
    	}
    	if(model.corsoConIscritti(c.getCodins())==false) {
    		txtRisultato.setText("Il corso non ha iscritti");
    		return;
    	}
    	
    	
    	List<Studente> studenti=this.model.getStudentiIscrittiAlCorso(c);
    	for(Studente s:studenti) {
    		txtRisultato.appendText(s.toString());
    	}
    	
    }

   /* @FXML
    void doIscrivi(ActionEvent event) { MIO CODICE
    	txtRisultato.clear();
    	String matricolaString=txtMatricola.getText();
    	Integer matricola = null;
    	Corso c=null;
    	boolean trovato;
    	
    	try {
    		
    		c=cmbCorsi.getValue();
    		matricola=Integer.parseInt(matricolaString);
    		
    		if(c.getNome()=="") {//new Corso("Nessun corso", null, "", null); -->"" e' il nome
        		txtRisultato.setText("Selezionare un corso!");
        		return;
        	}
    		
    		trovato=this.model.StudenteIscrittoCorso(c, matricola);
    		if(trovato==true) {
        		txtRisultato.setText("Studente gia' iscritto al corso!");
        	}
        	else
        		txtRisultato.setText("Studente non ancora iscritto al corso!");
        	
    	} catch(NumberFormatException nfe) {
    		txtRisultato.setText("Riempire tutti i campi!");
    		txtMatricola.clear();
    		return;
    	} catch(NullPointerException npe) {
    		txtRisultato.setText("Riempire tutti i campi!");
    		txtMatricola.clear();
    		return;
    	} catch(RuntimeException re) {
    		txtRisultato.setText("Riempire tutti i campi!");
    		txtMatricola.clear();
    		return;
    	} 
    	
    	
    	
    }*/
    @FXML
    void doIscrivi(ActionEvent event) {

    	txtRisultato.clear();

		try {

			if (txtMatricola.getText().isEmpty()) {
				txtRisultato.setText("Inserire una matricola.");
				return;
			}

			if (cmbCorsi.getValue() == null) {
				txtRisultato.setText("Selezionare un corso.");
				return;
			}

			// Prendo la matricola in input
			int matricola = Integer.parseInt(txtMatricola.getText());

			// mi basterebbe la matricola per fare l'iscrizione ma per completezza 
			// posso anche far apparire il Nome e Cognome dello studente nell'interfaccia
			Studente studente = model.getNomeCognome(matricola);
			if (studente == null) {
				txtRisultato.appendText("Nessun risultato: matricola inesistente");
				return;
			}

			txtNome.setText(studente.getNome());
			txtCognome.setText(studente.getCognome());

			// Ottengo il nome del corso
			Corso corso = cmbCorsi.getValue();

			// Controllo se lo studente è già iscritto al corso
			if (model.isStudenteIscrittoACorso(studente, corso)) {
				txtRisultato.appendText("Studente già iscritto a questo corso");
				return;
			}
			// Prima di passare a rendere il tasto 'Iscrivi' realmente operativo 
			// con l'iscrizione, la versione 'Cerca' avrebbe fatto solo il successivo else.
			//}else {
			//	txtResult.appendText("Studente non iscritto a questo corso");
			//	return;
			//}

			///*
			// Iscrivo lo studente al corso.
			// Controllo che l'inserimento vada a buon fine
			if (!model.inscriviStudenteACorso(studente, corso)) {
				txtRisultato.appendText("Errore durante l'iscrizione al corso");
				return;
			} else {
				txtRisultato.appendText("Studente iscritto al corso!");
			}
			//*/

		} catch (NumberFormatException e) {
			txtRisultato.setText("Inserire una matricola nel formato corretto.");
		} catch (RuntimeException e) {
			txtRisultato.setText("ERRORE DI CONNESSIONE AL DATABASE!");
		}


    }

    @FXML
    void doReset(ActionEvent event) {
    	//cmbCorsi.setPromptText("Corsi");
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	txtRisultato.clear();
    }
    @FXML
    void doCheck(ActionEvent event) {
    	txtRisultato.clear();
    	String matricolaString=txtMatricola.getText();
    	Integer matricola = null;
    	
    	try {
    		
    		matricola=Integer.parseInt(matricolaString);
        	
    	} catch(NumberFormatException nfe) {
    		txtRisultato.setText("Inserire un numero intero su 6 cifre per la matricola!");
    		txtMatricola.clear();
    		return;
    	} catch(NullPointerException npe) {
    		txtRisultato.setText("Devi inserire una matricola!");
    		txtMatricola.clear();
    		return;
    	} catch(RuntimeException re) {
    		txtRisultato.setText("Devi inserire una matricola su 6 cifre!");
    		txtMatricola.clear();
    		return;
    	} 
    	
    	Studente studente=this.model.getNomeCognome(matricola);
    	txtNome.setText(studente.getNome());
    	txtCognome.setText(studente.getCognome());
    	
    }
    
    public void setModel (Model model) {
   	 	this.model=model;
   	 	cmbCorsi.getItems().addAll(new Corso("Nessun corso", null, "", null));
   	 	cmbCorsi.getItems().addAll(this.model.getTuttiICorsi());
   	 	
    }
    
    @FXML
    void initialize() {
    	assert cmbCorsi != null : "fx:id=\"cmbCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
    	assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}

