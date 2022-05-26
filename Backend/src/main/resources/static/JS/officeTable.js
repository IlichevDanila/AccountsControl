$(document).on('click', "#ChangeViewButton", function(e)
{
    if($(".SelectedRow").length > 0)
    {
        var id = $(".SelectedRow").attr("data-row");
        window.location.href = "http://localhost:8080/EditOffices?id=" + id;
    }
})