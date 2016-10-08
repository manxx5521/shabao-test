
package com.xiaoshabao.wechat.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.xiaoshabao.wechat.webservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetToken_QNAME = new QName("http://server.webservice.wechat.xiaoshabao.com/", "getToken");
    private final static QName _GetTokenResponse_QNAME = new QName("http://server.webservice.wechat.xiaoshabao.com/", "getTokenResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.xiaoshabao.wechat.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetToken }
     * 
     */
    public GetToken createGetToken() {
        return new GetToken();
    }

    /**
     * Create an instance of {@link GetTokenResponse }
     * 
     */
    public GetTokenResponse createGetTokenResponse() {
        return new GetTokenResponse();
    }

    /**
     * Create an instance of {@link AccessToken }
     * 
     */
    public AccessToken createAccessToken() {
        return new AccessToken();
    }

    /**
     * Create an instance of {@link Timestamp }
     * 
     */
    public Timestamp createTimestamp() {
        return new Timestamp();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetToken }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.webservice.wechat.xiaoshabao.com/", name = "getToken")
    public JAXBElement<GetToken> createGetToken(GetToken value) {
        return new JAXBElement<GetToken>(_GetToken_QNAME, GetToken.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTokenResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.webservice.wechat.xiaoshabao.com/", name = "getTokenResponse")
    public JAXBElement<GetTokenResponse> createGetTokenResponse(GetTokenResponse value) {
        return new JAXBElement<GetTokenResponse>(_GetTokenResponse_QNAME, GetTokenResponse.class, null, value);
    }

}
