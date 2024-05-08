<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
$(document).ready(function () {
	  $(".action").on("click", function () {
	    $(".menu").toggleClass("active");
	  });
	});
</script>

</head>
<body>
<header>

<a href="/EasyGames/"><img src="images/logo.png" width="290" height="65" alt="Logo"></a>

<div>
<form action="searchGame" method="get">
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
	<button type="submit">submit</button>
</form>
</div>

<div>
<a href="CartServlet"><img src="images/carrello.png" width="35" height="35" alt="Carrello"></a>
</div>

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

 <div class="profile-menu">
      <div class="action">
		<img src="images/profilo.png" width="55" height="55" alt="Account">
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
            <a href="#">Ordini</a>
          </li>
          <li>
            <a href="<%= logPath %>"><%= log %></a>
          </li>
        </ul>
      </div>
    </div>

</header>
</body>
</html>