package com.devopslam.ftgo.consumerservice.domain;

import com.devopslam.common.domain.Money;
import com.devopslam.common.domain.PersonName;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "consumers")
public class Consumer {

    @Id
    private String id;

    @Embedded
    private PersonName name;

    @Embedded
    private Money money = new Money(0);

    public Consumer() {
    }

    public Consumer(String id, PersonName name) {
        this.id = id;
        this.name = name;
    }

    public Consumer(String id, PersonName name, Money money) {
        this.id = id;
        this.name = name;
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PersonName getName() {
        return name;
    }

    public void setName(PersonName name) {
        this.name = name;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Consumer{" +
                "id='" + id + '\'' +
                ", name=" + name +
                '}';
    }
}
