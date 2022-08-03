package com.ticco.service.ticket;

import com.ticco.common.exception.ForbiddenException;
import com.ticco.common.exception.NotFoundException;
import com.ticco.domain.ticket.Ticket;
import com.ticco.domain.ticket.repository.TicketRepository;
import com.ticco.domain.user.Onboarding;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.ticco.common.exception.ErrorCode.FORBIDDEN_TICKET_EXCEPTION;
import static com.ticco.common.exception.ErrorCode.NOT_FOUND_TICKET_EXCEPTION;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TicketServiceUtils {

    public static Ticket findTicketById(TicketRepository ticketRepository, Long ticketId) {
        Ticket ticket = ticketRepository.findTicketById(ticketId);
        if (ticket == null) {
            throw new NotFoundException(String.format("존재하지 않는 티켓 (%s) 입니다", ticketId), NOT_FOUND_TICKET_EXCEPTION);
        }
        return ticket;
    }

    public static void checkForbiddenTicket(Onboarding onboarding, Ticket ticket) {
        if (!ticket.getOnboarding().getId().equals(onboarding.getId())) {
            throw new ForbiddenException(String.format("(%s) 온보딩은 (%s) 티켓에 대한 권한이 없습니다.", onboarding.getId(), ticket.getId()), FORBIDDEN_TICKET_EXCEPTION);
        }
    }
}
