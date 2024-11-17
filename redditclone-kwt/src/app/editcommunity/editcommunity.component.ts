import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CommunityRequestPayload } from '../createcommunity/community-request.payload';
import { AuthService } from '../shared/auth.service';
import { CommunityEditDTO } from './communityeditDto';

@Component({
  selector: 'app-editcommunity',
  templateUrl: './editcommunity.component.html',
  styleUrls: ['./editcommunity.component.css']
})
export class EditcommunityComponent implements OnInit {
  communityeditform!: FormGroup;
  idcommunity!: number;
  community!: CommunityRequestPayload;
  communityedit!: CommunityEditDTO ;
  constructor(private auth: AuthService, private route: ActivatedRoute,private router: Router) { 
    this.community = {
     
      id: 0,
      name: '',
      description: '',
      author: ''
    }
    this.communityedit = {
      name: '',
      description: ''
    }
  }

  ngOnInit(): void {
    this.communityeditform = new FormGroup({
      name: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required)
    })
    this.idcommunity = this.route.snapshot.params['id']
    this.community.id = this.idcommunity
    this.getOneCommunity()
  }
  getOneCommunity(){
    this.auth.getOneCommunity(this.idcommunity).subscribe(data => {
      console.log(data);
      this.community = data;
    })
  }
  edit(){
    this.communityedit.name = this.communityeditform.get("name")?.value;
    if(this.communityeditform.get("name")?.value == null || this.communityeditform.get("name")?.value == ""){
      this.communityedit.name = this.community.name
    }
    this.communityedit.description = this.communityeditform.get("description")?.value;
    if(this.communityeditform.get("description")?.value == null ||  this.communityeditform.get("description")?.value == ""){
      this.communityedit.description = this.community.description
    }
    this.auth.editCommunity(this.communityedit, this.idcommunity).subscribe(data =>{
      console.log(data);
      this.router.navigate(['/all-communities']);
    })
  }
}
