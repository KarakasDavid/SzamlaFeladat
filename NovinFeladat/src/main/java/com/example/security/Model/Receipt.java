package com.example.security.Model;

import jakarta.persistence.*;

import javax.validation.constraints.Size;
import java.time.LocalDate;


@Entity
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false)
    private LocalDate start_date;

    @Column(nullable = false)
    private LocalDate end_date;

    @Column(nullable = false, length = 20)
    private String item_name;

    @Column(nullable = false,length = 40)
    private String comment;

    @Column(nullable = false, length = 1000000)
    private int price;

    public Long getId() {
        return id;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public String getComment() {
        return comment;
    }

    public String getItem_name() {
        return item_name;
    }
}
