document.addEventListener("DOMContentLoaded", function() {
  var actionButton = document.querySelector(".action");
  var menu = document.querySelector(".menu");

  actionButton.addEventListener("click", function() {
    menu.classList.toggle("active");
  });
});

function toggleSearchBar() {
	  var searchBar = document.getElementById('searchBar');
	  searchBar.classList.toggle('active');
	  var logo = document.getElementById('logo');
	  logo.classList.toggle('active');
	  var accountCartDiv = document.getElementById('account_cart');
	  accountCartDiv.classList.toggle('active');
	  var buttonX = document.getElementById('buttonX');
	  buttonX.classList.toggle('active');
	  var buttonSearch = document.getElementById('buttonSearchResponsive');
	  buttonSearch.classList.toggle('active');
	  }
	  
