import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { CertificateEmergencyTextUpdateComponent } from 'app/entities/certificate-emergency-text/certificate-emergency-text-update.component';
import { CertificateEmergencyTextService } from 'app/entities/certificate-emergency-text/certificate-emergency-text.service';
import { CertificateEmergencyText } from 'app/shared/model/certificate-emergency-text.model';

describe('Component Tests', () => {
  describe('CertificateEmergencyText Management Update Component', () => {
    let comp: CertificateEmergencyTextUpdateComponent;
    let fixture: ComponentFixture<CertificateEmergencyTextUpdateComponent>;
    let service: CertificateEmergencyTextService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [CertificateEmergencyTextUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CertificateEmergencyTextUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CertificateEmergencyTextUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CertificateEmergencyTextService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CertificateEmergencyText(123);
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
        const entity = new CertificateEmergencyText();
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
