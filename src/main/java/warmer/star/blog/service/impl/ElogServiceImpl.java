package warmer.star.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warmer.star.blog.mapper.ElogMapper;
import warmer.star.blog.model.ELog;
import warmer.star.blog.model.ELogFile;
import warmer.star.blog.service.ElogService;

import java.util.List;

@Service
public class ElogServiceImpl implements ElogService {

    @Autowired
    private  ElogMapper elogMapper;
    @Override
    public List<ELog> getAll() {
        return elogMapper.getAll();
    }
    @Override
    public void deleteElog(Integer eLogId) {
        elogMapper.deleteElog(eLogId);
    }

    @Override
    public void deleteElogFile(Integer eLogId) {
        elogMapper.deleteElog(eLogId);
    }

    @Override
    public int addELog(ELog eLog) {
        return elogMapper.addELog(eLog);
    }
    @Override
    public void addELogFile(ELogFile eLogFile) {
         elogMapper.addElogFile(eLogFile);
    }
}
