package it.polito.tdp.genes.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {

	private SimpleWeightedGraph<Genes, DefaultWeightedEdge> grafo;
	private GenesDao dao;
	private Map<String, Genes> idMap;
	private List<Arco> archi;
	
	public Model() {
		this.dao = new GenesDao();
	}
	
	public String creaGrafo() {
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		this.idMap = new HashMap<>();
		
		this.dao.getVertex(this.idMap);
		Graphs.addAllVertices(this.grafo, this.idMap.values());
		
		this.archi = new ArrayList<>(this.dao.getEdges(this.idMap));
		for(Arco a : this.archi)
			Graphs.addEdgeWithVertices(this.grafo, a.getGene1(), a.getGene2(), a.getPeso());
		
		return String.format("GRAFO CREATO!!\n\n#VERTICI: %s\n#ARCHI: %s", this.grafo.vertexSet().size(), this.grafo.edgeSet().size());
	}
	
	public List<Genes> getVertex() {
		List<Genes> result = new ArrayList<>(this.grafo.vertexSet());
		Collections.sort(result);
		
		return result;
	}

	public List<Interazione> getAdiacenti(Genes scelto) {
		List<Interazione> result = new ArrayList<>();
		
		for(Genes g : Graphs.neighborListOf(this.grafo, scelto))
			result.add(new Interazione(g, this.grafo.getEdgeWeight(this.grafo.getEdge(scelto, g))));
		
		Collections.sort(result);
		
		return result;
	}
	
	public SimpleWeightedGraph<Genes, DefaultWeightedEdge> getGrafo(){
		return this.grafo;
	}
	
}
