$(document).ready(function () {
			//hide error secion
			$("specCodeError").hide();
			$("specNameError").hide();
			$("specNoteError").hide();
			//define error variable
			var specCodeError = false;
			var specNameError = false;
			var specNoteError = false;
			//validate function

			function validate_specNote() {
				var val = $("#specNote").val();
				var exp = /^[A-Za-z0-9\s\.\-]{4,25}$/;
				if (val == '') {
					$("#specNoteError").show();
					specNoteError = false;
					$("#specNoteError").html("<b>*Note</b> cannot be empty");
					$("#specNoteError").css('color', 'red');
				}
				else if (!exp.test(val)) {
					$("#specNoteError").show();
					specNoteError = false;
					$("#specNoteError").html("<b>*Note</b> can have 10 to 150 chars A-Za-z0-9.,-(space)");
					$("#specNoteError").css('color', 'red');
				}
				else {
					
					$("#specNoteError").hide();
					specNoteError = true;


				}
				return specNoteError;
			}
			function validate_specName() {
				var val = $("#specName").val();
				var exp = /^[A-Za-z0-9\s\.]{4,25}$/;
				if (val == '') {
					$("#specNameError").show();
					specNameError = false;
					$("#specNameError").html("<b>*Name</b> cannot be empty");
					$("#specNameError").css('color', 'red');
				}
				else if (!exp.test(val)) {
					$("#specNameError").show();
					specNameError = false;
					$("#specNameError").html("<b>*Name</b> must be 4 to 12");
					$("#specNameError").css('color', 'red');
				}
				else {
					$.ajax({
						url:'checkName',
						data:{"name":val},
						success:function(respText){
							if (respText != '') {
								$("#specNameError").show();
								specNameError = false;
								$("#specNameError").html(respText);
								$("#specNameError").css('color', 'red');
							}
							else {
								$("#specNameError").hide();
								specNameError = true;
							}
							
						}
					})


				}
				return specNameError;
			}

			function validate_specCode() {
				var val = $("#specCode").val();
				var exp = /^[A-Z]{4,12}$/;
				if (val == '') {
					$("#specCodeError").show();
					specCodeError = false;
					$("#specCodeError").html("<b>*Code</b> cannot be empty");
					$("#specCodeError").css('color', 'red');
				}
				else if (!exp.test(val)) {
					$("#specCodeError").show();
					specCodeError = false;
					$("#specCodeError").html("<b>*Code</b> must be 4 to 12 without space");
					$("#specCodeError").css('color', 'red');
				}
				else {
					$.ajax({
						url:'checkCode',
						data:{"code":val},
						success:function(respText){
							if (respText != '') {
								$("#specCodeError").show();
								specCodeError = false;
								$("#specCodeError").html(respText);
								$("#specCodeError").css('color', 'red');
							}
							else {
								$("#specCodeError").hide();
								specCodeError = true;
							}
							
						}
					})


				}
				return specCodeError;
			}
			//action-event
			$("#specNote").keyup(function () {
				validate_specNote();
			})
			$("#specName").keyup(function () {
				validate_specName();
			})
			$("#specCode").keyup(function () {
				$(this).val($(this).val().toUpperCase());
				validate_specCode();
			})
			//on submit
			$("#specForm").submit(function () {
				validate_specCode();
				validate_specName();
				validate_specNote();
				if(specCodeError && specNameError &&specNoteError)
					return true;
				
				else return false;

			})


		})