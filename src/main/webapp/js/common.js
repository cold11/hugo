$(function() {
	var inputDate = $('.date').val();
	if (inputDate != null) {
		$('.date-hours').datetimepicker({
			format: 'yyyy-mm-dd hh:00',
			weekStart: 1,
			autoclose: true,
			todayHighlight: true,
			startView: 2,
			minView: 1,
			pickerPosition: "top-left"
		});
	}
	
	var events = {
		sidebarMenu: function() {
			$(".c-dropdown-a").click(function() {
				$(this).parent().siblings().removeClass("c-open");
				$(this).parent().toggleClass("c-open");
			});
		},
		zan: function() {
			$(".zan").click(function() {
				$(this).toggleClass("zanok");
			});
		},
		//equalToPassword: function() {
		//	$("input[type=submit]").click(function() {
		//		var psd = $("#password").val();
		//		var epsd = $("#equalToPassword").val();
		//		if(epsd != psd){
		//			alert("两次密码输入不一致！");
		//			return false;
		//		}
		//	});
		//},
		uploadImg: function() {
			function getObjectURL(file) {
				var url = null;
				if (window.createObjectURL != undefined) {
					url = window.createObjectURL(file);
				} else if (window.URL != undefined) {
					url = window.URL.createObjectURL(file);
				} else if (window.webkitURL != undefined) {
					url = window.webkitURL.createObjectURL(file);
				}
				return url;
			}
			$(".photo-add .file-input").change(function() {
				var imgBox = $(this).parent().next();
				var files = this.files;
				for (var i = 0; i < files.length; i++) {
					var objUrl = getObjectURL(files[i]);
					var objName = files[i].name;
					if (objUrl) {
						imgBox.append("<a class='file-del'><img src='" + objUrl + "' alt='" + objName + "' /><em>-</em></a>");
						$(this).after($(this).clone(true));
						$(this).hide();
					}
				}
			});
			$(".doc-add .file-input").change(function() {
				var fileBox = $(this).parent().next();
				var value = this.value;
				fileBox.text(value);
			});
			$(document).on("click", ".file-img a em", function() {
				$(this).parent().remove();
			});
		},
		selectOption: function() {
			//color
			$("select").change(function() {
				if($(this).val() == ""){
					$(this).css("color","#ccc");
				}else{
					$(this).css("color","#555");
				}
			});
			//releaseType
			$("#publishType").change(function() {
				var ebookBox = $(this).parents(".form-group").next();
				var ebook = ebookBox.find("#ebook");
				if($(this).val() == 2){
					ebookBox.show();
					ebook.attr("required",true);
				}else{
					ebookBox.hide();
					ebook.removeAttr("required");
				}
			});
			//releaseRole
			$("#releaseRole").change(function() {
				var releaseAgency = $("#releaseAgency");
				var releaseCompany = $("#releaseCompany");
				var n = $(this).val();
				switch(n) {
					case "1":
						releaseAgency.attr("required",true).parents(".form-group").show();
						releaseCompany.removeAttr("required").parents(".form-group").hide();
						break;
					case "2":
						releaseCompany.removeAttr("required").parents(".form-group").hide();
						releaseAgency.removeAttr("required").parents(".form-group").hide();
						break;
					case "3":
						releaseCompany.attr("required",true).parents(".form-group").show();
						releaseAgency.removeAttr("required").parents(".form-group").hide();
						break;
				}
			});
		},
		offcanvas: function() {
			$('[data-toggle="offcanvas"]').click(function () {
			    $('.row-offcanvas').toggleClass('active')
			});
		}
	}
	
	
	
	
	events.sidebarMenu();
	events.zan();
	//events.equalToPassword();
	events.uploadImg();
	events.selectOption();
	events.offcanvas();
});