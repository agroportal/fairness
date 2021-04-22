package fr.lirmm.fairness.assessment.principles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.reflections.Reflections;

import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.principles.impl.AbstractPrincipleCriterion;

public abstract class AbstractPrinciple extends AbstractScoredEntity implements Evaluable, Serializable {
	
	private static final long serialVersionUID = -4625119538425966086L;
	
	private List<AbstractPrincipleCriterion> principleCriteria = null;
	private List<ResultSet> resultSets = null;
	private Integer normalizedPrincipleScore= 0;
	protected AbstractPrinciple() {
		super();
		this.fillPrincipleCriteria();
	}
	
	@Override
	public final void evaluate(Ontology ontology) {

		Iterator<AbstractPrincipleCriterion> iterator = this.principleCriteria.iterator();
		this.resultSets = new ArrayList<ResultSet>(this.principleCriteria.size());
		while(iterator.hasNext()) {
			normalizedPrincipleScore=0;
			try {
				System.out.println("\n");
				AbstractPrincipleCriterion criterion = iterator.next();
				criterion.evaluate(ontology);
				this.resultSets.add(criterion.getResultSet());
				normalizedPrincipleScore+=criterion.getNormalizedTotalScore();
				System.out.println("> " + criterion.getClass().getSimpleName() + " points : " + criterion.getResultSet().getScores());
				System.out.println("> Total score for " + criterion.getClass().getSimpleName() + " : " + criterion.getResultSet().getTotalScore());
				System.out.println("> Explanations : " + criterion.getResultSet().getExplanations());
				System.out.println("> Normalized total score for " + criterion.getClass().getSimpleName() + " : " + criterion.getNormalizedTotalScore());				
			}
			catch(Exception iae) {
				iae.printStackTrace();
			}	
		}
	}
	      
	
	@SuppressWarnings("unchecked")
	private List<Class<? extends AbstractPrincipleCriterion>> getPrincipleCriteriaClasses() {
		Reflections reflections = new Reflections(this.getClass().getName().toLowerCase());
		Object[] reflectedPrincipleCriteriaClasses = reflections.getSubTypesOf(AbstractPrincipleCriterion.class).toArray();
		Arrays.sort(reflectedPrincipleCriteriaClasses, new Comparator<Object>() {
			@Override
			public int compare(Object o1, Object o2) {
				return o1.toString().compareTo(o2.toString());
			}
		});
		List<Class<? extends AbstractPrincipleCriterion>> principleCriteriaClasses = new ArrayList<Class<? extends AbstractPrincipleCriterion>>(reflectedPrincipleCriteriaClasses.length);
		for(Object o : reflectedPrincipleCriteriaClasses) {
			principleCriteriaClasses.add((Class<? extends AbstractPrincipleCriterion>)o);
		}
		return principleCriteriaClasses;
	}
	
	private void fillPrincipleCriteria() {
		List<Class<? extends AbstractPrincipleCriterion>> principleCriteriaClasses = this.getPrincipleCriteriaClasses();
		this.principleCriteria = new ArrayList<AbstractPrincipleCriterion>(principleCriteriaClasses.size());
		Iterator<Class<? extends AbstractPrincipleCriterion>> iterator = principleCriteriaClasses.iterator();
		while(iterator.hasNext()) {
			Class<? extends AbstractPrincipleCriterion> subCriteriaClassesClass = iterator.next();
			try {
				this.principleCriteria.add(subCriteriaClassesClass.getDeclaredConstructor().newInstance());
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public Integer getNormalizedToTPrincipleScore() {
		
		return (normalizedPrincipleScore); 
	}
	

	public List<Integer> getScores() {
		List<Integer> scores = new ArrayList<Integer>();
		for(ResultSet resultSet : this.resultSets) {
			scores.addAll(resultSet.getScores());
		}
		return scores;
	}

	public List<AbstractPrincipleCriterion> getPrincipleCriteria() {
		return principleCriteria;
	}

	public List<ResultSet> getResultSets() {
		return resultSets;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
}
