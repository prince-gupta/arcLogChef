package com.arcosoft.arcLogChef.services.dish;

import com.arcosoft.arcLogChef.dto.DishInfo;
import com.arcosoft.arcLogChef.dto.GenericResponse;
import com.arcosoft.arcLogChef.dto.Query;
import com.arcosoft.arcLogChef.dto.QueryResponse;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by princegupta on 15/11/17.
 */
public interface DishService {

    TreeSet<DishInfo> getDishsInfo() throws InterruptedException;

    DishInfo getDishInfo(String id) throws InterruptedException;

    String shutdownDish(String id);

    Set<QueryResponse> queryDishs(Query query) throws InterruptedException;

    GenericResponse reIndex(String dishId);
}
