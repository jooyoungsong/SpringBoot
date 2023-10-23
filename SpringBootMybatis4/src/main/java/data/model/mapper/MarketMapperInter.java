package data.model.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MarketMapperInter {
	
	public int getTotalCount();
}
