window.onload = MyMain;

var Persoane = [];

function Person(nume, dn, cnp) {
	this.nume = nume;
	this.dn = dn;
	this.cnp = cnp;
	
}

function MyMain(){

	document.getElementById('btn1').onclick = Adauga;
	document.getElementById('btn2').onclick = Afiseaza;
	document.getElementById('btn3').onclick = Selecteaza;
}

function correctCnp(cnp) {
	if (cnp.length != 13) {
		return false;
	}
	
	var regularExpression = /\d{13}/;
	var isOk = regularExpression.exec(cnp);
	if (!isOk)
		return false;
	return cnp == isOk[0];
}

function Adauga() {
	var nume = prompt("Introduceti numele");
	var dn = new Date(prompt("Data de nastere"));
	var cnp = prompt("Codul numeric personal");
	if (correctCnp(cnp)) {
		var persoana = new Person(nume, dn, cnp);
		Persoane.push(persoana);
	}
	else {
		alert("Cnp-ul introdus este gresit");
	}
}

function ProprietatiPersoana(persoana) {
	return persoana.nume + " avand data de nastere pe " + persoana.dn.toString() + " si cnp-ul " + persoana.cnp;
}

function Afiseaza() {
	var result = "";
	
	for (var i = 0; i < Persoane.length; ++i) {
		var persoana = Persoane[i];
		result += ProprietatiPersoana(persoana) + " ";
	}
	
	var div = document.getElementById("info");
	div.innerHTML = result;
}

function Selecteaza() {
	var varsta = parseInt(prompt("Introduceti o varsta"));
	
	varstaInMiliS = varsta * 365 * 24 * 60 * 60 * 1000;
	
	var result = "";
	for (var i = 0; i < Persoane.length; ++i)
	{
		var persoana = Persoane[i];
		if (Date.now() - persoana.dn <= varstaInMiliS)
			result += ProprietatiPersoana(persoana) + "\n";
	}
	
	alert(result);
	
}
