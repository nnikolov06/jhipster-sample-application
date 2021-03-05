import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { CertificateEmergencyTextComponent } from 'app/entities/certificate-emergency-text/certificate-emergency-text.component';
import { CertificateEmergencyTextService } from 'app/entities/certificate-emergency-text/certificate-emergency-text.service';
import { CertificateEmergencyText } from 'app/shared/model/certificate-emergency-text.model';

describe('Component Tests', () => {
  describe('CertificateEmergencyText Management Component', () => {
    let comp: CertificateEmergencyTextComponent;
    let fixture: ComponentFixture<CertificateEmergencyTextComponent>;
    let service: CertificateEmergencyTextService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [CertificateEmergencyTextComponent],
      })
        .overrideTemplate(CertificateEmergencyTextComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CertificateEmergencyTextComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CertificateEmergencyTextService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CertificateEmergencyText(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.certificateEmergencyTexts && comp.certificateEmergencyTexts[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
