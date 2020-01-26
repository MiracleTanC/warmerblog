package warmer.star.blog.service;

import warmer.star.blog.model.ELog;
import warmer.star.blog.model.ELogFile;

import java.util.List;

public interface ElogService {

    List<ELog> getAll();

    void deleteElog(Integer eLogId);

    void deleteElogFile(Integer eLogId);

    int addELog(ELog eLog);
    void addELogFile(ELogFile eLogFile);
}
