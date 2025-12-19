package org.example.visitors;

import org.example.entity.Booking;

public abstract class DiscountVisitor implements Cloneable {
    public boolean discountApplied = false;
    public String description;

    @Override
    protected DiscountVisitor clone() throws CloneNotSupportedException {
        return (DiscountVisitor) super.clone();
    }

    public abstract void apply(Booking booking) throws CloneNotSupportedException;
}
