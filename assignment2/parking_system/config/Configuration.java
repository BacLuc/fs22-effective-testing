package zest;

import nl.tudelft.cse1110.andy.codechecker.engine.CheckScript;
import nl.tudelft.cse1110.andy.config.MetaTest;
import nl.tudelft.cse1110.andy.config.RunConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Configuration extends RunConfiguration {

  @Override
  public CheckScript checkScript() {
    return new CheckScript(List.of());
  }

  @Override
  public Map<String, Float> weights() {
    return new HashMap<>() {
      {
        put("coverage", 0.1f);
        put("mutation", 0.1f);
        put("meta", 0.7f);
        put("codechecks", 0.1f);
      }
    };
  }

  @Override
  public List<String> classesUnderTest() {
    return List.of("zest.ParkingSystem");
  }

  @Override
  public List<MetaTest> metaTests() {
    return List.of();
  }
}
