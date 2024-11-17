import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Post } from '../communitypage/communitypage.component';
import { AuthService } from '../shared/auth.service';
import { Reaction } from './Reaction';
import { EReactionType } from './ReactionType';

@Component({
  selector: 'app-karma',
  templateUrl: './karma.component.html',
  styleUrls: ['./karma.component.css'],
})
export class KarmaComponent implements OnInit {
  @Input() onePost!: Post;
  reaction!: Reaction;
  karma!: number;

  constructor(
    private authService: AuthService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.reaction = {
      reactionAuthor: '',
      postId: 1,
      reactionType: EReactionType.UPVOTE,
    };
  }

  ngOnInit(): void {
    this.findPost();
    this.calculateKarma();
  }

  upvote() {
    if (this.authService.isLoggedIn()) {
      this.reaction.reactionType = EReactionType.UPVOTE;
      this.createReaction();
    } else {
      alert('You have to log in in order to do this!');
    }
  }

  downvote() {
    if (this.authService.isLoggedIn()) {
      this.reaction.reactionType = EReactionType.DOWNVOTE;
      this.createReaction();
    } else {
      alert('You have to log in in order to do this!');
    }
  }

  private createReaction() {
    this.reaction.postId = this.onePost.id;
    console.log(this.onePost.id);
    this.reaction.reactionAuthor = this.authService.findUsername();
    this.authService.createReaction(this.reaction).subscribe(
      () => {
        this.findPost;
        this.calculateKarma();
        this.router.navigateByUrl('/homepage');
      },
      (error) => {
        location.reload();
      }
    );
  }

  // private napraviReakciju() {
  //   this.reakcija.idObjave = this.jednaobjava.idObjave;
  //   console.log(this.reakcija.idObjave);
  //   this.reakcija.autorReakcije = this.authServis.nadjiKorisnickoIme();
  //   this.reakcijaServis.napraviReakciju(this.reakcija).subscribe(
  //     () => {
  //       this.nadjiObjavu;
  //       this.izracunajKarmu();
  //       this.ruter.navigateByUrl('/homeStranica');
  //     },
  //     (error) => {
  //       location.reload();
  //     }
  //   );
  // }

  calculateKarma() {
    this.authService.findKarmaPost(this.onePost.id).subscribe((data) => {
      this.karma = data;
    });
  }

  private findPost() {
    this.authService.getOnePost(this.onePost.id).subscribe((onePost) => {
      this.onePost = onePost;
      console.log(onePost);
    });
  }
}
