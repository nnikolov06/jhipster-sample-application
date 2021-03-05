import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ModuleConfigurationUpdateComponent } from 'app/entities/module-configuration/module-configuration-update.component';
import { ModuleConfigurationService } from 'app/entities/module-configuration/module-configuration.service';
import { ModuleConfiguration } from 'app/shared/model/module-configuration.model';

describe('Component Tests', () => {
  describe('ModuleConfiguration Management Update Component', () => {
    let comp: ModuleConfigurationUpdateComponent;
    let fixture: ComponentFixture<ModuleConfigurationUpdateComponent>;
    let service: ModuleConfigurationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [ModuleConfigurationUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ModuleConfigurationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ModuleConfigurationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ModuleConfigurationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ModuleConfiguration(123);
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
        const entity = new ModuleConfiguration();
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
