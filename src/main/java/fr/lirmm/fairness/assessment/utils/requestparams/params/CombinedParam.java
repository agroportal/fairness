package fr.lirmm.fairness.assessment.utils.requestparams.params;

import fr.lirmm.fairness.assessment.utils.requestparams.RequestParam;

import javax.servlet.http.HttpServletRequest;

public class CombinedParam extends RequestParam{

    public static RequestParam instance;
    public CombinedParam(String key) {
        super(key);
    }

    @Override
    public boolean validate(HttpServletRequest request) {
        this.value = request.getParameter(this.getKey());
        return true;
    }


    public static RequestParam getInstance() {
        if(instance == null)
            instance = new CombinedParam("combined");
        return  instance;
    }

}
