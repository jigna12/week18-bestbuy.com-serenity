package com.bestbuy.servicesinfo;

import com.bestbuy.categoriesinfo.ServicesSteps;
import com.bestbuy.testbase.ServicesTestBase;
import com.bestbuy.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;
@RunWith(SerenityRunner.class)
public class ServicesCURDtestWithSteps extends ServicesTestBase {
    static String name = "PrimeTesting" + TestUtils.getRandomValue();
    static int servicesId;

    @Steps
    ServicesSteps servicesSteps;

    @Title("This will create a new service")
    @Test
    public void test001() {
        servicesSteps.createService(name).statusCode(201).log().all();
    }

    @Title("Verify if the service was added")
    @Test
    public void test002() {

        HashMap<String, Object> value = servicesSteps.getServiceInfoByName(name);
        Assert.assertThat(value, hasValue(name));
        servicesId = (int) value.get("id");
        System.out.println(servicesId);
    }

    @Title("Update the service information and verify the updated information")
    @Test
    public void test003() {
        name = "Rima";
        servicesId = 26;
        servicesSteps.updateService(name, servicesId).statusCode(200).log().all();

    }

    @Title("Delete the service and verify if the service is deleted!")
    @Test
    public void test004() {
        servicesId = 26;
        servicesSteps.deleteService(servicesId).statusCode(200).log().status();
        servicesSteps.getServiceByName(servicesId).statusCode(404).log().status();

    }
}
