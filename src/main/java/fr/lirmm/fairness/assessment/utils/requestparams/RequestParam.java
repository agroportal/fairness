package fr.lirmm.fairness.assessment.utils.requestparams;


import javax.servlet.http.HttpServletRequest;

public abstract class RequestParam {
    protected String key;
    protected String value;
    protected String errorMessage;


    public RequestParam(String key) {
        this.key = key;
    }

    public abstract boolean validate(HttpServletRequest request);

    public String getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }

    public String getErrorMessage() {
        return errorMessage;
    }


    public  String get(HttpServletRequest req) throws Exception {
        if (this.validate(req)) {
            return  getValue();
        } else {
            throw new Exception(getErrorMessage());
        }
    }
}
