<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.saikishore.expenseanalyzer.model.Expense" %>

<!DOCTYPE html>
<%@ include file="WEB-INF/header.jsp" %>

<div class="container">

    <!-- Search Section -->
    <div class="search-card">

        <h2>🔍 Search Expenses</h2>

        <form action="${pageContext.request.contextPath}/viewExpenses"
              method="get"
              class="search-form">

            <input type="text"
                   name="title"
                   placeholder="Search by Title"
                   value="${param.title}">

            <select name="category">

                <option value=""
                    ${empty param.category ? "selected" : ""}>
                    All Categories
                </option>

                <option value="Food"
                    ${param.category == 'Food' ? 'selected' : ''}>
                    Food
                </option>

                <option value="Travel"
                    ${param.category == 'Travel' ? 'selected' : ''}>
                    Travel
                </option>

                <option value="Shopping"
                    ${param.category == 'Shopping' ? 'selected' : ''}>
                    Shopping
                </option>

                <option value="Bills"
                    ${param.category == 'Bills' ? 'selected' : ''}>
                    Bills
                </option>

                <option value="Entertainment"
                    ${param.category == 'Entertainment' ? 'selected' : ''}>
                    Entertainment
                </option>

                <option value="Health"
                    ${param.category == 'Health' ? 'selected' : ''}>
                    Health
                </option>

                <option value="Other"
                    ${param.category == 'Other' ? 'selected' : ''}>
                    Other
                </option>

            </select>

            <input type="date"
                   name="fromDate"
                   value="${param.fromDate}">

            <input type="date"
                   name="toDate"
                   value="${param.toDate}">

            <button class="btn" type="submit">
                🔍 Search
            </button>

            <button type="button"
        class="btn"
        onclick="window.location.href='${pageContext.request.contextPath}/viewExpenses'">
    Reset
</button>

        </form>

    </div>

    <h2>Expense List</h2>

    <%
        List<Expense> expenses =
                (List<Expense>) request.getAttribute("expenses");
    %>

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
            if (expenses != null && !expenses.isEmpty()) {

                for (Expense expense : expenses) {
        %>

        <tr>

            <td><%= expense.getId() %></td>

            <td><%= expense.getTitle() %></td>

            <td>₹ <%= String.format("%,.2f", expense.getAmount()) %></td>

            <td><%= expense.getCategory() %></td>

            <td><%= expense.getExpenseDate() %></td>

            <td><%= expense.getNotes() %></td>

            <td>

                <a class="btn"
                   href="updateExpense?id=<%= expense.getId() %>">
                    Edit
                </a>

                <a class="btn delete-btn"
                   href="deleteExpense?id=<%= expense.getId() %>"
                   onclick="return confirm('Are you sure you want to delete this expense?');">
                    Delete
                </a>

            </td>

        </tr>

        <%
                }

            } else {
        %>

        <tr>

            <td colspan="7"
                style="text-align:center;padding:25px;font-weight:bold;color:#777;">

                🔍 No expenses found.

            </td>

        </tr>

        <%
            }
        %>

    </table>

</div>

<%@ include file="WEB-INF/footer.jsp" %>