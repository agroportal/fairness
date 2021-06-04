package fr.lirmm.fairness.assessment.principles.impl.interoperable;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

import org.json.JSONException;

import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.principles.impl.AbstractPrincipleCriterion;

public class I1 extends AbstractPrincipleCriterion {

	private static final long serialVersionUID = -7037423784770664158L;

	@Override
	protected void doEvaluation(Ontology ontology) throws JSONException, IOException, MalformedURLException, SocketTimeoutException {
		
		String ontoLang = ontology.getHasOntoLang();
		String ontoSyntax = ontology.getHasOntoSyntax();
		String formalLevel = ontology.getHasFormalLevel();
		String hasFormat = ontology.getHasFormat();
		String isFormatOf = ontology.getIsFormatOf();
		try {

			// Q1: What is the representation language used for (metadata)data? Note that
			// I1Q1Points is set to 0 in the configuration file because the attributed point
			// value is not fix and depends on the nature of the ontology.
			// Q2: Is the representation language used a W3C Recommendation?
			switch (ontoLang) {
			case "OWL":
				this.addResult(0, 20, "Ontology and ontology metadata are in OWL");
				this.addResult(1, this.questionsPoints.get(1),
						"Ontology and ontology metadata are represented in a W3C language");
				break;

			case "OBO":

				this.addResult(0, 14, "Ontology and ontology metadata are in OBO");
				this.addResult(1, this.questionsPoints.get(1),
						"Ontology and ontology metadata are represented in a W3C language");
				break;

			case "RDFS":
				this.addResult(0, 16, "Ontology and ontology metadata are in RDFS");
				this.addResult(1, this.questionsPoints.get(1),
						"Ontology and ontology metadata are represented in a W3C language");
				break;

			case "SKOS":
				this.addResult(0, 18, "Ontology and ontology metadata are in SKOS");
				this.addResult(1, this.questionsPoints.get(1),
						"Ontology and ontology metadata are represented in a W3C language");
				break;

			case "CSV":
				this.addResult(0, 11, "Ontology and ontology metadata are in CSV");
				this.addResult(1, this.questionsPoints.get(1),
						"Ontology and ontology metadata are not in a W3C language");
				break;

			case "XML":
				this.addResult(0, 12, "Ontology and ontology metadata are in XML");
				this.addResult(1, this.questionsPoints.get(1),
						"Ontology and ontology metadata are represented in a W3C language");
				break;

			case "PDF":
				this.addResult(0, 5, "Ontology and ontology metadata are in PDF");
				this.addResult(1, this.questionsPoints.get(1),
						"Ontology and ontology metadata are represented in a W3C language");
				break;

			case "TXT":
				this.addResult(0, 5, "Ontology and ontology metadata are in TXT");
				this.addResult(1, this.questionsPoints.get(1),
						"Ontology and ontology metadata are represented in a W3C language");
				break;

			default:
				this.addResult(0, this.questionsPoints.get(0),
						"Ontology and ontology metadata are not in a formal, accessible, shared and broadly applicable language");
				this.addResult(1, this.questionsPoints.get(1),
						"Ontology and ontology metadata are represented in a W3C language");
			}

			// Q3: Is the syntax of the ontology informed?
			if (!ontoSyntax.isEmpty()) {
				this.addResult(2, this.questionsPoints.get(2), "Ontology syntax is informed");
			} else {
				this.addResult(2, 0, "Ontology syntax is not informed");
			}

			// Q4: Is the formality level of the ontology asserted by the author?
			if (!formalLevel.isEmpty()) {
				this.addResult(3, this.questionsPoints.get(3), "Ontology formality level is informed");
			} else {
				this.addResult(3, 0, "Ontology formality level is not informed");
			}

			// Q5: Is the availability of other formats informed?
			if (((!hasFormat.isEmpty())&&(!hasFormat.contains("null"))) || ((!isFormatOf.isEmpty())&& (!isFormatOf.contains("null")))) {
				this.addResult(4, this.questionsPoints.get(4),
						"The availability of other ontology formats is informed");
			} else {
				this.addResult(4, 0, "No information about other ontology formats ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
