import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ScheduleLockDetailComponent } from 'app/entities/schedule-lock/schedule-lock-detail.component';
import { ScheduleLock } from 'app/shared/model/schedule-lock.model';

describe('Component Tests', () => {
  describe('ScheduleLock Management Detail Component', () => {
    let comp: ScheduleLockDetailComponent;
    let fixture: ComponentFixture<ScheduleLockDetailComponent>;
    const route = ({ data: of({ scheduleLock: new ScheduleLock(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [ScheduleLockDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ScheduleLockDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ScheduleLockDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load scheduleLock on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.scheduleLock).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
