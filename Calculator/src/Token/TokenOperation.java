package Token;

import java.util.Optional;

public class TokenOperation extends Token<TokenOperation.OperationType>
{
    public enum OperationType { ADD, SUBTRACT, MULTIPLY, DIVIDE }
    public TokenOperation(String str)
    {
        super(str);
    }
    public int Priority () { return getData().ordinal(); }
    @Override
    public Optional<Pair<String, OperationType>> fromString(String str)
    {
        char first_character = str.charAt(0);
        switch (first_character)
        {
            case '+' -> { return Optional.of(new Pair<>("+", OperationType.ADD)); }
            case '-' -> { return Optional.of(new Pair<>("-", OperationType.SUBTRACT)); }
            case '*' -> { return Optional.of(new Pair<>("*", OperationType.MULTIPLY)); }
            case '/' -> { return Optional.of(new Pair<>("/", OperationType.DIVIDE)); }
            default -> { return Optional.empty(); }
        }
    }

    @Override
    public String toString(OperationType data)
    {
        String str = "";
        switch (data) {
            case ADD -> str = "+";
            case SUBTRACT -> str = "-";
            case MULTIPLY -> str = "*";
            case DIVIDE -> str = "/";
        }
        return str;
    }

    public Optional<TokenNumber> Apply (TokenNumber left, TokenNumber right)
    {
        double lvalue = left.getData();
        double rvalue = right.getData();

        if (!left.isOk() || !right.isOk())
            return Optional.empty();

        switch (getData())
        {
            case ADD      -> { return Optional.of(new TokenNumber(lvalue + rvalue)); }
            case SUBTRACT -> { return Optional.of(new TokenNumber(lvalue - rvalue)); }
            case MULTIPLY -> { return Optional.of(new TokenNumber(lvalue * rvalue)); }
            case DIVIDE   -> { return Optional.of(new TokenNumber(lvalue / rvalue)); }
            default -> { return Optional.empty(); }
        }
    }
}
