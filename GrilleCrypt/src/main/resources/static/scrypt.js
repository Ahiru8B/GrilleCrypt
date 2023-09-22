$(document).ready(() => {
	$('select').on('change', function(e) {
		let size = this.value;
		$.ajax({
			url: "./api/setSize/" + size,
			type: 'GET',
			success: function(res) {
				let matrix = "";
				for(let i = 0; i < size; i++) {
					matrix += "<div>";
					for(let j = 0; j < size; j++) {
						matrix += '<button id="' + i + '-' + j + '" type="button" class="btn btn-secondary matrix-button" disabled></button>'
					}
					matrix += "</div>";
				}
				
				$("#matrix").html(matrix);
				$.ajax({
					url: "./api/getFreeIndex",
					type: 'GET',
					success: function(free) {
						for(let i = 0; i < free.length; i++) {
							id = free[i]["row"] + "-" + free[i]["column"];
							$("#" + id).prop('disabled', false);
						}
					}
				});
			}
		});
	});
});