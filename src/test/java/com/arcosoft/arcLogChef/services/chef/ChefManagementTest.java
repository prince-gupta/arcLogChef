package com.arcosoft.arcLogChef.services.chef;

import com.arcosoft.arcLogChef.OS;
import com.arcosoft.arcLogChef.dto.ChefMetrics;
import com.arcosoft.arcLogChef.services.chef.impls.ChefManagement;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.actuate.endpoint.EnvironmentEndpoint;
import org.springframework.boot.actuate.endpoint.InfoEndpoint;
import org.springframework.boot.actuate.endpoint.MetricsEndpoint;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


/**
 * Created by princegupta on 04/12/17.
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class ChefManagementTest {

    @Mock
    MetricsEndpoint metricsEndpoint;

    @Mock
    InfoEndpoint infoEndpoint;

    @Mock
    EnvironmentEndpoint environmentEndpoint;

    @InjectMocks
    ChefManagement chefManagement;

    //@Test
    public void populateChefMetricsTest() {
        when(metricsEndpoint.invoke()).thenReturn(null);
        when(environmentEndpoint.getResolver().getProperty("os.version")).thenReturn("test version");
        when(environmentEndpoint.getResolver().getProperty("os.name")).thenReturn(OS.MAC.name());
        List<ChefMetrics> metricss = new ArrayList<>();
        chefManagement.populateChefMetrics(metricss);
        ChefMetrics chefMetrics = metricss.get(0);

        assertEquals(chefMetrics.getCatogery(),"Operating System");
        assertEquals(chefMetrics.getValue(),"test version");
        assertEquals(chefMetrics.getUnit(),OS.MAC.name());
        assertEquals(chefMetrics.getIcon(),OS.MAC.getIcon());
    }
}
