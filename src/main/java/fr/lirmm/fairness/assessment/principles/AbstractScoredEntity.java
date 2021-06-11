package fr.lirmm.fairness.assessment.principles;


import java.util.List;

public abstract class AbstractScoredEntity {

	protected List<Double> scores = null;
	protected List<Double> weights = null;


	public  List<Double> getScores(){
		return scores;
	}

	public  List<Double> getScoresWeights(){
		return weights;
	}

	public final Double getTotalScoreWeight() {
		double w = 0;
		for (double s : this.getScoresWeights()) {
			w += s;
		}
		return w;
	}



	public final Double getTotalScore() {
		Double score = 0.0;
		for (Double s : this.getScores()) {
			score += s;
		}
		return score;
	}
	
	public final Double getAverageScore() {
		return (((double) this.getTotalScore()  / this.getTotalScoreWeight()) * 100);
	}

	public  Integer getNormalizedTotalScore(){
		return  this.getAverageScore().intValue();
	}

}
