import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { RezmaEntityComponent } from 'app/entities/rezma-entity/rezma-entity.component';
import { RezmaEntityService } from 'app/entities/rezma-entity/rezma-entity.service';
import { RezmaEntity } from 'app/shared/model/rezma-entity.model';

describe('Component Tests', () => {
  describe('RezmaEntity Management Component', () => {
    let comp: RezmaEntityComponent;
    let fixture: ComponentFixture<RezmaEntityComponent>;
    let service: RezmaEntityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [RezmaEntityComponent],
      })
        .overrideTemplate(RezmaEntityComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RezmaEntityComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RezmaEntityService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new RezmaEntity(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.rezmaEntities && comp.rezmaEntities[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
