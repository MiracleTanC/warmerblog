package warmer.star.blog.mapper;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import warmer.star.blog.model.ELog;
import warmer.star.blog.model.ELogFile;

import java.util.List;
@Repository
public interface ElogMapper {
    List<ELog> getAll();

    void deleteElog(@Param("eLogId") Integer eLogId);

    void deleteElogFile(@Param("eLogId") Integer eLogId);

    int addELog(ELog eLog);
    void addElogFile(ELogFile eLogFile);
}
