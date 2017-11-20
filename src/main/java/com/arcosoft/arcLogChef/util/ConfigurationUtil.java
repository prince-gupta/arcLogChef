package com.arcosoft.arcLogChef.util;

import com.arcosoft.arcLogChef.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by princegupta on 18/11/17.
 */
@Component
public class ConfigurationUtil {

    @Autowired
    PropertyConfigurator propertyConfigurator;

    public String getDishId(String url){
        Optional<String> optional = propertyConfigurator
                .getHost()
                .entrySet()
                .stream()
                .filter(host -> host.getValue().equalsIgnoreCase(url))
                .findFirst()
                .map(entity -> entity.getKey());
        return optional.get();
    }

    public String getHostName(String url){
        return url.substring(url.indexOf("//")+2, url.lastIndexOf(":"));
    }
}
