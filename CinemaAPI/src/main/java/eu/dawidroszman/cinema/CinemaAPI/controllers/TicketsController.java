package eu.dawidroszman.cinema.CinemaAPI.controllers;

import eu.dawidroszman.cinema.CinemaAPI.enums.Price;
import eu.dawidroszman.cinema.CinemaAPI.models.TicketEntity;
import eu.dawidroszman.cinema.CinemaAPI.objects.TicketDTO;
import eu.dawidroszman.cinema.CinemaAPI.requests.TicketRequest;
import eu.dawidroszman.cinema.CinemaAPI.services.TicketService;
import eu.dawidroszman.cinema.CinemaAPI.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/v1/tickets")
public class TicketsController {

    TicketService ticketService;

    public TicketsController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/get")
    public List<TicketDTO> getTickets(Principal principal) {
        List<TicketEntity> tickets = ticketService.getTicketsByUsername(principal.getName());
        return TicketDTO.from(tickets);
    }

    @PostMapping("/buy")
    public void buyTicket(Principal principal, @RequestBody TicketRequest ticketRequest) {
        ticketService.buyTicket(principal.getName(), ticketRequest.getScreeningId(), ticketRequest.getSeatId(), ticketRequest.getDiscount());
    }
}