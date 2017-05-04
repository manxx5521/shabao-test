<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
    <div class="form-group">
        <label class="control-label col-md-4 col-sm-4 col-xs-12" >${label}：</label>
        <div class="col-md-8 col-sm-8 col-xs-12">
        	<input  id="${fieldCode}" name="${fieldCode}" class="form-control" <#if value?? >value="${value}"</#if> readonly="readonly" />
        </div>
    </div>
</div>
