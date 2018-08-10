package warmer.star.blog.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import warmer.star.blog.dto.TagItem;
import warmer.star.blog.model.Tag;

public interface TagMapper {
    List<Tag> getAll();
    int saveTag(TagItem submitItem);
	boolean updateTag(TagItem submitItem);
	boolean deleteTag(@Param("tagId") int tagId);
}
