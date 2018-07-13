import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

//ui-components
import { AppComponent } from './app.component';
import { BaseMenuComponent } from './ui-components/base-menu/base-menu.component';
import { ActiveUiComponent } from './ui-components/active-ui/active-ui.component';

//primeng modules
import { AccordionModule, PanelModule, MenuItem, TabMenuModule, PanelMenuModule } from 'primeng/primeng';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TableModule } from 'primeng/table';
import { routing } from 'src/app/routing-conf/app-routes';
import { HttpModule } from '@angular/http';


@NgModule({
  declarations: [
    AppComponent,
    BaseMenuComponent,
    ActiveUiComponent
  ],
  imports: [
    BrowserModule,
    AccordionModule,
    BrowserAnimationsModule,
    PanelModule,
    TableModule,
    TabMenuModule,
    routing,
    HttpModule,
    PanelMenuModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
