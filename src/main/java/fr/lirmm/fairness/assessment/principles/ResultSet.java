package fr.lirmm.fairness.assessment.principles;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import fr.lirmm.fairness.assessment.Configuration;

public class ResultSet  implements Serializable {
	
	private static final long serialVersionUID = 2746658890779865382L;
	protected Evaluable item = null;
	protected List<Integer> scores = null; 
	protected List<String> explanations = null;
    
	public ResultSet(Evaluable item) {
		super();
		this.item = item;
		this.scores = new ArrayList<Integer>();
		this.explanations = new ArrayList<String>();
	}
	
	public Evaluable getItem() {
		return item;
	}


	public List<Integer> getScores() {
		return this.scores;
	}



	public List<String> getExplanations() {
		return explanations;
	}

	@Override
	public String toString() {
		return String.format("%s : %s, %s", this.item.toString(), this.scores.toString(), this.explanations.toString());
	}
		
		
}
