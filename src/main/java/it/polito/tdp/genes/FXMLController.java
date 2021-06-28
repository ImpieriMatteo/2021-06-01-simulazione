/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.genes;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.genes.model.Genes;
import it.polito.tdp.genes.model.Interazione;
import it.polito.tdp.genes.model.Model;
import it.polito.tdp.genes.model.Simulatore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;
	private Simulatore sim;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="cmbGeni"
    private ComboBox<Genes> cmbGeni; // Value injected by FXMLLoader

    @FXML // fx:id="btnGeniAdiacenti"
    private Button btnGeniAdiacenti; // Value injected by FXMLLoader

    @FXML // fx:id="txtIng"
    private TextField txtIng; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	this.txtResult.clear();
    	
    	String result = this.model.creaGrafo();

    	this.txtResult.appendText(result+"\n\n");
    	this.btnGeniAdiacenti.setDisable(false);
    	
    	this.cmbGeni.getItems().addAll(this.model.getVertex());
    }

    @FXML
    void doGeniAdiacenti(ActionEvent event) {
    	
    	Genes scelto = this.cmbGeni.getValue();
    	if(scelto==null) {
    		this.txtResult.appendText("Devi prima scegliere un GENE!!");
    		return;
    	}
    	
    	List<Interazione> result = this.model.getAdiacenti(scelto);
    	
    	this.txtResult.appendText("Geni adiacenti a: "+scelto.toString()+"\n");
    	for(Interazione i : result)
    		this.txtResult.appendText(i.toString());
    	
    	this.txtResult.appendText("\n");
    	this.btnSimula.setDisable(false);
    }

    @FXML
    void doSimula(ActionEvent event) {
    	this.txtResult.clear();

    	Integer N;
    	try {
    		N = Integer.parseInt(this.txtIng.getText());
    	}
    	catch(NumberFormatException e) {
    		this.txtResult.appendText("Devi inserire un numero INTERO!!");
    		return;
    	}
    	
    	Genes scelto = this.cmbGeni.getValue();
    	if(scelto==null) {
    		this.txtResult.appendText("Devi prima scegliere un GENE!!");
    		return;
    	}
    	
    	this.sim = new Simulatore();
    	
    	this.sim.init(N, scelto, this.model);
    	this.sim.simula();
    	
    	Map<Genes, Integer> geniInStudio = this.sim.getGeniInStudio();
    	
    	this.txtResult.appendText("Risultati alla fine della simulazione: \n\n");
    	for(Genes g : geniInStudio.keySet()) 
    		this.txtResult.appendText(g.toString()+" - Ingegneri: "+geniInStudio.get(g)+"\n");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbGeni != null : "fx:id=\"cmbGeni\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnGeniAdiacenti != null : "fx:id=\"btnGeniAdiacenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtIng != null : "fx:id=\"txtIng\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
    
}
