window.onload = Main;

function Main() {
	document.getElementById("btn").onclick = Start;
}

function Start() {
	setTimeout(function() {
		setInterval(function() {
			alert("mesaj1");
			alert("mesaj2");
			alert("mesaj3");
		}, 3000);
	}, 10000);
}