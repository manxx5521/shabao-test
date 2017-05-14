$(document).ready(function() {
    $('.${dataTablesName}').dataTable( {
        "processing": true,
        "serverSide": true,
        "columns":[
            <#list titleList as column>
            {data:'${column}'}<#if column_has_next>,</#if>
  			</#list>
        ],
        "ajax": "../resources/server_processing_custom.php"
    } );
} );