import { Moment } from 'moment';
import { IRezmaEntity } from 'app/shared/model/rezma-entity.model';

export interface IInspection {
  id?: number;
  insNumber?: string;
  insDate?: Moment;
  insCustomsOffice?: string;
  insUser?: string;
  insSysDate?: Moment;
  insType?: string;
  rezmaEntities?: IRezmaEntity[];
}

export class Inspection implements IInspection {
  constructor(
    public id?: number,
    public insNumber?: string,
    public insDate?: Moment,
    public insCustomsOffice?: string,
    public insUser?: string,
    public insSysDate?: Moment,
    public insType?: string,
    public rezmaEntities?: IRezmaEntity[]
  ) {}
}
