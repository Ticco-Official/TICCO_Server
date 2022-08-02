package com.ticco.service.ticket;

import com.ticco.domain.ticket.repository.TicketRepository;
import com.ticco.domain.user.User;
import com.ticco.domain.user.repository.UserRepository;
import com.ticco.service.ticket.dto.request.RetrieveTicketsRequestDto;
import com.ticco.service.ticket.dto.response.TicketPagingResponse;
import com.ticco.service.user.UserServiceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class TicketRetrieveService {

    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;

    public TicketPagingResponse retrieveTicketsUsingPaging(RetrieveTicketsRequestDto request, Pageable pageable, Long userId) {
        User user = UserServiceUtils.findUserById(userRepository, userId);
        return TicketPagingResponse.of(ticketRepository.findTicketByFilterConditionUsingPaging(user.getOnboarding(), request.getCategory(), pageable));
    }
}
