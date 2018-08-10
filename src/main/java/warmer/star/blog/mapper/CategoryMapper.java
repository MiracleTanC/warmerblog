package warmer.star.blog.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import warmer.star.blog.dto.CategorySubmitItem;
import warmer.star.blog.model.Category;

public interface CategoryMapper {
    List<Category> getCategoryList(@Param("parentId")Integer parentId);
    List<Category> getAll();
    Category getById(int cateId);
    boolean saveCategory(CategorySubmitItem submitItem);
    boolean updateCategory(CategorySubmitItem submitItem);
    boolean deleteCategoryById(@Param("cateId") int cateId);
}
