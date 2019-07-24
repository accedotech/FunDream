import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {InputTextModule} from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {MenubarModule} from 'primeng/menubar';
import {DropdownModule} from 'primeng/dropdown';
import {CalendarModule} from 'primeng/calendar';
import {MessagesModule} from 'primeng/messages';
import {MessageModule} from 'primeng/message';
import {PanelModule} from 'primeng/panel';
import {MultiSelectModule} from 'primeng/multiselect';
import {SpinnerModule} from 'primeng/spinner';
import {InputTextareaModule} from 'primeng/inputtextarea';
import {FileUploadModule} from 'primeng/fileupload';
import {CardModule} from 'primeng/card';
import {DataViewModule} from 'primeng/dataview';
import {GalleriaModule} from 'primeng/galleria';





@NgModule({
    imports: [
        CommonModule,        
        InputTextModule,
        ButtonModule,
        MenubarModule,
        DropdownModule,
        CalendarModule,
        MessagesModule,
        MessageModule,
        PanelModule,
        MultiSelectModule,
        SpinnerModule,
        InputTextareaModule,
        FileUploadModule,
        CardModule,
        DataViewModule,
        GalleriaModule
    ],

    exports: [      
      InputTextModule,
      ButtonModule,
      MenubarModule,
      DropdownModule,
      CalendarModule,
      MessagesModule,
      MessageModule,
      PanelModule,
      MultiSelectModule,
      SpinnerModule,
      InputTextareaModule,
      FileUploadModule,
      CardModule,
      DataViewModule,
      GalleriaModule
    ]
})



// tslint:disable-next-line: class-name
export class primengConfig {}
