$(document).on('click', '#ChangeViewButton',
function()
{
    var id = $('#ID_input').val();
    var name = $('#Name_input').val();
    var profitability_low = $('#Profitability_low_input').val();
    var profitability_high = $('#Profitability_high_input').val();
    var percent = $('#Percent_input').is(":checked");
    var debiting = $('#Debiting_input').val();
    var accrual = $('#Accrual_input').val();
    var period = $('#Period_input').val();
    var valid = $('#Valid_input').val();

    var params = "";

    if(id != "")
    {
        params += "id=" + encodeURIComponent(id) + "&";
    }
    if(name != "")
    {
        params += "name=" + encodeURIComponent(name) + "&";
    }

    if(percent)
    {
        if(profitability_low != "")
        {
            params += "pprofit_low=" + encodeURIComponent(profitability_low) + "&";
        }
        if(profitability_high != "")
        {
            params += "pprofit_high=" + encodeURIComponent(profitability_high) + "&";
        }
    }
    else
    {
        if(profitability_low != "")
        {
            params += "fprofit_low=" + encodeURIComponent(profitability_low) + "&";
        }
        if(profitability_high != "")
        {
            params += "fprofit_high=" + encodeURIComponent(profitability_high) + "&";
        }
    }

    params += "debiting=" + encodeURIComponent(debiting) + "&";
    params += "accrual=" + encodeURIComponent(accrual) + "&";
    params += "valid=" + encodeURIComponent(valid) + "&";
    params += "period=" + encodeURIComponent(period);

    window.location.href = "http://localhost:8080/AccountTypesSearch?" + params;
});

$(document).on('click', '#CreateButton',
function()
{
    var id = $('#ID_input').val();
       var name = $('#Name_input').val();
       var profitability = $('#Profitability').val();
       var percent = $('#Percent_input').is(":checked");
       var debiting = $('#Debiting_input').val();
       var accrual = $('#Accrual_input').val();
       var period = $('#Period_input').val();
       var valid = $('#Valid_input').val();
       var params = "";
       if(id != "")
       {
           params += "id=" + encodeURIComponent(id) + "&";
       }
       if(name != "")
       {
           params += "name=" + encodeURIComponent(name) + "&";
       }
       if(percent)
       {
           params += "percent=X&";
       }
       params += "profit=" + encodeURIComponent(profitability) + "&";
       params += "debiting=" + encodeURIComponent(debiting) + "&";
       params += "accrual=" + encodeURIComponent(accrual) + "&";
       params += "valid=" + encodeURIComponent(valid) + "&";
       params += "period=" + encodeURIComponent(period);
       window.location.href = "http://localhost:8080/AccountTypesUpdate?" + params;
})