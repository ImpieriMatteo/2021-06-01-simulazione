package it.polito.tdp.genes.model;

public class Interazione implements Comparable<Interazione>{
	
	private Genes gene;
	private Double peso;
	
	public Interazione(Genes gene, Double peso) {
		this.gene = gene;
		this.peso = peso;
	}

	public Genes getGene() {
		return gene;
	}

	public void setGene(Genes gene) {
		this.gene = gene;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	@Override
	public int compareTo(Interazione other) {
		return -this.peso.compareTo(other.peso);
	}

	@Override
	public String toString() {
		return gene.toString() + " " + peso + "\n";
	}

}
