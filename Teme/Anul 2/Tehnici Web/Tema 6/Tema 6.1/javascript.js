window.onload = myMain;

function myMain() {
	document.getElementById('p1').onmouseover = stil1;
	document.getElementById('p1').onmouseout = stil2;
	
	document.getElementById('p2').onmouseover = stil3;
	document.getElementById('p2').onmouseout = stil4;
	
	document.getElementById('btn1').onclick = function () {
		var request = prompt("Introduceti cuvant si paragraf");

		var word = request.substr(0, request.indexOf(" "));
		
		var paragraph = parseInt(request.substr(request.indexOf(' ')));
		
		if (paragraph != 1 && paragraph != 2)
			return alert("Incorect");
		
		var text = "";
		if (paragraph == 1) text = document.getElementById("p1").textContent;
		else text = document.getElementById("p2").textContent;
		
		var cnt = 0;
		ind = text.indexOf(word);
		while(ind != -1) {
			cnt++;
			text = text.substr(ind + 1);
			ind = text.indexOf(word);
		}
		return alert(cnt + " aparitii");
	}
}

function stil1() {
	document.getElementById('p2').className = "stil1";
}

function stil2() {
	document.getElementById('p2').className = "stil2";
}

function stil3() {
	document.getElementById('p1').className = "stil2";
}

function stil4() {
	document.getElementById('p1').className = "stil1";
}