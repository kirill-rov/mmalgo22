package Calculator;
import Parser.Parser;
import Token.*;

import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Stack;

public class Calculator
{

    public static TokenNumber calculate(String str)
    {
        ArrayDeque<Token> input_deque = Parser.parse(str);
        ArrayDeque<Token> sorted_deque = SortDeque(input_deque);
        return calculate(sorted_deque);
    }

    public static TokenNumber calculate(ArrayDeque<Token> deque)
    {
        Stack<TokenNumber> stack = new Stack<>();
        for (Token t : deque)
        {
            if (t instanceof TokenNumber number)
            {
                stack.push(number);
            }
            else if (t instanceof TokenOperation op)
            {
                if (stack.size () < 2)
                    throw new RuntimeException ();

                TokenNumber t1 = stack.pop();
                TokenNumber t2 = stack.pop();

                Optional<TokenNumber> res = op.Apply(t2, t1);
                if (res.isEmpty())
                    throw new RuntimeException ();

                stack.push (res.get ());
            }
        }
        if (stack.size () != 1)
            return new TokenNumber("");

        TokenNumber token = stack.pop();
        if (!token.isOk())
            throw new RuntimeException ();
        return token;
    }

    public static ArrayDeque<Token> SortDeque (ArrayDeque<Token> deque)
    {
        ArrayDeque<Token> sorted_deque = new ArrayDeque<>();
        Stack<Token> stack = new Stack<>();

        for (Token t : deque)
        {
            if (t instanceof TokenNumber)
            {
                sorted_deque.add(t);
            }
            else if (t instanceof TokenOperation op1)
            {
                while (!stack.empty()
                        && stack.peek() instanceof TokenOperation op2
                        && op2.Priority() >= op1.Priority())
                {
                    sorted_deque.add(stack.pop());
                }
                stack.push(op1);
            }
            else if (t instanceof TokenBracket tb && tb.getData() == TokenBracket.BracketType.LEFT)
            {
                stack.push(tb);
            }
            else if (t instanceof TokenBracket tb && tb.getData() == TokenBracket.BracketType.RIGHT)
            {
                while (!stack.isEmpty()
                        && !(  stack.peek() instanceof TokenBracket bracket
                        && bracket.getData() == TokenBracket.BracketType.LEFT))
                {
                    sorted_deque.add(stack.pop());
                }
                if (stack.isEmpty())
                    throw new RuntimeException ();
                stack.pop();
            }
        }

        while (!stack.empty())
        {
            if (stack.peek() instanceof TokenBracket)
                throw new RuntimeException ();
            sorted_deque.add(stack.pop());
        }

        return sorted_deque;
    }
}
