import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges, ViewChild} from '@angular/core';
import {SelectItem} from 'primeng/api';
import {AbstractComponent} from "../abstract-component";
import {BelgeTipi} from "../constants";
import {AppStore} from "../app.store";
import {FileUtils} from "../file-utils";
import {FileUpload} from 'primeng/fileupload';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css']
})

export class FileUploadComponent extends AbstractComponent implements OnInit, OnChanges {

  @ViewChild('fileInput', {static: true}) fileInput: FileUpload;
  @Input() belgeYuklenebilirMi: boolean;
  @Input() fileTypes: string = null;
  @Input() url: string;
  @Input() belgeTipiRendered: boolean = true;
  @Input() multipleBelgeTipi: boolean = false;
  @Input() isMultiple: boolean = true;
  @Input() that;

  formData: Map<String, any>;

  @Output() isFileSelected: EventEmitter<boolean> = new EventEmitter<boolean>();
  @Output() isFileRemoved: EventEmitter<boolean> = new EventEmitter<boolean>();
  @Output() isFileClear: EventEmitter<boolean> = new EventEmitter<boolean>();

  id: number;
  belgeTipiList: SelectItem [] = [];
  uploadedFiles: any[] = [];
  belgeTipi: number;
  acceptedTypes: string;
  maxFileSize: number = FileUtils.fileSize;
  private callback: Function;

  belgeInfoList: any[] = [];

  enums: any = {
    BelgeTipi: BelgeTipi,
  };

  constructor(appStore: AppStore) {
    super(appStore);
  }

  setId(id: number) {
    this.id = id;
  }

  ngOnInit(): void {
    this.acceptedTypes = this.fileTypes != null ? this.fileTypes : FileUtils.acceptedTypes;
        this.belgeTipiList = this.appStore.selectService.getAll(BelgeTipi, false);
      this.fileInput.multiple = this.isMultiple;
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes.fileTypes) {
      this.acceptedTypes = changes.fileTypes.currentValue;
    }
  }

  uploadFile() {
    if (this.fileInput.files.length) {
      this.fileInput.upload();
    }
  }

  onBeforeUpload(event) {
    if (this.belgeTipiRendered) {
      if (this.multipleBelgeTipi) {
        for (let b of this.belgeInfoList) {
          event.formData.append('belgeTipiId', b.belgeTipi);
        }
      } else {
        event.formData.append('belgeTipiId', this.belgeTipi);
      }
    }
    event.formData.append('id', this.id);
    if (this.formData) {
      this.formData.forEach((v, k) => {
        event.formData.append(k, v);
      })
    }
  }

  onUpload(event) {
    this.handleHttpResponse(event.originalEvent, this.onUploadSuccess);
  }

  onUploadSuccess() {
    this.uploadedFiles = [];
    this.belgeTipi = null;
    this.callback.bind(this.that).call();
    // this.callback.bind(null).call();
  }

  onSelect(event) {
    this.isFileSelected.emit(true);

    if (this.multipleBelgeTipi) {
      for (let file of event.files) {
        if (this.fileInput.isFileSelected(file)) {
          let duplicateIndex = this.uploadedFiles.findIndex((item) => {
            return item.lastModified === file.lastModified && item.name === file.name;
          });
          if (duplicateIndex === -1) {
            this.uploadedFiles.push(file);
            this.belgeInfoList.push({
              lastModified: file.lastModified,
              name: file.name,
              belgeTipi: this.belgeTipi
            });
          }
        }
      }
    }
  }

  onRemove(event) {
    this.isFileRemoved.emit(true);

    if (this.multipleBelgeTipi) {
      let removeIndex = this.uploadedFiles.findIndex((item) => {
        return item.lastModified === event.file.lastModified && item.name === event.file.name;
      });
      this.uploadedFiles.splice(removeIndex, 1);
      this.belgeInfoList.splice(removeIndex, 1);
    }
  }

  onClear() {
    this.isFileClear.emit(true);

    if (this.multipleBelgeTipi) {
      this.uploadedFiles = [];
      this.belgeInfoList = [];
    }
  }

  setCallback(uploadCallback: () => any) {
    this.callback = uploadCallback;
  }

  onRemoveButtonClick(rowIndex) {
    let filesNode = document.getElementsByClassName('ui-fileupload-files')[0];
    let buttonList = filesNode.getElementsByTagName('button');
    buttonList[rowIndex].click();
  }
}
