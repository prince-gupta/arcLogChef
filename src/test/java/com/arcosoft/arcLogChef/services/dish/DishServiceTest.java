package com.arcosoft.arcLogChef.services.dish;

import com.arcosoft.arcLogChef.PropertyConfigurator;
import com.arcosoft.arcLogChef.dto.DishInfo;
import com.arcosoft.arcLogChef.exceptions.ChefException;
import com.arcosoft.arcLogChef.services.dish.impls.DishInfoServiceImpl;
import com.arcosoft.arcLogChef.services.http.HttpUtility;
import com.arcosoft.arcLogChef.util.ConfigurationUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by princegupta on 05/12/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DishServiceTest {

    @Mock
    HttpUtility<DishInfo> dishInfoHttpUtility;

    @Mock
    HttpUtility<String> stringHttpUtility;


    @Mock
    PropertyConfigurator configurator;

    @Mock
    ConfigurationUtil configurationUtil;

    @InjectMocks
    DishInfoServiceImpl dishService;

    @Test
    public void testGetDishsInfo() throws ChefException {

        when(configurator.getHost()).thenReturn(getHosts());
        when(dishInfoHttpUtility.get(anyString(), anyObject())).thenReturn(getDishInfo());
        TreeSet<DishInfo> result = dishService.getDishsInfo();

        Assert.assertEquals(result.size(), 1);
        Assert.assertEquals(true, result instanceof TreeSet);
    }

    @Test
    public void testGetDishInfo() throws InterruptedException {
        when(configurator.getHost()).thenReturn(getHosts());
        when(dishInfoHttpUtility.get(anyString(), anyObject())).thenReturn(getDishInfo());
        DishInfo dishInfo = dishService.getDishInfo("DISH-1");
        Assert.assertEquals(dishInfo.getName(), getDishInfo().getName());
    }

    private Map<String, String> getHosts(){
        Map<String, String> hosts = new HashMap<>();
        hosts.put("Dish1", "testURL");
        return hosts;
    }

    private DishInfo getDishInfo(){
        DishInfo dishInfo = new DishInfo();
        dishInfo.setHost("testURL");
        dishInfo.setName("DISH1");
        dishInfo.setRunning(false);
        dishInfo.setId("DISH-1");
        dishInfo.setDataPath("/tmp");
        return dishInfo;
    }

}
