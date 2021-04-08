package fr.lirmm.fairness.assessment;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fr.lirmm.fairness.assessment.principles.findable.TestF1;
import fr.lirmm.fairness.assessment.principles.findable.TestF2;
import fr.lirmm.fairness.assessment.principles.findable.TestF3;
import fr.lirmm.fairness.assessment.principles.findable.TestF4;

@RunWith(Suite.class)
@SuiteClasses({ TestF1.class, TestF2.class, TestF3.class, TestF4.class })
public class TestFindable {

}
