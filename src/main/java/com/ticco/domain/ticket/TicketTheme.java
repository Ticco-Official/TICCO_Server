package com.ticco.domain.ticket;

import com.ticco.common.model.EnumModel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum TicketTheme implements EnumModel {
    NORMAL("기본");
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
