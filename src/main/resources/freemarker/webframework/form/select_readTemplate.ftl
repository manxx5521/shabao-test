<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
    <div class="form-group">
        <label class="control-label col-md-4 col-sm-4 col-xs-12" >${label}：</label>
        <div class="col-md-8 col-sm-8 col-xs-12">
        <#if dataList??>
  			<#list dataList as list>
  				<#if value?? && list.id==value>
  			<input type="text" id="${fieldCode}" class="form-control" value="${list.text}" readonly="readonly" />
  				</#if>
  			</#list>
  		</#if>
        	<input type="text" id="${fieldCode}_val" name="${fieldCode}" class="form-control" style="display:none;" <#if value?? >value="${value}"</#if> readonly="readonly" />
        </div>
    </div>
</div>
