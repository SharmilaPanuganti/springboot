package cgg.blogapp.blogappspring.services;

import cgg.blogapp.blogappspring.exceptions.ResourceNotFoundException;
import cgg.blogapp.blogappspring.payloads.CategoryDTO;
import java.util.List;

public interface CategoryService {
  public CategoryDTO createCategory(CategoryDTO categoryDto);

  public CategoryDTO updateCategory(CategoryDTO categoryDTO, int id)
    throws ResourceNotFoundException;

  public CategoryDTO getCategoryById(int id) throws ResourceNotFoundException;

  public List<CategoryDTO> getAllCategories();

  public void deleteCategoryById(int id) throws ResourceNotFoundException;
}
