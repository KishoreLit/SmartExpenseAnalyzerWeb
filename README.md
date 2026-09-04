<div align="center">

# 💰 Smart Expense Analyzer

### A modern Java web application for smarter personal expense tracking,
### budgeting, and financial analysis.

<br>

<a href="https://smartexpenseanalyzerweb.onrender.com/">
  <img src="https://img.shields.io/badge/🚀%20LIVE%20DEMO-Visit%20Application-2ea44f?style=for-the-badge" alt="Live Demo">
</a>

<a href="https://github.com/KishoreLit/SmartExpenseAnalyzerWeb">
  <img src="https://img.shields.io/badge/💻%20SOURCE%20CODE-GitHub-24292f?style=for-the-badge&logo=github" alt="GitHub">
</a>

<br><br>

<img src="https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=openjdk">
<img src="https://img.shields.io/badge/Jakarta%20Servlet-6.0-blue?style=flat-square">
<img src="https://img.shields.io/badge/JSP-Technology-blueviolet?style=flat-square">
<img src="https://img.shields.io/badge/MySQL-8.4-4479A1?style=flat-square&logo=mysql&logoColor=white">
<img src="https://img.shields.io/badge/Maven-C71A36?style=flat-square&logo=apachemaven&logoColor=white">
<img src="https://img.shields.io/badge/Docker-2496ED?style=flat-square&logo=docker&logoColor=white">
<img src="https://img.shields.io/badge/Render-Deployed-46E3B7?style=flat-square">

</div>

---

## 📌 About The Project

**Smart Expense Analyzer** is a full-stack Java web application designed to help users manage their personal finances from a single platform.

It provides expense management, budgeting, financial reports, profile management, and secure authentication.

The application follows a layered architecture using **Servlets, JSP, DAO, JDBC, and MySQL**, and is deployed to the cloud using **Docker + Render** with **Aiven MySQL** as the production database.

---

## ✨ Key Features

<table>
<tr>
<td width="50%">

### 🔐 Authentication

- User registration
- Secure login
- BCrypt password hashing
- Session-based authentication
- Change password
- Forgot password
- Logout

</td>

<td width="50%">

### 💸 Expense Management

- Add expenses
- View expense history
- Edit expenses
- Delete expenses
- Expense categorization
- Expense date tracking

</td>
</tr>

<tr>
<td>

### 💰 Budget Management

- Set category budgets
- Monthly budget tracking
- Persistent budget storage
- Spending vs budget analysis

</td>

<td>

### 📊 Reports & Analytics

- Expense analysis
- Category-based analysis
- Monthly spending insights
- Financial reports

</td>
</tr>

<tr>
<td>

### 👤 Profile Management

- View profile
- Edit profile information
- Account management
- Password management

</td>

<td>

### ☁️ Cloud Deployment

- Dockerized application
- Render deployment
- Aiven cloud database
- Environment-based configuration

</td>
</tr>
</table>

---



## 🏗️ Architecture

```text
                         👤 USER
                           │
                           ▼
                ┌─────────────────────┐
                │     JSP / HTML      │
                │      CSS / JS       │
                └──────────┬──────────┘
                           │
                           ▼
                ┌─────────────────────┐
                │   SERVLET LAYER     │
                │     Controllers     │
                └──────────┬──────────┘
                           │
                           ▼
                ┌─────────────────────┐
                │      DAO LAYER      │
                │   Data Access       │
                └──────────┬──────────┘
                           │
                           ▼
                ┌─────────────────────┐
                │        JDBC         │
                │ Database Connection │
                └──────────┬──────────┘
                           │
                           ▼
                ┌─────────────────────┐
                │     MYSQL / AIVEN   │
                │   Production DB     │
                └─────────────────────┘
