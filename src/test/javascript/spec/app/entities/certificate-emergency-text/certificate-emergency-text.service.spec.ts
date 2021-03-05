import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { CertificateEmergencyTextService } from 'app/entities/certificate-emergency-text/certificate-emergency-text.service';
import { ICertificateEmergencyText, CertificateEmergencyText } from 'app/shared/model/certificate-emergency-text.model';

describe('Service Tests', () => {
  describe('CertificateEmergencyText Service', () => {
    let injector: TestBed;
    let service: CertificateEmergencyTextService;
    let httpMock: HttpTestingController;
    let elemDefault: ICertificateEmergencyText;
    let expectedResult: ICertificateEmergencyText | ICertificateEmergencyText[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CertificateEmergencyTextService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new CertificateEmergencyText(0, 'AAAAAAA', false);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a CertificateEmergencyText', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new CertificateEmergencyText()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a CertificateEmergencyText', () => {
        const returnedFromService = Object.assign(
          {
            cetText: 'BBBBBB',
            cetIsActive: true,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of CertificateEmergencyText', () => {
        const returnedFromService = Object.assign(
          {
            cetText: 'BBBBBB',
            cetIsActive: true,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a CertificateEmergencyText', () => {
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
