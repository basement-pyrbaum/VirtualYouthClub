import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KniggeTextComponent } from './knigge-text.component';

describe('KniggeTextComponent', () => {
  let component: KniggeTextComponent;
  let fixture: ComponentFixture<KniggeTextComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KniggeTextComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KniggeTextComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
