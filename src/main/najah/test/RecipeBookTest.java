package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import main.najah.code.Recipe;
import main.najah.code.RecipeBook;
import main.najah.code.RecipeException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RecipeBookTest {
	
	//First test Recipe methods because RecipeBook has an Array of recipes as instance variables.
	
	@Order(1)
	@Test
    @DisplayName("Price Test - Valid")
    public void testValidPrice() throws RecipeException {
        Recipe recipe = new Recipe();
        recipe.setPrice("50");
        assertEquals(50, recipe.getPrice());
    }
	
	@Order(2)
    @Test
    @DisplayName("Price Test - Invalid")
    public void testInvalidPrice() {
        Recipe recipe = new Recipe();
        assertThrows(RecipeException.class, () -> recipe.setPrice("-10"));
        assertThrows(RecipeException.class, () -> recipe.setPrice("five dollars"));
    }
	
	@Order(3)
    @Test
    @DisplayName("Coffee Test Amount - Valid")
    public void testValidAmtCoffee() throws RecipeException {
        Recipe recipe = new Recipe();
        recipe.setAmtCoffee("3");
        assertEquals(3, recipe.getAmtCoffee());
    }
	
	@Order(4)
    @Test
    @DisplayName("Coffee Test Amount - Invalid")
    public void testInvalidAmtCoffee() {
        Recipe recipe = new Recipe();
        assertThrows(RecipeException.class, () -> recipe.setAmtCoffee("-1"));
        assertThrows(RecipeException.class, () -> recipe.setAmtCoffee("one"));
    }
	
	@Order(5)
    @Test
    @DisplayName("Milk Test Amount - Valid")
    public void testValidAmtMilk() throws RecipeException {
        Recipe recipe = new Recipe();
        recipe.setAmtMilk("2");
        assertEquals(2, recipe.getAmtMilk());
    }
	
	@Order(6)
    @Test
    @DisplayName("Milk Test Amount - Invalid")
    public void testInvalidAmtMilk() {
        Recipe recipe = new Recipe();
        assertThrows(RecipeException.class, () -> recipe.setAmtMilk("-2"));
        assertThrows(RecipeException.class, () -> recipe.setAmtMilk("two"));
    }
	
	@Order(7)
    @Test
    @DisplayName("Sugar Test Amount - Valid")
    public void testValidAmtSugar() throws RecipeException {
        Recipe recipe = new Recipe();
        recipe.setAmtSugar("1");
        assertEquals(1, recipe.getAmtSugar());
    }
	
	@Order(8)
    @Test
    @DisplayName("Sugar Test Amount - Invalid")
    public void testInvalidAmtSugar() {
        Recipe recipe = new Recipe();
        assertThrows(RecipeException.class, () -> recipe.setAmtSugar("-5"));
        assertThrows(RecipeException.class, () -> recipe.setAmtSugar("three"));
    }
	
	@Order(9)
    @Test
    @DisplayName("Chocolate Test Amount - Valid")
    public void testValidAmtChocolate() throws RecipeException {
        Recipe recipe = new Recipe();
        recipe.setAmtChocolate("4");
        assertEquals(4, recipe.getAmtChocolate());
    }
	
	@Order(10)
    @Test
    @DisplayName("Chocolate Test Amount - Invalid")
    public void testInvalidAmtChocolate() {
        Recipe recipe = new Recipe();
        assertThrows(RecipeException.class, () -> recipe.setAmtChocolate("-4"));
        assertThrows(RecipeException.class, () -> recipe.setAmtChocolate("choco"));
    }
	
	@Order(11)
    @Test
    @DisplayName("Equals and HashCode Test")
    public void testEqualsAndHashCode() {
        Recipe r1 = new Recipe();
        r1.setName("Latte");

        Recipe r2 = new Recipe();
        r2.setName("Latte");

        Recipe r3 = new Recipe();
        r3.setName("Mocha");

        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
        assertNotEquals(r1, r3);
    }
	//End of Recipe methods general test
	
		
	    @Test
	    @DisplayName("Add Valid Recipe")
	    void testAddRecipe_Valid() {
	        RecipeBook book = new RecipeBook();
	        Recipe recipe = new Recipe();
	        recipe.setName("Espresso");

	        boolean added = book.addRecipe(recipe);

	        assertTrue(added);
	        assertEquals("Espresso", book.getRecipes()[0].getName());
	    }

	    @Test
	    @DisplayName("Add Duplicate Recipe Should Fail")
	    void testAddRecipe_Duplicate() {
	        RecipeBook book = new RecipeBook();
	        Recipe recipe = new Recipe();
	        recipe.setName("Latte");

	        book.addRecipe(recipe); 
	        boolean secondAdd = book.addRecipe(recipe); 

	        assertFalse(secondAdd);
	    }

	    @Test
	    @DisplayName("Delete Existing Recipe")
	    void testDeleteRecipe_Valid() {
	        RecipeBook book = new RecipeBook();
	        Recipe recipe = new Recipe();
	        recipe.setName("Mocha");
	        book.addRecipe(recipe);

	        String deleted = book.deleteRecipe(0);

	        assertEquals("Mocha", deleted);
	        assertNotEquals("Mocha", book.getRecipes()[0].getName()); 
	    }

	    @Test
	    @DisplayName("Delete Non-Existing Recipe Should Return Null")
	    void testDeleteRecipe_Invalid() {
	        RecipeBook book = new RecipeBook();

	        String deleted = book.deleteRecipe(0);

	        assertNull(deleted);
	    }

	    @Test
	    @DisplayName("Edit Existing Recipe")
	    void testEditRecipe_Valid() {
	        RecipeBook book = new RecipeBook();
	        Recipe oldRecipe = new Recipe();
	        oldRecipe.setName("Cappuccino");

	        Recipe newRecipe = new Recipe();
	        newRecipe.setName("New Cappuccino");

	        book.addRecipe(oldRecipe);
	        String result = book.editRecipe(0, newRecipe);

	        assertEquals("Cappuccino", result);
	        assertEquals("", book.getRecipes()[0].getName()); 
	    }

	    @Test
	    @DisplayName("Edit Non-Existing Recipe Should Return Null")
	    void testEditRecipe_Invalid() {
	        RecipeBook book = new RecipeBook();
	        Recipe newRecipe = new Recipe();
	        newRecipe.setName("Choco");

	        String result = book.editRecipe(0, newRecipe);

	        assertNull(result);
	    }

	    @ParameterizedTest
	    @ValueSource(strings = {"Mocha", "Espresso", "Latte", "Cappuccino"})
	    @DisplayName("Should not add duplicate recipe names")
	    void testDuplicateRecipeAdd(String name) {
	        RecipeBook book = new RecipeBook();

	        Recipe first = new Recipe();
	        first.setName(name);
	        assertTrue(book.addRecipe(first), "First addition of recipe should succeed");

	        Recipe duplicate = new Recipe();
	        duplicate.setName(name);
	        assertFalse(book.addRecipe(duplicate), "Duplicate recipe should not be added");
	    }

	    @Test
	    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	    @DisplayName("Get Recipes Should Return Quickly")
	    void testGetRecipesTimeout() {
	        RecipeBook book = new RecipeBook();
	        Recipe[] recipes = book.getRecipes();

	        assertNotNull(recipes);
	        assertEquals(4, recipes.length);
	    }

}
