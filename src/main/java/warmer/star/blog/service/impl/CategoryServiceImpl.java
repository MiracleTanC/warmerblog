package warmer.star.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warmer.star.blog.dto.CategorySubmitItem;
import warmer.star.blog.mapper.CategoryMapper;
import warmer.star.blog.model.Category;
import warmer.star.blog.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

	@Override
	public List<Category> getCategoryList(Integer parentId) {
		return categoryMapper.getCategoryList(parentId);
	}

	@Override
	public List<Category> getAll() {
		return categoryMapper.getAll();
	}

	@Override
	public Integer saveCategory(CategorySubmitItem submitItem) {
		return categoryMapper.saveCategory(submitItem);
	}

	@Override
	public boolean updateCategory(CategorySubmitItem submitItem) {
		return categoryMapper.updateCategory(submitItem);
	}

	@Override
	public boolean deleteCategoryById(int cateId) {
		return categoryMapper.deleteCategoryById(cateId);
	}

	@Override
	public Category getById(int cateId) {
		return categoryMapper.getById(cateId);
	}

	@Override
	public boolean updateCategoryCode(int id, String code) {
		return categoryMapper.updateCategoryCode(id,code);
	}

}
