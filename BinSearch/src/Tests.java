import Search.*;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class Tests
{
    @Test
    @DisplayName("Binary Search Test")
    public static void test()
    {
        assertEquals(-1, BinarySearch.find(new int[]{-3, 5, 12}, 0));
        assertEquals(0, BinarySearch.find(new int[]{-3, 5, 12}, -3));
        assertEquals(1, BinarySearch.find(new int[]{-3, 5, 12}, 5));
        assertEquals(2, BinarySearch.find(new int[]{-3, 5, 12}, 12));

    }
}