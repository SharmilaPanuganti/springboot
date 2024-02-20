package cgg.blogapp.blogappspring.services.impl;

import cgg.blogapp.blogappspring.dao.CategoryRepository;
import cgg.blogapp.blogappspring.entities.Category;
import cgg.blogapp.blogappspring.exceptions.ResourceNotFoundException;
import cgg.blogapp.blogappspring.payloads.CategoryDTO;
import cgg.blogapp.blogappspring.services.CategoryService;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private CategoryRepository cRepository;

  @Override
  public CategoryDTO createCategory(CategoryDTO categoryDto) {
    Category category = modelMapper.map(categoryDto, Category.class);
    Category save = cRepository.save(category);
    return modelMapper.map(save, CategoryDTO.class);
  }

  @Override
  public CategoryDTO updateCategory(CategoryDTO categoryDTO, int id)
    throws ResourceNotFoundException {
    Category category = cRepository
      .findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("category", "id", id));
    category.setName(categoryDTO.getName());
    category.setDesc(categoryDTO.getDesc());
    cRepository.save(category);
    return modelMapper.map(category, CategoryDTO.class);
  }

  @Override
  public CategoryDTO getCategoryById(int id) throws ResourceNotFoundException {
    Category category = cRepository
      .findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("category", "id", id));
    return modelMapper.map(category, CategoryDTO.class);
  }

  @Override
  public List<CategoryDTO> getAllCategories() {
    List<Category> categories = cRepository.findAll();
    List<CategoryDTO> categoryDTOs = categories
      .stream()
      .map(u -> modelMapper.map(u, CategoryDTO.class))
      .collect(Collectors.toList());
    return categoryDTOs;
  }

  @Override
  public void deleteCategoryById(int id) throws ResourceNotFoundException {
    cRepository
      .findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("category", "id", id));
    cRepository.deleteById(id);
  }
}
