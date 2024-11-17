import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NopostComponent } from './nopost.component';

describe('NopostComponent', () => {
  let component: NopostComponent;
  let fixture: ComponentFixture<NopostComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NopostComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NopostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
