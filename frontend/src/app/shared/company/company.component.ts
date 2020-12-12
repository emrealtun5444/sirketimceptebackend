import {Component, OnInit} from '@angular/core';
import {AppStore} from "../app.store";
import {SelectItem} from "primeng/api";
import {Router} from "@angular/router";
import {TokenStorageService} from "../service/token-storage.service";

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.scss']
})
export class CompanyComponent implements OnInit {

  companies: SelectItem[] = [];
  selectedItem;

  constructor(public tokenStorageService: TokenStorageService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.companies = this.tokenStorageService.getCompanies();
    this.selectedItem = Number(this.tokenStorageService.getSelectedCompany());
  }

  onChangeCompany(event) {
    this.tokenStorageService.saveSelectedCompany(event.value);
    this.router.navigate(['/']);
  }

  renderCombo() {
    return this.companies && this.companies.length > 1;
  }

}
