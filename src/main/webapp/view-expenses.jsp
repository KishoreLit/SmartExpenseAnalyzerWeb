<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.saikishore.expenseanalyzer.model.Expense" %>

<!DOCTYPE html>
<html>
<head>
    <title>View Expenses</title>

    <link rel="stylesheet" href="css/style.css">

</head>

<body>
    <header>

        <h1>Smart Expense Analyzer</h1>
        
        </header>
        
        <nav>
        
        <a href="dashboard.jsp">Dashboard</a>
        
        <a href="index.jsp">Add Expense</a>
        
        <a href="viewExpenses" class="active">Expenses</a>
        
        <a href="#">Budget</a>
        
        <a href="#">Reports</a>
        
        </nav>
        
        <div class="container">

<h2>Expense List</h2>

<table>

<tr>

    <th>ID</th>
    <th>Title</th>
    <th>Amount</th>
    <th>Category</th>
    <th>Date</th>
    <th>Notes</th>
    <th>Action</th>
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
<td>
    <a class="btn" href="updateExpense?id=<%=expense.getId()%>">
        Edit
    </a>

    <a class="btn delete-btn"
       href="deleteExpense?id=<%=expense.getId()%>"
       onclick="return confirm('Are you sure you want to delete this expense?');">
        Delete
    </a>
</td>
</tr>

<%

}

}

%>

</table>
</div>
</body>

</html>