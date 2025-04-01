package com.ShapeStorefront.Shapes;

import com.ShapeStorefront.Shapes.Shape_Enums.ShapeColor;
import com.ShapeStorefront.Shapes.Shape_Enums.ShapeType;

public class ShapeRequestDTO {
    private String name;
    private ShapeColor color;
    private ShapeType type;

    public ShapeRequestDTO() {
    }

    public ShapeRequestDTO(String name, ShapeColor color, ShapeType type) {
        this.name = name;
        this.color = color;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public ShapeColor getColor() {
        return color;
    }   

    public ShapeType getType() {
        return type;
    }

}
