package com.xiaoshabao.webframework.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoshabao.webframework.dto.PosterDto;
import com.xiaoshabao.webframework.entity.PosterEntity;

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
	/**
	 * 获得海报
	 * @param poster
	 * @return
	 */
	public List<String> getPosterUrl(@Param("type") String type,@Param("typeId")String typeId);
	/**
	 * 添加海报
	 * @param poster
	 * @return
	 */
	public int insertPoster(PosterEntity poster);
	/**
	 * 修改海报
	 * @param poster
	 * @return
	 */
	public int updatePoster(PosterEntity poster);
	/**
	 * 删除海报
	 * @param poster
	 * @return
	 */
	public int deletePoster(PosterEntity poster);
}
