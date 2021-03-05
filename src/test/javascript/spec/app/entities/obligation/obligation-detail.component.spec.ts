import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ObligationDetailComponent } from 'app/entities/obligation/obligation-detail.component';
import { Obligation } from 'app/shared/model/obligation.model';

describe('Component Tests', () => {
  describe('Obligation Management Detail Component', () => {
    let comp: ObligationDetailComponent;
    let fixture: ComponentFixture<ObligationDetailComponent>;
    const route = ({ data: of({ obligation: new Obligation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [ObligationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ObligationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ObligationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load obligation on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.obligation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
