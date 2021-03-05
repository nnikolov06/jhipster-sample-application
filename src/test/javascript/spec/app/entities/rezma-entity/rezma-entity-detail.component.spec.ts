import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { RezmaEntityDetailComponent } from 'app/entities/rezma-entity/rezma-entity-detail.component';
import { RezmaEntity } from 'app/shared/model/rezma-entity.model';

describe('Component Tests', () => {
  describe('RezmaEntity Management Detail Component', () => {
    let comp: RezmaEntityDetailComponent;
    let fixture: ComponentFixture<RezmaEntityDetailComponent>;
    const route = ({ data: of({ rezmaEntity: new RezmaEntity(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [RezmaEntityDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(RezmaEntityDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RezmaEntityDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load rezmaEntity on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.rezmaEntity).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
