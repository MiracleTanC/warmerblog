package warmer.star.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import warmer.star.blog.dto.BannerItem;
import warmer.star.blog.dto.BannerQueryItem;
import warmer.star.blog.dto.BannerSubmitItem;
import warmer.star.blog.mapper.BannerMapper;
import warmer.star.blog.service.BannerService;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerMapper bannerMapper;

	@Override
	public List<BannerItem> getBannerList(BannerQueryItem queryItem) {
		
		List<BannerItem> list=bannerMapper.getBannerList(queryItem);
		int i=1;
		for (BannerItem bannerItem : list) {
			int num=(queryItem.getPageIndex()-1)*queryItem.getPageSize()+i;
			bannerItem.setSerialNumber(num);
			i++;
		}
		return list;
	}

	
	@Override
	public BannerItem getById(int bannerId) {
	
		return bannerMapper.getById(bannerId);
	}

	@Override
	public boolean saveBanner(BannerSubmitItem submitItem) {
		return bannerMapper.saveBanner(submitItem);
	}

	@Override
	public boolean updateBanner(BannerSubmitItem submitItem) {
		return bannerMapper.updateBanner(submitItem);
	}

	@Override
	public boolean deleteBanner(int bannerId) {
		return bannerMapper.deleteBanner(bannerId);
	}
	
}
