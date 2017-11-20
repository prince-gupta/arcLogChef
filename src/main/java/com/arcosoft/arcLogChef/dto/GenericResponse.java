package com.arcosoft.arcLogChef.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by princegupta on 19/11/17.
 */
public class GenericResponse {

    String status;
    Map data = new HashMap();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map getData() {
        return data;
    }

    public void addData(String key, Object data) {
        this.data.put(key, data);
    }
}
