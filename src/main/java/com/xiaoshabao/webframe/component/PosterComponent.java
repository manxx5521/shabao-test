package com.xiaoshabao.webframe.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xiaoshabao.webframe.dao.PosterDao;
import com.xiaoshabao.webframe.dto.PosterDto;
import com.xiaoshabao.webframe.entity.PosterEntity;
import com.xiaoshabao.wechat.enums.WechatPoster;

@Component("poster")
public class PosterComponent {
	@Autowired
	private PosterDao posterDao;
	/**
	 * 获得微信海报
	 * @param accountId
	 * @param type 比如投票等
	 * @param voteId
	 * @return
	 */
	public List<PosterDto> getWchatPoset(Integer accountId,WechatPoster type,String typeId){
		PosterEntity poster=new PosterEntity(1,accountId.toString(),type.getValue(),typeId);
		return posterDao.getPoster(poster);
	}
	/**
	 * 获得微信投票海报
	 */
	public List<PosterDto> getWchatVotePoset(Integer voteId){
		PosterEntity poster=new PosterEntity(1,WechatPoster.VOTE.getValue(),voteId.toString());
		return posterDao.getPoster(poster);
	}
}
