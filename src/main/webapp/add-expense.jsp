<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
String error = request.getParameter("error");
%>

<%@ include file="WEB-INF/header.jsp" %>

<div class="form-wrapper">

    <h2>Add New Expense</h2>

    <p class="dashboard-text">
        Record your daily expenses and keep your finances organized.
    </p>

    <div class="form-card">

        <!-- Error Messages -->

        <% if("title".equals(error)){ %>

        <div class="error-message">
            Expense title must contain at least 3 characters.
        </div>

        <% } %>

        <% if("amount".equals(error)){ %>

        <div class="error-message">
            Please enter a valid amount.
        </div>

        <% } %>

        <% if("negative".equals(error)){ %>

        <div class="error-message">
            Amount must be greater than 0.
        </div>

        <% } %>

        <% if("category".equals(error)){ %>

        <div class="error-message">
            Please select a category.
        </div>

        <% } %>

        <% if("date".equals(error)){ %>

        <div class="error-message">
            Please select a date.
        </div>

        <% } %>

        <% if("saveFailed".equals(error)){ %>

        <div class="error-message">
            Unable to save expense. Please try again.
        </div>

        <% } %>

        <form
                action="${pageContext.request.contextPath}/add-expense"
                method="post"
                onsubmit="return validateExpenseForm()">

            <div class="form-group">

                <label for="title">Expense Title</label>

                <input
                        type="text"
                        id="title"
                        name="title"
                        placeholder="Enter expense title"
                        required
                        minlength="3"
                        maxlength="100">

            </div>

            <div class="form-group">

                <label>Amount</label>

                <input
                        type="number"
                        id="amount"
                        name="amount"
                        placeholder="Enter amount"
                        step="0.01"
                        min="0.01"
                        required>

            </div>

            <div class="form-group">

                <label for="category">Category</label>

                <select
                        id="category"
                        name="category"
                        required>

                    <option value="">-- Select Category --</option>
                    <option>Food</option>
                    <option>Travel</option>
                    <option>Shopping</option>
                    <option>Bills</option>
                    <option>Health</option>
                    <option>Education</option>
                    <option>Entertainment</option>
                    <option>Other</option>

                </select>

            </div>

            <div class="form-group">

                <label for="date">Date</label>

                <input
                        type="date"
                        id="date"
                        name="date"
                        required>

            </div>

            <div class="form-group">

                <label for="notes">Notes</label>

                <textarea
                        id="notes"
                        name="notes"
                        placeholder="Additional notes (optional)"
                        maxlength="250"></textarea>

            </div>

            <div class="expense-buttons">
                <button
                    type="submit"
                    class="btn-primary"
                    id="expenseBtn">
            
                    ➕ Add Expense
            
                </button>
            
                <a href="${pageContext.request.contextPath}/dashboard"
                   class="btn-secondary"
                   id="dashboardBtn">
            
                    ← Dashboard
            
                </a>
            
            </div>
            

        </form>

    </div>

</div>

<script>

function validateExpenseForm(){

    const title = document.getElementById("title").value.trim();

    const amount = parseFloat(document.getElementById("amount").value);

    const category = document.getElementById("category").value;

    const date = document.getElementById("date").value;

    if(title.length < 3){

        alert("Expense title must contain at least 3 characters.");
        return false;

    }

    if(isNaN(amount) || amount <= 0){

        alert("Amount must be greater than 0.");
        return false;

    }

    if(category === ""){

        alert("Please select a category.");
        return false;

    }

    if(date === ""){

        alert("Please select a date.");
        return false;

    }

    const btn = document.getElementById("expenseBtn");

    btn.disabled = true;
    btn.innerHTML = "Adding...";

    return true;
}

</script>

</body>
</html>