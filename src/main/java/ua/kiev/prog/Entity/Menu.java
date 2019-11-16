package ua.kiev.prog.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="menu")
public class Menu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;

    @Column(name="name_of_dish", nullable = false)
    private String dish;

    @Column(name="price",nullable = false)
    private double price;
    @Column(name="weight",nullable = false)
    private double weight;
    @Column(name="sale")
    private char sale;

    public Menu() {}

    public Menu(String dish, double price, double weight, char sale) {
        this.dish = dish;
        this.price = price;
        this.weight = weight;
        this.sale = sale;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public char getSale() {
        return sale;
    }

    public void setSale(char sale) {
        this.sale = sale;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id ")
                .append(id)
                .append(" " + dish.toUpperCase())
                .append(" price: ")
                .append(price)
                .append(" weight: ")
                .append(weight)
                .append(" sale: ")
                .append(sale);
        return sb.toString();
    }
}
