package warmer.star.blog.mapper;


import org.apache.ibatis.annotations.Param;
import warmer.star.blog.dto.TagItem;
import warmer.star.blog.model.Tag;

import java.util.List;

public interface TagMapper {
    List<Tag> getAll();
    List<Tag> getTagByName(@Param("tagName")String tagName);
    int saveTag(TagItem submitItem);
	boolean updateTag(TagItem submitItem);
	boolean deleteTag(@Param("tagId") int tagId);
}
