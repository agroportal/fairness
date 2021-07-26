package fr.lirmm.fairness.assessment.principles.criterion.question.tests;

interface Test<T> {
    boolean test(T... element);
}
