package Token;

import java.util.Optional;

public final class TokenBracket extends Token<TokenBracket.BracketType>
{
    public enum BracketType { LEFT, RIGHT }

    public TokenBracket(String str) { super(str); }
    @Override
    public Optional<Pair<String, BracketType>> fromString(String str)
    {
        Optional<Pair<String, BracketType>> data = Optional.empty();
        char first_character = str.charAt(0);
        switch (first_character)
        {
            case '(' -> data = Optional.of(new Pair<>("(", BracketType.LEFT));
            case ')' -> data = Optional.of(new Pair<>(")", BracketType.RIGHT));
        }
        return data;
    }
    @Override
    public String toString(BracketType data)
    {
        String str = "";
        switch (data)
        {
            case LEFT -> str = "(";
            case RIGHT -> str = ")";
        }
        return str;
    }
}
