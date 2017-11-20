package com.arcosoft.arcLogChef.services.chef.impls;

import com.arcosoft.arcLogChef.dto.ChefMetrics;
import com.arcosoft.arcLogChef.services.chef.Management;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.EnvironmentEndpoint;
import org.springframework.boot.actuate.endpoint.InfoEndpoint;
import org.springframework.boot.actuate.endpoint.MetricsEndpoint;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by princegupta on 16/11/17.
 */
@Service
public class ChefManagement implements Management {

    @Autowired
    MetricsEndpoint metricsEndpoint;

    @Autowired
    InfoEndpoint infoEndpoint;

    @Autowired
    EnvironmentEndpoint environmentEndpoint;

    @Override
    public void populateChefMetrics(List<ChefMetrics> chefMetrics) {
        metricsEndpoint.invoke();
        populateOS(chefMetrics);
    }

    public void populateChefEnv(List<ChefMetrics> chefMetrics) {
        populateOS(chefMetrics);
    }

    public void populateOS(List<ChefMetrics> chefMetrics){
        ChefMetrics metric = new ChefMetrics();
        metric.setCatogery("Operating System");
        metric.setValue(environmentEndpoint.getResolver().getProperty("os.version"));
        metric.setUnit(environmentEndpoint.getResolver().getProperty("os.name"));
        metric.setIcon("fa fa-apple");
        chefMetrics.add(metric);
    }
}
