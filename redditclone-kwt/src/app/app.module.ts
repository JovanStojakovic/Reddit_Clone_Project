import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';




import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';


import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { NouserComponent } from './nouser/nouser.component';
import { NavbarnoComponent } from './navbarno/navbarno.component';
import { PostComponent } from './post/post.component';
import { KarmaComponent } from './karma/karma.component';
import { UsernamepostComponent } from './usernamepost/usernamepost.component';
import { HomepageComponent } from './homepage/homepage.component';
import { NavbarComponent } from './navbar/navbar.component';
import { CreatepostComponent } from './createpost/createpost.component';
import { CreatecommunityComponent } from './createcommunity/createcommunity.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MyprofileComponent } from './myprofile/myprofile.component';
import { MydataComponent } from './mydata/mydata.component';
import { MypostsComponent } from './myposts/myposts.component';
import { MycommunityComponent } from './mycommunity/mycommunity.component';
import { CommunitypageComponent } from './communitypage/communitypage.component';
import { NopostComponent } from './nopost/nopost.component';
import { EditdeletepostComponent } from './editdeletepost/editdeletepost.component';
import { EditpostComponent } from './editpost/editpost.component';
import { EditcommunityComponent } from './editcommunity/editcommunity.component';
import { EditdeletecommunityComponent } from './editdeletecommunity/editdeletecommunity.component';
import { AllCommunitiesComponent } from './all-communities/all-communities.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    NouserComponent,
    NavbarnoComponent,
    PostComponent,
    KarmaComponent,
    UsernamepostComponent,
    HomepageComponent,
    NavbarComponent,
    CreatepostComponent,
    CreatecommunityComponent,
    MyprofileComponent,
    MydataComponent,
    MypostsComponent,
    MycommunityComponent,
    CommunitypageComponent,
    NopostComponent,
    EditdeletepostComponent,
    EditpostComponent,
    EditcommunityComponent,
    EditdeletecommunityComponent,
    AllCommunitiesComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ], 
  providers: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
