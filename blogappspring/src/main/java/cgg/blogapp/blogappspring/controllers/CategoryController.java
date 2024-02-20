package cgg.blogapp.blogappspring.controllers;

import cgg.blogapp.blogappspring.exceptions.ResourceNotFoundException;
import cgg.blogapp.blogappspring.payloads.CategoryDTO;
import cgg.blogapp.blogappspring.payloads.CategoryDTO;
import cgg.blogapp.blogappspring.payloads.UserDTO;
import cgg.blogapp.blogappspring.services.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
@SecurityRequirement(name = "bearerScheme")
@CrossOrigin("*")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @PostMapping("/")
  public ResponseEntity<CategoryDTO> createCategory(
    @Valid @RequestBody CategoryDTO categoryDTO
  ) {
    CategoryDTO user = categoryService.createCategory(categoryDTO);
    return new ResponseEntity<CategoryDTO>(user, HttpStatus.CREATED);
  }

  @GetMapping("/")
  public ResponseEntity<List<CategoryDTO>> getAllCategories() {
    List<CategoryDTO> categories = categoryService.getAllCategories();
    return ResponseEntity.ok(categories);
  }

  @PutMapping("/{catId}")
  public ResponseEntity<CategoryDTO> updateCategory(
    @RequestBody CategoryDTO categoryDTO,
    @PathVariable("catId") int id
  ) throws ResourceNotFoundException {
    CategoryDTO updateCategory = categoryService.updateCategory(
      categoryDTO,
      id
    );
    return ResponseEntity.ok(updateCategory);
  }

  @DeleteMapping("/{catId}")
  public ResponseEntity<ProblemDetail> deleteUser(
    @PathVariable("catId") int id
  ) throws ResourceNotFoundException {
    categoryService.deleteCategoryById(id);

    return ResponseEntity
      .of(ProblemDetail.forStatusAndDetail(HttpStatus.OK, "Deleted succesfuly"))
      .build();
  }

  @GetMapping("/{catId}")
  public ResponseEntity<CategoryDTO> getCategory(@PathVariable("catId") int id)
    throws ResourceNotFoundException {
    CategoryDTO categoryDTO = categoryService.getCategoryById(id);
    return ResponseEntity.ok(categoryDTO);
  }
}
