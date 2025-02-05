🚀 Simple Banking REST API

    A lightweight and efficient Banking System API built with Java Spring Boot and MySQL. This API lets users create accounts, manage funds, and check balances seamlessly.

✨ Features

    ✔️ Create an Account – Open a new bank account
    ✔️ Delete an Account – Close an account permanently
    ✔️ Update Account Details – Modify account information
    ✔️ Deposit Funds – Add money to an account
    ✔️ Withdraw Funds – Take out money from an account
    ✔️ Check Balance – Get the current balance of an account

📌 API Endpoints

    POST	/api/accounts	Create a new account
    DELETE	/api/accounts/{id}	Delete an account by ID
    PUT	/api/accounts/{id}	Update account details
    POST	/api/accounts/{id}/deposit	Deposit funds
    POST	/api/accounts/{id}/withdraw	Withdraw funds
    GET	/api/accounts/{id}/balance	Check account balance

🛠️ Technologies Used

    🔹 Java 17+
    🔹 Spring Boot (Spring Web, Spring Data JPA)
    🔹 MySQL (Relational Database)
    🔹 Lombok (To reduce boilerplate code)
