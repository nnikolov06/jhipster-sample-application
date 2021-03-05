import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ScheduleLockUpdateComponent } from 'app/entities/schedule-lock/schedule-lock-update.component';
import { ScheduleLockService } from 'app/entities/schedule-lock/schedule-lock.service';
import { ScheduleLock } from 'app/shared/model/schedule-lock.model';

describe('Component Tests', () => {
  describe('ScheduleLock Management Update Component', () => {
    let comp: ScheduleLockUpdateComponent;
    let fixture: ComponentFixture<ScheduleLockUpdateComponent>;
    let service: ScheduleLockService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [ScheduleLockUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ScheduleLockUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ScheduleLockUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ScheduleLockService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ScheduleLock(123);
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
        const entity = new ScheduleLock();
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
