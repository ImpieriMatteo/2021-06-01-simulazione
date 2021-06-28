package it.polito.tdp.genes.model;

public class Arco{
	
	private Genes gene1;
	private Genes gene2;
	private Double peso;
	
	public Arco(Genes gene1, Genes gene2, Double peso) {
		this.gene1 = gene1;
		this.gene2 = gene2;
		this.peso = peso;
	}

	public Genes getGene1() {
		return gene1;
	}

	public void setGene1(Genes gene1) {
		this.gene1 = gene1;
	}

	public Genes getGene2() {
		return gene2;
	}

	public void setGene2(Genes gene2) {
		this.gene2 = gene2;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gene1 == null) ? 0 : gene1.hashCode());
		result = prime * result + ((gene2 == null) ? 0 : gene2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arco other = (Arco) obj;
		if (gene1 == null) {
			if (other.gene1 != null)
				return false;
		} else if (!gene1.equals(other.gene1))
			return false;
		if (gene2 == null) {
			if (other.gene2 != null)
				return false;
		} else if (!gene2.equals(other.gene2))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Arco [gene1=" + gene1 + ", gene2=" + gene2 + ", peso=" + peso + "]";
	}

}
