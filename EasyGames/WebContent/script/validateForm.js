const nicknamePattern = /^[A-Za-z][A-Za-z0-9]*$/;
const nameOrSurnamePattern = /^[A-Za-z]+$/;
const emailPattern = /^\S+@\S+\.\S+$/;
const passwordPattern = /^(?=.*[A-Z])(?=.*\d).{6,}$/;
const nicknameErrorMessage = "Il nickname non può iniziare con un numero e può contenere solo numeri e lettere";
const nameErrorMessage = "Il nome può contenere solo lettere";
const surnameErrorMessage = "Il cognome può contenere solo lettere";
const emailErrorMessage = "L'email deve essere nel seguente formato username@domain.ext";
const passwordErrorMessage = "La password deve contenere almeno una lettera maiuscola, un numero e deve avere almeno 6 caratteri";
const emptyFieldErrorMessage = "Questo campo è obbligatorio";


function validateFormElem(formElem, pattern, span, message) {
	if (formElem.value.trim() === ""){
		formElem.classList.add("error");
		span.innerHTML = emptyFieldErrorMessage;
		span.style.color = "red";
		return false;
	}
	
	if(formElem.value.match(pattern)){
		formElem.classList.remove("error");
		span.style.color = "black";
		span.innerHTML = "";
		return true;
	}
	
	formElem.classList.add("error");
	span.innerHTML = message;
	span.style.color = "red";
	return false;
}

function validate() {
	let valid = true;	
	let form = document.getElementById("regForm");
	
	let spanNickname = document.getElementById("errorNickname");
	if(!validateFormElem(form.nickname, nicknamePattern, spanNickname, nicknameErrorMessage)){
		valid = false;
	}
	
	let spanName = document.getElementById("errorName");
	if (!validateFormElem(form.nome, nameOrSurnamePattern, spanName, nameErrorMessage)){
		valid = false;
	}
	
	let spanSurname = document.getElementById("errorSurname");
	if (!validateFormElem(form.cognome, nameOrSurnamePattern, spanSurname, surnameErrorMessage)){
		valid = false;
	}
	
	let spanEmail = document.getElementById("errorEmail");
	if (!validateFormElem(form.email, emailPattern, spanEmail, emailErrorMessage)){
		valid = false;
	}
	
	let spanPassword = document.getElementById("errorPassword");
	if (!validateFormElem(form.password, passwordPattern, spanPassword, passwordErrorMessage)){
		valid = false;
	}
	
	return valid;
}
