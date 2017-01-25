$(document).ready(function() {
	var token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsIm5hbWUiOiJzdHJpbmcifQ.MRBKP1GrBf2iUH2iS3J7aoLTpmwHDqNlrrPFV-k81YI";
	var updateTables = function(){
		console.log("Updating tables...");
		$.ajax({
			url: 'http://127.0.0.1:8090/api/leaderboard',
			type: 'get',
			headers: {
				token: token,
				Accept: 'application/json',
				'Content-Type': 'application/json' 
			},
			dataType: 'json',
			success: function (data) {
				console.info("Received the leaderboard : " + JSON.stringify(data));
				$("#leaderboardBody").text("");
				for(var i in data){
					$("#leaderboardBody").append("<tr><td>" + data[i].username + "</td><td>" + data[i].numberOfBadges + "</td></tr>");
				}
			},
			error: function (err) {
				console.info("Error getting the leaderboard : " + JSON.stringify(err));
			}
		});
		$.ajax({
			url: "http://127.0.0.1:8090/api/users/" + localStorage.getItem("username") + "/badges",
			type: 'get',
			headers: {
				token: token,
				Accept: 'application/json',
				'Content-Type': 'application/json' 
			},
			dataType: 'json',
			success: function (data) {
				console.info("Received the user badges : " + JSON.stringify(data));
				$("#badgesBody").text("");
				for(var i in data){
					$("#badgesBody").append("<tr><td>" + data[i].name + "</td></tr>");
				}
			},
			error: function (err) {
				console.info("Error getting the user badges : " + JSON.stringify(err));
			}
		});
		$.ajax({
			url: "http://127.0.0.1:8090/api/users/" + localStorage.getItem("username") + "/pointScales",
			type: 'get',
			headers: {
				token: token,
				Accept: 'application/json',
				'Content-Type': 'application/json' 
			},
			dataType: 'json',
			success: function (data) {
				console.info("Received the user pointscales : " + JSON.stringify(data));
				$("#pointscalesBody").text("");
				for(var i in data){
					$("#pointscalesBody").append("<tr><td>" + data[i].name + "</td><td>" + data[i].points + "</td></tr>");
				}
			},
			error: function (err) {
				console.info("Error getting the user pointscales : " + JSON.stringify(err));
			}
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
		$.ajax({
			url: "http://127.0.0.1:8090/api/events",
			type: 'post',
			data: JSON.stringify({type: "cat1Click", username: localStorage.getItem("username")}),
			headers: {
				token: token,
				'Content-Type': 'application/json' 
			},
			dataType: 'text',
			success: function (data) {
				console.info("Posted a new cat1Click event : " + JSON.stringify(data));
				updateTables();
			},
			error: function (err) {
				console.info("Error posting a new cat1Click event : " + JSON.stringify(err));
			}
		});
	});
	$( "#cat2" ).click(function() {
		cat2cpt++;
		$("#cat2cpt").text(cat2cpt);
		$.ajax({
			url: "http://127.0.0.1:8090/api/events",
			type: 'post',
			data: JSON.stringify({type: "cat2Click", username: localStorage.getItem("username")}),
			headers: {
				token: token,
				'Content-Type': 'application/json' 
			},
			dataType: 'text',
			success: function (data) {
				console.info("Posted a new cat2Click event : " + JSON.stringify(data));
				updateTables();
			},
			error: function (err) {
				console.info("Error posting a new cat2Click event : " + JSON.stringify(err));
			}
		});
	});
});