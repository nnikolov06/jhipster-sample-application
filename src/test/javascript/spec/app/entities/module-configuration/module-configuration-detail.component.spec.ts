import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ModuleConfigurationDetailComponent } from 'app/entities/module-configuration/module-configuration-detail.component';
import { ModuleConfiguration } from 'app/shared/model/module-configuration.model';

describe('Component Tests', () => {
  describe('ModuleConfiguration Management Detail Component', () => {
    let comp: ModuleConfigurationDetailComponent;
    let fixture: ComponentFixture<ModuleConfigurationDetailComponent>;
    const route = ({ data: of({ moduleConfiguration: new ModuleConfiguration(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [ModuleConfigurationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ModuleConfigurationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ModuleConfigurationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load moduleConfiguration on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.moduleConfiguration).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
