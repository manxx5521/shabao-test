function getDataTableUrl(){
	return "../${dataTablesQueryListId}/query?"+$('#form1').serialize();
}

$(document).ready(function() {
    var dataTable=$('.${dataTablesName}').DataTable( {
        "processing": true,
        "serverSide": true,//服务模式
        "columns":[
            <#list columnList as column>
            {"data":"${column}","defaultContent": ""}<#if column_has_next>,</#if>
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