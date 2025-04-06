package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import main.najah.code.Calculator;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CalculatorTest {

	private Calculator calculator = new Calculator();

    @Order(1)
    @Test
    @DisplayName("Addition - Valid Inputs")
    void testAdditionValid() {
        assertEquals(200, calculator.add(100, 100), "100 + 100 should = 200");
        assertEquals(13, calculator.add(15, -5, 3), "15 + -5 + 3 should = 13");
    }
   
    @Order(2)
    @Test
    @DisplayName("Addition - Invalid Input (Null)")
    void testAdditionInvalid() {
        assertThrows(NullPointerException.class, () -> calculator.add(null));
    }
   
   @Order(4)
    @Test
    @DisplayName("Division - Valid Inputs")
    void testDivisionValid() {
        assertEquals(10, calculator.divide(30, 3), "30 / 3 should = 10");
    }
   
   @Order(5)
    @Test
    @DisplayName("Division - Invalid Input (Zero Division)")
    void testDivisionInvalid() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));
    }

    @Order(8)
    @ParameterizedTest
    @CsvSource({
        "0, 1",
        "1, 1",
        "5, 120"
    })
    
    @DisplayName("Factorial - Parameterized Test")
    void testFactorialParameterized(int input, int expected) {
        assertEquals(expected, calculator.factorial(input), "Factorial calculation failed");
    }
   
    @Order(6)
    @Test
    @DisplayName("Factorial - Invalid Input (Negative Number)")
    void testFactorialInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> calculator.factorial(-5));
    }
   

    @Order(7)
    @Timeout(value=500,unit=TimeUnit.MILLISECONDS)
    @Test
    @DisplayName("Factorial - Timeout Test")
    void testFactorialTimeout() {
    	calculator.factorial(3000000);
    }

   @Order(9)
   @Test
   @DisplayName("Factorial - Intentionally Failing Test")
   @Disabled("The test fails because the factorial of 4 is not 20 , if you want to fix that change the assertEquals first parameter (expected) to 24  ")
   void testFactorialIntentionallyFailing() {
	   assertEquals(20, calculator.factorial(4));//wrong should be 24
   }

    @Order(3)
    @Test
    @DisplayName("Addition - Multiple Assertions")
    void testMultipleAssertions() {
        int firstResult = calculator.add(0, 1, -1);
        int secondResult = calculator.add(-9, -10, -11);
        int thirdResult = calculator.add(5, 50, 3);
        
        assertEquals(0, firstResult, "0 + 1 + -1 should = 0");
        assertEquals(-30, secondResult, "-9 + -10 + -11 should = -30");
        assertEquals(58, thirdResult, "5 + 50 + 3 should = 58");
        
    }
}
