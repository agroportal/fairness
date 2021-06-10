package fr.lirmm.fairness.assessment.principles;

import java.lang.reflect.ParameterizedType;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

import fr.lirmm.fairness.assessment.Configuration;
import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.model.PortalInstance;
import fr.lirmm.fairness.assessment.principles.impl.AbstractPrincipleCriterion;
import junit.framework.TestCase;

public abstract class AbstractTestPrincipleCriterion<T extends AbstractPrincipleCriterion> extends TestCase {

	protected final static String portalInstanceName = "agroportal";
	protected String ontologyAcronym = "NALT";
	protected Properties config = null;
	protected PortalInstance portalInstance = null;
	protected Ontology ontology = null;
	protected Integer[] expectedScores = null;
	protected int expectedScore = 0;
	protected String[] expectedExplanations = null;

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		config = Configuration.getInstance().getProperties(portalInstanceName);
		this.portalInstance = new PortalInstance(config);
		this.ontology = new Ontology(ontologyAcronym, this.portalInstance);
		this.fillExpectedValues();
	}

	@SuppressWarnings("unchecked")
	protected T getCriterionInstance() {
		ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();
		Class<T> type = (Class<T>) superClass.getActualTypeArguments()[0];
		try {
			return type.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testEvaluate() {

		T principleCriterion = this.getCriterionInstance();

		try {
			principleCriterion.evaluate(this.ontology);

			assertEquals(principleCriterion.getTotalScore().intValue(), this.expectedScore);
			assertEquals(principleCriterion.getResultSet().getScores().size(), this.expectedScores.length);
			assertEquals(principleCriterion.getResultSet().getExplanations().size(), this.expectedExplanations.length);

			Assert.assertArrayEquals(principleCriterion.getResultSet().getScores().toArray(new Integer[] {}), this.expectedScores);

			Assert.assertArrayEquals(principleCriterion.getResultSet().getExplanations().toArray(new String[] {}),
					this.expectedExplanations);
		} catch (Exception ex) {
			ex.printStackTrace();

		}
	}

	protected abstract void fillExpectedValues();
}
