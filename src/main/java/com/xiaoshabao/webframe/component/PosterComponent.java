package com.xiaoshabao.webframe.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xiaoshabao.webframe.dao.PosterDao;
import com.xiaoshabao.webframe.dto.PosterDto;

@Component("poster")
public class PosterComponent {
	@Autowired
	private PosterDao posterDao;
	/**
	 * 获得微信海报
	 * @param type 比如投票等
	 * @param voteId
	 * @return
	 */
	public List<PosterDto> getPoster(String type,String typeId){
		return posterDao.getPoster(type,typeId);
	}
}
