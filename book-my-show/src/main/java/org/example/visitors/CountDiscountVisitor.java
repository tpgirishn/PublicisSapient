package org.example.visitors;

import org.example.entity.Booking;
import org.example.entity.BookingSeat;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CountDiscountVisitor extends DiscountVisitor {
    public CountDiscountVisitor() {
        this.description = "50% discount on third ticket if you purchase three tickets";
    }

    @Override
    public void apply(Booking booking) throws CloneNotSupportedException {
        int numberOfFreeSeats = 0;
        for (int i = 3; i <= booking.getBookingSeats().size(); i = i + 3) {
            numberOfFreeSeats++;
        }
        Set<BookingSeat> sortedSeats = booking.getBookingSeats().stream().sorted(new Comparator<BookingSeat>() {
            @Override
            public int compare(BookingSeat o1, BookingSeat o2) {
                return o1.getPrice().subtract(o2.getPrice()).intValueExact();
            }
        }).collect(Collectors.toCollection(LinkedHashSet::new));
        BigDecimal amountToBeSubtracted = sortedSeats.stream().limit(numberOfFreeSeats).map((bookingSeat) -> {
            return bookingSeat.getSeat().getPrice();
        }).reduce((bigDecimal, bigDecimal2) -> {
            return bigDecimal.add(bigDecimal2);
        }).get();
        Double amountToBeAdded = sortedSeats.stream().limit(numberOfFreeSeats).map((bookingSeat) -> {
            return bookingSeat.getSeat().getPrice().divide(new BigDecimal(2));
        }).collect(Collectors.summingDouble(mapper -> mapper.doubleValue()));
        if (numberOfFreeSeats > 0) {
            booking.setTotalAmount(booking.getTotalAmount()
                    .subtract(amountToBeSubtracted).add(new BigDecimal(amountToBeAdded)));
            this.discountApplied = true;
            booking.getDiscountsApplied().add(this.clone());
        }
        this.discountApplied = false;
    }
}
