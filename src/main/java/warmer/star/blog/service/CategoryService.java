package warmer.star.blog.service;

import java.util.List;

import warmer.star.blog.dto.CategorySubmitItem;
import warmer.star.blog.model.Category;

public interface CategoryService {

	List<Category> getCategoryList(Integer parentId);
	List<Category> getAll();
	boolean saveCategory(CategorySubmitItem submitItem);
	boolean updateCategory(CategorySubmitItem submitItem);
	boolean deleteCategoryById(int cateId);
	Category getById(int cateId);
}
