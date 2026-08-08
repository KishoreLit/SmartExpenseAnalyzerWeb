<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">

    <title>Smart Expense Analyzer</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>

<header>

    <h1>Smart Expense Analyzer</h1>

</header>

<nav>

    <a href="${pageContext.request.contextPath}/dashboard"
       class="${activePage == 'dashboard' ? 'active' : ''}">
        🏠 Dashboard
    </a>

    <a href="${pageContext.request.contextPath}/addExpense"
   class="${activePage == 'addExpense' ? 'active' : ''}">
    ➕ Add Expense
</a>

    <a href="${pageContext.request.contextPath}/viewExpenses"
       class="${activePage == 'expenses' ? 'active' : ''}">
        📋 Expenses
    </a>

    <a href="${pageContext.request.contextPath}/budget"
   class="${activePage == 'budget' ? 'active' : ''}">
    💰 Budget
</a>

<a href="${pageContext.request.contextPath}/reports"
class="${activePage == 'reports' ? 'active' : ''}">
 📊 Reports
</a>
<a href="${pageContext.request.contextPath}/profile"
   class="${activePage == 'profile' ? 'active' : ''}">
    👤 Profile
</a>
<a href="${pageContext.request.contextPath}/logout"
   class="logout-btn">
    🚪 Logout
</a>
</nav>
<div class="container">