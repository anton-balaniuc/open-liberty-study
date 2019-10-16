package io.openliberty.sample.system;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Provider;

@RequestScoped
public class SystemConfig {
    @Inject
    @ConfigProperty(name = "io_openliberty_guides_system_inMaintenance")
    Provider<Boolean> inMaintenance;

    public Boolean isInMaintenance() {
        return inMaintenance.get();
    }
}
