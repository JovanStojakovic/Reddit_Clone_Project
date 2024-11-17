import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Route, Router } from '@angular/router';
import { AuthInterceptor } from '../interceptors/auth.interceptor';
import { AuthService } from '../shared/auth.service';
import {LoginRequest} from '../login/LoginDTO'




@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
 
  
  form!: FormGroup;
  formGroup: any;
  myPassword!: string;

  constructor(private authService:AuthService,private router : Router) { }

  ngOnInit(){
    this.initForm();
  }

  initForm(){
    this.form = new FormGroup({
      username: new FormControl('',[Validators.required]),
      password: new FormControl('',[Validators.required])
     }) 
    
  }
  loginProces(){
    if(this.form.valid){
       const dto : LoginRequest = {
        username: this.form.value.username,
        password : this.form.value.password
       
       }
      
      this.authService.logIn(dto).subscribe(result => {
        if (result) { 
          localStorage.setItem('JWT', result.token);
          console.log(result);
          localStorage.setItem("password", this.form.value.password) 
          console.log(this.form.value.password)
          this.router.navigate(['/homepage']);
       }
     },err =>{
        alert(err);
     });
   } 
  }
}

