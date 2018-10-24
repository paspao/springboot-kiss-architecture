import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';

import { ApiModule } from "./remote-services";

import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import {ContactRoutingModule} from "./contact-routing.module";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent

  ],
  imports: [
    BrowserModule,
    ApiModule,
    ContactRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
