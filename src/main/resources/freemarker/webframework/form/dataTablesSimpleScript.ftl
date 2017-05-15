$(document).ready(function() {
    $('.${dataTablesName}').dataTable( {
        "processing": true,
        "serverSide": true,//服务模式
        "columns":[
            <#list titleList as column>
            {data:'${column}'}<#if column_has_next>,</#if>
  			</#list>
        ],
        "ajax": {
        	"url": "../query",
        	"type": "POST"
    	}
    } );
} );