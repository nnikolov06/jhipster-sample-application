import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT, DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { DocumentService } from 'app/entities/document/document.service';
import { IDocument, Document } from 'app/shared/model/document.model';

describe('Service Tests', () => {
  describe('Document Service', () => {
    let injector: TestBed;
    let service: DocumentService;
    let httpMock: HttpTestingController;
    let elemDefault: IDocument;
    let expectedResult: IDocument | IDocument[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(DocumentService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Document(
        0,
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        false,
        false,
        false,
        currentDate,
        currentDate,
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            docDate: currentDate.format(DATE_FORMAT),
            docSysDate: currentDate.format(DATE_TIME_FORMAT),
            docLastChangeDate: currentDate.format(DATE_TIME_FORMAT),
            docIntoForceDate: currentDate.format(DATE_FORMAT),
            docDeferredDate: currentDate.format(DATE_FORMAT),
            docRescheduledDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Document', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            docDate: currentDate.format(DATE_FORMAT),
            docSysDate: currentDate.format(DATE_TIME_FORMAT),
            docLastChangeDate: currentDate.format(DATE_TIME_FORMAT),
            docIntoForceDate: currentDate.format(DATE_FORMAT),
            docDeferredDate: currentDate.format(DATE_FORMAT),
            docRescheduledDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            docDate: currentDate,
            docSysDate: currentDate,
            docLastChangeDate: currentDate,
            docIntoForceDate: currentDate,
            docDeferredDate: currentDate,
            docRescheduledDate: currentDate,
          },
          returnedFromService
        );

        service.create(new Document()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Document', () => {
        const returnedFromService = Object.assign(
          {
            docNumber: 'BBBBBB',
            docDate: currentDate.format(DATE_FORMAT),
            docDescription: 'BBBBBB',
            docType: 'BBBBBB',
            docSysDate: currentDate.format(DATE_TIME_FORMAT),
            docCreatedBy: 'BBBBBB',
            docMrn: 'BBBBBB',
            docStatus: 'BBBBBB',
            docLastChangeDate: currentDate.format(DATE_TIME_FORMAT),
            docIntoForce: true,
            docDeferred: true,
            docRescheduled: true,
            docIntoForceDate: currentDate.format(DATE_FORMAT),
            docDeferredDate: currentDate.format(DATE_FORMAT),
            docRescheduledDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            docDate: currentDate,
            docSysDate: currentDate,
            docLastChangeDate: currentDate,
            docIntoForceDate: currentDate,
            docDeferredDate: currentDate,
            docRescheduledDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Document', () => {
        const returnedFromService = Object.assign(
          {
            docNumber: 'BBBBBB',
            docDate: currentDate.format(DATE_FORMAT),
            docDescription: 'BBBBBB',
            docType: 'BBBBBB',
            docSysDate: currentDate.format(DATE_TIME_FORMAT),
            docCreatedBy: 'BBBBBB',
            docMrn: 'BBBBBB',
            docStatus: 'BBBBBB',
            docLastChangeDate: currentDate.format(DATE_TIME_FORMAT),
            docIntoForce: true,
            docDeferred: true,
            docRescheduled: true,
            docIntoForceDate: currentDate.format(DATE_FORMAT),
            docDeferredDate: currentDate.format(DATE_FORMAT),
            docRescheduledDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            docDate: currentDate,
            docSysDate: currentDate,
            docLastChangeDate: currentDate,
            docIntoForceDate: currentDate,
            docDeferredDate: currentDate,
            docRescheduledDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Document', () => {
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
