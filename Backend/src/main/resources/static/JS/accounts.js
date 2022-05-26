$(document).on('click', '#ChangeViewButton',
function()
{
    var accId = $("#ID_input").val();
    var status = $("#Status_input").val();
    var date_low = $("#Date_low_input").val();
    var date_high = $("#Date_high_input").val();
    var type = $("#AccountType_input").val();
    var response = $("#Response_input").val();
    var loan = $("#Loan_input").val();
    var client = $("#Client_input").val();
    var balance_low = $("#Balance_low_input").val();
    var balance_high = $("#Balance_high_input").val();

    var params = "";

    if(accId != "")
    {
        params += "accId=" + encodeURIComponent(accId) + "&";
    }
    if(status != "")
    {
        params += " status=" + encodeURIComponent(status) + "&";
    }
    if(date_low != "")
    {
        params += "date_low=" + encodeURIComponent(date_low) + "&";
    }
    if(date_high != "")
    {
        params += "date_high=" + encodeURIComponent(date_high) + "&";
    }
    params += "type=" + encodeURIComponent(type) + "&";
    if(response != "")
    {
        params += "response=" + encodeURIComponent(response) + "&";
    }
    if(loan != "")
    {
        params += "loan=" + encodeURIComponent(loan) + "&";
    }
    if(client != "")
    {
        params += "client=" + encodeURIComponent(client) + "&";
    }
    if(balance_low != "")
    {
        params += "balance_low=" + encodeURIComponent(balance_low) + "&";
    }
    if(balance_high != "")
    {
        params += "balance_high=" + encodeURIComponent(balance_high);
    }

    window.location.href = "http://localhost:8080/AccountsSearch?" + params;
})

$(document).on('click', '#OKButton',
function()
{
    var accId = $("#ID_input").val();
    var status = $("#Status_input").val();
    var date = $("#Date_input").val();
    var type = $("#AccountType_input").val();
    var response = $("#Response_input").val();
    var loan = $("#Loan_input").val();
    var client = $("#Client_input").val();
    var balance = $("#Balance_input").val();

    var params = "";

    if(accId != "")
    {
        params += "accId=" + encodeURIComponent(accId) + "&";
    }
    if(status != "")
    {
        params += " status=" + encodeURIComponent(status) + "&";
    }
    if(date != "")
    {
        params += "date=" + encodeURIComponent(date) + "&";
    }
    params += "type=" + encodeURIComponent(type) + "&";
    if(response != "")
    {
        params += "response=" + encodeURIComponent(response) + "&";
    }
    if(loan != "")
    {
        params += "loan=" + encodeURIComponent(loan) + "&";
    }
    if(client != "")
    {
        params += "client=" + encodeURIComponent(client) + "&";
    }
    if(balance != "")
    {
        params += "balance=" + encodeURIComponent(balance) + "&";
    }

    window.location.href = "http://localhost:8080/AccountsUpdate?" + params;
})