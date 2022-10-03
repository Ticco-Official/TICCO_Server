package com.ticco.domain.ticket;

import com.ticco.common.model.EnumModel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum TicketTheme implements EnumModel {

    BLACK("검정색"),
    GREEN("초록색"),
    BLUE("파랑색"),
    WHITE("흰색");
    
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
