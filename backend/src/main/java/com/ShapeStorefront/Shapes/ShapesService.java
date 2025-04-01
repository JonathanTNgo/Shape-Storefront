package com.ShapeStorefront.Shapes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<ShapeDTO> getDisplayShapes(String name, ShapeColor color, ShapeType type, Pageable pageable) {
        // Get relevant shapes from database
        Page<Shape> shapes = shapesRepository.findFilter(name, color, type, pageable);
        // Page<Shape> shapes = shapesRepository.findFilterTest(pageable);
        
        // Iterate through shapes and compress them (ONLY IF THEY ARE DISPLAYED ON HOME PAGE)
        return shapes.map(shape -> shape.compress());
    }

    public Shape getShape(int id) {
        return shapesRepository.findById(id).orElse(null);
    }
}