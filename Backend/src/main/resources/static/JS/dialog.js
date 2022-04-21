$(document).on('click', "#ClientType_select",
function()
{
	console.log($("#ClientType_select")[0].value);
	if($("#ClientType_select")[0].value == "Legal")
	{
		$("#FIO_label").addClass("Hidden");
		$("#FIO_input").addClass("Hidden");
		$("#Passport_label").addClass("Hidden");
		$("#Passport_input").addClass("Hidden");

		$("#OrgForm_label").removeClass("Hidden");
		$("#OrgName_label").removeClass("Hidden");
		$("#OrgForm_input").removeClass("Hidden");
		$("#OrgName_input").removeClass("Hidden");
	}
	else
	{
		$("#FIO_label").removeClass("Hidden");
		$("#FIO_input").removeClass("Hidden");
		$("#Passport_label").removeClass("Hidden");
		$("#Passport_input").removeClass("Hidden");

		$("#OrgForm_label").addClass("Hidden");
		$("#OrgName_label").addClass("Hidden");
		$("#OrgForm_input").addClass("Hidden");
		$("#OrgName_input").addClass("Hidden");
	}
})

$(document).on('click', "#AccountSearch_input",
function()
{
	if($(this).prop("checked"))
	{
		$("#Account_input").prop("disabled", false);

		$("#Debit_input").prop("disabled", true);
		$("#Credit_input").prop("disabled", true);
		$("#Time_low_input").prop("disabled", true);
		$("#Time_high_input").prop("disabled", true);
		$("#Sum_low_input").prop("disabled", true);
		$("#Sum_high_input").prop("disabled", true);
	}
	else
	{
		$("#Account_input").prop("disabled", true);

		$("#Debit_input").prop("disabled", false);
		$("#Credit_input").prop("disabled", false);
		$("#Time_low_input").prop("disabled", false);
		$("#Time_high_input").prop("disabled", false);
		$("#Sum_low_input").prop("disabled", false);
		$("#Sum_high_input").prop("disabled", false);
	}
})