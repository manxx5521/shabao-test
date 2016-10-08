package com.xiaoshabao.wechat.interceptor;
import java.util.List;

import javax.xml.namespace.QName;
 
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
public class TokenServiceInterceptorAdd extends AbstractPhaseInterceptor<SoapMessage>{
	private String key;
	public TokenServiceInterceptorAdd(String key) {
        super(Phase.PREPARE_SEND); // 发送SOAP消息之前调用拦截器
        this.key=key;
    }
 
    public void handleMessage(SoapMessage message) throws Fault {
        List<Header> headers=message.getHeaders();
         
        Document doc=DOMUtils.createDocument();
        Element ele=doc.createElement("authHeader");
        Element keyElement=doc.createElement("key");
        keyElement.setNodeValue(this.key);
        ele.appendChild(keyElement);
         
        headers.add(new Header(new QName("shabao"),ele));
    }
}
