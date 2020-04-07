import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KniggeComponent } from './knigge.component';

describe('KniggeComponent', () => {
  let component: KniggeComponent;
  let fixture: ComponentFixture<KniggeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KniggeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KniggeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
