<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<script>
	wx.config({
		debug : false,
		appId : '${jsParams.appid}',
		timestamp : '${jsParams.timestamp}',
		nonceStr : '${jsParams.nonceStr}',
		signature : '${jsParams.signature}',
		jsApiList : [ 'onMenuShareTimeline', 'onMenuShareAppMessage',
						'onMenuShareQQ', 'onMenuShareWeibo',
						'onMenuShareQZone', 'startRecord', 'stopRecord',
						'onVoiceRecordEnd', 'playVoice', 'pauseVoice',
						'stopVoice', 'onVoicePlayEnd', 'uploadVoice',
						'downloadVoice', 'chooseImage', 'previewImage',
						'uploadImage', 'downloadImage', 'translateVoice',
						'getNetworkType', 'openLocation', 'getLocation',
						'hideOptionMenu', 'showOptionMenu', 'hideMenuItems',
						'showMenuItems', 'hideAllNonBaseMenuItem',
						'showAllNonBaseMenuItem', 'closeWindow', 'scanQRCode',
						'chooseWXPay', 'openProductSpecificView', 'addCard',
						'chooseCard', 'openCard' ]
	});
	function getUrl(url,scope){
		authUrl='https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect';
		var appid='${wechat.appid}';
		var accountId='${wechat.accountId}';
		authUrl=authUrl.replace('APPID', appid).replace('REDIRECT_URI', encodeURI(url))
			.replace('STATE', accountId).replace('SCOPE', scope);
		return authUrl;
	}
	function getBaseUrl(url){
		return getUrl(url,'snsapi_base');
	}
	function getInfoUrl(url){
		return getUrl(url,'snsapi_userinfo');
	}
	
	function getShareUrl(url,scope){
		authUrl='https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect';
		
		authUrl=authUrl.replace('APPID', appid).replace('REDIRECT_URI', encodeURI(url))
			.replace('STATE', accountId).replace('SCOPE', scope);
		return authUrl;
	}
	function getBaseUrl(url){
		return getUrl(url,'snsapi_base');
	}
	function getShareUrl(url){
		var appid='${wechat.appid}';
		var accountId='${wechat.accountId}'; 
		eurl=encodeURIComponent(url);
		rurl= '${domain}${ctx}/wechat/share/url?appid='+appid+'&accountId='+accountId+'&scope=snsapi_userinfo'
				+'&url='+eurl;
		return rurl;
	}
</script>