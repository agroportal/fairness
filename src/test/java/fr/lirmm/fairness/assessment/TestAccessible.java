package fr.lirmm.fairness.assessment;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fr.lirmm.fairness.assessment.accessible.TestA1;
import fr.lirmm.fairness.assessment.accessible.TestA11;
import fr.lirmm.fairness.assessment.accessible.TestA12;
import fr.lirmm.fairness.assessment.accessible.TestA2;
import junit.framework.TestSuite;

@RunWith(Suite.class)
@SuiteClasses({ TestA1.class, TestA11.class, TestA12.class, TestA2.class })
public class TestAccessible extends TestSuite {

}
