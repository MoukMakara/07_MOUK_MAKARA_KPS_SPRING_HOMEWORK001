package org.ksga.springhomework001.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record TicketUpdatePaymentStatusRequest(
        List<Integer> ticketIds,
        Boolean paymentStatus
) {}
