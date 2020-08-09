import {Component, OnInit} from '@angular/core';
import {CarService} from '../service/carservice';
import {EventService} from '../service/eventservice';
import {Car} from '../domain/car';
import {SelectItem} from 'primeng/primeng';
import {MenuItem} from 'primeng/primeng';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin from '@fullcalendar/interaction';

@Component({
    templateUrl: './dashboard.component.html'
})
export class DashboardDemoComponent implements OnInit {

    cities: SelectItem[];

    selectedCity: any;

    fullcalendarOptions: any;

    constructor() { }

    ngOnInit() {

        this.cities = [];
        this.cities.push({label: 'Mesaj Tipi', value: null});
        this.cities.push({label: 'Sipariş', value: {id: 1, name: 'New York', code: 'NY'}});
        this.cities.push({label: 'Ürün', value: {id: 2, name: 'Rome', code: 'RM'}});
        this.cities.push({label: 'Fiyat', value: {id: 3, name: 'London', code: 'LDN'}});
        this.cities.push({label: 'Talep', value: {id: 4, name: 'Istanbul', code: 'IST'}});

        this.fullcalendarOptions = {
            plugins: [ dayGridPlugin, timeGridPlugin, interactionPlugin ],
            header: {
                right: 'prev,next, today',
                left: 'title'
            }
        };
    }
}
