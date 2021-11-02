package fr.lirmm.fairness.assessment.principles.criterion.impl.accessible;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

import org.json.JSONException;

import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.principles.criterion.AbstractPrincipleCriterion;

public class A12 extends AbstractPrincipleCriterion {

	private static final long serialVersionUID = -1055045295187387264L;

	@Override
	protected void doEvaluation(Ontology ontology) throws JSONException, IOException {


		// Q1: Is the ontology accessible through a protocol that supports authentication and authorization?
		this.setDefaultSuccess(0);
		// Q2: Are an ontology and its metadata through a protocol that supports authentification and authorization?
		this.setDefaultSuccess(1);



		/* OLD Q1 and Q2
		try {
			List<String> includedInDataCatalog = ontology.getIncludedInDataCatalog();Œ
			double score = 0; // AgroPortal ontologies are automatically registered in
			// FAIRsharing and Agrisemantics Map of Data Standards.
			double points=0;
			int nbRepo=0;

			// Q1: Does the protocol used to resolve URI and GUID support authentication and
			// authorization?
			this.addResult(0, this.questionsPoints.get(0),
						"Default value, AP uses a protocol that supports authentification and authorization.");

			// Q2: Is the ontology accessible in an ontology repository or library that
			// support authentication and authorization (such as NCBO BioPortal,
			// AgroPortal)?
			nbRepo=6;
			points= this.questionsPoints.get(1)/nbRepo;
			
			if (includedInDataCatalog.isEmpty()) {
				this.addResult(1, 0,
						"Ontology and ontology metadata are accessible not in an ontology repository that supports authentification and authorization");
			} else {
				int i = 0;
				while ((i < (includedInDataCatalog.size())) && (score < this.questionsPoints.get(1))) 
				{
					if (includedInDataCatalog.get(i).contains("obofoundry.org")) {
						score += points;
						
					} else if (includedInDataCatalog.get(i).contains("ontobee.org")) {
						score += points;
					}

					else if (includedInDataCatalog.get(i).contains("vest.agrisemantics.org")) {
						score += points;
					}

					else if (includedInDataCatalog.get(i).contains("ontohub.org")) {
						score += points;
					}

					else if (includedInDataCatalog.get(i).contains("fairsharing.org")) {
						score += points;
					}

					else if (includedInDataCatalog.get(i).contains("biosharing.org")) {
						score += points;
					}
					i++;
				}
				this.addResult(1, score,
						"Ontology and ontology metadata are accessible in an ontology repository that supports authentification and authorization");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
	}
	
}
