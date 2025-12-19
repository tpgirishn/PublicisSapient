package org.example.visitors;

import org.example.entity.Booking;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class CountDiscountVisitor extends DiscountVisitor{
    public CountDiscountVisitor() {
        this.description = "Pay the cost of only two tickets if you purchase three";
    }

    @Override
    public void apply(Booking booking) {
        int numberOfFreeSeats = 0;
        for (int i = 3; i <= booking.getBookingSeats().size(); i=i+3){
            numberOfFreeSeats++;
        }
        if (numberOfFreeSeats > 0) {
            booking.setTotalAmount(booking.getTotalAmount()
                    .subtract(booking.getTotalAmount()
                            .divide(new BigDecimal(booking.getBookingSeats().size()))
                            .multiply(new BigDecimal(numberOfFreeSeats)).divide(new BigDecimal(2))));
            this.discountApplied = true;
            booking.getDiscountsApplied().add(this);
        }
    }
}
