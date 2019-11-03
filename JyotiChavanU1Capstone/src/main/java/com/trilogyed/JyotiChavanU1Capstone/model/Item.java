package com.trilogyed.JyotiChavanU1Capstone.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {

    private String item_type;
    private  int item_id;
    private BigDecimal price;
    private int quantity;

    public Item(){}

    public Item(int item_id, BigDecimal price, int quantity) {
        this.item_id = item_id;
        this.price = price;
        this.quantity = quantity;
    }

    public Item(String item_type, int item_id, BigDecimal price, int quantity) {
        this.item_type = item_type;
        this.item_id = item_id;
        this.price = price;
        this.quantity = quantity;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return item_id == item.item_id &&
                quantity == item.quantity &&
                item_type.equals(item.item_type) &&
                price.equals(item.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item_type, item_id, price, quantity);
    }

    @Override
    public String toString() {
        return "Item{" +
                "item_type='" + item_type + '\'' +
                ", item_id=" + item_id +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
