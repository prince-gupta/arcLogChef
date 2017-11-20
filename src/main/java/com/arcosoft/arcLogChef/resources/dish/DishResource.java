package com.arcosoft.arcLogChef.resources.dish;

import com.arcosoft.arcLogChef.dto.DishInfo;
import com.arcosoft.arcLogChef.dto.GenericResponse;
import com.arcosoft.arcLogChef.dto.Query;
import com.arcosoft.arcLogChef.dto.QueryResponse;
import com.arcosoft.arcLogChef.services.dish.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by princegupta on 15/11/17.
 */
@RestController
@RequestMapping(value = "/dishResource/")
public class DishResource {

    @Autowired
    DishService dishService;

    @RequestMapping(value = "/dishsInfo" , method = RequestMethod.GET)
    public TreeSet<DishInfo> getDishsInfo() throws InterruptedException {
        return dishService.getDishsInfo();
    }

    @RequestMapping(value = "/dish/{id}", method = RequestMethod.GET)
    public DishInfo getDishInfo(@PathVariable("id") String id) throws InterruptedException {
        return dishService.getDishInfo(id);
    }

    @RequestMapping(value="/stop/{id}", method = RequestMethod.GET)
    public String shutdown(@PathVariable("id") String id){
        return dishService.shutdownDish(id);
    }

    @RequestMapping(value="/query", method = RequestMethod.POST)
    public Set<QueryResponse> queryDishs(@RequestBody Query query) throws InterruptedException {
        return dishService.queryDishs(query);
    }

    @RequestMapping(value="/reIndex/{id}", method = RequestMethod.GET)
    public GenericResponse reIndex(@PathVariable("id") String dishId){
        return dishService.reIndex(dishId);
    }
}
