package com.xiaoshabao.webframe.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoshabao.webframe.dto.PosterDto;

/**
 * 海报数据操作
 */
public interface PosterDao {
	/**
	 * 获得海报
	 * @param poster
	 * @return
	 */
	public List<PosterDto> getPoster(@Param("type") String type,@Param("typeId")String typeId);
}
