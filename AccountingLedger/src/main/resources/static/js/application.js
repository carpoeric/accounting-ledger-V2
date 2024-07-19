function loadPage(page) {
    return fetch(page)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.text();
        })
        .then(html => {
            document.getElementById('main').innerHTML = html;
        })
        .catch(error => console.error('Error loading page:', error));
}

function loadAddDepositPage() {
    loadPage('deposit.html');
}

function loadMakePaymentPage() {
    loadPage('payment.html');
}

function addDeposit() {
    const amount = document.getElementById("depositAmount").value;
    const account = document.getElementById("account").value;

    if (amount && account) {
        transactionService.deposit(account, amount)
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
        transactionService.makePayment(account, payee, amount)
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
    transactionService.s;
    if (categoryService) {
        categoryService.getAllCategories(loadCategories);
    } else {
        console.error("categoryService is not defined.");
    }
}

function editProfile() {
    profileService.loadProfile();
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
    const value = control.value;
    switch (value) {
        case '0':
            transactionService.search();
            break;
        case '1':
            transactionService.searchMonthToDate();
            break;
        case '2':
            transactionService.searchPreviousMonths();
            break;
        case '3':
            transactionService.searchYearToDate();
            break;
        case '4':
            const year = prompt("Enter the year:");
            if (year) {
                transactionService.searchByYear(year);
            }
            break;
        default:
            transactionService.search();
            break;
    }
}

function closeError(control) {
    setTimeout(() => {
        control.click();
    }, 3000);
}

function loadCategories(categories) {
    const categoryContainer = document.getElementById('category-container');
    if (!categoryContainer) {
        console.error("Category container element not found.");
        return;
    }

    // Clear existing categories
    categoryContainer.innerHTML = '';

    // Create and append new category elements
    categories.forEach(category => {
        const categoryElement = document.createElement('div');
        categoryElement.className = 'category';
        categoryElement.textContent = category.name; // Assuming category object has a 'name' property
        categoryContainer.appendChild(categoryElement);
    });
}

document.addEventListener('DOMContentLoaded', () => {
    categoryService = new CategoryService();
    transactionService = new TransactionsService();
    loadHome();
});
