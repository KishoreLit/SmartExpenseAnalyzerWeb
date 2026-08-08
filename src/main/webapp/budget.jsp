<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="WEB-INF/header.jsp" %>

<%
String error = request.getParameter("error");
%>

<div class="page-header">

    <h2>💰 Monthly Budget</h2>

    <p class="page-subtitle">
        Set your monthly budget to track your expenses.
    </p>

</div>

<div class="profile-card">

    <% if("amount".equals(error)){ %>

        <div class="error-message">
            Please enter a valid budget amount.
        </div>

    <% } %>

    <% if("negative".equals(error)){ %>

        <div class="error-message">
            Budget amount must be greater than 0.
        </div>

    <% } %>

    <form
            action="${pageContext.request.contextPath}/budget"
            method="post"
            onsubmit="return validateBudgetForm()">

        <div class="form-group">

            <label>Monthly Budget (₹)</label>

            <input
                    type="number"
                    id="monthlyBudget"
                    name="monthlyBudget"
                    placeholder="Enter your monthly budget"
                    step="0.01"
                    min="0.01"
                    required>

        </div>

        <div class="form-buttons">

            <button
                type="submit"
                id="saveBudgetBtn"
                class="btn-primary">
        
                💾 Save Budget
        
            </button>
        
            <a
                id="backBudgetBtn"
                href="${pageContext.request.contextPath}/dashboard"
                class="btn-secondary">
        
                ← Back
        
            </a>
        
        </div>

    </form>

</div>

<script>

function validateBudgetForm(){

    const budget =
        document.getElementById("monthlyBudget").value.trim();

    if(budget === ""){

        alert("Please enter your monthly budget.");
        return false;

    }

    if(parseFloat(budget) <= 0){

        alert("Budget amount must be greater than 0.");
        return false;

    }

    const btn = document.getElementById("budgetBtn");

    btn.disabled = true;
    btn.innerHTML = "Saving...";

    return true;

}

</script>

</body>
</html>