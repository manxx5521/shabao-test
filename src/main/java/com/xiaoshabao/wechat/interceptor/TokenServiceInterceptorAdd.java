package com.xiaoshabao.wechat.interceptor;
import java.util.Date;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.commons.codec.digest.DigestUtils;
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
        Long times=new Date().getTime();
        String md5=DigestUtils.md5Hex((this.key+times).getBytes());
        
        Element keyElement=doc.createElement("key");
        keyElement.setTextContent(md5);
        ele.appendChild(keyElement);
        Element timesElement=doc.createElement("time");
        timesElement.setTextContent(times.toString());
        ele.appendChild(timesElement);
         
        headers.add(new Header(new QName("shabao"),ele));
    }
}
