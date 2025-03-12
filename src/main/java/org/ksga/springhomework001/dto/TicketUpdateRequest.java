package org.ksga.springhomework001.dto;

import lombok.Builder;
import org.ksga.springhomework001.domain.Ticket;

import java.util.Date;
@Builder
public record TicketUpdateRequest(
        String passengerName,
        String travelDate,
        String sourceStation,
        String destinationStation,
        Double price,
        Boolean paymentStatus,
        Ticket.TicketStatus ticketStatus,
        String seatNumber
) {
}
