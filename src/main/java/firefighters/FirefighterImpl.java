package firefighters;

import api.City;
import api.CityNode;
import api.Firefighter;
import api.exceptions.NoFireFoundException;
import utils.DistanceUtil;

public class FirefighterImpl implements Firefighter {

  private int distanceTraveled;
  private CityNode location;

  public FirefighterImpl(CityNode location) {
    this.location = location;
  }

  @Override
  public void extinguishFire(City city, CityNode cityNode) throws NoFireFoundException {
    city.getBuilding(cityNode).extinguishFire();
  }

  @Override
  public void updateLocation(CityNode cityNode) {
    distanceTraveled += DistanceUtil.findDistance(getLocation().getX(), cityNode.getX(), getLocation().getY(), cityNode.getY());
    this.location = cityNode;
  }

  @Override
  public CityNode getLocation() {
    return location;
  }

  @Override
  public int distanceTraveled() {
    return distanceTraveled;
  }
}
