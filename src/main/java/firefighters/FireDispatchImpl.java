package firefighters;

import java.util.ArrayList;
import java.util.List;

import api.City;
import api.CityNode;
import api.FireDispatch;
import api.Firefighter;
import api.exceptions.NoFirefightersException;
import utils.DistanceUtil;

public class FireDispatchImpl implements FireDispatch {

  private List<Firefighter> firefighters;
  private final City city;

  public FireDispatchImpl(City city) {
    this.city = city;
  }

  @Override
  public void setFirefighters(int numFirefighters) {
    firefighters = new ArrayList<>(numFirefighters);
    for (int i = 0; i < numFirefighters; i++) {
      firefighters.add(new FirefighterImpl(this.city.getFireStation().getLocation()));
    }
  }

  @Override
  public List<Firefighter> getFirefighters() {
    return this.firefighters;
  }

  @Override
  public void dispatchFirefighters(CityNode... burningBuildings) throws Exception {
    int burningBuildingCount = burningBuildings.length;

    // throw an exception if there is at least 1 fire, but there are no firefighters.
    if (burningBuildingCount > 0 && (firefighters == null || firefighters.isEmpty())) {
      throw new NoFirefightersException();
    }
    int fireFighterCount = firefighters.size();

    // keep track of the index so that we can use it for both the burningBuildings and the firefighters.
    // this will prevent any double for loops to match fighters to buildings.
    for (int i = 0; i < burningBuildingCount; i++) {
      CityNode burningBuilding = burningBuildings[i];
      Firefighter firefighter = null;
      // dispatch a firefighter from the fire station if there is one available. else dispatch the nearest firefighter.
      if (i < fireFighterCount) {
       firefighter = firefighters.get(i);
      } else {
        firefighter = findNearestFireFighter(burningBuilding);
      }
      firefighter.updateLocation(burningBuilding);
      firefighter.extinguishFire(city, burningBuilding);
    }
  }

  private Firefighter findNearestFireFighter(CityNode cityNode) {
    Firefighter nearestFireFighter = firefighters.get(0);
    for (int i = 1; i < firefighters.size(); i++) {

      int currentNearestDist = DistanceUtil.findDistance(nearestFireFighter.getLocation().getX(), cityNode.getX(),
              nearestFireFighter.getLocation().getY(), cityNode.getY());
      int nextNearestDist = DistanceUtil.findDistance(firefighters.get(i).getLocation().getX(), cityNode.getX(),
              firefighters.get(i).getLocation().getY(), cityNode.getY());

      if (currentNearestDist > nextNearestDist) {
        nearestFireFighter = firefighters.get(i);
      }
    }
    return nearestFireFighter;
  }
}
