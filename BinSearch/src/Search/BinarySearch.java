package Search;

public class BinarySearch
{
    public static int find(int[] array, int element)
    {
        int l = 0, r = array.length;
        while (l <= r)
        {
            int mid = l + (r - l) / 2;
            if (array[mid] < element)
                l = mid + 1;
            else if (array[mid] > element)
                r = mid - 1;
            else
                return mid;
        }

        return -1;
    }
}
