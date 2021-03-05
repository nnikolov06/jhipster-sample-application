import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TransactionComponent } from 'app/entities/transaction/transaction.component';
import { TransactionService } from 'app/entities/transaction/transaction.service';
import { Transaction } from 'app/shared/model/transaction.model';

describe('Component Tests', () => {
  describe('Transaction Management Component', () => {
    let comp: TransactionComponent;
    let fixture: ComponentFixture<TransactionComponent>;
    let service: TransactionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [TransactionComponent],
      })
        .overrideTemplate(TransactionComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TransactionComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TransactionService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Transaction(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.transactions && comp.transactions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
