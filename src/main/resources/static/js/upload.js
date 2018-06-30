$(document).ready(init);

function init(){
	$('#btnSubmit').click(function(event){
		event.preventDefault();
		ajaxSubmit();
	})
}

function ajaxSubmit(){
	var form = $('#fileUploadForm')[0];
	var data = new FormData(form);
	$.ajax({
		type: 'POST',
		enctype: 'multipart/form-data',
		url: '/upload/',
		data: data,
		processData: false,
        contentType: false,
        cache: false,
	}).success(function(){
		$('#result').html('Success!')
	}).error(function(){
		$('#result').html('Error')
	})
}