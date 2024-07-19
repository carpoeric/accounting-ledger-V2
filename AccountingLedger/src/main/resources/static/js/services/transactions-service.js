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
    }

    clearCategoryFilter() {
        this.filter.cat = undefined;
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
}

document.addEventListener('DOMContentLoaded', () => {
    transactionService = new TransactionsService();
});
