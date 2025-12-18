package org.example.visitors;

import org.example.entity.Booking;

public abstract class DiscountVisitor {
    public boolean discountApplied = false;
    public String description;
    public abstract void apply(Booking booking);
}
