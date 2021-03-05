import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { RezmaEntityService } from 'app/entities/rezma-entity/rezma-entity.service';
import { IRezmaEntity, RezmaEntity } from 'app/shared/model/rezma-entity.model';

describe('Service Tests', () => {
  describe('RezmaEntity Service', () => {
    let injector: TestBed;
    let service: RezmaEntityService;
    let httpMock: HttpTestingController;
    let elemDefault: IRezmaEntity;
    let expectedResult: IRezmaEntity | IRezmaEntity[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(RezmaEntityService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new RezmaEntity(
        0,
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
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
            entSystemDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a RezmaEntity', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            entSystemDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            entSystemDate: currentDate,
          },
          returnedFromService
        );

        service.create(new RezmaEntity()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a RezmaEntity', () => {
        const returnedFromService = Object.assign(
          {
            entName: 'BBBBBB',
            entAddress: 'BBBBBB',
            entSystemDate: currentDate.format(DATE_TIME_FORMAT),
            entCreatedBy: 'BBBBBB',
            entIdentifier: 'BBBBBB',
            entType: 'BBBBBB',
            entCountry: 'BBBBBB',
            entIdentifierType: 'BBBBBB',
            entTin: 'BBBBBB',
            entDOB: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            entSystemDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of RezmaEntity', () => {
        const returnedFromService = Object.assign(
          {
            entName: 'BBBBBB',
            entAddress: 'BBBBBB',
            entSystemDate: currentDate.format(DATE_TIME_FORMAT),
            entCreatedBy: 'BBBBBB',
            entIdentifier: 'BBBBBB',
            entType: 'BBBBBB',
            entCountry: 'BBBBBB',
            entIdentifierType: 'BBBBBB',
            entTin: 'BBBBBB',
            entDOB: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            entSystemDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a RezmaEntity', () => {
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
