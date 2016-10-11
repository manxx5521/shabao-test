package com.xiaoshabao.wechat.interceptor;

import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
/**
 * tokenService服务端进入之前的拦截器
 * <p>一般用来做权限认证等</p>
 */
public class TokenServiceInterceptorIn  extends AbstractPhaseInterceptor<SoapMessage>{
	private String key="shbmx123";
	public TokenServiceInterceptorIn(){
		// 在调用方法之前调用拦截器
        super(Phase.PRE_INVOKE);
	}
	/**
     * 拦截获取消息
     */
    public void handleMessage(SoapMessage message) throws Fault {
    	
        List<Header> headers=message.getHeaders();
        if(headers==null || headers.size()==0){
            throw new Fault(new IllegalArgumentException("没有Header,拦截器实施拦截"));
        }
        Header firstHeader=headers.get(0);
        Element ele=(Element) firstHeader.getObject();
        NodeList keys=ele.getElementsByTagName("key");
        NodeList times=ele.getElementsByTagName("time");
        if(keys.getLength()!=1){
            throw new Fault(new IllegalArgumentException("key格式不对"));
        }
        if(times.getLength()!=1){
            throw new Fault(new IllegalArgumentException("时间格式不对"));
        }
        String key=keys.item(0).getTextContent();
        String time=times.item(0).getTextContent();
        if(StringUtils.isEmpty(key)){
        	throw new Fault(new IllegalArgumentException("key格式不对"));
        }
        if(StringUtils.isEmpty(time)){
        	throw new Fault(new IllegalArgumentException("时间格式不对"));
        }
        long ltime;
		try {
			ltime = Long.valueOf(time);
		} catch (NumberFormatException e) {
			throw new Fault(new IllegalArgumentException("时间格式不对"));
		}
		long newDate=new Date().getTime();
        if((newDate-1000*5)>ltime){
        	 throw new Fault(new IllegalArgumentException("已超过最大验证时间"));
        }
        
        String md5=DigestUtils.md5Hex(this.key+time);
        
        if(!key.equals(md5)){
        	 throw new Fault(new IllegalArgumentException("key验证未通过"));
        }
         
    }
}
