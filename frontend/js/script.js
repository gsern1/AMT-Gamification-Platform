$(document).ready(function() {
	$( "#username" ).val($.cookie("username"));
	$( "#username" ).on("input", function() {
		console.log("wtf");
	  $.cookie("username", $("username").val());console.log($.cookie());
	});
	$( "#cat1" ).click(function() {
		$.post( "127.0.0.1:8090", {type: "cat1Click", username: "user1"}, function(data) {
			$( ".result" ).html( data );
		});
	});
});