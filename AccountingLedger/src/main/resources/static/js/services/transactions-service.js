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
    }

    clearCategoryFilter() {
        this.filter.cat = undefined;
    }

    search() {
        const url = `${config.baseUrl}/api/transactions${this.filter.queryString()}`;
        axios.get(url)
            .then(response => {
                let data = {};
                data.transactions = response.data;
                templateBuilder.build('product', data, 'content', this.enableButtons);
            })
            .catch(error => {
                const data = { error: "Searching transactions failed." };
                templateBuilder.append("error", data, "errors");
            });
    }

    searchMonthToDate() {
        const url = `${config.baseUrl}/api/reports/monthToDate`;
        axios.get(url)
            .then(response => {
                let data = {};
                data.transactions = response.data;
                templateBuilder.build('product', data, 'content', this.enableButtons);
            })
            .catch(error => {
                const data = { error: "Searching month-to-date transactions failed." };
                templateBuilder.append("error", data, "errors");
            });
    }

    searchPreviousMonths() {
        const url = `${config.baseUrl}/api/reports/previousMonths`;
        axios.get(url)
            .then(response => {
                let data = {};
                data.transactions = response.data;
                templateBuilder.build('product', data, 'content', this.enableButtons);
            })
            .catch(error => {
                const data = { error: "Searching previous months' transactions failed." };
                templateBuilder.append("error", data, "errors");
            });
    }

    searchYearToDate() {
        const url = `${config.baseUrl}/api/reports/yearToDate`;
        axios.get(url)
            .then(response => {
                let data = {};
                data.transactions = response.data;
                templateBuilder.build('product', data, 'content', this.enableButtons);
            })
            .catch(error => {
                const data = { error: "Searching year-to-date transactions failed." };
                templateBuilder.append("error", data, "errors");
            });
    }

    searchByYear(year) {
        const url = `${config.baseUrl}/api/reports/${year}`;
        axios.get(url)
            .then(response => {
                let data = {};
                data.transactions = response.data;
                templateBuilder.build('product', data, 'content', this.enableButtons);
            })
            .catch(error => {
                const data = { error: `Searching transactions for year ${year} failed.` };
                templateBuilder.append("error", data, "errors");
            });
    }
}

document.addEventListener('DOMContentLoaded', () => {
    transactionService = new TransactionsService();
});

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
