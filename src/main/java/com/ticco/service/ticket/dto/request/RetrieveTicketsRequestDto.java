package com.ticco.service.ticket.dto.request;

import com.ticco.domain.ticket.TicketCategory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RetrieveTicketsRequestDto {

    @Nullable
    private TicketCategory category;
}
