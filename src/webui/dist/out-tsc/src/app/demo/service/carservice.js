import { __decorate } from "tslib";
import { Injectable } from '@angular/core';
let CarService = class CarService {
    constructor(http) {
        this.http = http;
    }
    getCarsSmall() {
        return this.http.get('assets/demo/data/cars-small.json')
            .toPromise()
            .then(res => res.data)
            .then(data => data);
    }
    getCarsMedium() {
        return this.http.get('assets/demo/data/cars-medium.json')
            .toPromise()
            .then(res => res.data)
            .then(data => data);
    }
    getCarsLarge() {
        return this.http.get('assets/demo/data/cars-large.json')
            .toPromise()
            .then(res => res.data)
            .then(data => data);
    }
};
CarService = __decorate([
    Injectable()
], CarService);
export { CarService };
//# sourceMappingURL=carservice.js.map