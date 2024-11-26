import {Component, OnInit} from '@angular/core';
import {NgForOf} from '@angular/common';
import {HttpClient} from '@angular/common/http';


@Component({
  selector: 'app-saleslog',
  imports: [
    NgForOf
  ],
  templateUrl: './saleslog.component.html',
  styleUrl: './saleslog.component.css'
})
export class SaleslogComponent implements OnInit {
  list: Array<any> = [];
  private intervalId: any;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    // Call the function every 5 seconds
    this.intervalId = setInterval(() => {
      this.checkLogs();
    }, 1000);
  }

  checkLogs(): void {
    this.http.get<any>('http://localhost:8080/api/v1/saleslog/?search_text=&page=0&size=25')
      .subscribe(res => {
        this.list=res.data.data_list;
      });
  }

  ngOnDestroy(): void {
    // Clear the interval when the component is destroyed
    if (this.intervalId) {
      clearInterval(this.intervalId);
    }
  }

}
