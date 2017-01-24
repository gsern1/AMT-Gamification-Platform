$(document).ready(function() {
	var updateTables = function(){
		$.get( "127.0.0.1:8090/api/leaderboard", function(data) {
			console.log("Received the leaderboard " + data);
		});
		$.get( "127.0.0.1:8090/api/user/" + localStorage.getItem("username") + "/badges", function(data) {
			console.log("Received the user's badges " + data);
		});
		$.get( "127.0.0.1:8090/api/user/" + localStorage.getItem("username") + "/pointScales", function(data) {
			console.log("Received the user's pointScales " + data);
		});
	}
	
	
	var cat1cpt = 0;
	var cat2cpt = 0;
	$( "#username" ).val(localStorage.getItem("username"));
	updateTables();
	$( "#username" ).on("input", function() {
		if($("#username").val() != ""){
			localStorage.setItem("username", $("#username").val());
			updateTables();
		}
	});
	$("#refresh").click(function(){
		updateTables();
		return false;
	});
	$( "#cat1" ).click(function() {
		cat1cpt++;
		$("#cat1cpt").text(cat1cpt);
		$.post( "127.0.0.1:8090/api/events", {type: "cat1Click", username: localStorage.getItem("username")}, function(data) {
			console.log("Posted a new cat1Click event " + data);
		});
	});
	$( "#cat2" ).click(function() {
		cat2cpt++;
		$("#cat2cpt").text(cat2cpt);
		$.post( "127.0.0.1:8090/api/events", {type: "cat2Click", username: localStorage.getItem("username")}, function(data) {
			console.log("Posted a new cat2Click event " + data);
		});
	});
});