package com.ticco.controller.ticket;

import com.ticco.common.dto.ApiResponse;
import com.ticco.common.success.SuccessCode;
import com.ticco.config.interceptor.Auth;
import com.ticco.config.resolver.UserId;
import com.ticco.config.validator.AllowedSortProperties;
import com.ticco.service.ticket.TicketRetrieveService;
import com.ticco.service.ticket.dto.request.RetrieveTicketsRequestDto;
import com.ticco.service.ticket.dto.response.TicketPagingResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@Api(tags = "Ticket")
@Validated
@RequiredArgsConstructor
@RestController
public class TicketRetrieveController {

    private final TicketRetrieveService ticketRetrieveService;

    @ApiOperation("[인증] 특정 조건에 해당하는 티켓 목록을 페이지네이션으로 조회합니다.")
    @Auth
    @GetMapping("/v1/ticket")
    public ApiResponse<TicketPagingResponse> retrieveTickets(@Valid RetrieveTicketsRequestDto request,
                                                             @AllowedSortProperties({"createdAt", "rating"}) Pageable pageable,
                                                             @ApiIgnore @UserId Long userId) {
        return ApiResponse.success(SuccessCode.READ_TICKET_SUCCESS, ticketRetrieveService.retrieveTicketsUsingPaging(request, pageable, userId));
    }
}
