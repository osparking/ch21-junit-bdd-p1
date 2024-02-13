package space.bum.junit.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;

@Getter
public abstract class Flight {
  private String id;
  Set<Passenger> passengers = new HashSet<>();

  public Flight(String id) {
    super();
    this.id = id;
  }
  
  public Set<Passenger> getPassengers() {
    return Collections.unmodifiableSet(passengers);
  }

  public abstract boolean addPassenger(Passenger passenger);

  public abstract boolean removePassenger(Passenger passenger);

}
