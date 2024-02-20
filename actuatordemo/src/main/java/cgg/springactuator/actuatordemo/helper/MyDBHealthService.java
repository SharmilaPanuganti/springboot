package cgg.springactuator.actuatordemo.helper;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MyDBHealthService implements HealthIndicator {

  public static final String DB_SERVICE = "db service";

  public boolean isGood() {
    return true;
  }

  @Override
  public Health health() {
    if (isGood()) {
      return Health.up().withDetail(DB_SERVICE, "Db is running").build();
    }
    return Health.down().withDetail(DB_SERVICE, "Db is not running").build();
  }
}
