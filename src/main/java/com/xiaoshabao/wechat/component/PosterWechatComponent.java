package com.xiaoshabao.wechat.component;

import java.util.List;

import org.springframework.stereotype.Component;

import com.xiaoshabao.webframework.component.PosterComponent;
import com.xiaoshabao.webframework.dto.PosterDto;
import com.xiaoshabao.wechat.enums.WechatType;

@Component("wechatPoster")
public class PosterWechatComponent extends PosterComponent{
	
	/**
	 * 获得微信海报
	 * @param type 比如投票等
	 * @param voteId
	 * @return
	 */
	public List<PosterDto> getWchatPoset(WechatType type,String typeId){
		return getPoster(type.getValue(),typeId);
	}
	/**
	 * 获得微信投票海报
	 */
	public List<PosterDto> getWchatVotePoset(Integer voteId){
		return  getWchatPoset(WechatType.VOTE, voteId.toString());
	}
	/**
	 * 获得微信砍价海报
	 */
	public List<PosterDto> getBargainPoset(Integer bargainId){
		return  getWchatPoset(WechatType.BARGAIN, bargainId.toString());
	}
}
