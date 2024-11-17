import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RegisterRequestPlaylad } from '../register/register-request-playlad';
import { Observable } from 'rxjs';
import { CommunityRequestPayload } from '../createcommunity/community-request.payload';
import { PostRequestPayload } from '../communitypage/post-request.payload';
import { baseUrl } from 'src/environments/environment';
import { LoginRequest, tokenResponse } from '../login/LoginDTO';
import { CommunityEditDTO } from '../editcommunity/communityeditDto';
import { Reaction } from '../karma/Reaction';
import { editProfileDTO } from '../mydata/editProfileDTO';
import { PasswordChangeDTO } from '../mydata/editPasswordDTO';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Headers': 'Content-Type',
    'Access-Control-Allow-Methods': 'GET,POST,OPTIONS,DELETE,PUT',
  });

  constructor(private httpClient: HttpClient) {}
  logIn(dto: LoginRequest): Observable<tokenResponse> {
    return this.httpClient.post<tokenResponse>(
      `${baseUrl}login`,
      JSON.stringify(dto),
      { headers: this.headers, responseType: 'json' }
    );
  }

  isLoggedIn() {
    if (!localStorage.getItem('JWT')) {
      return false;
    }
    return true;
  }

  findUsername(): string {
    let token = this.parseToken();

    if (token) {
      return this.parseToken()['sub'];
    }
    return '';
  }

  private parseToken() {
    let jwt = localStorage.getItem('JWT');
    if (jwt !== null) {
      let jwtData = jwt.split('.')[1];
      let decodedJwtJsonData = atob(jwtData);
      let decodedJwtData = JSON.parse(decodedJwtJsonData);
      return decodedJwtData;
    }
  }

  register(registerRequestPlaylad: RegisterRequestPlaylad): Observable<any> {
    return this.httpClient.post(
      'http://localhost:8080/auth/register',
      registerRequestPlaylad,
      { responseType: 'text' }
    );
  }

  createCommunity(
    communityRequestPayload: CommunityRequestPayload
  ): Observable<any> {
    return this.httpClient.post(
      'http://localhost:8080/communities/',
      communityRequestPayload,
      { responseType: 'text' }
    );
  }

  createPost(postRequestPayload: PostRequestPayload): Observable<any> {
    return this.httpClient.post(
      'http://localhost:8080/posts',
      postRequestPayload,
      { responseType: 'text' }
    );
  }

  getOneCommunity(id: number): Observable<any> {
    return this.httpClient.get('http://localhost:8080/communities/' + id);
  }

  editCommunity(edit: CommunityEditDTO, id: number): Observable<any> {
    return this.httpClient.put(
      'http://localhost:8080/communities/' + id,
      edit,
      { responseType: 'text' }
    );
  }

  getOnePost(id: number): Observable<any> {
    return this.httpClient.get('http://localhost:8080/posts/' + id);
  }

  createReaction(reaction: Reaction): Observable<any> {
    return this.httpClient.post(
      'http://localhost:8080/posts/createVote',
      reaction
    );
  }

  findKarmaPost(postId: number) {
    return this.httpClient.get<any>(
      'http://localhost:8080/posts/karma/' + postId
    );
  }
  editUserProfile(EditProfileDTO: editProfileDTO, username: string):Observable<any>{
    return this.httpClient.post('http://localhost:8080/auth/profileChange/' + username, EditProfileDTO)
  }
  editUserPassword(editProfilePassword: PasswordChangeDTO, username: string):Observable<any>{
    return this.httpClient.post('http://localhost:8080/auth/passwordChange/' + username, editProfilePassword)
  }
  findPassword(): string {
    let password = localStorage.getItem('password');
    if (password) {
      return password;
    }
    return '';
  }
}

//function data(data: any) {
// throw new Error('Function not implemented.');
//}
//function login(data: (data: any) => void) {
//  throw new Error('Function not implemented.');
//}
