package com.xiaoshabao.shabaowebtest.dto;

/**
 * 暴露秒杀地址
 */
public class SeckillExposer {
	// 是否开启秒杀
	private boolean exposed;
	// 加密
	private String md5;
	private long seckillId;
	// 系统当前时间 毫秒
	private long now;
	// 秒杀开始时间
	private long start;
	// 秒杀结束时间
	private long end;

	public SeckillExposer() {
	}

	public SeckillExposer(boolean exposed, String md5, long seckillId) {
		this.exposed = exposed;
		this.md5 = md5;
		this.seckillId = seckillId;
	}

	public SeckillExposer(boolean exposed, long seckillId, long now,
			long start, long end) {
		this.exposed = exposed;
		this.seckillId = seckillId;
		this.now = now;
		this.start = start;
		this.end = end;
	}

	public SeckillExposer(boolean exposed, long seckillId) {
		this.exposed = exposed;
		this.seckillId = seckillId;
	}

	/**
	 * 获得 exposed
	 * 
	 * @return the exposed
	 */
	public boolean isExposed() {
		return exposed;
	}

	/**
	 * 设置 exposed
	 * 
	 * @param exposed
	 *            the exposed to set
	 */
	public void setExposed(boolean exposed) {
		this.exposed = exposed;
	}
	/**
	 * 获得 md5
	 * @return the md5
	 */
	public String getMd5() {
		return md5;
	}

	/**
	 * 设置 md5
	 * @param md5 the md5 to set
	 */
	public void setMd5(String md5) {
		this.md5 = md5;
	}

	/**
	 * 获得 seckillId
	 * 
	 * @return the seckillId
	 */
	public long getSeckillId() {
		return seckillId;
	}

	/**
	 * 设置 seckillId
	 * 
	 * @param seckillId
	 *            the seckillId to set
	 */
	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	/**
	 * 获得 now
	 * 
	 * @return the now
	 */
	public long getNow() {
		return now;
	}

	/**
	 * 设置 now
	 * 
	 * @param now
	 *            the now to set
	 */
	public void setNow(long now) {
		this.now = now;
	}

	/**
	 * 获得 start
	 * 
	 * @return the start
	 */
	public long getStart() {
		return start;
	}

	/**
	 * 设置 start
	 * 
	 * @param start
	 *            the start to set
	 */
	public void setStart(long start) {
		this.start = start;
	}

	/**
	 * 获得 end
	 * 
	 * @return the end
	 */
	public long getEnd() {
		return end;
	}

	/**
	 * 设置 end
	 * 
	 * @param end
	 *            the end to set
	 */
	public void setEnd(long end) {
		this.end = end;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SeckillExposer [exposed=" + exposed + ", md5=" + md5
				+ ", seckillId=" + seckillId + ", now=" + now + ", start="
				+ start + ", end=" + end + "]";
	}
}
