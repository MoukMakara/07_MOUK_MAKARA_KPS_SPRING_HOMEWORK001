package org.ksga.springhomework001.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.ksga.springhomework001.domain.Ticket;
import org.ksga.springhomework001.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/tickets/")
public class TicketController {

    List<Ticket> tickets = new ArrayList<>();
    // id Auto Increment
    private int nextTicketId = 22;


    public TicketController(){
        tickets.add(new Ticket(1, "Mouk Makara", "2025-03-12", "Phnom Penh", "toul kork", 150.0, true, Ticket.TicketStatus.BOOKED, "s1"));
        tickets.add(new Ticket(2, "Pich Bopha", "2025-03-12", "Phnom Penh", "tk", 200.0, false, Ticket.TicketStatus.CANCELLED, "s2"));
        tickets.add(new Ticket(3, "Sok Kong", "2025-03-12", "Phnom Penh", "phn", 250.0, true, Ticket.TicketStatus.COMPLETED, "s3"));
        tickets.add(new Ticket(4, "Phnom Khong", "2025-03-12", "Phnom Penh", "phkh", 300.0, false, Ticket.TicketStatus.BOOKED, "s4"));
        tickets.add(new Ticket(5, "Koh Kong", "2025-03-12", "Phnom Penh", "kohk", 350.0, true, Ticket.TicketStatus.CANCELLED, "s5"));
        tickets.add(new Ticket(6, "Koh Sanam", "2025-03-12", "Phnom Penh", "kohsa", 400.0, false, Ticket.TicketStatus.COMPLETED, "s6"));
        tickets.add(new Ticket(7, "Koh Lanta", "2025-03-12", "Phnom Penh", "kohla", 450.0, true, Ticket.TicketStatus.BOOKED, "s7"));
        tickets.add(new Ticket(8, "Koh San", "2025-03-12", "Phnom Penh", "kohsa", 500.0, false, Ticket.TicketStatus.CANCELLED, "s8"));
        tickets.add(new Ticket(9, "Koh Bang", "2025-03-12", "Phnom Penh", "kohba", 550.0, true, Ticket.TicketStatus.COMPLETED, "s9"));
        tickets.add(new Ticket(10, "Koh Svay", "2025-03-12", "Phnom Penh", "kohsv", 600.0, true, Ticket.TicketStatus.COMPLETED, "s10"));
        tickets.add(new Ticket(11, "Koh Pong", "2025-03-12", "Phnom Penh", "kohpo", 650.0, true, Ticket.TicketStatus.BOOKED, "s11"));
        tickets.add(new Ticket(12, "Koh Ton", "2025-03-12", "Phnom Penh", "kohto", 700.0, true, Ticket.TicketStatus.BOOKED, "s12"));
        tickets.add(new Ticket(13, "Koh Ton", "2025-03-12", "Phnom Penh", "kohto", 750.0, true, Ticket.TicketStatus.BOOKED, "s13"));
        tickets.add(new Ticket(14, "Koh Svay", "2025-03-12", "Phnom Penh", "kohsv", 800.0, true, Ticket.TicketStatus.COMPLETED, "s14"));
        tickets.add(new Ticket(15, "Koh Ton", "2025-03-12", "Phnom Penh", "kohto", 850.0, true, Ticket.TicketStatus.BOOKED, "s15"));
        tickets.add(new Ticket(16, "Koh Ton", "2025-03-12", "Phnom Penh", "kohto", 900.0, true, Ticket.TicketStatus.COMPLETED, "s16"));
        tickets.add(new Ticket(17, "Koh Ton", "2025-03-12", "Phnom Penh", "kohto", 950.0, true, Ticket.TicketStatus.COMPLETED, "s17"));
        tickets.add(new Ticket(18, "Koh Ton", "2025-03-12", "Phnom Penh", "kohto", 1000.0, true, Ticket.TicketStatus.BOOKED, "s18"));
        tickets.add(new Ticket(19, "Koh Ton", "2025-03-12", "Phnom Penh", "kohto", 1050.0, true, Ticket.TicketStatus.BOOKED, "s19"));
        tickets.add(new Ticket(20, "Koh Ton", "2025-03-12", "Phnom Penh", "kohto", 1100.0, true, Ticket.TicketStatus.BOOKED, "s20"));
        tickets.add(new Ticket(21, "Koh Ton", "2025-03-12", "Phnom Penh", "kohto", 1150.0, true, Ticket.TicketStatus.BOOKED, "s21"));
    }
    // createNewTicket
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public ResponseEntity<?> createNewTicket(@RequestBody TicketCreateRequest ticketCreateRequest){
        Ticket newTicket = new Ticket(
                nextTicketId++,
                ticketCreateRequest.passengerName(),
                ticketCreateRequest.travelDate(),
                ticketCreateRequest.sourceStation(),
                ticketCreateRequest.destinationStation(),
                ticketCreateRequest.price(),
                ticketCreateRequest.paymentStatus(),
                ticketCreateRequest.ticketStatus(),
                ticketCreateRequest.seatNumber());
        tickets.add(newTicket);

        TicketResponse ticketResponse = new TicketResponse(
                newTicket.getTicketId(),
                newTicket.getPassengerName(),
                newTicket.getTravelDate(),
                newTicket.getSourceStation(),
                newTicket.getDestinationStation(),
                newTicket.getPrice(),
                newTicket.getPaymentStatus(),
                newTicket.getTicketStatus(),
                newTicket.getSeatNumber()
        );
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseTicket.createSuccessResponse("Ticket created successfully", ticketResponse));
    }
    // Retrieve All Tickets
    @GetMapping
    @Operation(summary = "Get all tickets")
    public ResponseEntity<ApiResponseTicket<TicketListResponse>> getAllTickets(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        int startIndex = page * size;
        int endIndex = Math.min(startIndex + size, tickets.size());

        List<TicketResponse> ticketResponses = tickets.subList(startIndex, endIndex)
                .stream()
                .map(ticket -> new TicketResponse(
                        ticket.getTicketId(),
                        ticket.getPassengerName(),
                        ticket.getTravelDate(),
                        ticket.getSourceStation(),
                        ticket.getDestinationStation(),
                        ticket.getPrice(),
                        ticket.getPaymentStatus(),
                        ticket.getTicketStatus(),
                        ticket.getSeatNumber()
                ))
                .collect(Collectors.toList());
        // Calculate total pages
        int totalElements = tickets.size();
        int totalPages = (int) Math.ceil((double) totalElements / size);

        PaginationInfo paginationInfo = new PaginationInfo(totalElements, page + 1, size, totalPages);
        TicketListResponse responsePayload = new TicketListResponse(ticketResponses, paginationInfo);

        return ResponseEntity.ok(ApiResponseTicket.createSuccessResponse("Tickets retrieved successfully", responsePayload));
    }


    // Retrieve a Ticket by ID (using @PathVariable)
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseTicket<TicketResponse>> getTicketByID(@PathVariable Integer id) {
        // Find ticket by ID
        Optional<Ticket> ticketOptional = tickets.stream()
                .filter(ticket -> ticket.getTicketId().equals(id))
                .findFirst();

        if (ticketOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponseTicket.createErrorResponse("Ticket not found", HttpStatus.NOT_FOUND));
        }

        Ticket ticket = ticketOptional.get();

        // Convert Ticket to TicketResponse
        TicketResponse ticketResponse = TicketResponse.builder()
                .ticketId(ticket.getTicketId())
                .passengerName(ticket.getPassengerName())
                .travelDate(ticket.getTravelDate())
                .sourceStation(ticket.getSourceStation())
                .destinationStation(ticket.getDestinationStation())
                .price(ticket.getPrice())
                .paymentStatus(ticket.getPaymentStatus())
                .ticketStatus(ticket.getTicketStatus())
                .seatNumber(ticket.getSeatNumber())
                .build();

        return ResponseEntity.ok(ApiResponseTicket.createSuccessResponse("Ticket retrieved by id successfully.", ticketResponse));
    }


    //    Search for a Ticket by Passenger Name (using @RequestParam)
    @GetMapping("/search")
    public ResponseEntity<ApiResponseTicket<TicketListResponse>> searchTicketsByPassengerName(@RequestParam String passengerName,
                                                                                          @RequestParam(defaultValue = "0") int page,
                                                                                          @RequestParam(defaultValue = "10") int size) {
        // Filter tickets by passengerName
        List<Ticket> filteredTickets = tickets.stream()
                .filter(ticket -> ticket.getPassengerName().toLowerCase().contains(passengerName.toLowerCase()))
                .collect(Collectors.toList());

        if (filteredTickets.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponseTicket.createErrorResponse("No tickets found for given passenger name", HttpStatus.NOT_FOUND));
        }

        int startIndex = page * size;
        int endIndex = Math.min(startIndex + size, filteredTickets.size());

        List<TicketResponse> ticketResponses = filteredTickets.subList(startIndex, endIndex)
                .stream()
                .map(ticket -> new TicketResponse(
                        ticket.getTicketId(),
                        ticket.getPassengerName(),
                        ticket.getTravelDate(),
                        ticket.getSourceStation(),
                        ticket.getDestinationStation(),
                        ticket.getPrice(),
                        ticket.getPaymentStatus(),
                        ticket.getTicketStatus(),
                        ticket.getSeatNumber()
                ))
                .collect(Collectors.toList());

        // Calculate total pages
        int totalElements = filteredTickets.size();
        int totalPages = (int) Math.ceil((double) totalElements / size);

        PaginationInfo paginationInfo = new PaginationInfo(totalElements, page + 1, size, totalPages);
        TicketListResponse responsePayload = new TicketListResponse(ticketResponses, paginationInfo);

        return ResponseEntity.ok(ApiResponseTicket.createSuccessResponse("Tickets retrieved successfully", responsePayload));
    }

    // Filter Tickets by Ticket Status and Travel Date (using @RequestParam)
    @GetMapping("/filter")
    public ResponseEntity<ApiResponseTicket<TicketListResponse>> filterTicketsByStatusAndDate(@RequestParam Ticket.TicketStatus ticketStatus,
                                                                                              @RequestParam String travelDate,
                                                                                              @RequestParam(defaultValue = "0") int page,
                                                                                              @RequestParam(defaultValue = "10") int size) {
        // Filter tickets by ticket status and travel date
        List<Ticket> filteredTickets = tickets.stream()
                .filter(ticket -> ticket.getTicketStatus().equals(ticketStatus) && ticket.getTravelDate().equals(travelDate))
                .collect(Collectors.toList());

        if (filteredTickets.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponseTicket.createErrorResponse("No tickets found for given status and date", HttpStatus.NOT_FOUND));
        }

        int startIndex = page * size;
        int endIndex = Math.min(startIndex + size, filteredTickets.size());

        List<TicketResponse> ticketResponses = filteredTickets.subList(startIndex, endIndex)
                .stream().map(ticket -> new TicketResponse(
                        ticket.getTicketId(),
                        ticket.getPassengerName(),
                        ticket.getTravelDate(),
                        ticket.getSourceStation(),
                        ticket.getDestinationStation(),
                        ticket.getPrice(),
                        ticket.getPaymentStatus(),
                        ticket.getTicketStatus(),
                        ticket.getSeatNumber()
                ))
                .collect(Collectors.toList());

        // Calculate total pages
        int totalElements = tickets.size();
        int totalPages = (int) Math.ceil((double) totalElements / size);

        PaginationInfo paginationInfo = new PaginationInfo(totalElements, page + 1, size, totalPages);
        TicketListResponse responsePayload = new TicketListResponse(ticketResponses, paginationInfo);

        return ResponseEntity.ok(ApiResponseTicket.createSuccessResponse("filter by ticket status and travel date is successfully", responsePayload));
    }
    //    Update a Ticket by ID
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseTicket<TicketResponse>> updateTicketById(@PathVariable int id,
                                                                                        @RequestBody TicketUpdateRequest ticketUpdateRequest){
        // Find ticket by ID
        Optional<Ticket> ticketOptional = tickets.stream()
                .filter(ticket -> ticket.getTicketId().equals(id))
                .findFirst();

        if (ticketOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponseTicket.createErrorResponse("Ticket not found", HttpStatus.NOT_FOUND));
        }

        Ticket ticket = ticketOptional.get();

        // Update ticket fields
        ticket.setPassengerName(ticketUpdateRequest.passengerName());
        ticket.setTravelDate(ticketUpdateRequest.travelDate());
        ticket.setSourceStation(ticketUpdateRequest.sourceStation());
        ticket.setDestinationStation(ticketUpdateRequest.destinationStation());
        ticket.setPrice(ticketUpdateRequest.price());
        ticket.setPaymentStatus(ticketUpdateRequest.paymentStatus());
        ticket.setTicketStatus(ticketUpdateRequest.ticketStatus());
        ticket.setSeatNumber(ticketUpdateRequest.seatNumber());

        TicketResponse ticketResponse = new TicketResponse(ticket.getTicketId(), ticket.getPassengerName(), ticket.getSourceStation(), ticket.getDestinationStation(),
                ticket.getTravelDate(), ticket.getPrice(), ticket.getPaymentStatus(), ticket.getTicketStatus(), ticket.getSeatNumber());

        return ResponseEntity.ok(ApiResponseTicket.createSuccessResponse("Ticket updated successfully", ticketResponse));
    }
    // Delete a Ticket by ID return void
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseTicket<Void>> deleteTicketById(@PathVariable int id){
        // Find ticket by ID
        Optional<Ticket> ticketOptional = tickets.stream()
                .filter(ticket -> ticket.getTicketId().equals(id))
                .findFirst();

        if (ticketOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponseTicket.createErrorResponse("Ticket not found", HttpStatus.NOT_FOUND));
        }

        tickets.remove(ticketOptional.get());

        return ResponseEntity.ok(ApiResponseTicket.createSuccessResponse("Ticket deleted successfully", null));
    }
}
