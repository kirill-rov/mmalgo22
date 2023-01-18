import Search.BinarySearch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests
{
    @Test
    @DisplayName("Binary Search Test")
    public static void test()
    {
        //Exceptions
        assertEquals(-1, BinarySearch.find(new int[]{-3, 5, 12}, 0));
        assertEquals(-1, BinarySearch.find(new int[]{-3, 5, 12}, -4));
        assertEquals(-1, BinarySearch.find(new int[]{-3, 5, 12}, 13));
        assertEquals(-1, BinarySearch.find(new int[]{}, 0));
        assertEquals(1, BinarySearch.find(new int[]{1, 1, 1}, 1));
        
        //Normal work
        assertEquals(0, BinarySearch.find(new int[]{-3, 5, 12}, -3));
        assertEquals(1, BinarySearch.find(new int[]{-3, 5, 12}, 5));
        assertEquals(2, BinarySearch.find(new int[]{-3, 5, 12}, 12));

    }
}