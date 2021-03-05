import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { EmergencyProcedureService } from 'app/entities/emergency-procedure/emergency-procedure.service';
import { IEmergencyProcedure, EmergencyProcedure } from 'app/shared/model/emergency-procedure.model';

describe('Service Tests', () => {
  describe('EmergencyProcedure Service', () => {
    let injector: TestBed;
    let service: EmergencyProcedureService;
    let httpMock: HttpTestingController;
    let elemDefault: IEmergencyProcedure;
    let expectedResult: IEmergencyProcedure | IEmergencyProcedure[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(EmergencyProcedureService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new EmergencyProcedure(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', false, currentDate, currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            empDateStart: currentDate.format(DATE_TIME_FORMAT),
            empDateEnd: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a EmergencyProcedure', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            empDateStart: currentDate.format(DATE_TIME_FORMAT),
            empDateEnd: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            empDateStart: currentDate,
            empDateEnd: currentDate,
          },
          returnedFromService
        );

        service.create(new EmergencyProcedure()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EmergencyProcedure', () => {
        const returnedFromService = Object.assign(
          {
            empSystem: 'BBBBBB',
            empReason: 'BBBBBB',
            empUser: 'BBBBBB',
            empIsActive: true,
            empDateStart: currentDate.format(DATE_TIME_FORMAT),
            empDateEnd: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            empDateStart: currentDate,
            empDateEnd: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EmergencyProcedure', () => {
        const returnedFromService = Object.assign(
          {
            empSystem: 'BBBBBB',
            empReason: 'BBBBBB',
            empUser: 'BBBBBB',
            empIsActive: true,
            empDateStart: currentDate.format(DATE_TIME_FORMAT),
            empDateEnd: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            empDateStart: currentDate,
            empDateEnd: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a EmergencyProcedure', () => {
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
