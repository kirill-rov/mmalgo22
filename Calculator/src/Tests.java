import Calculator.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Tests
{

    @Test
    public static void Do_Tests()
    {
        assertEquals(2., Calculator.calculate("1 + 1").getData());
        assertEquals(0., Calculator.calculate("1 - 1").getData());
        assertEquals(1., Calculator.calculate("1 * 1").getData());
        assertEquals(1., Calculator.calculate("1 / 1").getData());

        assertEquals(9., Calculator.calculate("(1 + 2) * (1 + 2)").getData());
        assertEquals(8., Calculator.calculate("1 + 2 * 3 + 2 / 2").getData());
        assertEquals(2.2, Calculator.calculate("1.1 * 2").getData());
        assertEquals(6., Calculator.calculate("1.5 * 10 / 5 + (1 + 2) * 1").getData());

        try
        {
            // Missing operators before/after bracket
            Calculator.calculate("1*(1+1))");
            Calculator.calculate("1*((1+1)");
            // Operator at start/end of expression
            Calculator.calculate("+1");
            Calculator.calculate("1+");
            // Multiply operators
            Calculator.calculate("1+-1");
            Calculator.calculate("1*/2");
            //Bad input
            Calculator.calculate("1+A");
            Calculator.calculate("A+1");
            Calculator.calculate("ABC");
        }
        catch (RuntimeException e)
        {
            assertTrue(true);
        }

    }
}
