$(".Cell").click(function(e)
{
    if($(this).hasClass("SelectedRow"))
    {
        $(".Cell").removeClass("SelectedRow");
    }
    else
    {
        $(".Cell").removeClass("SelectedRow");
        $("[data-row='" + $(this).attr("data-row") + "']").addClass("SelectedRow");
    }
})