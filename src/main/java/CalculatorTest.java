// CalculatorTest.java
import org.junit.jupiter.api.Test;           // For @Test annotation
import org.junit.jupiter.api.BeforeEach;     // For setup before each test
import static org.assertj.core.api.Assertions.*; // For AssertJ assertions
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;

public class CalculatorTest {

    // This will hold our calculator instance for testing
    private Calculator calculator;

    /**
     * This method runs before each test method
     * It creates a fresh Calculator instance for each test
     */
    @BeforeEach
    void setUp() {
        calculator = new Calculator();
        System.out.println("Setting up calculator for test...");
    }

    /**
     * Test the add method with positive numbers
     * This is our first test!
     */
    @Test
    void testAddPositiveNumbers() {
        // Arrange: Set up test data
        int a = 5;
        int b = 3;
        int expected = 8;

        // Act: Call the method we're testing
        int result = calculator.add(a, b);

        // Assert: Check if the result is what we expected
        assertThat(result).isEqualTo(expected);

        // This is equivalent to the above, but less readable:
        // assertEquals(expected, result);

        System.out.println("✅ Addition test passed: " + a + " + " + b + " = " + result);
    }

    /**
     * Test the add method with negative numbers
     */
    @Test
    void testAddNegativeNumbers() {
        // Testing with negative numbers
        int result = calculator.add(-5, -3);

        // AssertJ makes assertions very readable
        assertThat(result)
                .isEqualTo(-8)
                .isNegative(); // We can chain multiple assertions!

        System.out.println("✅ Negative addition test passed: result = " + result);
    }

    /**
     * Test the subtract method
     */
    @Test
    void testSubtract() {
        int result = calculator.subtract(10, 4);

        assertThat(result)
                .isEqualTo(6)
                .isGreaterThan(0);

        System.out.println("✅ Subtraction test passed: result = " + result);
    }

    /**
     * Test the multiply method
     */
    @Test
    void testMultiply() {
        int result = calculator.multiply(4, 5);

        assertThat(result).isEqualTo(20);

        System.out.println("✅ Multiplication test passed: result = " + result);
    }
    /**
     * Test that divide method throws exception for division by zero
     * This shows how to test for expected exceptions
     */
    @Test
    void testDivideByZeroThrowsException() {
        // We expect this to throw an IllegalArgumentException
        assertThatThrownBy(() -> {
            calculator.divide(10, 0);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot divide by zero!");

        System.out.println("✅ Exception test passed: Division by zero correctly throws exception");
    }

    /**
     * Test normal division
     */
    @Test
    void testDivideNormal() {
        double result = calculator.divide(10, 3);

        assertThat(result)
                .isCloseTo(3.333, within(0.001)) // For floating point comparisons
                .isPositive();

        System.out.println("✅ Division test passed: result = " + result);
    }
    /**
     * Test isEven method with multiple values
     * This test will run once for each value in @ValueSource
     */
    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 100, -2, 0})
    void testIsEvenWithEvenNumbers(int number) {
        boolean result = calculator.isEven(number);

        assertThat(result).isTrue();
        System.out.println("✅ " + number + " is correctly identified as even");
    }

    /**
     * Test isEven method with odd numbers
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 99, -1, -3})
    void testIsEvenWithOddNumbers(int number) {
        boolean result = calculator.isEven(number);

        assertThat(result).isFalse();
        System.out.println("✅ " + number + " is correctly identified as odd");
    }

    /**
     * Test addition with multiple combinations
     * CSV format: first,second,expected
     */
    @ParameterizedTest
    @CsvSource({
            "1, 2, 3",
            "5, 5, 10",
            "-1, 1, 0",
            "0, 0, 0",
            "100, -50, 50"
    })
    void testAddWithMultipleInputs(int first, int second, int expected) {
        int result = calculator.add(first, second);

        assertThat(result).isEqualTo(expected);
        System.out.println("✅ " + first + " + " + second + " = " + result);
    }
}