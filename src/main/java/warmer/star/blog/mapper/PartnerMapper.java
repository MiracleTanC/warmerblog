package warmer.star.blog.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import warmer.star.blog.model.Partner;

public interface PartnerMapper {
    List<Partner> getAll();
    int savePartner(Partner submitItem);
	boolean updatePartner(Partner submitItem);
	boolean deletePartner(@Param("partnerId") int partnerId);
}
