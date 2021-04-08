package fr.lirmm.fairness.assessment;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fr.lirmm.fairness.assessment.interoperable.TestI1;
import fr.lirmm.fairness.assessment.interoperable.TestI2;
import fr.lirmm.fairness.assessment.interoperable.TestI3;

@RunWith(Suite.class)
@SuiteClasses({ TestI1.class, TestI2.class, TestI3.class })
public class TestInteroperable {

}
