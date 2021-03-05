import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ObligationService } from 'app/entities/obligation/obligation.service';
import { IObligation, Obligation } from 'app/shared/model/obligation.model';

describe('Service Tests', () => {
  describe('Obligation Service', () => {
    let injector: TestBed;
    let service: ObligationService;
    let httpMock: HttpTestingController;
    let elemDefault: IObligation;
    let expectedResult: IObligation | IObligation[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ObligationService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Obligation(
        0,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        false,
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            oblSysDate: currentDate.format(DATE_TIME_FORMAT),
            oblMraDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Obligation', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            oblSysDate: currentDate.format(DATE_TIME_FORMAT),
            oblMraDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            oblSysDate: currentDate,
            oblMraDate: currentDate,
          },
          returnedFromService
        );

        service.create(new Obligation()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Obligation', () => {
        const returnedFromService = Object.assign(
          {
            oblSysDate: currentDate.format(DATE_TIME_FORMAT),
            oblCreatedBy: 'BBBBBB',
            oblCustomsOffice: 'BBBBBB',
            oblType: 'BBBBBB',
            oblAmount: 1,
            oblLapsed: true,
            oblVpoNumber: 'BBBBBB',
            oblTransactionNumber: 'BBBBBB',
            oblMraDate: currentDate.format(DATE_TIME_FORMAT),
            oblState: 'BBBBBB',
            oblIdentifier: 'BBBBBB',
            oblAmountDifference: 1,
            oblGuarantyDifference: 1,
            oblCustomsOfficeName: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            oblSysDate: currentDate,
            oblMraDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Obligation', () => {
        const returnedFromService = Object.assign(
          {
            oblSysDate: currentDate.format(DATE_TIME_FORMAT),
            oblCreatedBy: 'BBBBBB',
            oblCustomsOffice: 'BBBBBB',
            oblType: 'BBBBBB',
            oblAmount: 1,
            oblLapsed: true,
            oblVpoNumber: 'BBBBBB',
            oblTransactionNumber: 'BBBBBB',
            oblMraDate: currentDate.format(DATE_TIME_FORMAT),
            oblState: 'BBBBBB',
            oblIdentifier: 'BBBBBB',
            oblAmountDifference: 1,
            oblGuarantyDifference: 1,
            oblCustomsOfficeName: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            oblSysDate: currentDate,
            oblMraDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Obligation', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
