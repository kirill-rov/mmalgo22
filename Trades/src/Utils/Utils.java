package Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils
{
    public static List<List<Row>> readFile (String filename)
    {
        try (Stream<String> stream = Files.lines(Paths.get(filename)))
        {
            Comparator<List<Row>> priceCmp = (list1, list2) ->
            {
                Double d1 = list1.get(list1.size() - 1).m_price - list1.get(0).m_price;
                Double d2 = list2.get(list2.size() - 1).m_price - list2.get(0).m_price;
                return d1.compareTo(d2);
            };

            Comparator<Row> dateCmp = Comparator.comparing(trade -> trade.m_tradetime);

            return stream
                    .skip(1)
                    .map(Row::fromString)
                    .filter((r) -> r.m_secboard.equals("TQBR") || r.m_secboard.equals("FQBR"))
                    .collect(Collectors.groupingBy(Row::getSeccode))
                    .values()
                    .stream()
                    .peek((group) -> group.sort(dateCmp))
                    .sorted(priceCmp)
                    .toList();
        }
        catch (IOException e)
        {
            System.out.println ("No file " + filename);
            e.printStackTrace();
        }

        return null;
    }

    public static void printData (List<List<Row>> groups)
    {
        System.out.println(" SecCode | Num of trades | Total volume | Diff in %");
        groups.forEach((group) ->
        {
            Row first = group.get(0);
            Row last = group.get(group.size() - 1);

            Double priceDiffPercent = 100. * (last.m_price - first.m_price) / first.m_price;
            Double totalVolume = group.stream()
                    .map(Row::getValue)
                    .reduce(Double::sum)
                    .get();

            System.out.printf("%8s  %10d %18.2f %+7.2f%%\n", first.m_seccode, group.size(),
                    totalVolume, priceDiffPercent);
        });
    }
}
