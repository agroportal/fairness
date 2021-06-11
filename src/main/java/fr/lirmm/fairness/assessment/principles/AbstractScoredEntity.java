package fr.lirmm.fairness.assessment.principles;


import java.util.List;

public abstract class AbstractScoredEntity {

	protected List<Integer> scores = null;
	protected List<Integer> weights = null;
	protected ResultSet resultSet = null;

	public  List<Integer> getScores(){
		return scores;
	}

	public  List<Integer> getScoresWeights(){
		return weights;
	}

	public final Integer getTotalScoreWeight() {
		int w = 0;
		for (int s : this.getScoresWeights()) {
			w += s;
		}
		return w;
	}

	public final Integer getTotalScore() {
		Integer score = 0;
		for (Integer s : this.getScores()) {
			score += s;
		}
		return score;
	}
	
	public final Integer getAverageScore() {
		return (int)(((double) this.getTotalScore()  / this.getTotalScoreWeight()) * 100);
	}

	public  Integer getNormalizedTotalScore(){
		return this.getAverageScore();
	}

}
