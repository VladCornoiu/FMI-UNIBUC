window.onload = Main;


function Main() {
	document.getElementById("fbut").onclick = Adauga;
	document.getElementById("sbut").onclick = Adauga2;
}

function Adauga() {
	var div1 = document.createElement("div");
	var paragraph1 = document.createElement("p");
	var paragraph2 = document.createElement("p");
	var txt1 = document.createTextNode("Under first title1");
	var txt2 = document.createTextNode("Under first title2");
	paragraph1.appendChild(txt1);
	paragraph2.appendChild(txt2);
	div1.appendChild(paragraph1);
	div1.appendChild(paragraph2);
	var item = document.body;
	
	document.body.insertBefore(div1, item.childNodes[2]);
}

function Adauga2() {
	var div1 = document.createElement("div");
	var paragraph1 = document.createElement("p");
	var paragraph2 = document.createElement("p");
	var txt1 = document.createTextNode("Under second title1");
	var txt2 = document.createTextNode("Under second title2");
	paragraph1.appendChild(txt1);
	paragraph2.appendChild(txt2);
	div1.appendChild(paragraph1);
	div1.appendChild(paragraph2);
	var item = document.body;
	var insertBef = document.getElementById("fbut");
	
	document.body.insertBefore(div1, insertBef);
}