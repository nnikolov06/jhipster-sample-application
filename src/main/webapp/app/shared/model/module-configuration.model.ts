export interface IModuleConfiguration {
  id?: number;
  mocName?: string;
}

export class ModuleConfiguration implements IModuleConfiguration {
  constructor(public id?: number, public mocName?: string) {}
}
