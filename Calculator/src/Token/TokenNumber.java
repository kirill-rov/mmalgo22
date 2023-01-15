package Token;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class TokenNumber extends Token<Double>
{
    public TokenNumber(String str)
    {
        super(str);
    }

    public TokenNumber(double data)
    {
        super(data);
    }

    @Override
    public Optional<Pair<String, Double>> fromString(String str)
    {
        Pattern pattern = Pattern.compile("^\\d+\\.?(\\d+)?");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find(0))
        {
            int end = matcher.end (0);
            String value_str = str.substring (0, end);
            return Optional.of(new Pair<>(value_str, Double.parseDouble(value_str)));
        }

        return Optional.empty();
    }

    @Override
    public String toString(Double data) {
        return data.toString ();
    }
}
