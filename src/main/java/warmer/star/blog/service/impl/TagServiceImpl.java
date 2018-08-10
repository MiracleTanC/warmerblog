package warmer.star.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import warmer.star.blog.dto.TagItem;
import warmer.star.blog.mapper.TagMapper;
import warmer.star.blog.model.Tag;
import warmer.star.blog.service.TagService;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;
	@Override
	public List<Tag> getAll() {
		return tagMapper.getAll();
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
