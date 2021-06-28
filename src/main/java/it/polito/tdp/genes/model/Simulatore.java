package it.polito.tdp.genes.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;

public class Simulatore {
	
	private Integer numIng;
	private Genes partenza;
	private Integer numEventi;
	
	private List<Ingegnere> ing;
	
	private Model model;
	
	public void init(Integer n, Genes scelto, Model model) {
		this.ing = new ArrayList<>();
		
		this.numIng = n;
		this.partenza = scelto;
		this.numEventi = 36;
		
		this.model = model;
		
		for(int i=1; i<=this.numIng; i++)
			this.ing.add(new Ingegnere(i, this.partenza));
	}

	public void simula() {
		
		for(int i=1; i<=this.numEventi; i++) {
			
			for(Ingegnere ing : this.ing) {
				
				Integer prob = (int)(Math.random()*100);
				
				if(prob>=30) {
					
					Double probabilita = Math.random();
					
					Double sommaPesiArchi = 0.0;
					for(Genes g : Graphs.neighborListOf(this.model.getGrafo(), ing.getGeneStud()))
						sommaPesiArchi += this.model.getGrafo().getEdgeWeight(this.model.getGrafo().getEdge(ing.getGeneStud(), g));
					
					Double valoreProbPrec = 0.0;
					for(Genes g : Graphs.neighborListOf(this.model.getGrafo(), ing.getGeneStud())){
						
						Double valoreProb = valoreProbPrec + this.model.getGrafo().getEdgeWeight(this.model.getGrafo().getEdge(ing.getGeneStud(), g))/sommaPesiArchi;
						if(valoreProbPrec <= probabilita && probabilita < valoreProb) {
							ing.setGeneStud(g);
							break;
						}
						
						valoreProbPrec = valoreProb;
					}
				}
			}
		}
		
	}

	public Map<Genes, Integer> getGeniInStudio() {
		Map<Genes, Integer> result = new HashMap<>();
		
		for(Ingegnere i : this.ing) {
			
			if(!result.containsKey(i.getGeneStud()))
				result.put(i.getGeneStud(), 0);
		}
		
		for(Ingegnere i : this.ing) {
			
			Integer tot = result.get(i.getGeneStud())+1;
			result.put(i.getGeneStud(), tot);
		}
			
		
		return result;
	}

}
