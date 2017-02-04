window.onload = MyMain;

function MyMain() {
	document.getElementById('btn').onclick = Generate;
	document.getElementById('btn2').onclick = MyAlert;
	document.getElementById('btn3').onclick = Disappear;
	Highlight();
}

function Generate() {
	var section = document.body.children[0];
	var list = document.createElement("ul");
	
	var li = document.createElement("li");
	var h1 = document.createElement("h1");
	var txt = document.createTextNode("carti");
	
	h1.appendChild(txt);
	li.appendChild(h1);
	
	var h2 = document.createElement("h2");
	txt = document.createTextNode("Cartea Junglei");
	
	h2.appendChild(txt);
	li.appendChild(h2);
	
	h2 = document.createElement("h2");
	txt = document.createTextNode("Moby Dick");
	h2.appendChild(txt);
	li.appendChild(h2);
	
	list.appendChild(li);
	
	li = document.createElement("li");
	h1 = document.createElement("h1");
	txt = document.createTextNode("poezii");
	
	h1.appendChild(txt);
	li.appendChild(h1);
	
	var h2 = document.createElement("h2");
	txt = document.createTextNode("Luceafarul");
	
	h2.appendChild(txt);
	li.appendChild(h2);
	
	h2 = document.createElement("h2");
	txt = document.createTextNode("Testament");
	
	h2.appendChild(txt);
	li.appendChild(h2);
	
	list.appendChild(li);
	
	var answer = prompt("Unde sa inserez textul? (inceput/sfarsit)");
	if (answer == "inceput") {
		section.appendChild(list);
	}
	else if (answer == "sfarsit") {
		document.body.appendChild(list);
	}
	else {
		alert("wrong input. Try again!")
	}
}

function Highlight() {
	var spans = document.getElementsByTagName("span");
	for(var i = 0; i < spans.length; ++i) {
		var span = spans[i];
		
		var occurence = span.className.indexOf("highlight");
		if (occurence == -1) {
			span.className += " highlight";
		}
		else {
			span.className.replace("highlight", "highlight");
		}
		
  }
}

function MyAlert() {
	var input = document.getElementById("input").value;
	var inAlert = document.getElementById("textInAlert");
	inAlert.innerHTML = input;

	var myAlert = document.getElementById("alert");
	myAlert.className = "showAlert"
}

function Disappear() {
	var myAlert = document.getElementById("alert");
	myAlert.className = "hideAlert"
}