package Utils;

public class Row
{
    public Integer m_tradetime;
    public String m_secboard;
    public String m_seccode;
    public Double m_price;
    public Double m_value;
    //no need in other values for current task

    public Row(String line)
    {
        String[] tokens = line.split("\t");
        m_tradetime = Integer.parseInt(tokens[1]);
        m_secboard = tokens[2];
        m_seccode = tokens[3];
        m_price = Double.parseDouble(tokens[4]);
        m_value = Double.parseDouble(tokens[8]);
    }

    public static Row fromString(String line) { return new Row(line); }

    public String getSeccode () {
        return m_seccode;
    }
    public Double getValue () { return m_value; }
}
