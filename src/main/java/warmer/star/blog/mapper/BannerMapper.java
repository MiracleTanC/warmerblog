package warmer.star.blog.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import warmer.star.blog.dto.BannerItem;
import warmer.star.blog.dto.BannerQueryItem;
import warmer.star.blog.dto.BannerSubmitItem;

public interface BannerMapper {
    List<BannerItem> getBannerList(BannerQueryItem queryItem);
    BannerItem getById(@Param("bannerId") int bannerId);
    boolean saveBanner(BannerSubmitItem submitItem);
    boolean updateBanner(BannerSubmitItem submitItem);
    boolean deleteBanner(@Param("bannerId") int bannerId);
}
