package space.bum.junit.model;

public class BusinessFlight extends Flight {

  public BusinessFlight(String id) {
    super(id);
  }

  @Override
  public boolean addPassenger(Passenger passenger) {
    if (passenger.isVip()) {
      return passengers.add(passenger);
    } else {
      return false;
    }
  }

  @Override
  public boolean removePassenger(Passenger passenger) {
    return false;
  }

}
