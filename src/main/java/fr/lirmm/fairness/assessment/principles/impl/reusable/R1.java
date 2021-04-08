package fr.lirmm.fairness.assessment.principles.impl.reusable;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

import org.json.JSONException;

import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.principles.impl.AbstractPrincipleCriterion;
import fr.lirmm.fairness.assessment.utils.OntologyRestApi;

public class R1 extends AbstractPrincipleCriterion {
	
	private static final long serialVersionUID = 5277719201473995295L;

	private final static String[] metadataFlags = new String[] { "CLASS DEFINITIONS", "HIERARCHY", "CLASSES", "PROVENANCE" };

	@Override
	protected void doEvaluation(Ontology ontology) throws JSONException, IOException, MalformedURLException, SocketTimeoutException {

		try {
			String[][] allMetadata = allMetadataToEvaluate(ontology);
			for (int i = 0; i < 4; i++) { // 4 questions
				int points= 0, score=0;
				if (allMetadata[i].length > 0) {
					int nbinfo = 0;
					for (String s : allMetadata[i]) {
						points= this.questionsPoints.get(i)/allMetadata[i].length;
						if (OntologyRestApi.isValidMetadatumValue(s)) {
							nbinfo++;
							score+=points; 
						}
						
					}
					this.addResult(i, score,
							String.format("Ontology provides %d information about its %s", nbinfo, metadataFlags[i]));
				}
                   else {
					this.addResult(i, 0, "Ontology does not provide information about all R1 required metadata");
                   }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String[][] allMetadataToEvaluate(Ontology ontology) {
		return new String[][] {
				// Q1: Does the ontology provide information about how classes are defined?
				new String[] {ontology.getClassesWithNoDefinition()},
				// Q2: Does the ontology provide information about its hierarchy?
				new String[] { ontology.getHierarchyProperty(), ontology.getObsoleteParent() },
				// Q3: How much of the ontology classes (or concepts) are defined ?
				new String[] { ontology.getMaxChildCount(), ontology.getAverageChildCount(),
						ontology.getClassesWithOneChild() },
				// Q4: If relevant, how much of the ontology objects provide provenance
				// information (e.g., author, date)?
				new String[] { ontology.getPrefLabelProperty(), ontology.getSynonymProperty(),
						ontology.getDefinitionProperty(), ontology.getAuthorProperty(),
						ontology.getObsoleteProperty() },

		};
	}

}
