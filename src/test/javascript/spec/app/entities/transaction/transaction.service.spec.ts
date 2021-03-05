import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { TransactionService } from 'app/entities/transaction/transaction.service';
import { ITransaction, Transaction } from 'app/shared/model/transaction.model';

describe('Service Tests', () => {
  describe('Transaction Service', () => {
    let injector: TestBed;
    let service: TransactionService;
    let httpMock: HttpTestingController;
    let elemDefault: ITransaction;
    let expectedResult: ITransaction | ITransaction[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TransactionService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Transaction(
        0,
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        0,
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            trnDate: currentDate.format(DATE_TIME_FORMAT),
            trnCreationDate: currentDate.format(DATE_TIME_FORMAT),
            trnSysDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Transaction', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            trnDate: currentDate.format(DATE_TIME_FORMAT),
            trnCreationDate: currentDate.format(DATE_TIME_FORMAT),
            trnSysDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            trnDate: currentDate,
            trnCreationDate: currentDate,
            trnSysDate: currentDate,
          },
          returnedFromService
        );

        service.create(new Transaction()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Transaction', () => {
        const returnedFromService = Object.assign(
          {
            trnNumber: 'BBBBBB',
            trnDate: currentDate.format(DATE_TIME_FORMAT),
            trnSystem: 'BBBBBB',
            trnAmount: 1,
            trnCreationDate: currentDate.format(DATE_TIME_FORMAT),
            trnSysDate: currentDate.format(DATE_TIME_FORMAT),
            trnCreatedBy: 'BBBBBB',
            trnType: 'BBBBBB',
            trnCode: 'BBBBBB',
            trnStatus: 'BBBBBB',
            trnDescription: 'BBBBBB',
            trnPaymentType: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            trnDate: currentDate,
            trnCreationDate: currentDate,
            trnSysDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Transaction', () => {
        const returnedFromService = Object.assign(
          {
            trnNumber: 'BBBBBB',
            trnDate: currentDate.format(DATE_TIME_FORMAT),
            trnSystem: 'BBBBBB',
            trnAmount: 1,
            trnCreationDate: currentDate.format(DATE_TIME_FORMAT),
            trnSysDate: currentDate.format(DATE_TIME_FORMAT),
            trnCreatedBy: 'BBBBBB',
            trnType: 'BBBBBB',
            trnCode: 'BBBBBB',
            trnStatus: 'BBBBBB',
            trnDescription: 'BBBBBB',
            trnPaymentType: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            trnDate: currentDate,
            trnCreationDate: currentDate,
            trnSysDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Transaction', () => {
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
