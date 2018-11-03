package warmer.star.blog.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import warmer.star.blog.dto.MusicQueryItem;
import warmer.star.blog.model.Music;

public interface MusicMapper {
    List<Music> getMusicList(MusicQueryItem queryItem);
    Music getById(@Param("musicId") int musicId);
    boolean saveMusic(Music submitItem);
    boolean updateMusic(Music submitItem);
    boolean deleteMusic(@Param("musicId") int musicId);
}
