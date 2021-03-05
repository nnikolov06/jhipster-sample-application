import { Moment } from 'moment';

export interface IEmergencyProcedure {
  id?: number;
  empSystem?: string;
  empReason?: string;
  empUser?: string;
  empIsActive?: boolean;
  empDateStart?: Moment;
  empDateEnd?: Moment;
}

export class EmergencyProcedure implements IEmergencyProcedure {
  constructor(
    public id?: number,
    public empSystem?: string,
    public empReason?: string,
    public empUser?: string,
    public empIsActive?: boolean,
    public empDateStart?: Moment,
    public empDateEnd?: Moment
  ) {
    this.empIsActive = this.empIsActive || false;
  }
}
