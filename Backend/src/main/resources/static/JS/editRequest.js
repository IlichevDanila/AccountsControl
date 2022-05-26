$("#EditButton").click(function(e)
{
	if($(".EntityPressed").length > 0)
	{
	    window.location.href = "http://localhost:8080/Edit" + $(".EntityPressed").attr('data-entityid');
	}
})