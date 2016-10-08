package com.xiaoshabao.wechat.interceptor;

import java.util.List;

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
        if(keys.getLength()!=1){
            throw new Fault(new IllegalArgumentException("用户名格式不对"));
        }
        String key=keys.item(0).getNodeValue();
        if(!key.equals(this.key)){
        	 throw new Fault(new IllegalArgumentException("用户名格式不对"));
        }
         
    }
}
