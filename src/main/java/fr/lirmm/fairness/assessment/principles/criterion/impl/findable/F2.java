package fr.lirmm.fairness.assessment.principles.criterion.impl.findable;

import java.io.IOException;

import fr.lirmm.fairness.assessment.principles.criterion.question.AbstractCriterionQuestion;
import fr.lirmm.fairness.assessment.principles.criterion.question.Testable;
import fr.lirmm.fairness.assessment.principles.criterion.question.Tester;
import org.json.JSONException;

import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.principles.criterion.AbstractPrincipleCriterion;

public class F2 extends AbstractPrincipleCriterion {

    private static final long serialVersionUID = -3376420498708614002L;

    @Override
    protected void doEvaluation(Ontology ontology) throws JSONException, IOException {

        //Q1
        this.addResult(Tester.doEvaluation(ontology, questions.get(0), new Testable() {
            @Override
            public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
                String[] props = {ontology.getAcronym(), ontology.getName(),ontology.getAlternative() ,
                        ontology.getHiddenLabel(), ontology.getDescription(), ontology.getDocumentation(),
                        ontology.getPullLocation(), ontology.getKeyWords(), ontology.getCoverage(), ontology.getHomePage(),
                        ontology.getExample(), ontology.getPreferredNamespaceUri(),  ontology.getUriRegexPat(),
                        ontology.getExampleIdentifier(), ontology.getPublisher(),
                        ontology.getTranslator(), ontology.getDomain()
                        , ontology.getIsBackwardCompatibleWith(), ontology.getComesFromTheSameDomain() ,
                        ontology.getKnownUsage(), ontology.getAudience(), ontology.getRepository(), ontology.getBugDatabase(),
                        ontology.getMailingList(), ontology.getHasEvaluation()
                };

                this.setScoreLevel(countExistentMetaData(props, question.getPoints().size()), question);
            }
        }));

        //Q2
        this.addResult(Tester.doEvaluation(ontology, questions.get(1), new Testable() {
            @Override
            public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
                String[] props = { // should properties
                        ontology.getMetrics(),
                        ontology.getNumberOfClasses(),
                        ontology.getNumberOfProperties(),
                        ontology.getNumberOfIndividuals(),
                        ontology.getNumberOfAxioms(),
                        ontology.getPreferredNamespacePrefix()
                };

                this.setScoreLevel(countExistentMetaData(props, question.getPoints().size()), question);
            }
        }));

        //Q3
        this.addResult(Tester.doEvaluation(ontology, questions.get(2), new Testable() {
            @Override
            public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
                String[] props = {
                        String.join(";", ontology.getNaturalLanguage()), ontology.getAbstract(), ontology.getAnalytics(),
                        ontology.getPublication(), ontology.getNotes(), ontology.getDepiction(), ontology.getLogo(), ontology.getToDoList(),
                        ontology.getAward(), ontology.getAssociatedMedia(), String.join(";", ontology.getIsIncompatibleWith()),
                        ontology.getHasPart(), ontology.getWorkTranslation(), ontology.getHasDisparateModelling(),
                        String.join(";", ontology.getUsedBy()), ontology.getHasDisjunctionsWith(),
                        ontology.getKeyClasses(),  ontology.getRoots(),ontology.getUI(), ontology.getDataDump(), ontology.getOpenSearchDescription(),
                        ontology.getUriLookupEndpoint(), ontology.getReleased(), ontology.getModificationDate(),
                        ontology.getValid(), ontology.getCreationDate(), ontology.getCuratedOn(),ontology.getType(),
                        ontology.getProperties(), ontology.getClasses(), ontology.getClassesWithMoreThan25Children(),
                        ontology.getClassesWithOneChild(), ontology.getClassesWithNoDefinition(), ontology.getMaxChildCount()
                };

                this.setScoreLevel(countExistentMetaData(props, question.getPoints().size()), question);
            }
        }));

    }


}
