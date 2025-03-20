package com.ShapeStorefront.Shapes;

@Entity
@Table(name = "Products")
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

    @Column(nullable = false)
    private ShapeType type;

    @Column(nullable = false)
    private ShapeColor color;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String imageUrl_small;

    @Column(nullable = false)
    private String imageUrl_large;

    public Shape() {
    }

    public Shape(ShapeType type, ShapeColor color, String name, String imageUrl_small, String imageUrl_large    ) {
        this.type = type;
        this.color = color;
        this.name = name;
        this.imageUrl_small = imageUrl_small;
        this.imageUrl_large = imageUrl_large;
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

    public String getImageUrl_small() {
        return imageUrl_small;
    }

    public String getImageUrl_large() {
        return imageUrl_large;
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

    public void setImageUrl_small(String imageUrl_small) {
        this.imageUrl_small = imageUrl_small;
    }

    public void setImageUrl_large(String imageUrl_large) {
        this.imageUrl_large = imageUrl_large;
    }

    @Override
    public String toString() {
        return "Shape {" +
                "type=" + type +
                ", color=" + color +
                ", name='" + name + '\'' +
                ", imageUrl_small='" + imageUrl_small + '\'' +
                ", imageUrl_large='" + imageUrl_large + '\'' +
                '}';
    }
    
}