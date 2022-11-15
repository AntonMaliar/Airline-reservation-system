package anton.maliar.AirlineReservationSystem.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Component
public class AuthenticationManager {
    private List<HttpSession> allSessions;

    public AuthenticationManager(){
        allSessions = new ArrayList<>();
    }
    public boolean isSessionValid(HttpSession session) throws Exception{
        for(int i = 0; i < allSessions.size(); i++){
            if(allSessions.get(i).getId().equals(session.getId())){
                return true;
            }
        }
        throw new Exception("Session not found");
    }

    public boolean addSession(HttpSession session){
        if(allSessions.add(session)){
            return true;
        }
        return false;
    }
}
