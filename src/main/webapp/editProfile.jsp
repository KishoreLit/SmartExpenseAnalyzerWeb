<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.saikishore.expenseanalyzer.model.User"%>

<%
User user = (User) request.getAttribute("user");
String error = request.getParameter("error");
%>

<%@ include file="WEB-INF/header.jsp"%>

<div class="page-header">

    <h2>✏ Edit Profile</h2>

    <p class="page-subtitle">
        Update your account information.
    </p>

</div>

<div class="profile-card">

    <!-- Error Messages -->
    <% if("name".equals(error)){ %>

        <div class="error-message">
            Full name must contain at least 3 characters.
        </div>

    <% } %>

    <% if("invalidName".equals(error)){ %>

        <div class="error-message">
            Full name can contain only letters and spaces.
        </div>

    <% } %>

    <% if("email".equals(error)){ %>

        <div class="error-message">
            Email is required.
        </div>

    <% } %>

    <% if("emailExists".equals(error)){ %>

        <div class="error-message">
            This email is already registered with another account.
        </div>

    <% } %>

    <% if("updateFailed".equals(error)){ %>

        <div class="error-message">
            Unable to update your profile. Please try again.
        </div>

    <% } %>

    <form
            action="${pageContext.request.contextPath}/edit-profile"
            method="post"
            onsubmit="return validateEditProfileForm()">

        <div class="form-group">

            <label>Full Name</label>

            <input
                    type="text"
                    id="fullName"
                    name="fullName"
                    value="<%= user.getFullName() %>"
                    placeholder="Enter your full name"
                    required
                    minlength="3"
                    maxlength="50"
                    pattern="[A-Za-z ]+"
                    title="Only letters and spaces are allowed.">

        </div>

        <div class="form-group">

            <label>Email</label>

            <input
                    type="email"
                    id="email"
                    name="email"
                    value="<%= user.getEmail() %>"
                    placeholder="Enter your email"
                    required
                    maxlength="100">

        </div>

        <div class="expense-buttons">

            <button
                    type="submit"
                    class="btn-primary"
                    id="saveProfileBtn">

                💾 Save Changes

            </button>

            <a href="${pageContext.request.contextPath}/profile"
               class="btn-secondary">

                ← Back

            </a>

        </div>

    </form>

</div>

<script>

function validateEditProfileForm(){

    const fullName = document.getElementById("fullName").value.trim();
    const email = document.getElementById("email").value.trim();

    const nameRegex = /^[A-Za-z ]+$/;

    if(fullName.length < 3){

        alert("Full name must contain at least 3 characters.");
        return false;

    }

    if(!nameRegex.test(fullName)){

        alert("Full name can contain only letters and spaces.");
        return false;

    }

    if(email === ""){

        alert("Please enter your email.");
        return false;

    }

    const btn = document.getElementById("saveProfileBtn");

    btn.disabled = true;
    btn.innerHTML = "Saving...";

    return true;
}

</script>

</body>
</html>