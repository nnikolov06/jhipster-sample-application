import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { EmergencyProcedureUpdateComponent } from 'app/entities/emergency-procedure/emergency-procedure-update.component';
import { EmergencyProcedureService } from 'app/entities/emergency-procedure/emergency-procedure.service';
import { EmergencyProcedure } from 'app/shared/model/emergency-procedure.model';

describe('Component Tests', () => {
  describe('EmergencyProcedure Management Update Component', () => {
    let comp: EmergencyProcedureUpdateComponent;
    let fixture: ComponentFixture<EmergencyProcedureUpdateComponent>;
    let service: EmergencyProcedureService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [EmergencyProcedureUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(EmergencyProcedureUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EmergencyProcedureUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EmergencyProcedureService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EmergencyProcedure(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new EmergencyProcedure();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
