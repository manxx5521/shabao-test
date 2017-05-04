<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
    <div class="form-group">
        <label class="control-label col-md-4 col-sm-4 col-xs-12" >${label}：</label>
        <div class="col-md-8 col-sm-8 col-xs-12">
            <select id="${fieldCode}" name="${fieldCode}" class="form-control">
            <#if dataList??>
  			<#list dataList as list>
  				<#if value?? && list.id==value>
  				<option value="${list.id}" selected="selected">${list.text}</option>
  				<#else>
  				<option value="${list.id}">${list.text}</option>
  				</#if>
  			</#list>
  			</#if>
			</select>
        </div>
    </div>
</div>
