package com.ShapeStorefront.Shapes;

import com.ShapeStorefront.Shapes.Shape_Enums.ShapeColor;
import com.ShapeStorefront.Shapes.Shape_Enums.ShapeType;

import jakarta.validation.constraints.*;

public class ShapeRequestDTO {
    private String name;
    private ShapeColor color;
    private ShapeType type;

    @Min(value = 1, message = "Elements per page must be greater than 0")
    @Max(value = 50, message = "Elements per page must be less than 50")
    private int elementsPerPage = 4;

    @Min(value = 1, message = "Page number must be greater than 0")
    // NOT 0 index based
    private int pageNumber = 1;

    public ShapeRequestDTO() {
    }

    public ShapeRequestDTO(String name, ShapeColor color, ShapeType type, int elementsPerPage, int pageNumber) {
        this.name = name;
        this.color = color;
        this.type = type;
        this.elementsPerPage = elementsPerPage;
        this.pageNumber = pageNumber;
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

    public int getElementsPerPage() {
        return elementsPerPage;
    }

    public int getPageNumber() {
        return pageNumber;
    }
}
