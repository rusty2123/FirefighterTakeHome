package scenarios;

import api.*;
import impls.CityImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AdvancedScenarios {

    @Test
    public void testFiveFiresTwoFirefighters() throws Exception {
        City basicCity = new CityImpl(10, 10, new CityNode(4, 9));
        FireDispatch fireDispatch = basicCity.getFireDispatch();


        CityNode[] fireNodes = {
                new CityNode(1, 9),
                new CityNode(2, 1),
                new CityNode(5, 4),
                new CityNode(8, 6),
                new CityNode(7, 0)};
        Pyromaniac.setFires(basicCity, fireNodes);

        fireDispatch.setFirefighters(2);
        fireDispatch.dispatchFirefighters(fireNodes);

        List<Firefighter> firefighters = fireDispatch.getFirefighters();
        int totalDistanceTraveled = 0;
        boolean firefighterPresentAtFireOne = false;
        boolean firefighterPresentAtFireFive = false;
        for (Firefighter firefighter : firefighters) {
            totalDistanceTraveled += firefighter.distanceTraveled();

            if (firefighter.getLocation().equals(fireNodes[0])) {
                firefighterPresentAtFireOne = true;
            }
            if (firefighter.getLocation().equals(fireNodes[4])) {
                firefighterPresentAtFireFive = true;
            }
        }

        Assert.assertEquals(31, totalDistanceTraveled);
        Assert.assertTrue(firefighterPresentAtFireOne);
        Assert.assertTrue(firefighterPresentAtFireFive);
        Assert.assertFalse(basicCity.getBuilding(fireNodes[0]).isBurning());
        Assert.assertFalse(basicCity.getBuilding(fireNodes[1]).isBurning());
        Assert.assertFalse(basicCity.getBuilding(fireNodes[2]).isBurning());
        Assert.assertFalse(basicCity.getBuilding(fireNodes[3]).isBurning());
        Assert.assertFalse(basicCity.getBuilding(fireNodes[4]).isBurning());
    }
}
