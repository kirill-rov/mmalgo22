import Calculator.*;
import Token.*;

public class Main
{
    public static void main (String[] args)
    {
        if (args.length > 0)
        {
            String input_expr = "";
            for (int i = 0; i < args.length; i++)
                input_expr += args[i];

            System.out.println("Input : " + input_expr);
            TokenNumber res = Calculator.calculate(input_expr);
            System.out.println("Result : " + res.getString());
        }

        Tests.Do_Tests ();
    }
}
