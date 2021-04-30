package scenarios;

import org.junit.Test;
import utils.DistanceUtil;

import static org.junit.Assert.assertEquals;

public class DistanceUtilTest {

    @Test
    public void testFindDistance() {
        int distance = DistanceUtil.findDistance(3, 7, 6, 2);
        assertEquals(8, distance);
    }

    @Test
    public void testFindDistanceWithNegatives() {
        int distance = DistanceUtil.findDistance(-3, 7, -6, 2);
        assertEquals(18, distance);
    }
}
