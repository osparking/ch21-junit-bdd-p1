package space.bum.junit.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

class PassengerPolicy {
  private Flight economyFlight;
  private Passenger kim;
  private Flight businessFlight;
  private Flight premiumFlight;
  private Passenger park;

  @Given("there is an economy flight")
  public void there_is_an_economy_flight() throws Throwable {
    economyFlight = new EconomyFlight("1");
  }

  @When("we have a regular passenger")
  public void we_have_a_regular_passenger() throws Throwable {
    kim = new Passenger("김", false);
  }

  @Then("you can add and remove him from an economy flight")
  public void you_can_add_and_remove_him_from_an_economy_flight()
      throws Throwable {
    assertAll("검증 대상 상태 - 일반 승객 경제적 항공편",
        () -> assertEquals("1", economyFlight.getId()),
        () -> assertEquals(true, economyFlight.addPassenger(kim)),
        () -> assertEquals(1, economyFlight.getPassengers().size()),
        () -> assertTrue(economyFlight.getPassengers().contains(kim)),
        () -> assertEquals(true, economyFlight.removePassenger(kim)),
        () -> assertEquals(0, economyFlight.getPassengers().size()));
  }

  @Then("^you cannot add a regular passenger to an economy flight more than once$")
  public void add_a_regular_passenger_to_an_economy_flight_more_than_once()
      throws Throwable {
    for (int i = 0; i < 10; i++) {
      economyFlight.addPassenger(kim);
    }
    assertAll(
        "Verify a regular passenger can be added to an economy flight only once",
        () -> assertEquals(1, economyFlight.getPassengers().size()),
        () -> assertTrue(economyFlight.getPassengers().contains(kim)),
        () -> assertTrue(new ArrayList<>(economyFlight.getPassengers())
            .get(0).getName().equals("Mike")));
  }

  @When("^we have a VIP passenger$")
  public void we_have_a_VIP_passenger() throws Throwable {
    park = new Passenger("박", true);
  }

  @Then("^you can add him but cannot remove him from an economy flight$")
  public void you_can_add_him_but_cannot_remove_him_from_an_economy_flight()
      throws Throwable {
    assertAll("Verify all conditions for a VIP passenger and an economy flight",
        () -> assertEquals("1", economyFlight.getId()),
        () -> assertEquals(true, economyFlight.addPassenger(park)),
        () -> assertEquals(1, economyFlight.getPassengers().size()),
        () -> assertTrue(economyFlight.getPassengers().contains(park)),
        () -> assertEquals(false, economyFlight.removePassenger(park)),
        () -> assertEquals(1, economyFlight.getPassengers().size()));
  }

  @Then("^you cannot add a VIP passenger to an economy flight more than once$")
  public void you_cannot_add_a_VIP_passenger_to_an_economy_flight_more_than_once()
      throws Throwable {
    for (int i = 0; i < 10; i++) {
      economyFlight.addPassenger(park);
    }

    assertAll(
        "Verify a VIP passenger can be added to an economy flight only once",
        () -> assertEquals(1, economyFlight.getPassengers().size()),
        () -> assertTrue(economyFlight.getPassengers().contains(park)),
        () -> assertTrue(new ArrayList<>(economyFlight.getPassengers())
            .get(0).getName().equals("박")));
  }

  @Given("^there is a business flight$")
  public void there_is_an_business_flight() throws Throwable {
    businessFlight = new BusinessFlight("2");
  }

  @Then("^you cannot add or remove him from a business flight$")
  public void you_cannot_add_or_remove_him_from_a_business_flight()
      throws Throwable {
    assertAll(
        "Verify all conditions for a regular passenger and a business flight",
        () -> assertEquals(false, businessFlight.addPassenger(kim)),
        () -> assertEquals(0, businessFlight.getPassengers().size()),
        () -> assertEquals(false, businessFlight.removePassenger(kim)),
        () -> assertEquals(0, businessFlight.getPassengers().size()));
  }

  @Then("^you can add him but cannot remove him from a business flight$")
  public void you_can_add_him_but_cannot_remove_him_from_a_business_flight()
      throws Throwable {
    assertAll("Verify all conditions for a VIP passenger and a business flight",
        () -> assertEquals(true, businessFlight.addPassenger(park)),
        () -> assertEquals(1, businessFlight.getPassengers().size()),
        () -> assertEquals(false, businessFlight.removePassenger(park)),
        () -> assertEquals(1, businessFlight.getPassengers().size()));
  }

  @Then("^you cannot add a VIP passenger to a business flight more than once$")
  public void you_cannot_add_a_VIP_passenger_to_a_business_flight_more_than_once()
      throws Throwable {
    for (int i = 0; i < 10; i++) {
      businessFlight.addPassenger(park);
    }

    assertAll(
        "Verify a VIP passenger can be added to a business flight only once",
        () -> assertEquals(1, businessFlight.getPassengers().size()),
        () -> assertTrue(businessFlight.getPassengers().contains(park)),
        () -> assertTrue(new ArrayList<>(businessFlight.getPassengers())
            .get(0).getName().equals("박")));
  }

  @Given("^there is a premium flight$")
  public void thereIsAnPremiumFlight() throws Throwable {
    premiumFlight = new PremiumFlight("3");
  }

  @Then("^you cannot add or remove him from a premium flight$")
  public void there_is_an_premium_flight() throws Throwable {
    assertAll(
        "Verify all conditions for a regular passenger and a premium flight",
        () -> assertEquals(false, premiumFlight.addPassenger(kim)),
        () -> assertEquals(0, premiumFlight.getPassengers().size()),
        () -> assertEquals(false, premiumFlight.removePassenger(kim)),
        () -> assertEquals(0, premiumFlight.getPassengers().size()));
  }

  @Then("^you can add and remove him from a premium flight$")
  public void you_cannot_add_or_remove_him_from_a_premium_flight()
      throws Throwable {
    assertAll("Verify all conditions for a VIP passenger and a premium flight",
        () -> assertEquals(true, premiumFlight.addPassenger(park)),
        () -> assertEquals(1, premiumFlight.getPassengers().size()),
        () -> assertEquals(true, premiumFlight.removePassenger(park)),
        () -> assertEquals(0, premiumFlight.getPassengers().size()));
  }

  @Then("^you cannot add a VIP passenger to a premium flight more than once$")
  public void you_cannot_add_a_VIP_passenger_to_a_premium_flight_more_than_once()
      throws Throwable {
    for (int i = 0; i < 10; i++) {
      premiumFlight.addPassenger(park);
    }

    assertAll(
        "Verify a VIP passenger can be added to a premium flight only once",
        () -> assertEquals(1, premiumFlight.getPassengers().size()),
        () -> assertTrue(premiumFlight.getPassengers().contains(park)),
        () -> assertTrue(new ArrayList<>(premiumFlight
            .getPassengers())
            .get(0).getName().equals("박")));
  }
}
