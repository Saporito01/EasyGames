<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, java.time.LocalDate"%>

<%
LocalDate oggi = LocalDate.now();
int anno = oggi.getYear();
int mese = oggi.getMonthValue();
String annoMese = String.format("%04d-%02d", anno, mese);
%>

<!DOCTYPE html>
<html>
<head>
<title>Ordini</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/png" href="../images/logo_scheda.png"/>
<link rel="stylesheet" type="text/css" href="../css/orders_view.css">

<script>
function fetchAndRenderOrders(annoMese) {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', '../UserOrder?annoMese=' + annoMese, true);
    
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
var annoMeseInput = document.querySelector('input[name="annoMese"]');
    
    annoMeseInput.addEventListener('change', function() {
        var annoMeseValue = annoMeseInput.value;
        fetchAndRenderOrders(annoMeseValue);
    });
    
    // Carica gli ordini iniziali
    fetchAndRenderOrders("<%=annoMese%>");
});
</script>
</head>
<body>

<%@include file="/fragment/header.jsp"%>

<div class="orders-main">

<div class="inputDate"><input type="month" name="annoMese" value="<%=annoMese%>"></div>

<div class="order-list"  id="ordersList">
    <!-- Gli ordini saranno aggiunti dinamicamente qui -->
</div>

</div>

<%@include file="/fragment/footer.jsp" %>

</body>
</html>