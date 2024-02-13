package space.bum.junit.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import space.bum.junit.tdd.model.BusinessFlight;
import space.bum.junit.tdd.model.Flight;
import space.bum.junit.tdd.model.Passenger;
import space.bum.junit.tdd.model.PremiumFlight;

class AirportTest {

  @Nested
  @DisplayName("비즈니스 항공편")
  class BusinessFlightTest {
    private Flight businessFlight;

    @BeforeEach
    void setUp() {
      businessFlight = new BusinessFlight("2");
    }

    @Nested
    @DisplayName("보통의 승객")
    class NormPassengerOnBusinessTest {

      @Test
      @DisplayName("보통승객이 비즈니스 탑승 시험")
      void normOnBusinessTest() {
        Passenger choi = new Passenger("최", false);
        assertAll("일반 승객은 비즈니 항공편 이용에 관한 검증",
            () -> assertEquals(false, businessFlight.addPassenger(choi)),
            () -> assertEquals(0, businessFlight.getPassengers().size()),
            () -> assertEquals(false, businessFlight.removePassenger(choi)),
            () -> assertEquals(0, businessFlight.getPassengers().size()));
      }
    }

    @Nested
    @DisplayName("VIP인 승객")
    class VipPassengerOnBusinessTest {
      @Test
      @DisplayName("VIP 승객이 비즈니스 탑승 시험")
      void normOnBusinessTest() {
        Passenger park = new Passenger("박", true);
        assertAll("VIP 승객이 비즈니스 항공편 이용에 관한 검증",
            () -> assertEquals(true, businessFlight.addPassenger(park)),
            () -> assertEquals(1, businessFlight.getPassengers().size()),
            () -> assertEquals(false, businessFlight.removePassenger(park)),
            () -> assertEquals(1, businessFlight.getPassengers().size()));
      }
    }
  }

  @Nested
  @DisplayName("프리미엄 항공편")
  class PremiumFlightTest {
    private Flight premiumFlight;

    @BeforeEach
    void setUp() {
      premiumFlight = new PremiumFlight("3");
    }

    @Nested
    @DisplayName("일반 승객")
    class NormPassengerOnBusinessTest {

      @Test
      @DisplayName("일반 승객 프리미엄 탑승 시험")
      void normOnBusinessTest() {
        Passenger choi = new Passenger("최", false);
        assertAll("일반 승객 프리미엄 항공편 추가&삭제 불가함 검증",
            () -> assertEquals(false, premiumFlight.addPassenger(choi)),
            () -> assertEquals(0, premiumFlight.getPassengers().size()),
            () -> assertEquals(false, premiumFlight.removePassenger(choi)),
            () -> assertEquals(0, premiumFlight.getPassengers().size()));
      }
    }

    @Nested
    @DisplayName("VIP 승객")
    class VipPassengerOnBusinessTest {
      @Test
      @DisplayName("VIP 승객 프리미엄 탑승 시험")
      void normOnBusinessTest() {
        Passenger park = new Passenger("박", true);
        assertAll("VIP 승객 프리미엄 항공편 추가&삭제 가능 검증",
            () -> assertEquals(true, premiumFlight.addPassenger(park)),
            () -> assertEquals(1, premiumFlight.getPassengers().size()),
            () -> assertEquals(true, premiumFlight.removePassenger(park)),
            () -> assertEquals(0, premiumFlight.getPassengers().size()));
      }
    }
  }

  @Nested
  @DisplayName("경제적 항공편이 하나 있다.")
  class EconomyFlightTest {
    Flight ecoFlight;

    @BeforeEach
    void prepareFlight() {
      ecoFlight = new EconomyFlight("1");
    }

    @Nested
    @DisplayName("보통 승객이 탑승 및 취소를 시도한다.")
    class NormPassengerOnEconomyFlightTest {

      Passenger kim = new Passenger("김", false);

      @Test
      void normPassengerOnEconomyFlightTest() {
        assertAll("",
            () -> assertTrue(ecoFlight.addPassenger(kim)),
            () -> assertEquals("김",
                new ArrayList<>(ecoFlight.passengers).get(0).getName()),
            () -> assertEquals(1, ecoFlight.getPassengers().size()),
            () -> assertTrue(ecoFlight.removePassenger(kim)),
            () -> assertEquals(0, ecoFlight.getPassengers().size()));
      }
    }

    @Nested
    @DisplayName("보통 승객이 탑승 및 취소를 시도한다.")
    class VipOnEconomyFlightTest {
      Passenger kim = new Passenger("이", true);

      @Test
      void vipOnEconomyFlightTest() {
        assertAll("",
            () -> assertTrue(ecoFlight.addPassenger(kim)),
            () -> assertEquals("이",
                new ArrayList<>(ecoFlight.passengers).get(0).getName()),
            () -> assertEquals(1, ecoFlight.getPassengers().size()),
            () -> assertFalse(ecoFlight.removePassenger(kim)),
            () -> assertEquals(1, ecoFlight.getPassengers().size()));
      }
    }
  }

}
