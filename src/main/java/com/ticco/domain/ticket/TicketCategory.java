package com.ticco.domain.ticket;

import com.ticco.common.model.EnumModel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum TicketCategory implements EnumModel {
    MUSICAL("뮤지컬"),
    THEATER("연극"),
    MOVIE("영화"),
    EXHIBITION("전시회"),
    CONCERT("콘서트"),
    FESTIVAL("페스티벌");
    private final String value;

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return value;
    }
}
