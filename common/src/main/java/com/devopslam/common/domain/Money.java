package com.devopslam.common.domain;


import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class Money {

    public static Money ZERO = new Money(0);
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

    public Money multiply(int quantity) {
        return new Money(amount.multiply(new BigDecimal(quantity)));
    }

    public Money add(Money delta) {
        return new Money(amount.add(delta.amount));
    }
}
