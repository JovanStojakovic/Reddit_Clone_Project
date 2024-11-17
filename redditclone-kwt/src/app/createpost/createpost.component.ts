import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../shared/auth.service';




@Component({
  selector: 'app-createpost',
  templateUrl: './createpost.component.html',
  styleUrls: ['./createpost.component.css']
})
export class CreatepostComponent implements OnInit {

  PostForm: FormGroup = new FormGroup({})


  constructor(private authService: AuthService) { 
   
   
  }

  ngOnInit(): void {
    
    this.PostForm = new FormGroup({
      title: new FormControl(''),
      text: new FormControl('')

  })

 
}

}
