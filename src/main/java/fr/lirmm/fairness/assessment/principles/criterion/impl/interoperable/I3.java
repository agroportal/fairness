package fr.lirmm.fairness.assessment.principles.criterion.impl.interoperable;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

import org.json.JSONException;

import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.principles.criterion.AbstractPrincipleCriterion;

public class I3 extends AbstractPrincipleCriterion {

	private static final long serialVersionUID = -70784125403909250L;

	@Override
	protected void doEvaluation(Ontology ontology) throws JSONException, IOException, MalformedURLException, SocketTimeoutException {

		try {
			double points = this.questionsPoints.get(3)/2;
			double score=0;
			
			// Q1: Non assessable evaluation of qualified references?
			this.addResult(0, 0,
					String.format("Default value 0, this indicator can not be evaluated in %s",
							ontology.getPortalInstance().getName()));

			// Q2: Does an ontology provide cross-references to external resources?
			this.addResult(1, 0,
					String.format("Default value 0, this indicator can not be evaluated in %s",
							ontology.getPortalInstance().getName()));

			// Q3: If yes, are those cross-references well represented and to unambigous
			// entities?
			this.addResult(2, 0,
					String.format("Default value 0, this indicator can not be evaluated in %s",
							ontology.getPortalInstance().getName()));

			// Q4: Does an ontology provide information about projects using or
			// organization endorsing?
			
           if ((!ontology.getendorsedBy().isEmpty())) 
              {
        	   score+=points; 
              }
           if (!ontology.getProjects().isEmpty())
           {
               score+=points; 
           }
           
           if ((ontology.getProjects().isEmpty()) && (ontology.getendorsedBy().isEmpty()))
           {
        	   this.addResult(3, 0,
    					String.format("Ontology does not provide infromation about projects and endorsing organizations in %s",
    							ontology.getPortalInstance().getName()));
           }
           
           else if ((!ontology.getendorsedBy().isEmpty()))
        		   {
           this.addResult(3, score,
					String.format("Ontology provides infromation about endorsing organization %s",
							ontology.getPortalInstance().getName()));
        		   }
           else if ((!ontology.getProjects().isEmpty())) {
        	   this.addResult(3, score,
   					String.format("Ontology provides infromation about projects %s",
   							ontology.getPortalInstance().getName()));    	   
           }
               
			// TODO Q5: Is an ontology using resolvable URIs or GUIDs to encode some
			// metadata values?	
           
           this.addResult(4, this.questionsPoints.get(4),
					String.format(ontology.getPortalInstance().getName() + "'s ontologies uses URIS and GUIDs to encode metadata values in %s",
							ontology.getPortalInstance().getName()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
