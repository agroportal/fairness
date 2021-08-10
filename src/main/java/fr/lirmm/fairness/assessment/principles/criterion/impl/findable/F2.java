package fr.lirmm.fairness.assessment.principles.criterion.impl.findable;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.Arrays;

import fr.lirmm.fairness.assessment.principles.criterion.question.tests.MetaDataExistTest;
import fr.lirmm.fairness.assessment.utils.QuestionResult;
import org.json.JSONException;

import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.principles.criterion.AbstractPrincipleCriterion;

public class F2 extends AbstractPrincipleCriterion {
	
	private static final long serialVersionUID = -3376420498708614002L;


	private int nbValidProp=0;

	@Override 
	protected void doEvaluation(Ontology ontology) throws JSONException, IOException {

			String[][] allMetadata = this.allMetadataToEvaluate(ontology);
			for(int i = 0 ; i < allMetadata.length; i ++) {
				this.addResult(this.evaluateCheckMetadata(allMetadata[i], i));
			}
	}
	
	private QuestionResult evaluateCheckMetadata(String[] metadata, int questionIndex) {
		QuestionResult result = this.questions.get(questionIndex).getMaxPoint(Math.max(this.nbValidProp - 1 , 0));
		this.nbValidProp=0;

		for (int j = 0; j < Math.min(metadata.length, this.questions.get(questionIndex).getPoints().size()); j++) {
			if (MetaDataExistTest.isValid(metadata[j])) {
				this.nbValidProp++;
			}
		}

		return  new QuestionResult(result.getScore(), result.getExplanation() , this.questions.get(questionIndex) );
	}
	
	private String[][] allMetadataToEvaluate(Ontology ontology) {
		return new String[][] {
				//Q1
			new String[] { // must properties
				ontology.getName(),
				ontology.getAlternative(),
				ontology.getHiddenLabel(),
				ontology.getIdentifier(),
				ontology.getHasOntoLang(),
				ontology.getDescription(),
				ontology.getHomePage(),
				ontology.getPullLocation(),
				ontology.getKeyWords(),
				ontology.getCoverage(),
				ontology.getPrefNamSpacUri(),
				ontology.getUriRegexPat(),
				ontology.getExpId(),
				ontology.getCreator(),
				ontology.getContributor(),
				ontology.getPublisher(),
				ontology.getCuratedBy(),
				ontology.getTranslator(),
				ontology.getDomain(),
				ontology.getCompatWith(),
				ontology.getSameDomain(),
				ontology.getKnownUsage(),
				ontology.getAudience(),
				ontology.getRepository(),
				ontology.getBugDatabase(),
				ontology.getMailingList(), 
			},
				//Q2
			new String[] { // should properties
				ontology.getMetrics(), 
				ontology.getNumberOfClasses(),
				ontology.getNumberOfProperties(),
				ontology.getNumberOfIndividuals(),
				ontology.getNumberOfAxioms(), 
			},
				//Q3
			new String[] { // optional properties
				ontology.getPreferredNamespacePrefix()
			},
				//Q4
			new String[] { // no mapping
				String.join(";", ontology.getLanguage()),
				ontology.getAbstra(),
				ontology.getPublication(),
				ontology.getNotes(),
				ontology.getDepiction(),
				ontology.getLogo(),
				ontology.getToDoList(),
				ontology.getAward(),
				ontology.getAssociatedMedia(),
				String.join(";", ontology.getIsIncompatibleWith()),
				ontology.getHasPart(),
				ontology.getWorkTranslation(),
				ontology.getHasDisparateModelling(),
				String.join(";", ontology.getUsedBy()),
				ontology.getHasDisjunctionsWith(),
				ontology.getKeyClasses(),
				ontology.getDataDump(),
				ontology.getOpenSearchDescription(),
				ontology.getUriLookupEndpoint(),
				ontology.getReleased(),
				ontology.getModificationDate(),
				ontology.getValid(),
				ontology.getType(),
			}		
		};
	}
}
