import { Component } from '@angular/core';
import { SystemconfigComponent } from '../systemconfig/systemconfig.component';
import { SaleslogComponent } from '../saleslog/saleslog.component';

@Component({
  selector: 'app-navbar',
  imports: [SystemconfigComponent, SaleslogComponent],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

}
