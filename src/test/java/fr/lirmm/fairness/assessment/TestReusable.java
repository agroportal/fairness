package fr.lirmm.fairness.assessment;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fr.lirmm.fairness.assessment.reusable.TestR1;
import fr.lirmm.fairness.assessment.reusable.TestR11;
import fr.lirmm.fairness.assessment.reusable.TestR12;
import fr.lirmm.fairness.assessment.reusable.TestR13;

@RunWith(Suite.class)
@SuiteClasses({ TestR1.class, TestR11.class, TestR12.class, TestR13.class })
public class TestReusable {

}
