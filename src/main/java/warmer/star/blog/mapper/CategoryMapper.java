package warmer.star.blog.mapper;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import warmer.star.blog.dto.CategorySubmitItem;
import warmer.star.blog.model.Category;

import java.util.List;

@Repository
public interface CategoryMapper {
    List<Category> getCategoryList(@Param("parentId")Integer parentId);
    List<Category> getAll();
    Category getById(int cateId);
    Integer saveCategory(CategorySubmitItem submitItem);
    boolean updateCategory(CategorySubmitItem submitItem);
    boolean updateCategoryCode(@Param("id") int id,@Param("code") String code);
    boolean deleteCategoryById(@Param("cateId") int cateId);
}
