package fr.lirmm.fairness.assessment.principles;


import java.util.ArrayList;
import java.util.List;


public abstract class AbstractScoredEntity {

	protected List<Double> scores = new ArrayList<>();
	protected List<Double> weights = new ArrayList<>();


	public  List<Double> getScores(){
		return scores;
	}

	public  List<Double> getScoresWeights(){
		return weights;
	}

	public final Double getTotalScoreWeight() {
		return round(this.getScoresWeights().stream().reduce(Double::sum).get());
	}


	private double round(double score){
		return Math.round(score * 100.0) / 100.0;
	}


	public final Double getTotalScore() {
		return round(getScores().stream().reduce(Double::sum).get());
	}
	
	public final Double getAverageScore() {
		return (((double) this.getTotalScore()  / this.getTotalScoreWeight()) * 100);
	}

	public  Integer getNormalizedTotalScore(){
		return  this.getAverageScore().intValue();
	}

}
