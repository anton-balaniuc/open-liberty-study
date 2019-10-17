package io.openliberty.sample.system;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Readiness
@ApplicationScoped
public class SystemReadinessCheck implements HealthCheck {

    @Inject
    SystemConfig systemConfig;

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder builder = HealthCheckResponse.named(
                SystemResource.class.getSimpleName() + " readiness check");
        if (systemConfig.isInMaintenance()) {
            return builder.withData("services", "not available").down().build();
        }
        return builder.withData("services", "available").up().build();
    }

}