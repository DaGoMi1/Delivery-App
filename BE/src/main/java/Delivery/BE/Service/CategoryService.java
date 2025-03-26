package Delivery.BE.Service;

import Delivery.BE.DTO.ResponseCategoryDTO;
import Delivery.BE.Domain.Category;
import Delivery.BE.Exception.NotFoundException;
import Delivery.BE.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<ResponseCategoryDTO> findAll() {
        List<ResponseCategoryDTO> responseCategoryDTOList = new ArrayList<>();
        List<Category> categoryList = categoryRepository.findAll();

        for (Category category : categoryList) {
            ResponseCategoryDTO responseCategoryDTO = new ResponseCategoryDTO(category);
            responseCategoryDTOList.add(responseCategoryDTO);
        }

        return responseCategoryDTOList;
    }

    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("카테고리를 찾을 수 없습니다. ID: " + id));
    }
}
