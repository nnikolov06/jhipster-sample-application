import { IModuleConfiguration } from 'app/shared/model/module-configuration.model';

export interface IConfig {
  id?: number;
  cfgSection?: string;
  cfgEnvironmnent?: string;
  cfgItem?: string;
  cfgValue?: string;
  moduleConfiguration?: IModuleConfiguration;
}

export class Config implements IConfig {
  constructor(
    public id?: number,
    public cfgSection?: string,
    public cfgEnvironmnent?: string,
    public cfgItem?: string,
    public cfgValue?: string,
    public moduleConfiguration?: IModuleConfiguration
  ) {}
}
