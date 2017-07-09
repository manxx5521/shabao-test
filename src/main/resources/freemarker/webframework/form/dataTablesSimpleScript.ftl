function getDataTableUrl(){
	return "../${dataTablesQueryListId}/query?"+$('#form1').serialize();
}

$(document).ready(function() {
    var dataTable=$('.${dataTablesName}').on('xhr.dt', function ( e, settings, json, xhr ) {//返回数据后操作
        if(!json.success){
			cbox.alert(json.message); 
			json={};       
        }
    } ).DataTable( {
        "processing": true,
        "serverSide": true,//服务模式
        "columns":[
            <#list columnList as item>
            {
            	"data":"${item.column}",
            	"defaultContent": ""
              	<#if item.href >
              	,"render":function(data, type, row, meta) {
              		return '<a href="./view/'+row.${pkColumn}+'/detail" target="_self">'+data+'</a>';
              	}
              	</#if>
            }<#if item_has_next>,</#if>
  			</#list>
        ],
        "ajax": {
        	"url": getDataTableUrl(),
        	"type": "POST"
    	}
    } );
    
    $("#form1 button").click(function(){
    	dataTable.ajax.url( getDataTableUrl() ).load();
    })
} );