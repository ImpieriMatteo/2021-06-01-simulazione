package it.polito.tdp.genes.model;

public class Ingegnere {
	
	private Integer ID;
	private Genes geneStud;
	
	public Ingegnere(Integer iD, Genes geneStud) {
		ID = iD;
		this.geneStud = geneStud;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public Genes getGeneStud() {
		return geneStud;
	}

	public void setGeneStud(Genes geneStud) {
		this.geneStud = geneStud;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
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
		Ingegnere other = (Ingegnere) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}

}
