package com.xiaoshabao.webframe.dao;

import java.util.List;

import com.xiaoshabao.webframe.dto.PosterDto;
import com.xiaoshabao.webframe.entity.PosterEntity;

/**
 * 海报数据操作
 */
public interface PosterDao {
	/**
	 * 获得海报
	 * @param poster
	 * @return
	 */
	public List<PosterDto> getPoster(PosterEntity poster);
}
