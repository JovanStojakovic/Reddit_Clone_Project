import { HttpClient, HttpRequest } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { AuthService } from '../shared/auth.service';
import { editProfileDTO } from './editProfileDTO';
import { myUserDTO } from './getUserDTO';
import { FormControl,  Validators } from '@angular/forms';
import { PasswordChangeDTO } from './editPasswordDTO';
import { Router } from '@angular/router';


@Component({
  selector: 'app-mydata',
  templateUrl: './mydata.component.html',
  styleUrls: ['./mydata.component.css']
})
export class MydataComponent implements OnInit {

  loggedUsername!:string
  loggedUser!:myUserDTO
  editForm: FormGroup = new FormGroup({});
  passwordForm: FormGroup = new FormGroup({});
  editProfileDTO!: editProfileDTO;
  passwordChangeDTO!: PasswordChangeDTO;
  lcPassword!: string;

  constructor(private http: HttpClient,private authService:AuthService,private router:Router) {
    this.editProfileDTO = {
      description: "",
      displayName: ""
     }
    this.passwordChangeDTO = {
      oldPassword: "",
      newPassword: ""
     }
   }

  ngOnInit(): void {
    this.loggedUsername = this.authService.findUsername();
    this.getUser();
    this.lcPassword = this.authService.findPassword();

    this.editForm = new FormGroup({
      displayName: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required)



    });
    this.passwordForm = new FormGroup({
      oldPassword: new FormControl('', Validators.required),
      newPassword: new FormControl('', Validators.required)



    });
  }
  
  getUser(){
    this.findLogedUser(this.loggedUsername).subscribe(data=>{
      console.log(data)
      this.loggedUser = data
    })
  }

  findLogedUser(loggedUsername:string):Observable<any>{
    return this.http.get('http://localhost:8080/auth/users/'+ loggedUsername)
  }

  editProfile(){
    this.editProfileDTO.description = this.editForm.get('description')?.value
    if(this.editForm.get('description')?.value == null || this.editForm.get('description')?.value == ""){
      this.editProfileDTO.description = this.loggedUser.description;
    }
    this.editProfileDTO.displayName = this.editForm.get('displayName')?.value
    if(this.editForm.get('displayName')?.value == null || this.editForm.get('displayName')?.value==""){
      this.editProfileDTO.displayName = this.loggedUser.displayName
    }
  
    


    this.authService.editUserProfile(this.editProfileDTO, this.loggedUsername).subscribe(data=>{
      console.log(data)
    })
  }
  editPassword(){
      this.passwordChangeDTO.oldPassword = this.passwordForm.get('oldPassword')?.value
      console.log(this.passwordChangeDTO.oldPassword)
      if(this.passwordForm.get('oldPassword')?.value == null || this.passwordForm.get('oldPassword')?.value == ""){
        alert("Stara lozinka ne sme biti prazna!")
      }
      if(this.passwordForm.get('oldPassword')?.value != this.lcPassword){
        alert("Stara lozinka se ne podudara sa upisanom!")
      }
      this.passwordChangeDTO.newPassword = this.passwordForm.get('newPassword')?.value
      if(this.passwordForm.get('newPassword')?.value == null || this.passwordForm.get('newPassword')?.value == ""){
        alert("Nova lozinka ne sme biti prazna!")
      }
      this.authService.editUserPassword(this.passwordChangeDTO,this.loggedUsername).subscribe(data =>{
        console.log(data)
        localStorage.clear();
        this.router.navigate(["/login"])

      })
  }

}
