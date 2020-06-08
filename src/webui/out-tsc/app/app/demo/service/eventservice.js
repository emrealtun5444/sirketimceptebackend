import { __decorate } from "tslib";
import { Injectable } from '@angular/core';
let EventService = class EventService {
    constructor(http) {
        this.http = http;
    }
    getEvents() {
        return this.http.get('assets/demo/data/scheduleevents.json')
            .toPromise()
            .then(res => res.data)
            .then(data => data);
    }
};
EventService = __decorate([
    Injectable()
], EventService);
export { EventService };
//# sourceMappingURL=eventservice.js.map