import { TestBed } from '@angular/core/testing';

import { OpenGuardService } from './open-guard.service';

describe('OpenGuardService', () => {
  let service: OpenGuardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OpenGuardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
