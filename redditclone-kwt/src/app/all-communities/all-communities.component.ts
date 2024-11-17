import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

export class Community {
  constructor(
    public id: number,
    public name: String,
    public description: String,
    public creationDate: Date,
    public isSuspended: Boolean,
    public suspendedReason: String
  ) {}
}

export class User {
  constructor(
    public id: number,
    public username: String,
    public password: String,
    public email: Date,
    public avatar: String,
    public registrationData: Date,
    public description: String,
    public displayName: String
  ) {}
}

export class Post {
  constructor(
    public id: number,
    public title: String,
    public text: String,
    public creationDate: Date,
    public imagePath: String,
    public user: User,
    public communityId: Number
  ) {}
}

@Component({
  selector: 'app-all-communities',
  templateUrl: './all-communities.component.html',
  styleUrls: ['./all-communities.component.css'],
})
export class AllCommunitiesComponent implements OnInit {
  communities!: Community[];
  constructor(private httpClient: HttpClient) {}

  ngOnInit(): void {
    this.getCommmunities();
  }

  getCommmunities() {
    return this.httpClient
      .get<any>('http://localhost:8080/communities/all')
      .subscribe((response) => {
        console.log(response);
        this.communities = response;
      });
  }
}
