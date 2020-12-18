import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {TranslateModule} from '@ngx-translate/core';
import {DateFormatPipe} from './date/date-format.pipe';
import {DateTimeFormatPipe} from './date/date-time-format.pipe';
import {ParaComponent} from './para/para.component';
import {CardModule} from 'primeng/card';
import {ParaFormatPipe} from './para/para-format.pipe';
import {TableModule} from 'primeng/table';
import {EnumPipe} from './utils/enum.pipe';
import {DialogModule} from 'primeng/dialog';
import {TabViewModule} from 'primeng/tabview';
import {TreeModule} from 'primeng/tree';
import {DataViewModule} from 'primeng/dataview';
import {StepsModule} from 'primeng/steps';
import {ListboxModule} from 'primeng/listbox';
import {FieldsetModule} from 'primeng/fieldset';
import {StringWrapperPipe} from './utils/string-wrapper.pipe';
import {ValidationMessageComponent} from './validation-message/validation-message.component';
import {CommonDatatableComponent} from './datatable/common-datatable.component';
import {DatatableInputValidatorComponent} from './datatable-input-validator/datatable-input-validator.component';
import {FileUploadComponent} from './file-upload/file-upload.component';
import {SelectService} from './select/select.service';
import {ParaGoruntuleComponent} from './para-goruntule/para-goruntule.component';
import {CommonLazyDatatableComponent} from './lazy-datatable/common-lazy-datatable.component';
import {InputTextModule} from 'primeng/inputtext';
import {PanelModule} from 'primeng/panel';
import {DropdownModule} from 'primeng/dropdown';
import {MultiSelectModule} from 'primeng/multiselect';
import {ButtonModule} from 'primeng/button';
import {InputSwitchModule} from 'primeng/inputswitch';
import {ToggleButtonModule} from 'primeng/togglebutton';
import {OverlayPanelModule} from 'primeng/overlaypanel';
import {InputTextareaModule} from 'primeng/inputtextarea';
import {SplitButtonModule} from 'primeng/splitbutton';
import {BlockUIModule} from 'primeng/blockui';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {MessagesModule} from 'primeng/messages';
import {AccordionModule} from 'primeng/accordion';
import {AutoCompleteModule} from 'primeng/autocomplete';
import {SpinnerModule} from 'primeng/spinner';
import {FileUploadModule} from 'primeng/fileupload';
import {MessageModule} from 'primeng/message';
import {TooltipModule} from 'primeng/tooltip';
import {CalendarModule} from 'primeng/calendar';
import {KeyFilterModule} from 'primeng/keyfilter';
import {InputMaskModule} from 'primeng/inputmask';
import {CheckboxModule} from 'primeng/checkbox';
import {MegaMenuModule} from 'primeng/megamenu';
import {OrganizationChartModule} from 'primeng/organizationchart';
import {SelectButtonModule} from 'primeng/selectbutton';
import {TriStateCheckboxModule} from 'primeng/tristatecheckbox';
import {TreeTableModule} from 'primeng/treetable';
import {TabMenuModule} from 'primeng/tabmenu';
import {PickListModule} from 'primeng/picklist';
import {ChipsModule} from 'primeng/chips';
import {BreadcrumbModule} from 'primeng/breadcrumb';
import {ChartModule} from 'primeng/chart';
import {ColorPickerModule} from 'primeng/colorpicker';
import {CarouselModule} from 'primeng/carousel';
import {CodeHighlighterModule} from 'primeng/codehighlighter';
import {ContextMenuModule} from 'primeng/contextmenu';
import {FullCalendarModule} from 'primeng/fullcalendar';
import {GalleriaModule} from 'primeng/galleria';
import {InplaceModule} from 'primeng/inplace';
import {MenuModule} from 'primeng/menu';
import {LightboxModule} from 'primeng/lightbox';
import {MenubarModule} from 'primeng/menubar';
import {OrderListModule} from 'primeng/orderlist';
import {PaginatorModule} from 'primeng/paginator';
import {PanelMenuModule} from 'primeng/panelmenu';
import {PasswordModule} from 'primeng/password';
import {ProgressBarModule} from 'primeng/progressbar';
import {RatingModule} from 'primeng/rating';
import {SliderModule} from 'primeng/slider';
import {SlideMenuModule} from 'primeng/slidemenu';
import {TerminalModule} from 'primeng/terminal';
import {TieredMenuModule} from 'primeng/tieredmenu';
import {ToastModule} from 'primeng/toast';
import {VirtualScrollerModule} from 'primeng/virtualscroller';
import {ScrollPanelModule} from 'primeng/scrollpanel';
import {RadioButtonModule} from 'primeng/radiobutton';
import {ToolbarModule} from 'primeng/toolbar';
import {InputNumberModule} from 'primeng/inputnumber';
import {CompanyComponent} from './company/company.component';
import {ChangePasswordComponent} from './change-password/change-password.component';
import {ConfirmPopupModule} from "primeng/confirmpopup";
import {ConfirmationService} from "primeng/api";

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        InputTextModule,
        ButtonModule,
        PanelModule,
        TabViewModule,
        DropdownModule,
        InputSwitchModule,
        MultiSelectModule,
        TreeModule,
        ToggleButtonModule,
        CardModule,
        OverlayPanelModule,
        SplitButtonModule,
        TableModule,
        InputTextareaModule,
        BlockUIModule,
        TranslateModule,
        ConfirmDialogModule,
        MessageModule,
        MessagesModule,
        TooltipModule,
        AccordionModule,
        CalendarModule,
        AutoCompleteModule,
        KeyFilterModule,
        SpinnerModule,
        DialogModule,
        InputMaskModule,
        FileUploadModule,
        DataViewModule,
        StepsModule,
        ListboxModule,
        FieldsetModule,
        CheckboxModule,
        TriStateCheckboxModule,
        MegaMenuModule,
        OrganizationChartModule,
        TreeTableModule,
        TabMenuModule,
        SelectButtonModule,
        PickListModule,
        ChipsModule,
        BreadcrumbModule,
        CarouselModule,
        ChartModule,
        CodeHighlighterModule,
        ColorPickerModule,
        ContextMenuModule,
        FullCalendarModule,
        GalleriaModule,
        InplaceModule,
        LightboxModule,
        MenuModule,
        MenubarModule,
        OrderListModule,
        PaginatorModule,
        PanelMenuModule,
        PasswordModule,
        ProgressBarModule,
        RatingModule,
        SlideMenuModule,
        SliderModule,
        TerminalModule,
        TieredMenuModule,
        ToastModule,
        VirtualScrollerModule,
        InputNumberModule,
        ConfirmPopupModule
    ],
    providers: [SelectService, ConfirmationService],
    declarations: [
        DateFormatPipe,
        DateTimeFormatPipe,
        ParaFormatPipe,
        EnumPipe,
        StringWrapperPipe,
        ParaComponent,
        ValidationMessageComponent,
        CommonDatatableComponent,
        CommonLazyDatatableComponent,
        DatatableInputValidatorComponent,
        FileUploadComponent,
        ParaGoruntuleComponent,
        CompanyComponent,
        ChangePasswordComponent
    ],
    exports: [
        DateFormatPipe,
        EnumPipe,
        StringWrapperPipe,
        SpinnerModule,
        DateTimeFormatPipe,
        ParaFormatPipe,
        CommonModule,
        CardModule,
        FormsModule,
        ReactiveFormsModule,
        InputTextModule,
        InputTextareaModule,
        ButtonModule,
        PanelModule,
        DropdownModule,
        BlockUIModule,
        InputSwitchModule,
        MultiSelectModule,
        TreeModule,
        TabViewModule,
        TooltipModule,
        AccordionModule,
        ToggleButtonModule,
        OverlayPanelModule,
        SplitButtonModule,
        TableModule,
        ConfirmDialogModule,
        TranslateModule,
        MessageModule,
        MessagesModule,
        CalendarModule,
        AutoCompleteModule,
        ParaComponent,
        KeyFilterModule,
        DialogModule,
        InputMaskModule,
        FileUploadModule,
        TabViewModule,
        DataViewModule,
        StepsModule,
        FieldsetModule,
        ListboxModule,
        CheckboxModule,
        TriStateCheckboxModule,
        MegaMenuModule,
        OrganizationChartModule,
        TreeTableModule,
        TabMenuModule,
        ScrollPanelModule,
        SelectButtonModule,
        PickListModule,
        RadioButtonModule,
        ToolbarModule,
        ChipsModule,
        BreadcrumbModule,
        CarouselModule,
        ChartModule,
        CodeHighlighterModule,
        ColorPickerModule,
        ContextMenuModule,
        FullCalendarModule,
        GalleriaModule,
        InplaceModule,
        LightboxModule,
        MenuModule,
        MenubarModule,
        OrderListModule,
        PaginatorModule,
        PanelMenuModule,
        PasswordModule,
        ProgressBarModule,
        RatingModule,
        SlideMenuModule,
        SliderModule,
        TerminalModule,
        TieredMenuModule,
        ToastModule,
        VirtualScrollerModule,
        ValidationMessageComponent,
        CommonDatatableComponent,
        CommonLazyDatatableComponent,
        DatatableInputValidatorComponent,
        FileUploadComponent,
        ParaGoruntuleComponent,
        InputNumberModule,
        ConfirmPopupModule,
        CompanyComponent,
        ChangePasswordComponent
    ]
})
export class SharedModule {
}
