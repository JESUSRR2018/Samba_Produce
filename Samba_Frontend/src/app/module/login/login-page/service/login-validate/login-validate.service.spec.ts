import { TestBed } from '@angular/core/testing';

import { LoginValidateService } from './login-validate.service';

describe('LoginValidateService', () => {
  let service: LoginValidateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LoginValidateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
