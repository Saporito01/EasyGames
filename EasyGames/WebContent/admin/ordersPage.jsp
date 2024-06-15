<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, java.time.LocalDate"%>
    
<%
ArrayList<String> accountList = (ArrayList<String>) request.getAttribute("accounts");
if(accountList == null) {
	response.sendRedirect("../AdminOrder?getAccounts=true");
	return;
}
%>
    
<%
LocalDate oggi = LocalDate.now();
int anno = oggi.getYear();
int mese = oggi.getMonthValue();
String annoMese = String.format("%04d-%02d", anno, mese);
%>

<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/png" href="./images/logo_scheda.png"/>
<link rel="stylesheet" type="text/css" href="./css/adminPage_style.css"/>

<script>
function fetchAndRenderOrders(annoMese,accountValue) {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'AdminOrder?annoMese=' + annoMese + '&account=' + accountValue, true);
    
    xhr.onload = function() {
        if (xhr.status === 200) {
            var ordersListDiv = document.getElementById('ordersList');
            ordersListDiv.innerHTML = xhr.responseText;
        } else {
            console.error('Errore durante il recupero degli ordini. Codice: ' + xhr.status);
        }
    };

    xhr.onerror = function() {
        console.error('Errore di rete durante la richiesta AJAX');
    };

    xhr.send();
}

document.addEventListener('DOMContentLoaded', function() {
var form = document.getElementById('searchForm');
var annoMeseInput = document.querySelector('input[name="annoMese"]');
var accountSelect = document.getElementById('account');
    
    form.addEventListener('change', function() {
        var annoMeseValue = annoMeseInput.value;
        var accountValue = accountSelect.value;
        fetchAndRenderOrders(annoMeseValue,accountValue);
    });
    
 	// Carica gli ordini iniziali
    fetchAndRenderOrders('<%=annoMese%>','tutto');
});
</script>

<title>Ordini</title>
</head>
<body>

<header class="header-admin">
<a href="/EasyGames/admin/gestione.jsp"><img src="./images/logo.png" width="290" height="65" alt="Logo"></a>
</header>

<div class="orders-main">

<div class="searchOrdersBar">
<form id="searchForm">
<input type="month" name="annoMese" value="<%=annoMese%>">
<select id="account" name="account">
<option value="tutto">Tutto</option>
<%for(int i=0; i<accountList.size(); i++){%>
<option value="<%=accountList.get(i)%>"><%=accountList.get(i)%></option>
<%}%>
</select>
</form>
</div>

<div class="order-list"  id="ordersList">
    <!-- Gli ordini saranno aggiunti dinamicamente qui -->
</div>

</div>

</body>
</html>