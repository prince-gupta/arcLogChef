package com.arcosoft.arcLogChef.resources.chef;

import com.arcosoft.arcLogChef.dto.ActionResult;
import com.arcosoft.arcLogChef.dto.ChefMetrics;
import com.arcosoft.arcLogChef.services.chef.Management;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by princegupta on 16/11/17.
 */
@RestController
@RequestMapping(value = "/info")
public class ChefInfoResource {

    @Autowired
    Management management;

    @Autowired
    ActionResult result;

    @RequestMapping(value = "/metrics", method = RequestMethod.GET)
    public ActionResult getMetrics(){
        List<ChefMetrics> metrics = new ArrayList<>();
        management.populateChefMetrics(metrics);
        result.addResult("metrics", metrics);
        return result;
    }
}
