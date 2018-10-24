import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListContactComponent } from './list-contact.component';

describe('ListContactComponent', () => {
  let component: ListContactComponent;
  let fixture: ComponentFixture<ListContactComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListContactComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListContactComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
