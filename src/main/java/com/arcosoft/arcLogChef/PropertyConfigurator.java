package com.arcosoft.arcLogChef;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by princegupta on 15/11/17.
 */
@ConfigurationProperties(prefix = "config")
@Component
public class PropertyConfigurator {

    private Map<String, String> host;

    public Map<String, String> getHost() {
        return host;
    }

    public void setHost(Map<String, String> host) {
        this.host = host;
    }
}
