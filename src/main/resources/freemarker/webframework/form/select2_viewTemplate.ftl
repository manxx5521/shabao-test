<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
    <div class="form-group">
        <label class="control-label col-md-4 col-sm-4 col-xs-12">${lable}：</label>
        <div class="col-md-8 col-sm-8 col-xs-12">
            <select id="${elementId}" name="${formKey}"></select>
        </div>
    </div>
</div>
$(document).ready(function(){
	$('#${elementId}').dselect2({
    	element_id:${elementId},
    	url:function(){
			return window.webroot+'/admin/ui/${engineType}/ajax/${elementId}';
		}
    });
}