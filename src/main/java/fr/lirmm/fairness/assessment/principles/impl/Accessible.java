package fr.lirmm.fairness.assessment.principles.impl;

import fr.lirmm.fairness.assessment.principles.AbstractPrinciple;

public class Accessible extends AbstractPrinciple {
	
	private static final long serialVersionUID = 863641754657723377L;

	protected static Accessible instance = null;
	
	public static Accessible getInstance() {
		if(instance == null) {
			instance = new Accessible();
		}
		return instance;
	}
	
	private Accessible() {
		super();
	}
}
