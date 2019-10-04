package io.openliberty.sample.inventory;

import java.net.URL;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.net.MalformedURLException;
import javax.ws.rs.ProcessingException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import javax.inject.Inject;
import javax.enterprise.context.ApplicationScoped;

import io.openliberty.sample.inventory.client.SystemClient;
import io.openliberty.sample.inventory.client.UnknownUrlException;
import io.openliberty.sample.inventory.client.UnknownUrlExceptionMapper;
import io.openliberty.sample.inventory.model.InventoryList;
import io.openliberty.sample.inventory.model.SystemData;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class InventoryManager {

    private List<SystemData> systems = Collections.synchronizedList(new ArrayList<>());
    private final String DEFAULT_PORT = System.getProperty("default.http.port");

    @Inject
    @RestClient
    private SystemClient defaultRestClient;

    public Properties get(String hostname) {
        Properties properties = null;
        if (hostname.equals("localhost")) {
            properties = getPropertiesWithDefaultHostName();
        } else {
            properties = getPropertiesWithGivenHostName(hostname);
        }

        return properties;
    }

    public void add(String hostname, Properties systemProps) {
        Properties props = new Properties();
        props.setProperty("os.name", systemProps.getProperty("os.name"));
        props.setProperty("user.name", systemProps.getProperty("user.name"));

        SystemData host = new SystemData(hostname, props);
        if (!systems.contains(host))
            systems.add(host);
    }

    public InventoryList list() {
        return new InventoryList(systems);
    }

    private Properties getPropertiesWithDefaultHostName() {
        try {
            return defaultRestClient.getProperties();
        } catch (UnknownUrlException e) {
            System.err.println("The given URL is unreachable.");
        } catch (ProcessingException ex) {
            handleProcessingException(ex);
        }
        return null;
    }

    private Properties getPropertiesWithGivenHostName(String hostname) {
        String customURLString = "http://" + hostname + ":" + DEFAULT_PORT + "/system";
        URL customURL = null;
        try {
            customURL = new URL(customURLString);
            SystemClient customRestClient = RestClientBuilder.newBuilder()
                    .baseUrl(customURL)
                    .register(UnknownUrlExceptionMapper.class)
                    .build(SystemClient.class);
            return customRestClient.getProperties();
        } catch (ProcessingException ex) {
            handleProcessingException(ex);
        } catch (UnknownUrlException e) {
            System.err.println("The given URL is unreachable.");
        } catch (MalformedURLException e) {
            System.err.println("The given URL is not formatted correctly.");
        }
        return null;
    }

    private void handleProcessingException(ProcessingException ex) {
        Throwable rootEx = ExceptionUtils.getRootCause(ex);
        if (rootEx != null && (rootEx instanceof UnknownHostException ||
                rootEx instanceof ConnectException)) {
            System.err.println("The specified host is unknown.");
        } else {
            throw ex;
        }
    }

}