package anton.maliar.AirlineReservationSystem.controllers;

import anton.maliar.AirlineReservationSystem.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/ticket")
public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }

    @GetMapping("/reserve")
    public String reserveTicket(HttpServletRequest request){
        if(request.getSession(false) != null){
            ticketService.reserveTicket(request);
            return "redirect:/user/account";
        }
        return "redirect:/?errorMessage=errorMessage";
    }
    @GetMapping("/cancel")
    public String cancelReservation(HttpServletRequest request){
        if(request.getSession(false) != null){
            ticketService.cancelReservation(request);
            return "redirect:/user/account";
        }
        return "redirect:/?errorMessage=errorMessage";
    }
}
