package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import main.najah.code.Product;

class ProductTest {

    @BeforeAll
    static void setupAll() {
        System.out.println(">> Starting ProductTest class...");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println(">> Finished all ProductTest cases.");
    }

    @BeforeEach
    void setup() {
        System.out.println("--> Starting a new test...");
    }

    @AfterEach
    void tearDown() {
        System.out.println("--> Test finished.\n");
    }

    @Test
    @DisplayName("Valid Product Creation and Final Price Check")
    void testValidProductCreation() {
        Product p = new Product("Laptop", 1000);
        p.applyDiscount(10); 

        assertEquals("Laptop", p.getName());
        assertEquals(1000, p.getPrice());
        assertEquals(10, p.getDiscount());
        assertEquals(900, p.getFinalPrice());
    }

    @Test
    @DisplayName("Invalid Price Should Throw Exception")
    void testInvalidPrice() {
        assertThrows(IllegalArgumentException.class, () -> new Product("Phone", -100));
    }

    @Test
    @DisplayName("Invalid Discount Should Throw Exception")
    void testInvalidDiscount() {
        Product p = new Product("TV", 500);
        assertThrows(IllegalArgumentException.class, () -> p.applyDiscount(-5));
        assertThrows(IllegalArgumentException.class, () -> p.applyDiscount(60));
    }

    @ParameterizedTest
    @CsvSource({
        "100, 0, 100",
        "200, 10, 180",
        "300, 50, 150"
    })
    @DisplayName("Parameterized Test for Final Price Calculation")
    void testFinalPriceCalculation(double price, double discount, double expectedFinalPrice) {
        Product p = new Product("Item", price);
        p.applyDiscount(discount);
        assertEquals(expectedFinalPrice, p.getFinalPrice()); 
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    @DisplayName("Timeout Test - Price Calculation Should Be Fast")
    void testFinalPriceTimeout() {
        Product p = new Product("Watch", 250);
        p.applyDiscount(20);
        double result = p.getFinalPrice();
        assertEquals(200, result);
    }
}
