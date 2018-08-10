package warmer.star.blog.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import warmer.star.blog.dto.TagItem;
import warmer.star.blog.model.Tag;

public interface TagService {
	List<Tag> getAll();
	int saveTag(TagItem submitItem);
	boolean updateTag(TagItem submitItem);
	boolean deleteTag(@Param("tagId") int tagId);
}
