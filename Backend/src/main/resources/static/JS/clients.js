$(document).on('click', '#ChangeViewButton',
function()
{
    var tin = $('#TIN_input').val();
    var phone = $('#Phone_input').val();
    var address = $('#Address_input').val();
    var type = $('#ClientType_select').val();

    var params = "";
    if(tin != "")
    {
        params += "tin=" + encodeURIComponent(tin) + "&";
    }
    if(phone != "")
    {
        params += "phone=" + encodeURIComponent(phone) + "&";
    }
    if(address != "")
    {
        params += "address=" + encodeURIComponent(address) + "&";
    }
    params += "type=" + encodeURIComponent(type) + "&";

    if(type == "Physical")
    {
        var fio = $('#FIO_input').val();
        var passport = $('#Passport_input').val();

        if(fio != "")
        {
            params += "fullName=" + encodeURIComponent(fio) + "&";
        }
        if(passport != "")
        {
            params += "passport=" + encodeURIComponent(passport);
        }
    }
    else
    {
        var name = $('#OrgName_input').val();
        var form = $('#OrgForm_input').val();

        if(name != "")
        {
            params += "name=" + encodeURIComponent(name) + "&";
        }
        if(form != "")
        {
            params += "form=" + encodeURIComponent(form);
        }
    }

    window.location.href = "http://localhost:8080/ClientsSearch?" + params;
})

$(document).on('click', '#CreateButton',
function()
{
    var tin = $('#TIN_input').val();
    var phone = $('#Phone_input').val();
    var address = $('#Address_input').val();
    var type = $('#ClientType_select').val();

    var params = "";
    if(tin != "")
    {
        params += "tin=" + encodeURIComponent(tin) + "&";
    }
    if(phone != "")
    {
        params += "phone=" + encodeURIComponent(phone) + "&";
    }
    if(address != "")
    {
        params += "address=" + encodeURIComponent(address) + "&";
    }
    params += "type=" + encodeURIComponent(type) + "&";

    if(type == "Physical")
    {
        var fio = $('#FIO_input').val();
        var passport = $('#Passport_input').val();

        if(fio != "")
        {
            params += "fullName=" + encodeURIComponent(fio) + "&";
        }
        if(passport != "")
        {
            params += "passport=" + encodeURIComponent(passport);
        }
    }
    else
    {
        var name = $('#OrgName_input').val();
        var form = $('#OrgForm_input').val();

        if(name != "")
        {
            params += "name=" + encodeURIComponent(name) + "&";
        }
        if(form != "")
        {
            params += "form=" + encodeURIComponent(form);
        }
    }

    window.location.href = "http://localhost:8080/ClientsUpdate?" + params;
})