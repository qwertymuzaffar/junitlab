package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.*;

public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void testAdd() {
        int result = calculator.add(2, 3);
        assertThat(result).isEqualTo(5);
        System.out.println("✅ Addition test passed");
    }

    @Test
    void testSubtract() {
        int result = calculator.subtract(5, 3);
        assertThat(result).isEqualTo(2);
        System.out.println("✅ Subtraction test passed");
    }

    @Test
    void testMultiply() {
        int result = calculator.multiply(4, 5);
        assertThat(result).isEqualTo(20);
        System.out.println("✅ Multiplication test passed");
    }

    @Test
    void testDivide() {
        double result = calculator.divide(10, 2);
        assertThat(result).isEqualTo(5.0);
        System.out.println("✅ Division test passed");
    }

    @Test
    void testSqrt() {
        double result = calculator.sqrt(100);
        assertThat(result).isEqualTo(10.0);
        System.out.println("✅ √100 = " + result);
    }

    @Test
    void testDivideByZero() {
        assertThatThrownBy(() -> calculator.divide(10, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot divide by zero!");
        System.out.println("✅ Exception test passed");
    }

    @Test
    void testSquareRootWithNegativeNumber() {
        assertThatThrownBy(() -> calculator.sqrt(-4))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot calculate square root of negative number!");
        System.out.println("✅ √(-4) exception test passed");
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 0, -2})
    void testIsEven(int number) {
        assertThat(calculator.isEven(number)).isTrue();
        System.out.println("✅ " + number + " is even");
    }

    @ParameterizedTest
    @CsvSource({
            "1, 1, 2",
            "2, 3, 5",
            "5, 5, 10",
            "-1, 1, 0"
    })
    void testAddParameterized(int a, int b, int expected) {
        int result = calculator.add(a, b);
        assertThat(result).isEqualTo(expected);
        System.out.println("✅ " + a + " + " + b + " = " + result);
    }

    @ParameterizedTest
    @CsvSource({
            "16.0, 4.0",
            "25.0, 5.0",
            "2.0, 1.414",
            "0.0, 0.0",
            "1.0, 1.0",
            "9.0, 3.0",
            "4.0, 2.0"
    })
    void testSqrt(double number, double expected) {
        double result = calculator.sqrt(number);
        assertThat(result).isCloseTo(expected, within(0.01));
        System.out.println("✅ √" + number + " = " + result);
    }
}
