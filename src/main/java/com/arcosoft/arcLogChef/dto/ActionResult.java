package com.arcosoft.arcLogChef.dto;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by princegupta on 06/12/17.
 */
@Component
public class ActionResult {

    private int code;
    private boolean isError ;
    private Map result = new HashMap();

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public Map getResult() {
        return result;
    }

    public void addResult(String key, Object result) {
        this.result.put(key,result);
    }
}
