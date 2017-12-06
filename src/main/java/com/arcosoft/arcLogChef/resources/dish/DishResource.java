package com.arcosoft.arcLogChef.resources.dish;

import com.arcosoft.arcLogChef.dto.ActionResult;
import com.arcosoft.arcLogChef.dto.Query;
import com.arcosoft.arcLogChef.exceptions.ChefException;
import com.arcosoft.arcLogChef.services.dish.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by princegupta on 15/11/17.
 */
@RestController
@RequestMapping(value = "/dishResource/")
public class DishResource {

    @Autowired
    DishService dishService;

    @RequestMapping(value = "/dishsInfo" , method = RequestMethod.GET)
    public ActionResult getDishsInfo() throws ChefException {
        ActionResult actionResult = new ActionResult();
        actionResult.addResult("dishsInfo",dishService.getDishsInfo());
        return actionResult;
    }

    @RequestMapping(value = "/dish/{id}", method = RequestMethod.GET)
    public ActionResult getDishInfo(@PathVariable("id") String id) throws InterruptedException {
        ActionResult actionResult = new ActionResult();
        actionResult.addResult("dish-"+id, dishService.getDishInfo(id));
        return actionResult;
    }

    @RequestMapping(value="/stop/{id}", method = RequestMethod.GET)
    public ActionResult shutdown(@PathVariable("id") String id){
        ActionResult actionResult = new ActionResult();
        actionResult.addResult("stop-"+id, dishService.shutdownDish(id));
        return actionResult;
    }

    @RequestMapping(value="/query", method = RequestMethod.POST)
    public ActionResult queryDishs(@RequestBody Query query) throws ChefException {
        ActionResult actionResult = new ActionResult();
        actionResult.addResult("query",dishService.queryDishs(query));
        return actionResult;
    }

    @RequestMapping(value="/reIndex/{id}", method = RequestMethod.GET)
    public ActionResult reIndex(@PathVariable("id") String dishId){
        ActionResult actionResult = new ActionResult();
        actionResult.addResult("reIndex-"+dishId, dishService.reIndex(dishId));
        return actionResult;
    }
}
