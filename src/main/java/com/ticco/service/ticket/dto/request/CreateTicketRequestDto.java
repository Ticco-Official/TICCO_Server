package com.ticco.service.ticket.dto.request;

import com.ticco.domain.ticket.TicketCategory;
import com.ticco.domain.ticket.TicketTheme;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateTicketRequestDto {

    @ApiModelProperty(value = "티켓의 날짜 (yyyy-MM-dd)", example = "2022-01-01", required = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "{ticket.date.notNull}")
    private LocalDate date;

    @ApiModelProperty(value = "티켓의 카테고리", example = "MUSICAL", required = true)
    private TicketCategory category;

    @ApiModelProperty(value = "티켓의 제목", example = "토르 러브 앤 썬더", required = true)
    @NotEmpty(message = "{ticket.title.notEmpty}")
    private String title;

    @ApiModelProperty(value = "티켓의 만족도", example = "5.0", required = true)
    private double rating;

    @ApiModelProperty(value = "티켓의 글", example = "오랜만에 만나는 친구랑 함께봤던 토르!  토르도 과거 여자친구와 오랜만에 만나서 만들어가는 스토리가 재밌었다. 다만, 이번엔 마블 작품 중에서도 훨씬 가벼운 느낌이 있었달까? 그래서인지 살짝 아쉬움도 남았다. 이러나 저러나 마블 영화는 앞으로도 계속 챙겨볼 것같긴하다 :)", required = true)
    @NotNull(message = "{ticket.content.notNull}")
    @Size(max = 200, message = "{ticket.content.size}")
    private String content;

    @ApiModelProperty(value = "티켓의 테마 - 업데이트 예정", example = "NORMAL", required = true)
    private TicketTheme theme;
}
