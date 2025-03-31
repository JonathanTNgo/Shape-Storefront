package com.ShapeStorefront.Shapes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ShapeStorefront.Shapes.Shape_Enums.ShapeColor;
import com.ShapeStorefront.Shapes.Shape_Enums.ShapeType;

@RestController
@RequestMapping("/api")
public class ShapesController {

    private final ShapesService shapesService;

    @Autowired
    public ShapesController(ShapesService shapesService) {
        this.shapesService = shapesService;
    }
    
    // Current website design:

    // 1. Home page showcasing limited shapes per pagination
    //    - RequestBody: (?Name, ?Type, ?Color, # elements per page, page #)

    // 2. Singular Product page
    //    - RequestBody: (Shape ID)

    @GetMapping("/Home")
    public ResponseEntity<?> getDisplayShapes(@RequestBody (required = false) ShapeRequestDTO request) {
        try {
            if (request == null) {
                request = new ShapeRequestDTO();
            }
    
            // Validate Color
            if (request.getColor() != null && !isValidColor(request.getColor().toString())) {
                throw new IllegalArgumentException("Invalid color");
            }
    
            // Validate Type
            if (request.getType() != null && !isValidType(request.getType().toString())) {
                throw new IllegalArgumentException("Invalid type");
            }
    
            // Page info validation NOT required as it is defaulted to 4 and 1 if not provided
    
            return ResponseEntity.accepted().body(shapesService.getDisplayShapes(request.getName(), request.getColor(), request.getType(), request.getElementsPerPage(), request.getPageNumber()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/shape")
    public ResponseEntity<?> getShape(@RequestBody (required = true) int id) {
        try {
            return ResponseEntity.accepted().body(shapesService.getShape(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Valid color data from user
    private boolean isValidColor(String color) {
        try {
            ShapeColor.valueOf(color);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    // Valid type data from user
    private boolean isValidType(String type) {
        try {
            ShapeType.valueOf(type);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}