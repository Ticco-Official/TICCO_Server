package com.ticco.domain.ticket.repository;

import com.ticco.domain.ticket.Ticket;
import com.ticco.domain.ticket.TicketCategory;
import com.ticco.domain.user.Onboarding;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TicketRepositoryCustom {

    Page<Ticket> findTicketByFilterConditionUsingPaging(Onboarding onboarding, @Nullable TicketCategory category, Pageable pageable);
}
