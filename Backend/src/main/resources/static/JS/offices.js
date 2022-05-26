$(document).on('click', '#ChangeViewButton',
function()
{
    var id = $('#ID_input').val();
    var phone = $('#Phone_input').val();
    var address = $('#Address_input').val();

    var params = "";

    if(id != "")
    {
        params += "id=" + encodeURIComponent(id) + "&";
    }
    if(phone != "")
    {
        params += "phone=" + encodeURIComponent(phone) + "&";
    }
    if(address != "")
    {
        params += "address=" + encodeURIComponent(address);
    }

    window.location.href = "http://localhost:8080/OfficesSearch?" + params;
})