import { Moment } from 'moment';
import { IDocument } from 'app/shared/model/document.model';
import { IRezmaEntity } from 'app/shared/model/rezma-entity.model';

export interface IObligation {
  id?: number;
  oblSysDate?: Moment;
  oblCreatedBy?: string;
  oblCustomsOffice?: string;
  oblType?: string;
  oblAmount?: number;
  oblLapsed?: boolean;
  oblVpoNumber?: string;
  oblTransactionNumber?: string;
  oblMraDate?: Moment;
  oblState?: string;
  oblIdentifier?: string;
  oblAmountDifference?: number;
  oblGuarantyDifference?: number;
  oblCustomsOfficeName?: string;
  document?: IDocument;
  rezmaEntities?: IRezmaEntity[];
}

export class Obligation implements IObligation {
  constructor(
    public id?: number,
    public oblSysDate?: Moment,
    public oblCreatedBy?: string,
    public oblCustomsOffice?: string,
    public oblType?: string,
    public oblAmount?: number,
    public oblLapsed?: boolean,
    public oblVpoNumber?: string,
    public oblTransactionNumber?: string,
    public oblMraDate?: Moment,
    public oblState?: string,
    public oblIdentifier?: string,
    public oblAmountDifference?: number,
    public oblGuarantyDifference?: number,
    public oblCustomsOfficeName?: string,
    public document?: IDocument,
    public rezmaEntities?: IRezmaEntity[]
  ) {
    this.oblLapsed = this.oblLapsed || false;
  }
}
