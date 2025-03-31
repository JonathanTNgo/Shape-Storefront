package com.ShapeStorefront.Shapes;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ShapeStorefront.Shapes.Shape_Enums.ShapeColor;
import com.ShapeStorefront.Shapes.Shape_Enums.ShapeType;

public interface ShapesRepository extends JpaRepository<Shape, Integer> {
    
    @Query("SELECT COUNT(*) FROM Shape WHERE (:name IS NULL OR name = :name) AND (:color IS NULL OR color = :color) AND (:type IS NULL OR type = :type)")
    long countFiltered(@Param("name") String name, @Param("color") ShapeColor color, @Param("type") ShapeType type);

    @Query("SELECT s FROM Shape WHERE (:name IS NULL OR name = :name) AND (:color IS NULL OR color = :color) AND (:type IS NULL OR type = :type)")
    List<Shape> findFilter(@Param("name") String name, @Param("color") ShapeColor color, @Param("type") ShapeType type, Pageable pageable);
}
