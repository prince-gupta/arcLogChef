package com.arcosoft.arcLogChef.services.dish;

import com.arcosoft.arcLogChef.dto.DishInfo;
import com.arcosoft.arcLogChef.dto.GenericResponse;
import com.arcosoft.arcLogChef.dto.Query;
import com.arcosoft.arcLogChef.dto.QueryResponse;
import com.arcosoft.arcLogChef.exceptions.ChefException;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by princegupta on 15/11/17.
 */
public interface DishService {

    TreeSet<DishInfo> getDishsInfo() throws ChefException;

    DishInfo getDishInfo(String id);

    String shutdownDish(String id);

    Set<QueryResponse> queryDishs(Query query) throws ChefException;

    GenericResponse reIndex(String dishId);
}
