package Parser;

import Token.Token;
import Token.TokenOperation;
import Token.TokenBracket;
import Token.TokenNumber;

import java.util.ArrayDeque;

public class Parser
{
    public static ArrayDeque<Token> parse(String str)
    {
        String str_no_spaces = str.replaceAll("\\s+","");
        ArrayDeque<Token> deque = new ArrayDeque<> ();

        for (int start = 0; start < str_no_spaces.length();)
        {
            Token new_token = extract(str_no_spaces, start);
            if (!new_token.isOk())
                break;

            start += new_token.getString().length();
            deque.add (new_token);
        }

        return deque;
    }
    private static Token extract(String str, int start)
    {
        Token token;

        if ((token = new TokenNumber(str.substring(start))).isOk())
            return token;
        if ((token = new TokenOperation(str.substring(start))).isOk())
            return token;
        if ((token = new TokenBracket(str.substring(start))).isOk())
            return token;
        throw new RuntimeException ();
    }

}