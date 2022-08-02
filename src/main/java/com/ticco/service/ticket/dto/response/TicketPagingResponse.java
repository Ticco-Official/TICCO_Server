package com.ticco.service.ticket.dto.response;

import com.ticco.domain.ticket.Ticket;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TicketPagingResponse {

    private static final long LAST_PAGE = -1L;

    private List<TicketInfoResponse> contents = new ArrayList<>();
    private long lastPage;
    private long nextPage;

    private TicketPagingResponse(List<TicketInfoResponse> contents, long lastPage, long nextPage) {
        this.contents = contents;
        this.lastPage = lastPage;
        this.nextPage = nextPage;
    }

    public static TicketPagingResponse of(Page<Ticket> ticketPaging) {
        if (!ticketPaging.hasNext()) {
            return TicketPagingResponse.newLastScroll(ticketPaging.getContent(), ticketPaging.getTotalPages() - 1);
        }
        return TicketPagingResponse.newPagingHasNext(ticketPaging.getContent(), ticketPaging.getTotalPages() - 1, ticketPaging.getPageable().getPageNumber() + 1);
    }

    private static TicketPagingResponse newLastScroll(List<Ticket> ticketPaging, long lastPage) {
        return newPagingHasNext(ticketPaging, lastPage, LAST_PAGE);
    }

    private static TicketPagingResponse newPagingHasNext(List<Ticket> ticketPaging, long lastPage, long nextPage) {
        return new TicketPagingResponse(getContents(ticketPaging), lastPage, nextPage);
    }

    private static List<TicketInfoResponse> getContents(List<Ticket> ticketPaging) {
        return ticketPaging.stream()
                .map(TicketInfoResponse::of)
                .collect(Collectors.toList());
    }
}
