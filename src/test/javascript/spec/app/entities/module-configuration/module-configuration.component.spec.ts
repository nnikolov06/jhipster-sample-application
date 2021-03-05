import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ModuleConfigurationComponent } from 'app/entities/module-configuration/module-configuration.component';
import { ModuleConfigurationService } from 'app/entities/module-configuration/module-configuration.service';
import { ModuleConfiguration } from 'app/shared/model/module-configuration.model';

describe('Component Tests', () => {
  describe('ModuleConfiguration Management Component', () => {
    let comp: ModuleConfigurationComponent;
    let fixture: ComponentFixture<ModuleConfigurationComponent>;
    let service: ModuleConfigurationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [ModuleConfigurationComponent],
      })
        .overrideTemplate(ModuleConfigurationComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ModuleConfigurationComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ModuleConfigurationService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ModuleConfiguration(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.moduleConfigurations && comp.moduleConfigurations[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
