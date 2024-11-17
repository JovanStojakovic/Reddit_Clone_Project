import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../shared/auth.service';
import { CommunityRequestPayload } from './community-request.payload';

@Component({
  selector: 'app-createcommunity',
  templateUrl: './createcommunity.component.html',
  styleUrls: ['./createcommunity.component.css']
})
export class CreatecommunityComponent implements OnInit {

  CommunityForm: FormGroup = new FormGroup({})
  communityRequestPayload!: CommunityRequestPayload;


  constructor(private httpClient: HttpClient, private activatedroute: ActivatedRoute, private authService: AuthService, private router: Router) { 
   
    this.communityRequestPayload = {
      id: 0,
      name: '',
      description: '',
      author:''
    }
  }

  ngOnInit(): void {
    this.CommunityForm = new FormGroup({
      name: new FormControl(''),
      description: new FormControl('')

    })
  }

  createCommunity(){
    this.communityRequestPayload.name = this.CommunityForm.get('name')?.value;
    this.communityRequestPayload.description = this.CommunityForm.get('description')?.value;
    this.communityRequestPayload.author = this.authService.findUsername();
    console.log(this.communityRequestPayload.author)
    this.authService.createCommunity(this.communityRequestPayload)
    .subscribe(data => {
      console.log(data);
      this.router.navigate(['/all-communities']);
    })
  }

}
