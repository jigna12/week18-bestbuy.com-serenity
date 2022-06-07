package com.bestbuy.productinfo;

import com.bestbuy.categoriesinfo.ProductsSteps;
import com.bestbuy.testbase.ProductTestBase;
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
public class ProductCURDtestWithSteps extends ProductTestBase {
    static String name = "Prime" + TestUtils.getRandomValue();
    static String type = "Testing";
    static int price = 40;
    static int shipping = 0;
    static String upc = "039800011513"; // + TestUtils.getRandomValue();
    static String description = "Compatible with select electronic devices; D size; DURALOCK Power Preserve technology; 4-pack";
    static String manufacturer = "Prime Testing";
    static String model = "Selenium" + TestUtils.getRandomValue() + "YZ";
    static String url = "http://www.bestbuy.com/site/duracell-d-batteries-4-pack/185267.p?id=1051384046551&skuId=185267&cmp=RMXCC";
    static String image = "http://img.bbystatic.com/BestBuy_US/images/products/1852/185267_sa.jpg";
    static int productsId;

    @Steps
    ProductsSteps productsSteps;

    @Title("This will create a new product")
    @Test
    public void test001() {
        productsSteps.createProduct(name, type, price, shipping, upc, description, manufacturer, model, url, image).statusCode(201).log().all();
    }

    @Title("Verify if the product was added")
    @Test
    public void test002() {

        HashMap<String, Object> value = productsSteps.getProductInfoByName(name);
        Assert.assertThat(value, hasValue(name));
        productsId = (int) value.get("id");
        System.out.println(productsId);
    }

    @Title("Update the product information and verify the updated information")
    @Test
    public void test003() {
        name = "Tina";
        type = "HardGood";
        price = 10;
        shipping = 20;
        upc = "039800011513";
        description = "Compatible with select electronic devices; D size; DURALOCK Power Preserve technology; 4-pack";
        manufacturer = "Prime Testing";
        model = "Selenium" + TestUtils.getRandomValue() + "YZ";
        url = "http://www.bestbuy.com/site/duracell-d-batteries-4-pack/185267.p?id=1051384046551&skuId=185267&cmp=RMXCC";
        image = "http://img.bbystatic.com/BestBuy_US/images/products/1852/185267_sa.jpg";
        productsId = 9999689;
        productsSteps.updateProduct(productsId,name,type,price,shipping,upc,description,manufacturer,model,url,image).statusCode(200).log().all();

    }

    @Title("Delete the product and verify if the product is deleted!")
    @Test
    public void test004() {
        productsId = 9999689;
        productsSteps.deleteProduct(productsId).statusCode(200).log().status();
        productsSteps.getProductById(productsId) .statusCode(404).log().status();
    }

}


