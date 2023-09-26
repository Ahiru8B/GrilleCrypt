$(document).ready(() => {
	let size;
	$('select').on('change', function(e) {

		$("body").on("click", ".matrix-button", function() {
			matrixClick($(this));
		})

		$("body").on("click", "#encrypt", function() {
			encrypt();
		})

		$("body").on("click", "#decrypt", function() {
			decrypt();
		})
		
		size = this.value;
		$.ajax({
			url: "./api/setSize/" + size,
			type: 'GET',
			success: function(res) {
				drawMatrix(size);
			}
		});
	});

	function decrypt() {
		let text = $("#messageTextArea").val();
		$.ajax({
			url: "./api/decrypt/" + text,
			type: 'GET',
			success: function(res) {
				$("#outputMessage").val(res);
			}
		});
	}

	function encrypt() {
		let text = $("#messageTextArea").val();
		$.ajax({
			url: "./api/encrypt/" + text,
			type: 'GET',
			success: function(res) {
				$("#outputMessage").val(res);
			}
		});
	}


	function matrixClick(button) {
		let id = button.attr("id");
		let index = {}
		index["row"] = id.split("-")[0];
		index["column"] = id.split("-")[1];


		console.log(index);
		if (button.hasClass("passive")) {
			$.ajax({
				url: '/api/addIndex',
				contentType: "application/json",
				method: 'post',
				dataType: 'json',
				data: JSON.stringify(index),
				success: function(data) {
					alert(data);
				}
			});
			drawMatrix(size);
		} else {
			$.ajax({
				url: '/api/deleteIndex',
				contentType: "application/json",
				method: 'delete',
				dataType: 'json',
				data: JSON.stringify(index),
				success: function(data) {
					alert(data);
				}
			});
			drawMatrix(size);
		}
	}

	function drawMatrix(size) {
		let matrix = "";
		for (let i = 0; i < size; i++) {
			matrix += "<div>";
			for (let j = 0; j < size; j++) {
				matrix += '<button id="' + i + '-' + j + '" type="button" class="btn btn-secondary matrix-button" disabled></button>'
			}
			matrix += "</div>";
		}
		$("#matrix").html(matrix);
		addFreeElements();
		addActiveElements();
	}

	function addFreeElements() {
		$.ajax({
			url: "./api/getFreeIndex",
			type: 'GET',
			success: function(free) {
				for (let i = 0; i < free.length; i++) {
					id = free[i]["row"] + "-" + free[i]["column"];
					$("#" + id).prop('disabled', false);
					//$("#" + id).removeClass("btn-secondary");
					//					$("#" + id).addClass("btn-primary");
					$("#" + id).addClass("passive");
				}
			}
		});
	}

	function addActiveElements() {
		$.ajax({
			url: "./api/getActive",
			type: 'GET',
			success: function(free) {
				for (let i = 0; i < free.length; i++) {
					id = free[i]["row"] + "-" + free[i]["column"];
					$("#" + id).prop('disabled', false);
					$("#" + id).removeClass("btn-secondary");
					$("#" + id).addClass("btn-primary");
					$("#" + id).addClass("active");
				}
			}
		});
	}
});
