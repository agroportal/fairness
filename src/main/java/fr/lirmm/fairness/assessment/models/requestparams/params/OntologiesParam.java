package fr.lirmm.fairness.assessment.models.requestparams.params;

import fr.lirmm.fairness.assessment.models.requestparams.ParamTest;
import fr.lirmm.fairness.assessment.models.requestparams.RequestParam;
import fr.lirmm.fairness.assessment.models.requestparams.RequestParamValidator;
import fr.lirmm.fairness.assessment.models.requestparams.tests.RegexMatch;
import fr.lirmm.fairness.assessment.models.requestparams.tests.Present;

import javax.servlet.http.HttpServletRequest;

public class OntologiesParam extends RequestParam {

    public OntologiesParam(HttpServletRequest request) {
        super("ontologies" ,request);
    }

    @Override
    protected boolean validate() {
        try {
            this.value = RequestParamValidator.getParam(request , this.key ,
                    new ParamTest[]{new Present() ,
                            new RegexMatch("^[-\\w\\s]+(?:,[-\\w\\s]*)*$" , "parameter is either an acronym on an ontology or a list of acronyms separated by a coma or all")});
            return true;
        } catch (Exception e) {
            this.errorMessage = e.getMessage();
            return false;
        }
    }
}
