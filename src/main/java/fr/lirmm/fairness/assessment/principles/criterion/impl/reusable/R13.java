package fr.lirmm.fairness.assessment.principles.criterion.impl.reusable;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

import org.json.JSONException;

import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.principles.criterion.AbstractPrincipleCriterion;

public class R13 extends AbstractPrincipleCriterion {
	
	private static final long serialVersionUID = 8721990196106759026L;

	private static String[] groupsToCheck = new String[] {"OBO", "", "WHEAT", "CROP", "INRAE"};
	private static String[] oboFoundryOntologiesToCheck = new String[] {"BFO", "CHEBI", "DOID", "GO", "OBI", "PATO", "PO", "PR", "XAO" , "ZFA"};  

	@Override
	protected void doEvaluation(Ontology ontology) throws JSONException, IOException, MalformedURLException, SocketTimeoutException {

		try {			
			final String group = ontology.getGroup();
			for (int i = 0; i < groupsToCheck.length; i++) {
				if (i!=1)
				{
				final String groupToCheck = groupsToCheck[i];
				final boolean groupFound = group.contains(groupToCheck);
				this.addResult(i, groupFound ? this.questionsPoints.get(i) : 0, String.format(
						"Ontology is %sincluded in the %s group/project", groupFound ? "" : "NOT ", groupToCheck));
				}
				else 
				{  
					if (group.contains("OBO"))
				    {
				    boolean oboOnologyFound= false;
					final String acronym= ontology.getAcronym();
				    int j=0;
				    String oboFoundryToCheck=""; 
				    while ((!oboOnologyFound) && (j<oboFoundryOntologiesToCheck.length))    
					{
					oboFoundryToCheck= oboFoundryOntologiesToCheck[j];
					oboOnologyFound= acronym.contains(oboFoundryToCheck);
					j++; 
					}	    
				    this.addResult(1, oboOnologyFound ? this.questionsPoints.get(1) : 0, String.format(
							"Ontology is %s included in the %s OBO foundry", oboOnologyFound ? "" : "NOT ", oboFoundryToCheck));
					}
					else 
					{
					   this.addResult(1, 0.0, "Ontology is not included in the OBO foundry group");
					}
				}
			}	
			
			// Q6: Is the ontology openly and freely available?
			String visibility= ontology.getViewingrestriction();
				if(visibility.contains("public"))
				{
				  this.addResult(5,this.questionsPoints.get(5), "Ontology is openly and freely available");	
				}
				else
				{
			      this.addResult(5, 0, "Ontology is not openly and freely available");
				}
		}
			catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
