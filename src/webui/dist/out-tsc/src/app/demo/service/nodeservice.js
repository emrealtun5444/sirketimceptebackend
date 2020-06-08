import { __decorate } from "tslib";
import { Injectable } from '@angular/core';
let NodeService = class NodeService {
    constructor(http) {
        this.http = http;
    }
    getFiles() {
        return this.http.get('assets/demo/data/files.json')
            .toPromise()
            .then(res => res.data)
            .then(data => data);
    }
    getFilesystem() {
        return this.http.get('assets/demo/data/filesystem.json')
            .toPromise()
            .then(res => res.data)
            .then(data => data);
    }
};
NodeService = __decorate([
    Injectable()
], NodeService);
export { NodeService };
//# sourceMappingURL=nodeservice.js.map