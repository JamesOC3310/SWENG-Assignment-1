import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorAppTest {
    CalculatorApp calculatorTest = new CalculatorApp();

    @Test
    public void testingRegularExpression(){
        assertEquals(15, calculatorTest.evaluate("5+10"));
        assertEquals(50, calculatorTest.evaluate("5*10"));
        assertEquals(1, calculatorTest.evaluate("9-8"));
        assertEquals(44, calculatorTest.evaluate("5*10+2-8"));
    }

    @Test
    public void testingHasPrecedence(){
        boolean expectedResult = false;
        assertEquals(expectedResult, calculatorTest.hasPrecedence('*', '+'));
        assertEquals(expectedResult, calculatorTest.hasPrecedence('*', '-'));
    }

    @Test
    public void testingApplyOp(){
       assertEquals(3, calculatorTest.applyOp('+', 1, 2));
       assertEquals(4, calculatorTest.applyOp('-', 4, 8));
       assertEquals(50000, calculatorTest.applyOp('*', 500, 100));
    }
}