<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="css/header_style.css">
<script src="script/headerScript.js"></script>
</head>
<body>
<header>

<a href="/EasyGames/"><img id="logo" src="images/logo.png" width="250" height="65" alt="Logo"></a>

<div id="searchBar" class="search-bar">
<form id="formSearch" action="searchGame" method="get">
	<select name="piattaforma">
	<option value="tutto">Tutto</option>
	<option value="origin">Origin</option>
	<option value="steam">Steam</option>
	<option value="battle.net">Battle.net</option>
	<option value="ubisoft">Ubisoft</option>
	<option value="xbox one">XBOX One</option>
	<option value="xbox x/s">XBOX Series X/S</option>
	<option value="ps4">PS4</option>
	<option value="ps5">PS5</option>
	<option value="nintendo switch">Nintendo Switch</option>
	<option value="epic games">Epic Games</option>
	</select>
	<input type="text" name="search" placeholder="Search">
	<button type="submit"><i class="fa fa-search icon-search icon-color"></i></button>
</form>
</div>

<i id ="buttonSearchResponsive" class="fa fa-search icon-search icon-color" onclick="toggleSearchBar()"></i>

<i id ="buttonX" class="fa fa-times icon-color fa-2x" onclick="toggleSearchBar()"></i>

<%
	String accountName = (String) request.getSession().getAttribute("accountName");
	String logPath;
	String log;
	if(accountName == null)
	{
		accountName = "Ospite";
		logPath = "./login.jsp";
		log = "Log In";
	}
	else
	{
		logPath = request.getContextPath() + "/common/Logout";
		log = "Log Out";
	}
%>

<div id="account_cart">
<div class="cart">
<a href="CartServlet"><img src="images/carrello.png" width="60" height="60" alt="Carrello"></a>
</div>

 <div class="profile-menu">
      <div class="action">
		<img src="images/profilo.png" width="60" height="60" alt="Account">
      </div>
      <div class="menu">
        <div class="profile">
          <div class="info">
            <h2><%= accountName %></h2>
          </div>
        </div>
        <ul>
          <li>
            <a href="#">Account</a>
          </li>
          <li>
            <a href="UserOrder">Ordini</a>
          </li>
          <li>
            <a href="<%= logPath %>"><%= log %></a>
          </li>
        </ul>
      </div>
    </div>
</div>

</header>
</body>
</html>