$(document).on('click', '#ChangeViewButton',
function()
{
    var id = $("#ID_input").val();
    var byAcc = $('#AccountSearch_input').is(":checked");
    var acc = $("#Account_input").val();
    var debit_acc = $("#Debit_input").val();
    var credit_acc = $("#Credit_input").val();
    var time_low = $("#Time_low_input").val();
    var time_high = $("#Time_high_input").val();
    var amount_low = $("#Sum_low_input").val();
    var amount_high = $("#Sum_high_input").val();

    var params = "";

    if(id != "")
    {
        params += "id=" + encodeURIComponent(id) + "&";
    }
    if(byAcc)
    {
        params += "byAcc=X&";
        if(acc != "")
        {
            params += "acc=" + encodeURIComponent(acc) + "&";
        }
    }
    else
    {
        if(debit_acc != "")
        {
            params += "debit=" + encodeURIComponent(debit_acc) + "&";
        }
        if(credit_acc != "")
        {
            params += "credit=" + encodeURIComponent(credit_acc) + "&";
        }
    }

    if(time_low != "")
    {
        params += "timeL=" + encodeURIComponent(time_low) + "&";
    }
    if(time_high != "")
    {
        params += "timeH=" + encodeURIComponent(time_high) + "&";
    }
    if(amount_low != "")
    {
        params += "amountL=" + encodeURIComponent(amount_low) + "&";
    }
    if(amount_high != "")
    {
        params += "amountH=" + encodeURIComponent(amount_high);
    }

    window.location.href = "http://localhost:8080/TransactionsSearch?" + params;
})

$(document).on('click', '#OKButton',
function()
{
    var id = $("#ID_input").val();
    var debit = $("#Debit_input").val();
    var credit = $("#Credit_input").val();
    var date = $("#Date_input").val();
    var balance = $("#Sum_input").val();

    params="id="+id+"&";

    if(debit != "")
    {
        params += "debit=" + encodeURIComponent(debit) + "&";
    }
    if(credit != "")
    {
        params += "credit=" + encodeURIComponent(credit) + "&";
    }
    if(date != "")
    {
        params += "date=" + encodeURIComponent(date) + "&";
    }
    if(balance != "")
    {
        params += "balance=" + encodeURIComponent(balance) + "&";
    }

    window.location.href = "http://localhost:8080/TransactionsUpdate?" + params;
})