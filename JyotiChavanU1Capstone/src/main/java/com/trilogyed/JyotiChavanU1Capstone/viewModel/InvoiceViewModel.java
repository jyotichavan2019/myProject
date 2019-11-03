package com.trilogyed.JyotiChavanU1Capstone.viewModel;

import com.trilogyed.JyotiChavanU1Capstone.model.Console;
import com.trilogyed.JyotiChavanU1Capstone.model.Game;
import com.trilogyed.JyotiChavanU1Capstone.model.Item;
import com.trilogyed.JyotiChavanU1Capstone.model.TShirt;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceViewModel {
    private int invoiceId;
    @NotNull(message="Please supply a name")
    private String name;
    @NotNull(message="Please supply street")
    private String street;
    @NotNull(message="Please supply city")
    private String city;
    @NotNull(message="Please supply state")
    private String state;
    @NotNull(message="Please supply zipcode")
    private String zipcode;
    @NotNull(message="Please supply item_id")
    private int item_id;
    @NotNull(message="Please supply item_type")
    private String item_type;
    @NotNull(message="Please supply unitPrice")
    private BigDecimal unitPrice;
    @NotNull(message="Please supply qtantity")
    private int quantity;
    private BigDecimal subtotal;
    private BigDecimal salesTax;
    private BigDecimal processingFee;
    private BigDecimal total;
    private Item item;
    private Game game;
    private Console console;
    private TShirt tShirt;


    public  InvoiceViewModel(){}

    public InvoiceViewModel(@NotNull(message = "Please supply a name") String name, @NotNull(message = "Please supply street") String street, @NotNull(message = "Please supply city") String city, @NotNull(message = "Please supply state") String state, @NotNull(message = "Please supply zipcode") String zipcode, @NotNull(message = "Please supply item_id") int item_id, @NotNull(message = "Please supply item_type") String item_type, @NotNull(message = "Please supply unitPrice") BigDecimal unitPrice, @NotNull(message = "Please supply qtantity") int quantity, BigDecimal subtotal, BigDecimal salesTax, BigDecimal processingFee, BigDecimal total, Item item, Game game, Console console, TShirt tShirt) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.item_id = item_id;
        this.item_type = item_type;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.salesTax = salesTax;
        this.processingFee = processingFee;
        this.total = total;
        this.item = item;
        this.game = game;
        this.console = console;
        this.tShirt = tShirt;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int quantity, BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(BigDecimal salesTax) {
        this.salesTax = salesTax;
    }

    public BigDecimal getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(BigDecimal processingFee) {
        this.processingFee = processingFee;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Console getConsole() {
        return console;
    }

    public void setConsole(Console console) {
        this.console = console;
    }

    public TShirt gettShirt() {
        return tShirt;
    }

    public void settShirt(TShirt tShirt) {
        this.tShirt = tShirt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceViewModel that = (InvoiceViewModel) o;
        return invoiceId == that.invoiceId &&
                item_id == that.item_id &&
                quantity == that.quantity &&
                name.equals(that.name) &&
                street.equals(that.street) &&
                city.equals(that.city) &&
                state.equals(that.state) &&
                zipcode.equals(that.zipcode) &&
                item_type.equals(that.item_type) &&
                Objects.equals(unitPrice, that.unitPrice) &&
                Objects.equals(subtotal, that.subtotal) &&
                Objects.equals(salesTax, that.salesTax) &&
                Objects.equals(processingFee, that.processingFee) &&
                Objects.equals(total, that.total) &&
                Objects.equals(item, that.item) &&
                Objects.equals(game, that.game) &&
                Objects.equals(console, that.console) &&
                Objects.equals(tShirt, that.tShirt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceId, name, street, city, state, zipcode, item_id, item_type, unitPrice, quantity, subtotal, salesTax, processingFee, total, item, game, console, tShirt);
    }

    @Override
    public String toString() {
        return "InvoiceViewModel{" +
                "invoiceId=" + invoiceId +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", item_id=" + item_id +
                ", item_type='" + item_type + '\'' +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                ", salesTax=" + salesTax +
                ", processingFee=" + processingFee +
                ", total=" + total +
                ", item=" + item +
                ", game=" + game +
                ", console=" + console +
                ", tShirt=" + tShirt +
                '}';
    }
}
