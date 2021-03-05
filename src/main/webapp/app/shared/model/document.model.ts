import { Moment } from 'moment';

export interface IDocument {
  id?: number;
  docNumber?: string;
  docDate?: Moment;
  docDescription?: string;
  docType?: string;
  docSysDate?: Moment;
  docCreatedBy?: string;
  docMrn?: string;
  docStatus?: string;
  docLastChangeDate?: Moment;
  docIntoForce?: boolean;
  docDeferred?: boolean;
  docRescheduled?: boolean;
  docIntoForceDate?: Moment;
  docDeferredDate?: Moment;
  docRescheduledDate?: Moment;
}

export class Document implements IDocument {
  constructor(
    public id?: number,
    public docNumber?: string,
    public docDate?: Moment,
    public docDescription?: string,
    public docType?: string,
    public docSysDate?: Moment,
    public docCreatedBy?: string,
    public docMrn?: string,
    public docStatus?: string,
    public docLastChangeDate?: Moment,
    public docIntoForce?: boolean,
    public docDeferred?: boolean,
    public docRescheduled?: boolean,
    public docIntoForceDate?: Moment,
    public docDeferredDate?: Moment,
    public docRescheduledDate?: Moment
  ) {
    this.docIntoForce = this.docIntoForce || false;
    this.docDeferred = this.docDeferred || false;
    this.docRescheduled = this.docRescheduled || false;
  }
}
