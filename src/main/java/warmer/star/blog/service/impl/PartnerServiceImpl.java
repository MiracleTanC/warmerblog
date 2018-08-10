package warmer.star.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warmer.star.blog.mapper.PartnerMapper;
import warmer.star.blog.model.Partner;
import warmer.star.blog.service.PartnerService;

@Service
public class PartnerServiceImpl implements PartnerService {

    @Autowired
    private PartnerMapper partnerMapper;
	@Override
	public List<Partner> getAll() {
		return partnerMapper.getAll();
	}

	@Override
	public int savePartner(Partner submitItem) {
		return partnerMapper.savePartner(submitItem);
	}

	@Override
	public boolean updatePartner(Partner submitItem) {
		return partnerMapper.updatePartner(submitItem);
	}

	@Override
	public boolean deletePartner(int PartnerId) {
		return partnerMapper.deletePartner(PartnerId);
	}

	
}
