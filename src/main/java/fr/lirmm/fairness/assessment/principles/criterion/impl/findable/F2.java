package fr.lirmm.fairness.assessment.principles.criterion.impl.findable;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

import org.json.JSONException;

import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.principles.criterion.AbstractPrincipleCriterion;
import fr.lirmm.fairness.assessment.utils.OntologyRestApi;

public class F2 extends AbstractPrincipleCriterion {
	
	private static final long serialVersionUID = -3376420498708614002L;

	private String[] metadataFlags = new String[] {
		"MUST",
		"SHOULD",
		"OPTIONAL",
		"NO_MAPPING" 
	};
	private int nbValidProp=0;

	@Override 
	protected void doEvaluation(Ontology ontology) throws JSONException, IOException, MalformedURLException, SocketTimeoutException {
		try { 
			String[][] allMetadata = this.allMetadataToEvaluate(ontology);
			for(int i = 0 ; i < allMetadata.length; i ++) {
				int score = this.evaluatecheckMetadata(allMetadata[i], i); 
				this.addResult(i, score, String.format("%d %s properties provided", nbValidProp, metadataFlags[i]));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private int evaluatecheckMetadata(String[] metadata, int importanceLevel) {
		nbValidProp=0; 
		int credits = this.questionsPoints.get(importanceLevel); 
		int score = 0;
		int points= 0; 	
		int i=0; 
		if (metadataFlags[importanceLevel]== "MUST") 
		{points=4;}
		else if (metadataFlags[importanceLevel]== "SHOULD")
		{points=2;}
		else if ((metadataFlags[importanceLevel]== "OPTIONAL") || (metadataFlags[importanceLevel]== "NO_MAPPING"))
		{points=1;}	
		
		while((i<metadata.length)&&  (score<=credits-points))
		{ 
			if (OntologyRestApi.isValidMetadatumValue(metadata[i])) {
				score += points; 
				nbValidProp++;
			}	
			i++;
		}	
		
		return score; 
	}
	
	private String[][] allMetadataToEvaluate(Ontology ontology) {
		return new String[][] {
			new String[] {
				ontology.getName(),
				ontology.getAlternative(),
				ontology.getHiddenLabel(),
				ontology.getId(),
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
			new String[] {
				ontology.getMetrics(), 
				ontology.getNumberOfClasses(),
				ontology.getNumberOfProperties(),
				ontology.getNumberOfIndividuals(),
				ontology.getNumberOfAxioms(), 
			},
			new String[] {
				ontology.getPreferredNamespacePrefix()
			},
			new String[] {
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
