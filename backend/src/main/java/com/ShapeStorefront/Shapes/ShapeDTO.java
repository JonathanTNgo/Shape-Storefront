package com.ShapeStorefront.Shapes;


// This compressed shape is used to display a limited number of information about a shape
// Meant for use on the product summary page displaying multiple shapes
public class ShapeDTO {
    private String name;
    private String id;
    private String imageUrl_small;

    public ShapeDTO(String name, String id, String imageUrl_small) {
        this.name = name;
        this.id = id;
        this.imageUrl_small = imageUrl_small;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }   

    public String getImageUrl_small() {
        return imageUrl_small;
    }

    @Override
    public String toString() {
        return "ShapeCompress{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", imageUrl_small='" + imageUrl_small + '\'' +
                '}';
    }
}
