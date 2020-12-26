import {Component, OnInit} from '@angular/core';
import {FormGroup} from '@angular/forms';
import {AbstractBaseComponent, ColumnType} from '../../../../shared/abstract-base-component';
import {CariKart} from '../../../cari-kart/dto/cari-kart';
import {AppStore} from '../../../../shared/app.store';
import {ActivatedRoute, Params} from '@angular/router';
import {Location} from '@angular/common';
import {Siparis} from "../../dto/siparis";
import {SiparisService} from "../../service/siparis.service";


@Component({
  selector: 'app-siparis-detay',
  templateUrl: './siparis-detay.component.html',
  styleUrls: ['./siparis-detay.component.css']
})
export class SiparisDetayComponent extends AbstractBaseComponent implements OnInit {

  sorguForm: FormGroup;

  totalRecords: number;
  loading: boolean;

  // returnd values
  cariKart: CariKart;
  siparisDetayList: Siparis[];
  siparis: Siparis;

  constructor(public appStore: AppStore,
              private siparisService: SiparisService,
              public location: Location,
              private route: ActivatedRoute) {
    super(appStore);
  }

  ngOnInit(): void {
    this.loading = false;
    this.route.params.subscribe((params: Params) => {
      this.loadFatura(params['id']);
    });
  }

  loadFatura(id) {
    this.loading = true;
    this.subscribeToResponseBase(this.siparisService.loadSiparis(id), this.sorgulaSuccess, undefined);
  }

  private sorgulaSuccess(data: any) {
    this.loading = false;
    this.cariKart = data['cariKart'];
    this.siparisDetayList = data['siparisList'];
    this.siparis = data['siparis'];
  }

  print() {
    window.print();
  }

}
