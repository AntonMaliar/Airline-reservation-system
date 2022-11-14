package anton.maliar.AirlineReservationSystem.authentication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.*;

public class AuthenticationTest {
    private URL url;
    private HttpURLConnection con;

    @Test
    public void welcomePageTest() {

        try {
            url = new URL("http://localhost:8080");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            con = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            con.setRequestMethod("GET");
            assertEquals(200, con.getResponseCode());
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
