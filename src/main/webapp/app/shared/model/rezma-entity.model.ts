import { Moment } from 'moment';
import { IObligation } from 'app/shared/model/obligation.model';
import { IInspection } from 'app/shared/model/inspection.model';

export interface IRezmaEntity {
  id?: number;
  entName?: string;
  entAddress?: string;
  entSystemDate?: Moment;
  entCreatedBy?: string;
  entIdentifier?: string;
  entType?: string;
  entCountry?: string;
  entIdentifierType?: string;
  entTin?: string;
  entDOB?: string;
  obligations?: IObligation[];
  inspections?: IInspection[];
}

export class RezmaEntity implements IRezmaEntity {
  constructor(
    public id?: number,
    public entName?: string,
    public entAddress?: string,
    public entSystemDate?: Moment,
    public entCreatedBy?: string,
    public entIdentifier?: string,
    public entType?: string,
    public entCountry?: string,
    public entIdentifierType?: string,
    public entTin?: string,
    public entDOB?: string,
    public obligations?: IObligation[],
    public inspections?: IInspection[]
  ) {}
}
