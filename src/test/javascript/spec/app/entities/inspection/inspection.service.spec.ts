import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { InspectionService } from 'app/entities/inspection/inspection.service';
import { IInspection, Inspection } from 'app/shared/model/inspection.model';

describe('Service Tests', () => {
  describe('Inspection Service', () => {
    let injector: TestBed;
    let service: InspectionService;
    let httpMock: HttpTestingController;
    let elemDefault: IInspection;
    let expectedResult: IInspection | IInspection[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(InspectionService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Inspection(0, 'AAAAAAA', currentDate, 'AAAAAAA', 'AAAAAAA', currentDate, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            insDate: currentDate.format(DATE_TIME_FORMAT),
            insSysDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Inspection', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            insDate: currentDate.format(DATE_TIME_FORMAT),
            insSysDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            insDate: currentDate,
            insSysDate: currentDate,
          },
          returnedFromService
        );

        service.create(new Inspection()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Inspection', () => {
        const returnedFromService = Object.assign(
          {
            insNumber: 'BBBBBB',
            insDate: currentDate.format(DATE_TIME_FORMAT),
            insCustomsOffice: 'BBBBBB',
            insUser: 'BBBBBB',
            insSysDate: currentDate.format(DATE_TIME_FORMAT),
            insType: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            insDate: currentDate,
            insSysDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Inspection', () => {
        const returnedFromService = Object.assign(
          {
            insNumber: 'BBBBBB',
            insDate: currentDate.format(DATE_TIME_FORMAT),
            insCustomsOffice: 'BBBBBB',
            insUser: 'BBBBBB',
            insSysDate: currentDate.format(DATE_TIME_FORMAT),
            insType: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            insDate: currentDate,
            insSysDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Inspection', () => {
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
