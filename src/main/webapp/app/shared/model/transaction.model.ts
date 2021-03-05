import { Moment } from 'moment';
import { IObligation } from 'app/shared/model/obligation.model';

export interface ITransaction {
  id?: number;
  trnNumber?: string;
  trnDate?: Moment;
  trnSystem?: string;
  trnAmount?: number;
  trnCreationDate?: Moment;
  trnSysDate?: Moment;
  trnCreatedBy?: string;
  trnType?: string;
  trnCode?: string;
  trnStatus?: string;
  trnDescription?: string;
  trnPaymentType?: string;
  obligation?: IObligation;
}

export class Transaction implements ITransaction {
  constructor(
    public id?: number,
    public trnNumber?: string,
    public trnDate?: Moment,
    public trnSystem?: string,
    public trnAmount?: number,
    public trnCreationDate?: Moment,
    public trnSysDate?: Moment,
    public trnCreatedBy?: string,
    public trnType?: string,
    public trnCode?: string,
    public trnStatus?: string,
    public trnDescription?: string,
    public trnPaymentType?: string,
    public obligation?: IObligation
  ) {}
}
