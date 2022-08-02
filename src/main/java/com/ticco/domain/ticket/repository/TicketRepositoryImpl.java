package com.ticco.domain.ticket.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ticco.domain.ticket.Ticket;
import com.ticco.domain.ticket.TicketCategory;
import com.ticco.domain.user.Onboarding;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static com.ticco.domain.ticket.QTicket.ticket;

@RequiredArgsConstructor
public class TicketRepositoryImpl implements TicketRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Ticket> findTicketByFilterConditionUsingPaging(Onboarding onboarding, @Nullable TicketCategory category, Pageable pageable) {
        List<OrderSpecifier> orders = getAllOrderSpecifiers(pageable);
        List<Ticket> tickets = queryFactory
                .selectFrom(ticket).distinct()
                .where(
                        ticket.onboarding.eq(onboarding),
                        eqCategory(category)
                )
                .orderBy(orders.toArray(OrderSpecifier[]::new))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(tickets, pageable, queryFactory
                .selectFrom(ticket).distinct()
                .where(
                        ticket.onboarding.eq(onboarding),
                        eqCategory(category)
                ).fetch().size());
    }

    private BooleanExpression eqCategory(TicketCategory category) {
        if (category == null) {
            return null;
        }
        return ticket.category.eq(category);
    }

    private List<OrderSpecifier> getAllOrderSpecifiers(Pageable pageable) {
        List<OrderSpecifier> orders = new ArrayList<>();
        for (Sort.Order order : pageable.getSort()) {
            Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
            Path<Object> fieldPath = Expressions.path(Object.class, ticket, order.getProperty());
            orders.add(new OrderSpecifier(direction, fieldPath));
        }
        return orders;
    }
}
