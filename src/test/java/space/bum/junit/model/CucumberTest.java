package space.bum.junit.model;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty" }, features = "classpath:features")
public class CucumberTest {
  /**
   * 비어있어야 함. 단계 정의는 다른 클래스에 둬야 됨.
   */
}
