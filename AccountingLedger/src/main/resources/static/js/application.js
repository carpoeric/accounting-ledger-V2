function loadPage(page) {
    fetch(page)
        .then(response => response.text())
        .then(html => {
            document.getElementById('content').innerHTML = html;
        })
        .catch(error => console.error('Error loading page:', error));
}

function showLoginForm() {
    templateBuilder.build('login-form', {}, 'login');
}

function hideModalForm() {
    templateBuilder.clear('login');
}

function login() {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    userService.login(username, password);
    hideModalForm();
}

function loadHome() {
    templateBuilder.build('home', {}, 'main');
    TransactionsService.search();
    categoryService.getAllCategories(loadCategories);
}

function editProfile() {
    profileService.loadProfile();
}

function addDeposit() {
    const amount = document.getElementById("depositAmount").value;
    const account = document.getElementById("account").value;

    if (amount && account) {
        TransactionsService.deposit(account, amount)
            .then(response => {
                alert("Deposit successful! New balance: " + response.newBalance);
                loadHome();
            })
            .catch(error => {
                console.error("Error making deposit:", error);
                alert("Failed to make deposit.");
            });
    } else {
        alert("Please enter both amount and account.");
    }
}

function makePayment() {
    const amount = document.getElementById("paymentAmount").value;
    const account = document.getElementById("account").value;
    const payee = document.getElementById("payee").value;

    if (amount && account && payee) {
        TransactionsService.makePayment(account, payee, amount)
            .then(response => {
                alert("Payment successful! New balance: " + response.newBalance);
                loadHome();
            })
            .catch(error => {
                console.error("Error making payment:", error);
                alert("Failed to make payment.");
            });
    } else {
        alert("Please enter amount, account, and payee.");
    }
}

function viewLedger() {
    loadPage('ledger.html');
    TransactionsService.getAllTransactions()
        .then(transactions => {
            const ledgerTable = document.getElementById("ledgerTable");
            ledgerTable.innerHTML = ""; // Clear any existing rows

            const headerRow = document.createElement("tr");
            headerRow.innerHTML = `
                    <th>Date</th>
                    <th>Type</th>
                    <th>Amount</th>
                    <th>Account</th>
                    <th>Description</th>
                `;
            ledgerTable.appendChild(headerRow);

            transactions.forEach(transaction => {
                const row = document.createElement("tr");
                row.innerHTML = `
                        <td>${transaction.date}</td>
                        <td>${transaction.type}</td>
                        <td>${transaction.amount}</td>
                        <td>${transaction.account}</td>
                        <td>${transaction.description}</td>
                    `;
                ledgerTable.appendChild(row);
            });
        })
        .catch(error => {
            console.error("Error fetching transactions:", error);
            alert("Failed to load ledger.");
        });
}

function saveProfile() {
    const firstName = document.getElementById("firstName").value;
    const lastName = document.getElementById("lastName").value;
    const phone = document.getElementById("phone").value;
    const email = document.getElementById("email").value;
    const address = document.getElementById("address").value;
    const city = document.getElementById("city").value;
    const state = document.getElementById("state").value;
    const zip = document.getElementById("zip").value;

    const profile = {
        firstName,
        lastName,
        phone,
        email,
        address,
        city,
        state,
        zip
    };

    profileService.updateProfile(profile);
}

function setCategory(control) {
    TransactionsService.addCategoryFilter(control.value);
    TransactionsService.search();
}

function closeError(control) {
    setTimeout(() => {
        control.click();
    }, 3000);
}

document.addEventListener('DOMContentLoaded', () => {
    loadHome();
});