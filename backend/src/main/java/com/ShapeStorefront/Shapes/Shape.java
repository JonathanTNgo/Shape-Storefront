package com.ShapeStorefront.Shapes;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import com.ShapeStorefront.Shapes.Shape_Enums.ShapeType;
import com.ShapeStorefront.Shapes.Shape_Enums.ShapeColor;

@Entity
@Table(name = "products")
public class Shape {
    
    @Id
    @SequenceGenerator(
        name = "id_sequence",
        sequenceName = "id_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "id_sequence"
    )
    @Column(name = "id", nullable = false)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ShapeType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "color", nullable = false)
    private ShapeColor color;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "image_url_small", nullable = false)
    private String image_url_small;

    @Column(name = "image_url_large", nullable = false)
    private String image_url_large;

    public Shape() {
    }

    public Shape(ShapeType type, ShapeColor color, String name, String image_url_small, String image_url_large) {
        this.type = type;
        this.color = color;
        this.name = name;
        this.image_url_small = image_url_small;
        this.image_url_large = image_url_large;
    }

    public ShapeDTO compress() {
        return new ShapeDTO(this.name, this.id, this.image_url_small);
    }

    public long getId() {
        return id;
    }

    public ShapeType getType() {
        return type;
    }

    public ShapeColor getColor() {  
        return color;
    }

    public String getName() {
        return name;
    }   

    public String getimage_url_small() {
        return image_url_small;
    }

    public String getimage_url_large() {
        return image_url_large;
    }

    public void setType(ShapeType type) {
        this.type = type;
    }

    public void setColor(ShapeColor color) {
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }   

    public void setimage_url_small(String image_url_small) {
        this.image_url_small = image_url_small;
    }

    public void setimage_url_large(String image_url_large) {
        this.image_url_large = image_url_large;
    }

    @Override
    public String toString() {
        return "Shape {" +
                "type=" + type +
                ", color=" + color +
                ", name='" + name + '\'' +
                ", image_url_small='" + image_url_small + '\'' +
                ", image_url_large='" + image_url_large + '\'' +
                '}';
    }
    
}