<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.saikishore.expenseanalyzer.model.Expense" %>

<!DOCTYPE html>
<html>
<head>
    <title>View Expenses</title>

    <style>

        body{
            font-family: Arial, sans-serif;
            margin:40px;
            background:#f5f5f5;
        }

        h2{
            text-align:center;
        }

        table{
            width:100%;
            border-collapse:collapse;
            background:white;
        }

        th,td{
            border:1px solid #ddd;
            padding:12px;
            text-align:center;
        }

        th{
            background:#1976D2;
            color:white;
        }

        tr:nth-child(even){
            background:#f2f2f2;
        }

    </style>

</head>

<body>

<h2>Expense List</h2>

<table>

<tr>

    <th>ID</th>
    <th>Title</th>
    <th>Amount</th>
    <th>Category</th>
    <th>Date</th>
    <th>Notes</th>

</tr>

<%

List<Expense> expenses =
(List<Expense>) request.getAttribute("expenses");

if(expenses!=null){

for(Expense expense:expenses){

%>

<tr>

<td><%=expense.getId()%></td>

<td><%=expense.getTitle()%></td>

<td><%=expense.getAmount()%></td>

<td><%=expense.getCategory()%></td>

<td><%=expense.getExpenseDate()%></td>

<td><%=expense.getNotes()%></td>

</tr>

<%

}

}

%>

</table>

</body>

</html>