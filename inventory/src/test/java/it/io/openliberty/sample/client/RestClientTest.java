package it.io.openliberty.sample.client;

import org.apache.cxf.jaxrs.provider.jsrjsonp.JsrJsonpProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class RestClientTest {

    private static String port;

    private Client client;

    private final String INVENTORY_SYSTEMS = "inventory/systems";

    @BeforeClass
    public static void oneTimeSetup() {
        port = System.getProperty("liberty.test.port");
    }

    @Before
    public void setup() {
        client = ClientBuilder.newClient();
        client.register(JsrJsonpProvider.class);
    }

    @After
    public void teardown() {
        client.close();
    }

    @Test
    public void testSuite() {
//        this.testDefaultLocalhost();
//        this.testRestClientBuilder();
    }

//    public void testDefaultLocalhost() {
//        String hostname = "localhost";
//
//        String url = "http://localhost:" + port + "/" + INVENTORY_SYSTEMS + "/" + hostname;
//
//        JsonObject obj = fetchProperties(url);
//
//        assertEquals("The system property for the local and remote JVM should match",
//                System.getProperty("os.name"),
//                obj.getString("os.name"));
//    }

//    public void testRestClientBuilder() {
//        String hostname = null;
//        try{
//            hostname = InetAddress.getLocalHost().getHostAddress();
//        } catch (UnknownHostException e) {
//            System.err.println("Unknown Host.");
//        }
//
//        String url = "http://localhost:" + port + "/" + INVENTORY_SYSTEMS + "/" + hostname;
//
//        JsonObject obj = fetchProperties(url);
//
//        assertEquals("The system property for the local and remote JVM should match",
//                System.getProperty("os.name"),
//                obj.getString("os.name"));
//    }
//
//    private JsonObject fetchProperties(String url) {
//        WebTarget target = client.target(url);
//        Response response = target.request().get();
//
//        assertEquals("Incorrect response code from " + url, 200, response.getStatus());
//
//        JsonObject obj = response.readEntity(JsonObject.class);
//        response.close();
//        return obj;
//    }

}