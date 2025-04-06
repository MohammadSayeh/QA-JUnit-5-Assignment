package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;


import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import main.najah.code.UserService;

@Execution(value = ExecutionMode.CONCURRENT)
class UserServiceTest {

	 UserService service = new UserService();

	    @Test
	    @DisplayName("Valid Email Should Return True")
	    void testValidEmail() {
	    	try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        assertTrue(service.isValidEmail("mohammad.sayeh@gmail.com"));
	        assertTrue(service.isValidEmail("mohammad.khalldoon@hotmail.com"));
	    }

	    @Test
	    @DisplayName("Invalid Email Should Return False")
	    void testInvalidEmail() {
	    	try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        assertFalse(service.isValidEmail(null));
	        assertFalse(service.isValidEmail("mohammad.sayeh.com"));
	        assertFalse(service.isValidEmail("mohammad@gmailcom"));
	    }

	    @Test
	    @DisplayName("Correct Credentials Should Authenticate")
	    void testValidLogin() {
	        assertTrue(service.authenticate("admin", "1234"));
	    }

	    @Test
	    @DisplayName("Wrong Credentials Should Fail to Authenticate")
	    void testInvalidLogin() {
	        assertFalse(service.authenticate("admin", "1234567"));
	        assertFalse(service.authenticate("user", "1234"));
	    }

	    @ParameterizedTest
	    @CsvSource({
	        "admin, 1234, true",
	        "admin, 1234567, false",
	        "user, 1234, false",
	        "user, pass, false"
	    })
	    @DisplayName("Parameterized Authentication Test")
	    void testAuthenticationParameterized(String username, String password, boolean expected) {
	        assertEquals(expected, service.authenticate(username, password));
	    }

	    @Test
	    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	    @DisplayName("Email Validation Should Be Fast")
	    void testEmailValidationTimeout() {
	        assertTrue(service.isValidEmail("fastcheck@mail.com"));
	    }

}
