$(document).ready(function() {
	var cat1cpt = 0;
	var cat2cpt = 0;
	$( "#username" ).val($.cookie("username"));
	$( "#username" ).on("input", function() {
		console.log("wtf");
	  $.cookie("username", $("username").val());console.log($.cookie());
	});
	$( "#cat1" ).click(function() {
		cat1cpt++;
		$("#cat1cpt").text(cat1cpt);
		$.post( "127.0.0.1:8090/api/events", {type: "cat1Click", username: $.cookie("username")}, function(data) {
			console.log("Posted a new cat1Click event " + data);
		});
	});
	$( "#cat2" ).click(function() {
		cat2cpt++;
		$("#cat2cpt").text(cat2cpt);
		$.post( "127.0.0.1:8090/api/events", {type: "cat2Click", username: $.cookie("username")}, function(data) {
			console.log("Posted a new cat2Click event " + data);
		});
	});
	$.get( "127.0.0.1:8090/api/leaderboard", function(data) {
		console.log("Received the leaderboard " + data);
	});
	$.get( "127.0.0.1:8090/api/user/" + $.cookie("username") + "/badges", function(data) {
		console.log("Received the user's badges " + data);
	});
	$.get( "127.0.0.1:8090/api/user/" + $.cookie("username") + "/pointScales", function(data) {
		console.log("Received the user's pointScales " + data);
	});
});