import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { EmergencyProcedureComponent } from 'app/entities/emergency-procedure/emergency-procedure.component';
import { EmergencyProcedureService } from 'app/entities/emergency-procedure/emergency-procedure.service';
import { EmergencyProcedure } from 'app/shared/model/emergency-procedure.model';

describe('Component Tests', () => {
  describe('EmergencyProcedure Management Component', () => {
    let comp: EmergencyProcedureComponent;
    let fixture: ComponentFixture<EmergencyProcedureComponent>;
    let service: EmergencyProcedureService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [EmergencyProcedureComponent],
      })
        .overrideTemplate(EmergencyProcedureComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EmergencyProcedureComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EmergencyProcedureService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EmergencyProcedure(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.emergencyProcedures && comp.emergencyProcedures[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
