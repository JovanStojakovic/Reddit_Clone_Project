import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsernamepostComponent } from './usernamepost.component';

describe('UsernamepostComponent', () => {
  let component: UsernamepostComponent;
  let fixture: ComponentFixture<UsernamepostComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UsernamepostComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UsernamepostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
