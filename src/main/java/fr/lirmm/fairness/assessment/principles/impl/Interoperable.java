package fr.lirmm.fairness.assessment.principles.impl;

import fr.lirmm.fairness.assessment.principles.AbstractPrinciple;

public class Interoperable extends AbstractPrinciple {
	
	private static final long serialVersionUID = -1304186220723680282L;

	protected static Interoperable instance = null;
	
	public static Interoperable getInstance() {
		if(instance == null) {
			instance = new Interoperable();
		}
		return instance;
	}
	
	private Interoperable() {
		super();
	}
}
