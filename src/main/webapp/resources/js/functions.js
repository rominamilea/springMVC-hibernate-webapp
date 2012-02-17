function fillDateInput(inputDateId) {
    var selectedDate = $("#datePicker").datepicker("getDate");
    $("#" + inputDateId).attr("value", selectedDate);
}

function fillMonthsList(year) {
    /**
     * callMap is a set of key/value pairs that configure the Ajax request.
     */
    var callMap = {
        type:"POST",
        data:$('#dateForm').serialize(),
        url:"months",
        success:function (data) {
            $("#monthsTd").html(data);
        }
    };
    /**
     * Performs an asynchronous HTTP (Ajax) request.
     */
    $.ajax(callMap);
}

function showPayments() {
    var callMap = {
        type:'POST',
        data:$('#dateForm').serialize(),
        url:'getPayments',
        success:function (data) {
            $("#paymentsByMonthDiv").html(data);
        }
    };
    $.ajax(callMap);
    setSelectedDate();
}

function deletePayment(id) {
    var answer = confirm("Do you really want to delete this payment?");
    if (answer) {
        $("#delPaymentId").attr("value", id);
        var callMap = {
            type:"POST",
            data:$('#deletePayment').serialize(),
            url:"delete",
            success:function (data) {
                $("#content").html(data);
            }
        };
        $.ajax(callMap);
    }
}

function setSelectedDate() {
    $(".selectedMonth").html(getSelectedMonth('monthsList'));
    $(".selectedYear").html(getSelectedYear('yearsList'));
}

function getSelectedYear(listId) {
    return $("#" + listId + " :selected").text();
}

function getSelectedMonth(listId) {
    return $("#" + listId + " :selected").text();
}