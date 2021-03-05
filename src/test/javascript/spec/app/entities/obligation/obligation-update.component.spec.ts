import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ObligationUpdateComponent } from 'app/entities/obligation/obligation-update.component';
import { ObligationService } from 'app/entities/obligation/obligation.service';
import { Obligation } from 'app/shared/model/obligation.model';

describe('Component Tests', () => {
  describe('Obligation Management Update Component', () => {
    let comp: ObligationUpdateComponent;
    let fixture: ComponentFixture<ObligationUpdateComponent>;
    let service: ObligationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [ObligationUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ObligationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ObligationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ObligationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Obligation(123);
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
        const entity = new Obligation();
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
