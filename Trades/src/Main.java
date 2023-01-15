import Utils.Utils;

public class Main
{
    public static void main(String[] args)
    {
        if (args.length < 1)
        {
            System.out.println("Usage: <path to file with trades>");
            return;
        }

        var trades = Utils.readFile(args[0]);
        if (trades != null)
        {
            var worst = trades.subList(0, 10);
            var best = trades.subList(trades.size() - 10, trades.size());
            System.out.println("Best:");
            Utils.printData(best);
            System.out.println("Worst:");
            Utils.printData(worst);
        }
    }
}