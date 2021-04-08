package fr.lirmm.fairness.assessment.principles.impl;

import fr.lirmm.fairness.assessment.principles.AbstractPrinciple;

public class Findable extends AbstractPrinciple {
	
	private static final long serialVersionUID = -1217770245751321797L;

	protected static Findable instance = null;
	
	public static Findable getInstance() {
		if(instance == null) {
			instance = new Findable();
		}
		return instance;
	}
	
	private Findable() {
		super();
	}
}
