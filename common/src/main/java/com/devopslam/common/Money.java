package com.devopslam.common;


import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class Money {
    private static Money ZERO = new Money(0);
    private BigDecimal amount;

    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    public Money(String amount) {
        this.amount =  new BigDecimal(amount);
    }

    public Money(int i) {
        this.amount = new BigDecimal(i);
    }

    public Money() {
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                '}';
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
