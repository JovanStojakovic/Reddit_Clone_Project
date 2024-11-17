import { HttpClient } from '@angular/common/http';
import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../shared/auth.service';
import { PostRequestPayload } from './post-request.payload';

export class Community{
  constructor(
    public communityId: number,
    public name: String,
    public description: String,
    public creationDate: Date,
    public isSuspended: Boolean,
    public suspendedReason: String
    

  ){}
}

export class Post{
  constructor(
    public id: number,
    public title: String,
    public text: String,
    public creationDate: Date,
    public imagePath: String,
    public user: User,
    public communityId: Number

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
@Component({
  selector: 'app-communitypage',
  templateUrl: './communitypage.component.html',
  styleUrls: ['./communitypage.component.css']
})
export class CommunitypageComponent implements OnInit {

  community!: Community
  id: any
  posts!: Post[]
  PostForm: FormGroup = new FormGroup({})
  postRequestPayload!: PostRequestPayload;

  constructor(private httpClient: HttpClient, private activatedroute: ActivatedRoute, private authService: AuthService, private router: Router) {
    this.id = this.activatedroute.snapshot.paramMap.get("id");
    this.postRequestPayload = {
      title: '',
      text: '',
      communityId: 0,
      username:''
    }
   }

  ngOnInit(): void {
    this.getCommunity();
    this.getPosts();
    this.PostForm = new FormGroup({
      title: new FormControl(''),
      text: new FormControl(''),
      communityId: new FormControl('')

  })

  }

  getCommunity(){
    return this.httpClient.get<any>('http://localhost:8080/communities/' + this.id).subscribe(
        response => {
          console.log(response);
          this.community = response;
        }
      )
  }

  getPosts(){
    return this.httpClient.get<any>('http://localhost:8080/communities/' + this.id + '/posts').subscribe(
        response => {
          console.log(response);
          this.posts = response;
        }
      )
  }

  createPost(){
    this.postRequestPayload.title = this.PostForm.get('title')?.value;
    this.postRequestPayload.text = this.PostForm.get('text')?.value;
    this.postRequestPayload.communityId = this.id;
    this.postRequestPayload.username = this.authService.findUsername();
    console.log(this.postRequestPayload.username)
    this.authService.createPost(this.postRequestPayload)
    .subscribe(data => {
      console.log(data);
      this.router.navigate(['/communitypage/' + this.id])
      window.location.reload();
    })
  }


}
