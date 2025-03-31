package com.ShapeStorefront.Shapes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShapeStorefront.Shapes.Shape_Enums.ShapeColor;
import com.ShapeStorefront.Shapes.Shape_Enums.ShapeType;

@Service
public class ShapesService {

    private final ShapesRepository shapesRepository;

    @Autowired
    public ShapesService(ShapesRepository shapesRepository) {
        this.shapesRepository = shapesRepository;
    }

    public List<ShapeDTO> getDisplayShapes(String name, ShapeColor color, ShapeType type, int elementsPerPage, int pageNumber) {
        // Get COUNT of relevant shapes from database
        long totalRelevantShapes;

        if (name == null && color == null && type == null) {
            totalRelevantShapes = shapesRepository.count();
        } else {
            totalRelevantShapes = shapesRepository.countFiltered(name, color, type);
        }

        // Handle pagination validation
        // Current range assuming page and elementsPerPage are valid
        int start = (pageNumber - 1) * elementsPerPage;
        long end = Math.min(start + elementsPerPage, totalRelevantShapes);

        // This may not get hit as we have validation for page number and elements per page
        if (start < 0) {
            throw new IllegalArgumentException("Invalid page number or elements per page");
        }

        // Return empty list if end is greater than total relevant shapes
        if (end > totalRelevantShapes) {
            return new ArrayList<>();
        }

        // Get relevant shapes from database
        // Let database handle pagination instead of doing it here
        List<Shape> shapes = shapesRepository.findAll(name, color, type, start, end);
        
        // Iterate through shapes and compress them (ONLY IF THEY ARE DISPLAYED ON HOME PAGE)
        List<ShapeDTO> shapesDTO = new ArrayList<>();
        
        for (Shape shape : shapes) {
            shapesDTO.add(shape.compress());
        }

        return shapesDTO;
    }

    public Shape getShape(int id) {
        return shapesRepository.findById(id).orElse(null);
    }
}