package warmer.star.blog.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import warmer.star.blog.dto.LoggerQueryItem;
import warmer.star.blog.model.Logger;

public interface LoggerMapper {
    List<Logger> getList(LoggerQueryItem queryItem);
    Logger getById(@Param("logId") int logId);
    boolean savelogger(Logger submitItem);
}
