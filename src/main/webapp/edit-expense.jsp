<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.saikishore.expenseanalyzer.model.Expense" %>

<%
Expense expense = (Expense) request.getAttribute("expense");
%>

<!DOCTYPE html>
<html>
<head>

    <title>Edit Expense</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>
       <header>

              <h1>Smart Expense Analyzer</h1>
              
              </header>
              
              <nav>
              
              <a href="dashboard.jsp">Dashboard</a>
              
              <a href="index.jsp">Add Expense</a>
              
              <a href="viewExpenses">Expenses</a>
              
              <a href="#">Budget</a>
              
              <a href="#">Reports</a>
              
              </nav>
              
              <div class="container">
<h2>Edit Expense</h2>

<form action="updateExpense" method="post">

    <input type="hidden"
           name="id"
           value="<%=expense.getId()%>">

    Title

    <br>

    <input type="text"
           name="title"
           value="<%=expense.getTitle()%>"
           required>

    <br><br>

    Amount

    <br>

    <input type="number"
           step="0.01"
           name="amount"
           value="<%=expense.getAmount()%>"
           required>

    <br><br>

    Category

    <br>

    <input type="text"
           name="category"
           value="<%=expense.getCategory()%>"
           required>

    <br><br>

    Date

    <br>

    <input type="date"
           name="expenseDate"
           value="<%=expense.getExpenseDate()%>"
           required>

    <br><br>

    Notes

    <br>

    <textarea
        name="notes"><%=expense.getNotes()%></textarea>

    <br><br>

    <button type="submit">

        Update Expense

    </button>

</form>
</div>
</body>

</html>