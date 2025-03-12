package org.ksga.springhomework001.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    private Integer ticketId;
    private String passengerName;
    private String travelDate;
    private String sourceStation;
    private String destinationStation;
    private Double price;
    private Boolean paymentStatus;
    private TicketStatus ticketStatus;
    private String seatNumber;

    public enum TicketStatus {
        BOOKED, CANCELLED, COMPLETED
    }
}
