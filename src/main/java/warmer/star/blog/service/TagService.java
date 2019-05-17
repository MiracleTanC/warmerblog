package warmer.star.blog.service;

import warmer.star.blog.dto.TagItem;
import warmer.star.blog.model.Tag;

import java.util.List;

public interface TagService {
	List<Tag> getAll();
	List<Tag> getTagByIds(List<String> tagIds);
	List<Tag> getTagByName(String tagName);
	int saveTag(TagItem submitItem);
	boolean updateTag(TagItem submitItem);
	boolean deleteTag(int tagId);
}
