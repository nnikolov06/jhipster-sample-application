import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT, DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { CertificateService } from 'app/entities/certificate/certificate.service';
import { ICertificate, Certificate } from 'app/shared/model/certificate.model';

describe('Service Tests', () => {
  describe('Certificate Service', () => {
    let injector: TestBed;
    let service: CertificateService;
    let httpMock: HttpTestingController;
    let elemDefault: ICertificate;
    let expectedResult: ICertificate | ICertificate[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CertificateService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Certificate(
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
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        false,
        'AAAAAAA',
        false,
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            cerSysDate: currentDate.format(DATE_TIME_FORMAT),
            cerRequestDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Certificate', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            cerSysDate: currentDate.format(DATE_TIME_FORMAT),
            cerRequestDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            cerSysDate: currentDate,
            cerRequestDate: currentDate,
          },
          returnedFromService
        );

        service.create(new Certificate()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Certificate', () => {
        const returnedFromService = Object.assign(
          {
            cerNumber: 'BBBBBB',
            cerDate: 'BBBBBB',
            cerSysDate: currentDate.format(DATE_TIME_FORMAT),
            cerCreatedBy: 'BBBBBB',
            cerFileName: 'BBBBBB',
            cerServiceApplicant: 'BBBBBB',
            cerSigningPerson: 'BBBBBB',
            cerSigningPersonPosition: 'BBBBBB',
            cerCertificateType: 'BBBBBB',
            cerCheckBoxValues: 'BBBBBB',
            cerRequestNumber: 'BBBBBB',
            cerRequestDate: currentDate.format(DATE_FORMAT),
            cerIsDeactivated: true,
            cerState: 'BBBBBB',
            cerHasObligation: true,
            cerDocumentState: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            cerSysDate: currentDate,
            cerRequestDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Certificate', () => {
        const returnedFromService = Object.assign(
          {
            cerNumber: 'BBBBBB',
            cerDate: 'BBBBBB',
            cerSysDate: currentDate.format(DATE_TIME_FORMAT),
            cerCreatedBy: 'BBBBBB',
            cerFileName: 'BBBBBB',
            cerServiceApplicant: 'BBBBBB',
            cerSigningPerson: 'BBBBBB',
            cerSigningPersonPosition: 'BBBBBB',
            cerCertificateType: 'BBBBBB',
            cerCheckBoxValues: 'BBBBBB',
            cerRequestNumber: 'BBBBBB',
            cerRequestDate: currentDate.format(DATE_FORMAT),
            cerIsDeactivated: true,
            cerState: 'BBBBBB',
            cerHasObligation: true,
            cerDocumentState: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            cerSysDate: currentDate,
            cerRequestDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Certificate', () => {
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
