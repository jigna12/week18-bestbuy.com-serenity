package com.bestbuy.storesinfo;

import com.bestbuy.categoriesinfo.StoresSteps;
import com.bestbuy.testbase.StoresTestBase;
import com.bestbuy.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

//@RunWith(SerenityRunner.class)
public class StoresCURDtestWithSteps extends StoresTestBase{
    static String name = "Hemani";
    static String type = "Textile";
    static String address = TestUtils.getRandomValue() + ", Harborn Close";
    static String address2 = "SouthOxhey";
    static String city = "Watford";
    static String state = "Watford";
    static String zip = "WD29PY";
    static Double lat = 2.5;
    static Double lng = 5.66;
    static String hours = "22";
    static int storeId;

    @Steps
    StoresSteps storesSteps;

    @Title("This will create a new Store")
    @Test
    public void test001() {
        storesSteps.createStore(name, type, address, address2, city, state, zip, lat, lng, hours).log().all().statusCode(201);
    }

    @Title("Verify if the store was added to the application")
    @Test
    public void test002() {
        HashMap<String, Object> storesMap = storesSteps.getProductInfoByName(name);
        Assert.assertThat(storesMap, hasValue(name));
        storeId = (int) storesMap.get("id");
        System.out.println(storeId);
    }

    @Title("Update the store information and verify the updated information")
    @Test
    public void test003() {
        name = "PearRoad";
        type = "SuperStore";
        address = TestUtils.getRandomValue() + ", pear road";
        address2 = "uxbridge";
        city = "London";
        state = "London";
        zip = "1212222";
        lat = 2.5;
        lng = 4.88;
        hours = "12";
        storeId = 8922;
        storesSteps.updatestore(storeId, name, type, address, address2, city, state, zip, lat, lng, hours).statusCode(200).log().all();
    }

    @Title("Delete the stores and verify if the stores is deleted!")
    @Test
    public void test004() {
        storeId = 8922;
        storesSteps.deleteStore(storeId).statusCode(200).log().status();
        storesSteps.getProductById(storeId).statusCode(404).log().status();
    }
}


