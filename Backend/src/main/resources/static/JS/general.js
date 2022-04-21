$(document).on('click', ".Entity", function(e)
{
	$(".Entity").removeClass("EntityPressed");
	$(this).addClass("EntityPressed");
})

$("#SearchButton").click(function(e)
{
	console.log($(".EntityPressed"));
	console.log("http://localhost:8080/" + $(".EntityPressed").attr('data-entityid'));
	window.location.href = "http://localhost:8080/" + $(".EntityPressed").attr('data-entityid');
})