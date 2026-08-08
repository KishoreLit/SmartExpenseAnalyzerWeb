<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Map" %>

<%
Map<String, Double> categorySummary =
        (Map<String, Double>) request.getAttribute("categorySummary");
%>

<!DOCTYPE html>
<%@ include file="WEB-INF/header.jsp" %>

<div class="page-header">

    <h2>📊 Expense Reports</h2>

    <p class="page-subtitle">
        Category-wise summary of your expenses.
    </p>

</div>

<!-- Chart -->
<div class="chart-card">

    <h2>📊 Category-wise Expense Report</h2>

    <div class="chart-container">
        <canvas id="expenseChart"></canvas>
    </div>

</div>

<!-- Table -->
<div class="table-card">

    <h2>📋 Category Summary</h2>

    <table>

        <tr>
            <th>Category</th>
            <th>Total Spent</th>
        </tr>

<%
if(categorySummary != null && !categorySummary.isEmpty()){

    for(Map.Entry<String, Double> entry : categorySummary.entrySet()){
%>

        <tr>

            <td><%= entry.getKey() %></td>

            <td>
                ₹ <%= String.format("%,.2f", entry.getValue()) %>
            </td>

        </tr>

<%
    }

}else{
%>

        <tr>

            <td colspan="2" style="text-align:center;padding:20px;">
                No expense data available.
            </td>

        </tr>

<%
}
%>

    </table>

</div>

<%@ include file="WEB-INF/footer.jsp" %>

<!-- Chart.js -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<script>

const labels = [
<%
if(categorySummary != null){
    for(String key : categorySummary.keySet()){
%>
"<%= key %>",
<%
    }
}
%>
];

const values = [
<%
if(categorySummary != null){
    for(Double value : categorySummary.values()){
%>
<%= value %>,
<%
    }
}
%>
];

const colors = [
    "#2563eb",
    "#22c55e",
    "#f59e0b",
    "#ef4444",
    "#8b5cf6",
    "#06b6d4",
    "#ec4899"
];

new Chart(document.getElementById("expenseChart"), {

    type: "bar",

    data: {

        labels: labels,

        datasets: [{

            data: values,

            backgroundColor: colors,

            borderRadius: 8,

            borderWidth: 0,

            barThickness: 28

        }]

    },

    options: {

        responsive: true,

        maintainAspectRatio: false,

        indexAxis: "y",

        scales: {

            x: {

                beginAtZero: true,

                ticks: {

                    callback: function(value){
                        return "₹ " + value;
                    }

                },

                grid: {
                    color: "#d1d5db"
                }

            },

            y: {

                grid: {
                    display: false
                },

                ticks: {

                    font: {
                        size: 14,
                        weight: "bold"
                    }

                }

            }

        },

        plugins: {

            legend: {

                display: false

            },

            tooltip: {

                callbacks: {

                    label: function(context){
                        return " ₹ " + context.raw.toFixed(2);
                    }

                }

            }

        }

    }

});

</script>