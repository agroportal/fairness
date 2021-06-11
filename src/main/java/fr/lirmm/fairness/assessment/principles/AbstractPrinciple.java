package fr.lirmm.fairness.assessment.principles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.reflections.Reflections;

import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.principles.criterion.AbstractPrincipleCriterion;
import org.reflections.scanners.SubTypesScanner;

public abstract class AbstractPrinciple extends AbstractScoredEntity implements Evaluable,Serializable {
	
	private static final long serialVersionUID = -4625119538425966086L;
	
	private List<AbstractPrincipleCriterion> principleCriteria = null;

	protected AbstractPrinciple() {
		super();
		this.fillPrincipleCriteria();
	}


	@Override
	public final void evaluate(Ontology ontology) {

		Iterator<AbstractPrincipleCriterion> iterator = this.principleCriteria.iterator();

		this.scores = new ArrayList<>(this.principleCriteria.size());
		this.weights = new ArrayList<>(this.principleCriteria.size());
		while(iterator.hasNext()) {
			try {
				System.out.println("\n");
				AbstractPrincipleCriterion criterion = iterator.next();
				criterion.evaluate(ontology);
				this.scores.add(criterion.getTotalScore());
				this.weights.add(criterion.getTotalScoreWeight());
				System.out.println("> " + criterion.getClass().getSimpleName() + " points : " + criterion.getScores());
				System.out.println("> Total score for " + criterion.getClass().getSimpleName() + " : " + criterion.getTotalScore());
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
		Reflections reflections = new Reflections("fr.lirmm.fairness.assessment.principles.criterion.impl");
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


	public List<AbstractPrincipleCriterion> getPrincipleCriteria() {
		return principleCriteria;
	}



	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}


}
