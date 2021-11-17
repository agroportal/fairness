package fr.lirmm.fairness.assessment.utils.requestparams.params;

import fr.lirmm.fairness.assessment.utils.requestparams.ParamTest;
import fr.lirmm.fairness.assessment.utils.requestparams.RequestParam;
import fr.lirmm.fairness.assessment.utils.requestparams.RequestParamValidator;
import fr.lirmm.fairness.assessment.utils.requestparams.tests.RegexMatch;
import fr.lirmm.fairness.assessment.utils.requestparams.tests.Present;

import javax.servlet.http.HttpServletRequest;

public class OntologiesParam extends RequestParam {

    public OntologiesParam() {
        super("ontologies");
    }

    @Override
    public boolean validate(HttpServletRequest request) {
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
