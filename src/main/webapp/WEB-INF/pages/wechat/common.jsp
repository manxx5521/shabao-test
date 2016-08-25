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
</script>