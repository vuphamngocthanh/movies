package com.admin.service.category;

import com.model.dto.CategoryDto;
import com.model.entity.Category;
import com.repo.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService implements ICategoryService{
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    public CategoryService(ModelMapper modelMapper, CategoryRepository categoryRepository) {
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Iterable<CategoryDto> findAll() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.parallelStream()
                .map(category -> modelMapper.map(category,CategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto findById(Integer id) {
        return null;
    }

    @Override
    public void save(CategoryDto categoryDto) {

    }

    @Override
    public void remove(Integer id) {

    }
}
