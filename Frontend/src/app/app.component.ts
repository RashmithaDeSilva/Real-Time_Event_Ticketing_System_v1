import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from './navbar/navbar.component';
import { SaleslogComponent } from './saleslog/saleslog.component';
import { SystemconfigComponent } from './systemconfig/systemconfig.component';


@Component({
  selector: 'app-root',
  imports: [RouterOutlet, NavbarComponent, SaleslogComponent, SystemconfigComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Real-Time-Event-Ticketing-System';
}
