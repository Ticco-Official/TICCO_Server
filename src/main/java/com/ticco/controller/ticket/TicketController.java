package com.ticco.controller.ticket;

import com.ticco.common.dto.ApiResponse;
import com.ticco.config.interceptor.Auth;
import com.ticco.config.resolver.UserId;
import com.ticco.service.ticket.TicketService;
import com.ticco.service.ticket.dto.request.CreateTicketRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@Api(tags = "Ticket")
@RequiredArgsConstructor
@RestController
public class TicketController {

    private final TicketService ticketService;

    @ApiOperation("[인증] 새로운 티켓을 생성합니다.")
    @Auth
    @PostMapping("/v1/ticket")
    public ApiResponse<String> createTicket(@Valid CreateTicketRequestDto request,
                                            @ApiParam(name = "image", value = "티켓의 이미지", required = true)
                                            @RequestPart(required = false) MultipartFile image,
                                            @ApiIgnore @UserId Long userId) {
        ticketService.createTicket(request, image, userId);
        return ApiResponse.SUCCESS;
    }
}
