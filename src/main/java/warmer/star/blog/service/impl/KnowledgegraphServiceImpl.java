package warmer.star.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warmer.star.blog.mapper.KnowledgegraphMapper;
import warmer.star.blog.service.KnowledgegraphService;

import java.util.List;
import java.util.Map;

@Service
public class KnowledgegraphServiceImpl implements KnowledgegraphService {

	@Autowired
	private KnowledgegraphMapper knowledgegraphRepository;
	
	@Override
	public List<Map<String, Object>> getDomainList(String domainname,String createuser) {
		List<Map<String,Object>> list = knowledgegraphRepository.getDomainList(domainname,createuser);		
		return list;
	}

	@Override
	public void saveDomain(Map<String, Object> map) {
		knowledgegraphRepository.saveDomain(map);		
	}

	@Override
	public void updateDomain(Map<String, Object> map) {
		knowledgegraphRepository.updateDomain(map);
	}

	@Override
	public void deleteDomain(Integer id) {
		knowledgegraphRepository.deleteDomain(id);
	}

	@Override
	public List<Map<String, Object>> getDomainByName(String domainname) {
		return knowledgegraphRepository.getDomainByName(domainname);
	}

	@Override
	public List<Map<String, Object>> getDomains() {
		return knowledgegraphRepository.getDomains();
	}

	@Override
	public List<Map<String, Object>> getDomainById(Integer domainid) {
		return knowledgegraphRepository.getDomainById(domainid);
	}
	
	@Override
	public void saveNodeImage(List<Map<String, Object>> mapList) {
		knowledgegraphRepository.saveNodeImage(mapList);
	}

    @Override
    public void saveNodeContent(Map<String, Object> map) {
        knowledgegraphRepository.saveNodeContent(map);
    }

    @Override
    public void updateNodeContent(Map<String, Object> map) {
        knowledgegraphRepository.updateNodeContent(map);
    }

    @Override
	public List<Map<String, Object>> getNodeImageList(Integer domainid, Integer nodeid) {
		return knowledgegraphRepository.getNodeImageList(domainid,nodeid);
	}

    @Override
    public List<Map<String, Object>> getNodeContent(Integer domainid, Integer nodeid) {
        return knowledgegraphRepository.getNodeContent(domainid,nodeid);
    }

    @Override
    public void deleteNodeImage(Integer domainid, Integer nodeid) {
         knowledgegraphRepository.deleteNodeImage(domainid,nodeid);
    }

}
