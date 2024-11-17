import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

export class Post{
  constructor(
    public id: number,
    public title: String,
    public text: String,
    public creationDate: Date,
    public imagePath: String,
    public user: User,
    public community: Community

  ){}
}

export class User{
  constructor(
    public id: number,
    public username: String,
    public password: String,
    public email: Date,
    public avatar: String,
    public registrationData: Date,
    public description: String,
    public displayName: String

  ){}
}

export class Community{
  constructor(
    public id: number,
    public name: String,
    public description: String,
    public creationDate: Date,
    public isSuspended: Boolean,
    public suspendedReason: String
    

  ){}
}


@Component({
  selector: 'app-nopost',
  templateUrl: './nopost.component.html',
  styleUrls: ['./nopost.component.css']
})
export class NopostComponent implements OnInit {

  posts!: Post[]
  constructor(private httpClient: HttpClient, private router: Router) { }

  ngOnInit(): void {
    this.getPosts();
  }

  getPosts(){
    return this.httpClient.get<any>('http://localhost:8080/posts').subscribe(
      response => {
        console.log(response);
        this.posts = response;
      }
    )
  }

}
