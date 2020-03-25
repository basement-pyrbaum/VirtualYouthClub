import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViedeoComponent } from './viedeo.component';

describe('ViedeoComponent', () => {
  let component: ViedeoComponent;
  let fixture: ComponentFixture<ViedeoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViedeoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViedeoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
