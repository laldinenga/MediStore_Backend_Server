package com.medistore.MedServer.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import java.util.Date;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NaturalId(mutable = true)
    @Column(nullable = false)
    private String name;
    private float price;
    private String manufacturer;
    private Date manufacturingdate;
    private Date expirydate;
    private String Category;
    private String imageName;
    private byte[] imageData;

}
