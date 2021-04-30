package scenarios;

import api.*;
import api.exceptions.NoFirefightersException;
import impls.CityImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FireDispatchTest {

    @Test
    public void testHireFirefighters() {
        City basicCity = new CityImpl(5, 5, new CityNode(0, 0));
        FireDispatch fireDispatch = basicCity.getFireDispatch();
        fireDispatch.setFirefighters(5);
        List<Firefighter> firefighters = fireDispatch.getFirefighters();
        assertEquals(5, firefighters.size());
        firefighters.forEach(firefighter -> {
            CityNode fireStationLocation = basicCity.getFireStation().getLocation();
            CityNode fireFighterLocation = firefighter.getLocation();
            assertEquals(fireStationLocation.getX(), fireFighterLocation.getX());
            assertEquals(fireStationLocation.getY(), fireFighterLocation.getY());
        });
    }

    @Test(expected = NoFirefightersException.class)
    public void testEmptyFirefighters() throws Exception {
        City basicCity = new CityImpl(5, 5, new CityNode(0, 0));
        FireDispatch fireDispatch = basicCity.getFireDispatch();

        CityNode fireNode = new CityNode(0, 1);
        Pyromaniac.setFire(basicCity, fireNode);

        fireDispatch.setFirefighters(0);
        fireDispatch.dispatchFirefighters(fireNode);
        assertTrue(basicCity.getBuilding(fireNode).isBurning());
    }

    @Test(expected = NoFirefightersException.class)
    public void testNullFirefighters() throws Exception {
        City basicCity = new CityImpl(5, 5, new CityNode(0, 0));
        FireDispatch fireDispatch = basicCity.getFireDispatch();

        CityNode fireNode = new CityNode(0, 1);
        Pyromaniac.setFire(basicCity, fireNode);

        fireDispatch.dispatchFirefighters(fireNode);
        assertTrue(basicCity.getBuilding(fireNode).isBurning());
    }
}
