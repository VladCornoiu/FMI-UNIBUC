window.onload = Main;

function Main() {
	document.getElementById("afis").onclick = Afiseaza;
	document.getElementById("text").onclick = KeepText;
}

function KeepText() {
	var first = document.forms["myform"]["firstname"].value;
	var second = document.forms["myform"]["lastname"].value;
	
	localStorage.setItem("fname", first);
	localStorage.setItem("lname", second);
}

function Afiseaza() {
	alert("Firstname is: " + localStorage.getItem("fname") + "/ SecondName is: " + localStorage.getItem("fname")); 
	
}