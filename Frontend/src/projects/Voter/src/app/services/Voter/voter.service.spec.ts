import { TestBed } from '@angular/core/testing';

import { VoterService } from './voter.service';

describe('VotigDataService', () => {
  let service: VoterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VoterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
