<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.saikishore.expenseanalyzer.model.User" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.saikishore.expenseanalyzer.util.AvatarUtil" %>

<%
String success = request.getParameter("success");
User user = (User) request.getAttribute("user");

Double monthlyBudget = (Double) request.getAttribute("monthlyBudget");
Double totalExpense = (Double) request.getAttribute("totalExpense");
Double remainingBudget = (Double) request.getAttribute("remainingBudget");
Integer totalTransactions = (Integer) request.getAttribute("totalTransactions");

if(monthlyBudget == null) monthlyBudget = 0.0;
if(totalExpense == null) totalExpense = 0.0;
if(remainingBudget == null) remainingBudget = 0.0;
if(totalTransactions == null) totalTransactions = 0;

SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
%>

<%@ include file="WEB-INF/header.jsp" %>


<% if("updated".equals(success)){ %>

<div class="success-message">

    ✅ Profile updated successfully!

</div>

<% } %>
<% if("passwordChanged".equals(success)){ %>

    <div class="success-message">
    
    🔒 Password changed successfully!
    
    </div>
    
    <% } %>




<div class="profile-card">

    <%
String[] names = user.getFullName().trim().split("\\s+");

String initials = "";

if(names.length >= 2){

    initials = names[0].substring(0,1)
            + names[1].substring(0,1);

}else{

    initials = names[0].substring(0,1);

}

%>
<%
String avatarClass = AvatarUtil.getAvatarClass(user.getFullName());
%>

<div class="profile-avatar <%= avatarClass %>">

    <%= initials.toUpperCase() %>

</div>

    <h2><%= user.getFullName() %></h2>

    <p class="profile-email">
        <%= user.getEmail() %>
    </p>
    <div class="welcome-message">

        <h3>
            Welcome back,
            <%= user.getFullName().split(" ")[0] %> 👋
        </h3>
    
        <p>
            Manage your account information and monitor your financial summary.
        </p>
    
    </div>
    <div class="status-badge">

        🟢 Active Member
    
    </div>

    <hr>

<h3 class="section-title">
    📋 Account Information
</h3>

<div class="account-info">

    <div class="info-row">

        <span class="label">
            👤 Full Name
        </span>

        <span class="value">
            <%= user.getFullName() %>
        </span>

    </div>

    <div class="info-row">

        <span class="label">
            📧 Email Address
        </span>

        <span class="value">
            <%= user.getEmail() %>
        </span>

    </div>

    <div class="info-row">

        <span class="label">
            📅 Member Since
        </span>

        <span class="value">

            <%= user.getCreatedAt() != null
                    ? sdf.format(user.getCreatedAt())
                    : "N/A" %>

        </span>

    </div>

</div>

<h3 class="section-title">
    📊 Financial Summary
</h3>

<div class="profile-grid">
        <div class="profile-item">

            <h4>

                <span class="stat-icon budget-icon">💰</span>
                
                Monthly Budget
                
                </h4>

            <p>
                ₹ <%= String.format("%,.2f", monthlyBudget) %>
            </p>

        </div>

        <div class="profile-item">

            <h4>

                <span class="stat-icon expense-icon">💸</span>
                
                Total Expense
                
                </h4>

            <p>
                ₹ <%= String.format("%,.2f", totalExpense) %>
            </p>

        </div>

        <div class="profile-item">

            <h4>

                <span class="stat-icon remaining-icon">💵</span>
                
                Remaining Budget
                
                </h4>
            <p class="<%= remainingBudget >= 0 ? "positive-budget" : "negative-budget" %>">

                ₹ <%= String.format("%,.2f", remainingBudget) %>

            </p>

        </div>

        <div class="profile-item">

            <h4>

                <span class="stat-icon transaction-icon">📋</span>
                
                Transactions
                
                </h4>
            <p>
                <%= totalTransactions %>
            </p>

        </div>

    </div>

    <div class="profile-actions">

        <a href="${pageContext.request.contextPath}/edit-profile-page"
           class="btn">
            ✏ Edit Profile
        </a>

        <a href="${pageContext.request.contextPath}/change-password-page"
           class="btn">
            🔒 Change Password
        </a>

    </div>

</div>

<%@ include file="WEB-INF/footer.jsp" %>