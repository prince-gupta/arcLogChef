package com.arcosoft.arcLogChef.dto;

import java.util.List;

/**
 * Created by princegupta on 19/11/17.
 */
public class QueryResponse{

    private String dishName;

    private List<String> result;

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }

}
