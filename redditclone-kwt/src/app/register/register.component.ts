import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../shared/auth.service';
import { RegisterRequestPlaylad } from './register-request-playlad';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})


export class RegisterComponent implements OnInit {

  registerRequestPlaylad!: RegisterRequestPlaylad;
  registerForm!: FormGroup;

  constructor(private authService: AuthService, private router : Router) {
    this.registerRequestPlaylad = { 
      username: '',
      password: '',
      email: ''
    };
  }

  ngOnInit() {
    this.registerForm = new FormGroup({
      username: new FormControl ('', Validators.required),
      password: new FormControl ('',Validators.required ),
      email: new FormControl ('',[ Validators.required, Validators.email ] ),
     });
  }
  register(){
    this.registerRequestPlaylad.username = this.registerForm.get('username')?.value;
    this.registerRequestPlaylad.password = this.registerForm.get('password')?.value;
    this.registerRequestPlaylad.email = this.registerForm.get('email')?.value;

    this.authService.register(this.registerRequestPlaylad)
      .subscribe(data => {
        console.log(data);
        this.router.navigate(['/login']);
      });
  }
}
