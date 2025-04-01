package com.ShapeStorefront.Shapes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/home")
    public ResponseEntity<?> getDisplayShapes(@RequestBody (required = false) ShapeRequestDTO request, Pageable pageable) {
        try {
            if (request == null) {
                request = new ShapeRequestDTO();
                System.out.println("Request is null");
            }

            // Validate page and size
            if (pageable.getPageNumber() < 0 || pageable.getPageSize() < 1) {
                throw new IllegalArgumentException("Page number and size must be greater than 0");
            }
    
            // Validate Color
            if (request.getColor() != null && !isValidColor(request.getColor().toString())) {
                throw new IllegalArgumentException("Invalid color");
            }
    
            // Validate Type
            if (request.getType() != null && !isValidType(request.getType().toString())) {
                throw new IllegalArgumentException("Invalid type");
            }
    
            return ResponseEntity.accepted().body(shapesService.getDisplayShapes(request.getName(), request.getColor(), request.getType(), pageable));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/shape")
    public ResponseEntity<?> getShape(@RequestParam (required = true) int id) {
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