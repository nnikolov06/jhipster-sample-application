import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { CertificateEmergencyTextDetailComponent } from 'app/entities/certificate-emergency-text/certificate-emergency-text-detail.component';
import { CertificateEmergencyText } from 'app/shared/model/certificate-emergency-text.model';

describe('Component Tests', () => {
  describe('CertificateEmergencyText Management Detail Component', () => {
    let comp: CertificateEmergencyTextDetailComponent;
    let fixture: ComponentFixture<CertificateEmergencyTextDetailComponent>;
    const route = ({ data: of({ certificateEmergencyText: new CertificateEmergencyText(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [CertificateEmergencyTextDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CertificateEmergencyTextDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CertificateEmergencyTextDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load certificateEmergencyText on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.certificateEmergencyText).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
