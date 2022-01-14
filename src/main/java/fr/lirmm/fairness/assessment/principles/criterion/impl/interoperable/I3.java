package fr.lirmm.fairness.assessment.principles.criterion.impl.interoperable;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import fr.lirmm.fairness.assessment.principles.criterion.question.AbstractCriterionQuestion;
import fr.lirmm.fairness.assessment.principles.criterion.question.Testable;
import fr.lirmm.fairness.assessment.principles.criterion.question.Tester;
import fr.lirmm.fairness.assessment.principles.criterion.question.tests.URLValidTest;
import org.json.JSONException;

import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.principles.criterion.AbstractPrincipleCriterion;

public class I3 extends AbstractPrincipleCriterion {

	private static final long serialVersionUID = -70784125403909250L;

	@Override
	protected void doEvaluation(Ontology ontology) throws JSONException, IOException {

			// Q1: Does an ontology provide qualified cross-references to external resources/databases?
			this.setNotResolvable(0);

			// Q2: Does an ontology provide cross-references to external resources?
			this.setNotResolvable(1);

           //Q3: Does the ontology use a valid URIs to encode some metadata values?

			this.addResult(Tester.doEvaluation(ontology, questions.get(2), new Testable() {
				@Override
				public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
					List<?>[] propertiesToTest =  new List[]{
							ontology.getUseImports(),
							Collections.singletonList(ontology.getHasPriorVersion()),
							Collections.singletonList(ontology.getIsBackwardCompatibleWith()),
							ontology.getIsIncompatibleWith(),
							Collections.singletonList(ontology.getViewOf()),
							Collections.singletonList(ontology.getHasPart()),
							Collections.singletonList(ontology.getSubmissions()),
							Collections.singletonList(ontology.getIsFormatOf()),
							Collections.singletonList(ontology.getHasFormat()),
							ontology.getOntologyRelatedTo(),
							Collections.singletonList(ontology.getExplanationEvolution()),
							Collections.singletonList(ontology.getWorkTranslation()),
							Collections.singletonList(ontology.getTranslationOfWork()),
							Collections.singletonList(ontology.getComesFromTheSameDomain()),
							ontology.getSimilarTo(),
							ontology.getIsAlignedTo(),
							Collections.singletonList(ontology.getHasDisparateModelling()),
							ontology.getUsedBy(),
							Collections.singletonList(ontology.getGeneralizes()),
							Collections.singletonList(ontology.getHasDisjunctionsWith()),
							Collections.singletonList(ontology.getHasFormalLevel()),
							Collections.singletonList(ontology.getHasLicense()),
							Collections.singletonList(ontology.getHasOntoSyntax()),
							Collections.singletonList(ontology.getHasDomain()),
							ontology.getNaturalLanguage(),
							Collections.singletonList(ontology.getHasOntoLang()),
							Collections.singletonList(ontology.getPrefLabelProperty()),
							Collections.singletonList(ontology.getDefinitionProperty()),
							Collections.singletonList(ontology.getSynonymProperty()),
							Collections.singletonList(ontology.getAuthorProperty()),
							Collections.singletonList(ontology.getHierarchyProperty()),
							Collections.singletonList(ontology.getObsoleteProperty()),

					};
					int maxLevels = question.getPoints().size()-1;
					int count = 0;
					List<?> property = null;
					for (int i = 0 ; i < propertiesToTest.length && count < maxLevels ; i++ ) {
						property = propertiesToTest[i];
						boolean allAreValuesAreValid = property.stream()
								.allMatch(x -> URLValidTest.isValid(x.toString()));

						if(allAreValuesAreValid && !property.isEmpty()){
							count++;
						}
					}
					this.setScoreLevel(count , question);
				}
			}));


	}
	
}
