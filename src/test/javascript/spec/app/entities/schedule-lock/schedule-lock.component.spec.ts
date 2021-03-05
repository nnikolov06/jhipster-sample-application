import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ScheduleLockComponent } from 'app/entities/schedule-lock/schedule-lock.component';
import { ScheduleLockService } from 'app/entities/schedule-lock/schedule-lock.service';
import { ScheduleLock } from 'app/shared/model/schedule-lock.model';

describe('Component Tests', () => {
  describe('ScheduleLock Management Component', () => {
    let comp: ScheduleLockComponent;
    let fixture: ComponentFixture<ScheduleLockComponent>;
    let service: ScheduleLockService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [ScheduleLockComponent],
      })
        .overrideTemplate(ScheduleLockComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ScheduleLockComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ScheduleLockService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ScheduleLock(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.scheduleLocks && comp.scheduleLocks[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
