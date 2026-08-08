<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.saikishore.expenseanalyzer.model.Expense" %>

<%
Double totalExpense = (Double) request.getAttribute("totalExpense");
Integer totalTransactions = (Integer) request.getAttribute("totalTransactions");
Double highestExpense = (Double) request.getAttribute("highestExpense");
String latestExpense = (String) request.getAttribute("latestExpense");
Double monthlyBudget = (Double) request.getAttribute("monthlyBudget");
Double remainingBudget = (Double) request.getAttribute("remainingBudget");
Double budgetPercentage =
(Double) request.getAttribute("budgetPercentage");

if (budgetPercentage == null)
    budgetPercentage = 0.0;
List<Expense> recentExpenses =
    (List<Expense>) request.getAttribute("recentExpenses");

if (totalExpense == null) totalExpense = 0.0;
if (totalTransactions == null) totalTransactions = 0;
if (highestExpense == null) highestExpense = 0.0;
if (latestExpense == null) latestExpense = "No Expenses";
if (monthlyBudget == null) monthlyBudget = 0.0;
if (remainingBudget == null) remainingBudget = 0.0;
%>

<!DOCTYPE html>
<%@ include file="WEB-INF/header.jsp" %>
<%
String success = request.getParameter("success");
%>

<% if("budgetSaved".equals(success)){ %>

<div class="success-message">

    🎉 Monthly budget has been set successfully!

</div>

<% } %>

<div class="page-header">

    <h2>📊 Dashboard Overview</h2>

    <p class="page-subtitle">

        Welcome back!

        Here's a quick overview of your spending.

    </p>

</div>

<div class="dashboard-wrapper">

<div class="card-container">
    <div class="card">

        <h3>💰 Total Expense</h3>

<hr class="card-divider">
<p>₹ <%= String.format("%,.2f", totalExpense) %></p>

<span>Total amount spent so far</span>

    </div>
    <div class="card">

        <h3>📋 Total Transactions</h3>
        <hr class="card-divider">
    
        <p><%= totalTransactions %></p>
    
        <span>Total number of expenses</span>
    
    </div>
    <div class="card">

        <h3>🔥 Highest Expense</h3>
        <hr class="card-divider">
    
        <p>₹ <%= String.format("%,.2f", highestExpense) %></p>
    
        <span>Highest single expense</span>
    
    </div>
    <div class="card">

        <h3>🕒 Latest Expense</h3>
        <hr class="card-divider">
    
        <p><%= latestExpense %></p>
    
        <span>Most recently added expense</span>
    
    </div>
    <div class="card">

        <h3>💰 Monthly Budget</h3>
        <hr class="card-divider">
    
        <p>₹ <%= String.format("%,.2f", monthlyBudget) %></p>
    
        <span>Your monthly spending limit</span>
    
    </div>
    
    <div class="card">
    
        <h3>💵 Remaining Budget</h3>
        <hr class="card-divider">
    
        <p>₹ <%= String.format("%,.2f", remainingBudget) %></p>
    
        <span>Budget left after expenses</span>
    
    </div>

</div>

</div>

<div class="quick-action-card">

    <h2>Quick Actions</h2>

    <a class="btn"
       href="${pageContext.request.contextPath}/addExpense">
       ➕ Add Expense
    </a>

    <a class="btn"
       href="${pageContext.request.contextPath}/viewExpenses">
       📋 View Expenses
    </a>

</div>
<div class="progress-card">

    <h2>📊 Budget Usage</h2>

    <div class="progress-bar">

        <div class="progress-fill
            <%= budgetPercentage < 70 ? "green"
                : budgetPercentage < 90 ? "orange"
                : "red" %>"
             style="width:<%= budgetPercentage %>%;">
        </div>
        <p class="budget-status">

            <%
            if (budgetPercentage < 70) {
            %>
            
            🟢 You're well within your budget.
            
            <%
            } else if (budgetPercentage < 90) {
            %>
            
            🟡 You're approaching your budget limit.
            
            <%
            } else {
            %>
            
            🔴 Warning! Budget almost exhausted.
            
            <%
            }
            %>
            
            </p>

    </div>

    <p class="progress-text">
        <strong><%= String.format("%.1f", budgetPercentage) %>% Used</strong>
    </p>

    <div class="budget-summary">

        <div>
            <strong>💰Budget</strong><br>
            ₹ <%= String.format("%,.2f", monthlyBudget) %>
        </div>

        <div>
            <strong>💸 Spent</strong><br>
            ₹ <%= String.format("%,.2f", totalExpense) %>
        </div>

        <div>
            <strong>💵 Remaining</strong><br>
        
            <span class="<%= remainingBudget >= 0 ? "positive-budget" : "negative-budget" %>">
        </div>

    </div>

</div>
<div class="table-card">

    <h2>📋 Recent Expenses</h2>

    <table>

    <tr>
        <th>Title</th>
        <th>Amount</th>
        <th>Category</th>
        <th>Date</th>
    </tr>

<%
if(recentExpenses != null){

    for(Expense expense : recentExpenses){
%>

<tr>

    <td><%= expense.getTitle() %></td>

    <td>₹ <%= String.format("%,.2f", expense.getAmount()) %></td>

    <td><%= expense.getCategory() %></td>

    <td><%= expense.getExpenseDate() %></td>

</tr>

<%
    }
}
%>

</table>
</div>
<br>
</div>

<%@ include file="WEB-INF/footer.jsp" %>