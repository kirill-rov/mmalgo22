package Token;

import java.util.Optional;

public abstract class Token<DataType>
{
    private DataType m_data;
    private String m_string;
    private final Boolean m_is_ok;

    static class Pair <T1, T2>
    {
        public T1 m_first;
        public T2 m_second;

        public Pair (T1 first, T2 second)
        {
            m_first = first;
            m_second = second;
        }
    }

    public Token(String str)
    {
        m_string = str;
        Optional<Pair<String, DataType>> data = fromString(str);
        m_is_ok = data.isPresent();
        if (m_is_ok)
        {
            m_string = data.get ().m_first;
            m_data = data.get ().m_second;
        }
    }

    public Token(DataType data)
    {
        m_data = data;
        m_is_ok = true;
        m_string = toString(data);
    }

    public abstract Optional<Pair<String, DataType>> fromString(String str);
    public abstract String toString(DataType data);

    public final String getString() { return m_string; }
    public final DataType getData() { return m_data; }
    public final Boolean isOk()
    {
        return m_is_ok;
    }
}
