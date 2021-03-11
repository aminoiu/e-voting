import { TestBed } from '@angular/core/testing';

import { VotigDataService } from './votig-data.service';

describe('VotigDataService', () => {
  let service: VotigDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VotigDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
