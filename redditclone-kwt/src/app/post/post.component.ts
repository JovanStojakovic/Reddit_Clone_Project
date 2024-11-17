import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommunitypageComponent } from '../communitypage/communitypage.component';

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

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css'],
})
export class PostComponent implements OnInit {
  posts!: Post[];
  @Input() onePost!: Post;

  constructor(private httpClient: HttpClient, private router: Router) {}

  ngOnInit(): void {
    this.getPosts();
  }

  getPosts() {
    return this.httpClient
      .get<any>('http://localhost:8080/posts')
      .subscribe((response) => {
        console.log(response);
        this.posts = response;
      });
  }
}
