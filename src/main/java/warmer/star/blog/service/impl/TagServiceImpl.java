package warmer.star.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warmer.star.blog.dto.TagItem;
import warmer.star.blog.mapper.TagMapper;
import warmer.star.blog.model.Tag;
import warmer.star.blog.service.TagService;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;
	@Override
	public List<Tag> getAll() {
		return tagMapper.getAll();
	}

	@Override
	public List<Tag> getTagByIds(List<String> tagIds) {
		return tagMapper.getTagByIds(tagIds);
	}

	@Override
	public List<Tag> getTagByName(String tagName) {
		return tagMapper.getTagByName(tagName);
	}

	@Override
	public int saveTag(TagItem submitItem) {
		return tagMapper.saveTag(submitItem);
	}

	@Override
	public boolean updateTag(TagItem submitItem) {
		return tagMapper.updateTag(submitItem);
	}

	@Override
	public boolean deleteTag(int tagId) {
		return tagMapper.deleteTag(tagId);
	}

	
}
