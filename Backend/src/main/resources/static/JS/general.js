$(document).on('click', ".Entity", function(e)
{
	$(".Entity").removeClass("EntityPressed");
	$(this).addClass("EntityPressed");
})

$("#SearchButton").click(function(e)
{
	if($(".EntityPressed").length > 0)
	{
	    window.location.href = "http://localhost:8080/" + $(".EntityPressed").attr('data-entityid');
	}
})