import { TestBed } from '@angular/core/testing';

import { CandidateService } from './candidate.service';

describe('VotigDataService', () => {
  let service: CandidateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CandidateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
