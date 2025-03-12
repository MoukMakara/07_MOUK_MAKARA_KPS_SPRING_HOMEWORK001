package org.ksga.springhomework001.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TicketListResponse {
    private List<TicketResponse> items;
    private PaginationInfo pagination;
}