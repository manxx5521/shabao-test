package com.xiaoshabao.shabaowebtest.enums;
/**
 * 秒杀状态
 */
public enum SeckillStatEnum {
	/**
	 *秒杀成功
	 */
	SUCCESS(1,"秒杀成功"),
	END(0,"秒杀结束"),
	REPEAT_KILL(-1,"重复秒杀"),
	INNER_ERROR(-2,"系统异常"),
	DATA_REWRITE(-3,"数据篡改");
	
	private int state;
	private String stateInfo;
	
	private SeckillStatEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	/**
	 * 根据index返回字段
	 * @param index
	 * @return
	 */
	public static SeckillStatEnum stateOf(int index){
		for(SeckillStatEnum state:values()){
			if(state.getState()==index){
				return state;
			}
		}
		return null;
	}

	/**
	 * 获得 state
	 * @return the state
	 */
	public int getState() {
		return state;
	}

	/**
	 * 获得 stateInfo
	 * @return the stateInfo
	 */
	public String getStateInfo() {
		return stateInfo;
	}
	
	
	
	
}
