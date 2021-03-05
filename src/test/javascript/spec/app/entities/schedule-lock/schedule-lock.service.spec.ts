import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ScheduleLockService } from 'app/entities/schedule-lock/schedule-lock.service';
import { IScheduleLock, ScheduleLock } from 'app/shared/model/schedule-lock.model';

describe('Service Tests', () => {
  describe('ScheduleLock Service', () => {
    let injector: TestBed;
    let service: ScheduleLockService;
    let httpMock: HttpTestingController;
    let elemDefault: IScheduleLock;
    let expectedResult: IScheduleLock | IScheduleLock[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ScheduleLockService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new ScheduleLock(0, 'AAAAAAA', currentDate, currentDate, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            slcLockUntil: currentDate.format(DATE_TIME_FORMAT),
            slcLockedAt: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a ScheduleLock', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            slcLockUntil: currentDate.format(DATE_TIME_FORMAT),
            slcLockedAt: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            slcLockUntil: currentDate,
            slcLockedAt: currentDate,
          },
          returnedFromService
        );

        service.create(new ScheduleLock()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ScheduleLock', () => {
        const returnedFromService = Object.assign(
          {
            slcName: 'BBBBBB',
            slcLockUntil: currentDate.format(DATE_TIME_FORMAT),
            slcLockedAt: currentDate.format(DATE_TIME_FORMAT),
            slcLockedBy: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            slcLockUntil: currentDate,
            slcLockedAt: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ScheduleLock', () => {
        const returnedFromService = Object.assign(
          {
            slcName: 'BBBBBB',
            slcLockUntil: currentDate.format(DATE_TIME_FORMAT),
            slcLockedAt: currentDate.format(DATE_TIME_FORMAT),
            slcLockedBy: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            slcLockUntil: currentDate,
            slcLockedAt: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a ScheduleLock', () => {
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
