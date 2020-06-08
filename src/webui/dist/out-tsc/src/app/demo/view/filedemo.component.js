import { __decorate } from "tslib";
import { Component } from '@angular/core';
import { MessageService } from 'primeng/api';
let FileDemoComponent = class FileDemoComponent {
    constructor(messageService) {
        this.messageService = messageService;
        this.uploadedFiles = [];
    }
    onUpload(event) {
        for (const file of event.files) {
            this.uploadedFiles.push(file);
        }
        this.messageService.add({ severity: 'info', summary: 'Success', detail: 'Upload Completed' });
    }
};
FileDemoComponent = __decorate([
    Component({
        templateUrl: './filedemo.component.html',
        providers: [MessageService]
    })
], FileDemoComponent);
export { FileDemoComponent };
//# sourceMappingURL=filedemo.component.js.map