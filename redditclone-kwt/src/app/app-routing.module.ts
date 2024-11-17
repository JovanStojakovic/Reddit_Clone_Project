import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { NouserComponent } from './nouser/nouser.component';
import { NavbarnoComponent } from './navbarno/navbarno.component';
import { HomepageComponent } from './homepage/homepage.component';
import { NavbarComponent } from './navbar/navbar.component';
import { KarmaComponent } from './karma/karma.component';
import { PostComponent } from './post/post.component';
import { UsernamepostComponent } from './usernamepost/usernamepost.component';
import { CreatepostComponent } from './createpost/createpost.component';
import { CreatecommunityComponent } from './createcommunity/createcommunity.component';
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


const routes: Routes = [
  { path: 'register',component:RegisterComponent},
  { path: 'login',component:LoginComponent},
  { path: 'nouser',component:NouserComponent},
  { path: 'navbarno',component:NavbarnoComponent},
  { path: 'homepage',component:HomepageComponent},
  { path: 'navbar',component:NavbarComponent},
  { path: 'karma',component:KarmaComponent},
  { path: 'post',component:PostComponent},
  { path: 'usernamepost',component:UsernamepostComponent},
  { path: 'createpost',component:CreatepostComponent},
  { path: 'createcommunity',component:CreatecommunityComponent},
  { path: 'myprofile',component:MyprofileComponent},
  { path: 'mydata',component:MydataComponent},
  { path: 'myposts',component:MypostsComponent},
  { path: 'mycommunity',component:MycommunityComponent},
  { path: 'communitypage/:id',component:CommunitypageComponent},
  { path: 'nopost',component:NopostComponent},
  { path: 'editdeletepost',component:EditdeletepostComponent},
  { path: 'editpost/:id',component:EditpostComponent},
  { path: 'editcommunity/:id',component:EditcommunityComponent},
  { path: 'editdeletecommunity',component:EditdeletecommunityComponent},
  { path: 'all-communities',component:AllCommunitiesComponent}
 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
