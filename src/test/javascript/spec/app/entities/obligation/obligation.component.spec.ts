import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ObligationComponent } from 'app/entities/obligation/obligation.component';
import { ObligationService } from 'app/entities/obligation/obligation.service';
import { Obligation } from 'app/shared/model/obligation.model';

describe('Component Tests', () => {
  describe('Obligation Management Component', () => {
    let comp: ObligationComponent;
    let fixture: ComponentFixture<ObligationComponent>;
    let service: ObligationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [ObligationComponent],
      })
        .overrideTemplate(ObligationComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ObligationComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ObligationService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Obligation(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.obligations && comp.obligations[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
