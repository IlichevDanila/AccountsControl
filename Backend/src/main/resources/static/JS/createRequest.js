$("#CreateButton").click(function(e)
{
	if($(".EntityPressed").length > 0)
	{
	    window.location.href = "http://localhost:8080/Create" + $(".EntityPressed").attr('data-entityid');
	}
})