package warmer.star.blog.service;

import warmer.star.blog.dto.CategorySubmitItem;
import warmer.star.blog.model.Category;

import java.util.List;

public interface CategoryService {

	List<Category> getCategoryList(Integer parentId);
	List<Category> getAll();
	Integer saveCategory(CategorySubmitItem submitItem);
	boolean updateCategory(CategorySubmitItem submitItem);
	boolean deleteCategoryById(int cateId);
	Category getById(int cateId);
	boolean updateCategoryCode(int id,String code);
}
