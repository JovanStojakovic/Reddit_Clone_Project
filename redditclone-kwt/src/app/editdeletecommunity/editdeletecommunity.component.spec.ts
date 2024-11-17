import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditdeletecommunityComponent } from './editdeletecommunity.component';

describe('EditdeletecommunityComponent', () => {
  let component: EditdeletecommunityComponent;
  let fixture: ComponentFixture<EditdeletecommunityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditdeletecommunityComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditdeletecommunityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
