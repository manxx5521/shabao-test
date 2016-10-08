
package com.xiaoshabao.wechat.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>accessToken complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="accessToken"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="accessToken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="accountId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="appName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="appid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="appsecret" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="encodingAESKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="expiresIn" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="jsaccessToken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="jsexpiresIn" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="jsupdateTime" type="{http://server.webservice.wechat.xiaoshabao.com/}timestamp" minOccurs="0"/&gt;
 *         &lt;element name="updateTime" type="{http://server.webservice.wechat.xiaoshabao.com/}timestamp" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "accessToken", propOrder = {
    "accessToken",
    "accountId",
    "appName",
    "appid",
    "appsecret",
    "encodingAESKey",
    "expiresIn",
    "id",
    "jsaccessToken",
    "jsexpiresIn",
    "jsupdateTime",
    "updateTime"
})
public class AccessToken {

    protected String accessToken;
    protected Integer accountId;
    protected String appName;
    protected String appid;
    protected String appsecret;
    protected String encodingAESKey;
    protected int expiresIn;
    protected String id;
    protected String jsaccessToken;
    protected int jsexpiresIn;
    protected Timestamp jsupdateTime;
    protected Timestamp updateTime;

    /**
     * 获取accessToken属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * 设置accessToken属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccessToken(String value) {
        this.accessToken = value;
    }

    /**
     * 获取accountId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAccountId() {
        return accountId;
    }

    /**
     * 设置accountId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAccountId(Integer value) {
        this.accountId = value;
    }

    /**
     * 获取appName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAppName() {
        return appName;
    }

    /**
     * 设置appName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAppName(String value) {
        this.appName = value;
    }

    /**
     * 获取appid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAppid() {
        return appid;
    }

    /**
     * 设置appid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAppid(String value) {
        this.appid = value;
    }

    /**
     * 获取appsecret属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAppsecret() {
        return appsecret;
    }

    /**
     * 设置appsecret属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAppsecret(String value) {
        this.appsecret = value;
    }

    /**
     * 获取encodingAESKey属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEncodingAESKey() {
        return encodingAESKey;
    }

    /**
     * 设置encodingAESKey属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEncodingAESKey(String value) {
        this.encodingAESKey = value;
    }

    /**
     * 获取expiresIn属性的值。
     * 
     */
    public int getExpiresIn() {
        return expiresIn;
    }

    /**
     * 设置expiresIn属性的值。
     * 
     */
    public void setExpiresIn(int value) {
        this.expiresIn = value;
    }

    /**
     * 获取id属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * 获取jsaccessToken属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJsaccessToken() {
        return jsaccessToken;
    }

    /**
     * 设置jsaccessToken属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJsaccessToken(String value) {
        this.jsaccessToken = value;
    }

    /**
     * 获取jsexpiresIn属性的值。
     * 
     */
    public int getJsexpiresIn() {
        return jsexpiresIn;
    }

    /**
     * 设置jsexpiresIn属性的值。
     * 
     */
    public void setJsexpiresIn(int value) {
        this.jsexpiresIn = value;
    }

    /**
     * 获取jsupdateTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Timestamp }
     *     
     */
    public Timestamp getJsupdateTime() {
        return jsupdateTime;
    }

    /**
     * 设置jsupdateTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Timestamp }
     *     
     */
    public void setJsupdateTime(Timestamp value) {
        this.jsupdateTime = value;
    }

    /**
     * 获取updateTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Timestamp }
     *     
     */
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置updateTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Timestamp }
     *     
     */
    public void setUpdateTime(Timestamp value) {
        this.updateTime = value;
    }

}
