$(document).ready(() => {
	$('select').on('change', function(e) {
		$.ajax({
			url: "./api/setSize/" + this.value,
			type: 'GET',
			success: function(res) {
				console.log("OK")
			}
		});
	});
});