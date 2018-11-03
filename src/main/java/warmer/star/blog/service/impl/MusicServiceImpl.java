package warmer.star.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import warmer.star.blog.dto.MusicQueryItem;
import warmer.star.blog.mapper.MusicMapper;
import warmer.star.blog.model.Music;
import warmer.star.blog.service.MusicService;

@Service
public class MusicServiceImpl implements MusicService {

    @Autowired
    private MusicMapper musicMapper;

	@Override
	public List<Music> getMusicList(MusicQueryItem queryItem) {
		List<Music> list=musicMapper.getMusicList(queryItem);
		return list;
	}

	
	@Override
	public Music getById(int bannerId) {
	
		return musicMapper.getById(bannerId);
	}

	@Override
	public boolean saveMusic(Music submitItem) {
		return musicMapper.saveMusic(submitItem);
	}

	@Override
	public boolean updateMusic(Music submitItem) {
		return musicMapper.updateMusic(submitItem);
	}

	@Override
	public boolean deleteMusic(int bannerId) {
		return musicMapper.deleteMusic(bannerId);
	}
	
}
