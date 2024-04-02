package com.Perseo.inventory_app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@Table(name="product")
public class Product implements Serializable {


    @Serial
    private static final long serialVersionUID = 3148391154227596231L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    private int account;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Category category;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "picture")
    private byte[] picture;

}
