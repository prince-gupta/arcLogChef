package com.arcosoft.arcLogChef.dto;

/**
 * Created by princegupta on 19/11/17.
 */
public class Query {

    String queryString;

    String dishId;

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getDishId() {
        return dishId;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }
}
