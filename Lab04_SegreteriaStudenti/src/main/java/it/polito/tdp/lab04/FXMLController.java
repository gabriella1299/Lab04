/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuButton menuCorsi;

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

    }

    @FXML
    void doCercaIscritti(ActionEvent event) {

    }

    @FXML
    void doCheck(ActionEvent event) {
    	String matricolaString=txtMatricola.getText();
    	Integer matricola = null;
    	List<String> studente;
    	
    	try {
    		
    		matricola=Integer.parseInt(matricolaString);
    		studente=this.model.getNomeCognome(matricola);
    		
        	txtNome.setText(studente.get(0));
        	txtCognome.setText(studente.get(1));
        	
    	} catch(NumberFormatException nfe) {
    		txtRisultato.setText("Inserire un numero intero per la matricola!");
    		txtMatricola.clear();
    		return;
    	} catch(NullPointerException npe) {
    		txtRisultato.setText("Devi inserire una matricola!");
    		txtMatricola.clear();
    		return;
    	} catch(RuntimeException re) {
    		txtRisultato.setText("Devi inserire una matricola valida!");
    		txtMatricola.clear();
    		return;
    	} 
    	
    	
    	
    }

    @FXML
    void doIscrivi(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {

    }
    
    public void setModel (Model model) {
   	 this.model=model;
    }
    
    @FXML
    void initialize() {
        assert menuCorsi != null : "fx:id=\"menuCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}

