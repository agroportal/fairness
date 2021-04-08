package fr.lirmm.fairness.assessment;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestAccessible.class, TestFindable.class, TestInteroperable.class, TestReusable.class })
public class TestFair {

}
