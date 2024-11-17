import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditdeletepostComponent } from './editdeletepost.component';

describe('EditdeletepostComponent', () => {
  let component: EditdeletepostComponent;
  let fixture: ComponentFixture<EditdeletepostComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditdeletepostComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditdeletepostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
