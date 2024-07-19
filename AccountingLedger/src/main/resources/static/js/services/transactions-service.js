let transactionService;
class TransactionsService {
    filter = {
        cat: undefined,
        queryString: () => {
            let qs = "";
            if (this.filter.cat) { qs = `cat=${this.filter.cat}`; }
            return qs.length > 0 ? `?${qs}` : "";
        }
    }

    addCategoryFilter(cat) {
        if (cat == 0) this.clearCategoryFilter();
        else this.filter.cat = cat;
        this.searchAndPopulateTable(); // Fetch and populate table with filtered data
    }

    clearCategoryFilter() {
        this.filter.cat = undefined;
        this.searchAndPopulateTable(); // Fetch and populate table with all data
    }

    search() {
        const url = `${config.baseUrl}/transactions${this.filter.queryString()}`;
        return axios.get(url)
            .then(response => response.data)
            .catch(error => {
                const data = { error: "Searching transactions failed." };
                templateBuilder.append("error", data, "errors");
            });
    }

    searchMonthToDate() {
        const url = `${config.baseUrl}/reports/monthToDate`;
        return axios.get(url)
            .then(response => response.data)
            .catch(error => {
                const data = { error: "Searching month-to-date transactions failed." };
                templateBuilder.append("error", data, "errors");
            });
    }

    searchPreviousMonths() {
        const url = `${config.baseUrl}/reports/previousMonth`;
        return axios.get(url)
            .then(response => response.data)
            .catch(error => {
                const data = { error: "Searching previous months' transactions failed." };
                templateBuilder.append("error", data, "errors");
            });
    }

    searchYearToDate() {
        const url = `${config.baseUrl}/reports/yearToDate`;
        return axios.get(url)
            .then(response => response.data)
            .catch(error => {
                const data = { error: "Searching year-to-date transactions failed." };
                templateBuilder.append("error", data, "errors");
            });
    }

    searchByYear(year) {
        const url = `${config.baseUrl}/reports/${year}`;
        return axios.get(url)
            .then(response => response.data)
            .catch(error => {
                const data = { error: `Searching transactions for year ${year} failed.` };
                templateBuilder.append("error", data, "errors");
            });
    }

    searchAndPopulateTable() {
        this.search()
            .then(data => this.populateTable(data))
            .catch(error => {
                console.error("Error fetching data: ", error);
            });
    }

    populateTable(transactions) {
        const tableBody = document.querySelector("#transactionsTable tbody");
        tableBody.innerHTML = ""; // Clear existing table data

        transactions.forEach(transaction => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${transaction.id}</td>
                <td>${transaction.date}</td>
                <td>${transaction.category}</td>
                <td>${transaction.amount}</td>
            `;
            tableBody.appendChild(row);
        });
    }
}

document.addEventListener('DOMContentLoaded', () => {
    const transactionService = new TransactionsService();

    // Event listeners for filter options
    document.querySelector("#filterOption1").addEventListener("click", () => {
        transactionService.addCategoryFilter(1);
    });

    document.querySelector("#filterOption2").addEventListener("click", () => {
        transactionService.addCategoryFilter(2);
    });

    document.querySelector("#clearFilter").addEventListener("click", () => {
        transactionService.clearCategoryFilter();
    });

    // Initial population of the table
    transactionService.searchAndPopulateTable();
});
