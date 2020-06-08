import { __decorate } from "tslib";
import { Injectable } from '@angular/core';
let CountryService = class CountryService {
    constructor(http) {
        this.http = http;
    }
    getCountries() {
        return this.http.get('assets/demo/data/countries.json')
            .toPromise()
            .then(res => res.data)
            .then(data => data);
    }
};
CountryService = __decorate([
    Injectable()
], CountryService);
export { CountryService };
//# sourceMappingURL=countryservice.js.map