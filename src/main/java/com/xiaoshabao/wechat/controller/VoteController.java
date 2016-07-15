package com.xiaoshabao.wechat.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoshabao.baseframe.controller.AbstractController;
import com.xiaoshabao.baseframe.exception.DaoException;
import com.xiaoshabao.baseframe.exception.ServiceException;
import com.xiaoshabao.wechat.dto.VoteDetailResult;
import com.xiaoshabao.wechat.dto.VoteListResult;
import com.xiaoshabao.wechat.service.VoteService;

@Controller
public class VoteController extends AbstractController{
	
	@Resource(name="voteServiceImpl")
	private VoteService voteService;
	
	/**
	 * 管理端列表界面列表
	 * @param map
	 * @param article_id
	 * @return
	 * @throws DaoException 
	 */
	@RequestMapping(value="/wechat/vote")
	public ModelAndView getVote (ModelMap model){
		
		return new ModelAndView ("/wechat/vote/vote");
	}
	
	/**
	 * 投票界面列表<br>
	 * <p>http://localhost:8080/shabao-test/wechat/vote/10000001/list</p>
	 * @param map
	 * @param article_id
	 * @return
	 * @throws DaoException 
	 * @throws ServiceException 
	 */
	@RequestMapping(value="/wechat/vote/{voteId}/list")
	public ModelAndView getVoteList (ModelMap model,@PathVariable("voteId")Integer voteId){
		VoteListResult data=voteService.getVoteList(voteId);
		model.put("data", data);
		return new ModelAndView ("/wechat/vote/"+data.getTemplate()+"/voteList");
	}
	
	/**
	 * 获得选手详细信息
	 * @param map
	 * @param article_id
	 * @return
	 * @throws DaoException 
	 * @throws ServiceException 
	 */
	@RequestMapping(value="/wechat/vote/{playerId}/detail")
	public ModelAndView getVoteDetail (ModelMap model,@PathVariable("playerId")Integer playerId){
		VoteDetailResult result=voteService.getVoteDetailById(playerId);
		model.put("data", result);
		return new ModelAndView ("/wechat/vote/"+result.getVote().getTemplate()+"/voteDetail");
	}
	
	/**
	 * 报名参与界面
	 */
	@RequestMapping(value="/wechat/vote/{voteId}/participate")
	public ModelAndView getVoteParticipate (ModelMap model,@PathVariable("voteId")Integer voteId){
		VoteListResult data=voteService.getVoteList(voteId);
		model.put("data", data);
		return new ModelAndView ("/wechat/vote/"+data.getTemplate()+"/participate");
	}
	
	/**
	 * 排行界面
	 */
	@RequestMapping(value="/wechat/vote/{voteId}/ranking")
	public ModelAndView getVoteRanking (ModelMap model,@PathVariable("voteId")Integer voteId,Integer type){
		VoteListResult data=voteService.getVoteRanking(voteId, type);
		model.put("data", data);
		return new ModelAndView ("/wechat/vote/"+data.getTemplate()+"/ranking");
	}
}
