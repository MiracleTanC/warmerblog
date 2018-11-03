package warmer.star.blog.service;

import java.util.List;

import warmer.star.blog.dto.MusicQueryItem;
import warmer.star.blog.model.Music;

public interface MusicService {
	List<Music> getMusicList(MusicQueryItem queryItem);
    Music getById(int musicId);
    boolean saveMusic(Music submitItem);
    boolean updateMusic(Music submitItem);
    boolean deleteMusic(int musicId);
}
