import { TestBed } from '@angular/core/testing';

import { NewVotingService } from './new-voting.service';

describe('NewVotingService', () => {
  let service: NewVotingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NewVotingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
