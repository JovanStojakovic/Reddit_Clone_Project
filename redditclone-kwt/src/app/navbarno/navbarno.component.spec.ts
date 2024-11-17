import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavbarnoComponent } from './navbarno.component';

describe('NavbarnoComponent', () => {
  let component: NavbarnoComponent;
  let fixture: ComponentFixture<NavbarnoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NavbarnoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NavbarnoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
