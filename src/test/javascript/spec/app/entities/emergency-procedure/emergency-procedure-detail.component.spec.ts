import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { EmergencyProcedureDetailComponent } from 'app/entities/emergency-procedure/emergency-procedure-detail.component';
import { EmergencyProcedure } from 'app/shared/model/emergency-procedure.model';

describe('Component Tests', () => {
  describe('EmergencyProcedure Management Detail Component', () => {
    let comp: EmergencyProcedureDetailComponent;
    let fixture: ComponentFixture<EmergencyProcedureDetailComponent>;
    const route = ({ data: of({ emergencyProcedure: new EmergencyProcedure(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [EmergencyProcedureDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(EmergencyProcedureDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EmergencyProcedureDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load emergencyProcedure on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.emergencyProcedure).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
