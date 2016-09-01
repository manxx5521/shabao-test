package com.xiaoshabao.webframe.component;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xiaoshabao.webframe.dao.PosterDao;
import com.xiaoshabao.webframe.dto.PosterDto;
import com.xiaoshabao.webframe.entity.PosterEntity;

@Component("poster")
public class PosterComponent {
	@Autowired
	private PosterDao posterDao;
	/**
	 * 插入海报
	 * @param poster
	 * @return
	 */
	public int insertPoster(PosterEntity poster){
		return posterDao.insertPoster(poster);
	};
	/**
	 * 修改海报
	 * @param poster
	 * @return
	 */
	public int updatePoster(PosterEntity poster){
		return posterDao.updatePoster(poster);
	};
	/**
	 * 删除海报
	 * @param poster
	 * @return
	 */
	public int deletePoster(PosterEntity poster){
		return posterDao.deletePoster(poster);
	};
	/**
	 * 获得微信海报
	 * @param type 比如投票等
	 * @param voteId
	 * @return
	 */
	public List<PosterDto> getPoster(String type,String typeId){
		return posterDao.getPoster(type,typeId);
	}
	/**
	 * 获得海报imageURL
	 * @param poster
	 * @return
	 */
	public List<String> getPosterUrl(@Param("type") String type,@Param("typeId")String typeId){
		return posterDao.getPosterUrl(type,typeId);
	}
}
