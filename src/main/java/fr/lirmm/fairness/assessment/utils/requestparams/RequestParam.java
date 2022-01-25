package fr.lirmm.fairness.assessment.utils.requestparams;

import javax.servlet.http.HttpServletRequest;

public abstract class RequestParam {
    protected String key;
    protected String value;
    protected String errorMessage;

    protected HttpServletRequest request;

    public RequestParam(String key , HttpServletRequest request) {
        this.key = key;
        this.request = request;
        this.value = valueRequest(request);
    }


    public String getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    protected abstract boolean validate();

    public boolean isValid(){
        return this.validate();
    }

    public  String get() throws Exception {
        if (this.isValid()) {
            return  getValue();
        } else {
            throw new Exception(getErrorMessage());
        }
    }

    public String valueRequest(HttpServletRequest request){
        return request.getParameter(this.key);
    }
}
