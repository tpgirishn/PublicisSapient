package org.example.visitors;

import org.example.entity.Booking;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.ZoneOffset;
@Component
public class TimeDiscountVisitor extends DiscountVisitor{
    public TimeDiscountVisitor() {
        this.description = "Get 10% disount on total amount if showtimes are between 1:00 pm to 5:00 pm";
    }

    @Override
    public void apply(Booking booking) {
        LocalTime showStartTime = LocalTime.ofInstant(booking.getShowtime().getStartTime(), ZoneOffset.UTC);
        if (showStartTime.getHour()>=13 && showStartTime.getHour()<=17) {
            booking.setTotalAmount(booking.getTotalAmount()
                    .subtract(booking.getTotalAmount()
                            .multiply(new BigDecimal(0.1))));
            this.discountApplied = true;
            booking.getDiscountsApplied().add(this);
        }
    }
}
