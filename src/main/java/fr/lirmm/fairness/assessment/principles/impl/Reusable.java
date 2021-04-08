package fr.lirmm.fairness.assessment.principles.impl;

import fr.lirmm.fairness.assessment.principles.AbstractPrinciple;

public class Reusable extends AbstractPrinciple {
	
	private static final long serialVersionUID = 2832386515029860749L;

	protected static Reusable instance = null;
	
	public static Reusable getInstance() {
		if(instance == null) {
			instance = new Reusable();
		}
		return instance;
	}
	
	private Reusable() {
		super();
	}
}
