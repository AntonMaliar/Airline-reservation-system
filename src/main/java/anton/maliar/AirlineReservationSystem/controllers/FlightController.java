package anton.maliar.AirlineReservationSystem.controllers;

import anton.maliar.AirlineReservationSystem.repository.model.Flight;
import anton.maliar.AirlineReservationSystem.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/flight")
public class FlightController {
    private FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService){
        this.flightService = flightService;
    }

    @GetMapping("/find")
    public String findFlight(HttpServletRequest request){
        if(request.getSession(false) != null){
            return "flight/find";
        }
        return "redirect:/?errorMessage=errorMessage";
    }

    @PostMapping("/find")
    public String findFlightPost(HttpServletRequest request, Model model){
        if(request.getSession(false) != null){
            model.addAttribute("flights", flightService.findFlight(request));
            return "flight/find";
        }
        return "redirect:/?errorMessage=errorMessage";
    }

    @GetMapping("/get")
    public String getFlight(HttpServletRequest request, Model model){
        if(request.getSession(false) != null){
            Flight flight = flightService.getFlight(request);
            List<Integer> freeSeats = flightService.getFreeSeats(flight);

            model.addAttribute("flight", flight);
            model.addAttribute("freeSeats", freeSeats);
            return "flight/get";
        }
        return "redirect:/?errorMessage=errorMessage";
    }
}
