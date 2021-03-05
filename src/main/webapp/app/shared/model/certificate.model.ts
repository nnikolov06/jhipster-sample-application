import { Moment } from 'moment';
import { IRezmaEntity } from 'app/shared/model/rezma-entity.model';

export interface ICertificate {
  id?: number;
  cerNumber?: string;
  cerDate?: string;
  cerSysDate?: Moment;
  cerCreatedBy?: string;
  cerFileName?: string;
  cerServiceApplicant?: string;
  cerSigningPerson?: string;
  cerSigningPersonPosition?: string;
  cerCertificateType?: string;
  cerCheckBoxValues?: string;
  cerRequestNumber?: string;
  cerRequestDate?: Moment;
  cerIsDeactivated?: boolean;
  cerState?: string;
  cerHasObligation?: boolean;
  cerDocumentState?: string;
  rezmaEntity?: IRezmaEntity;
}

export class Certificate implements ICertificate {
  constructor(
    public id?: number,
    public cerNumber?: string,
    public cerDate?: string,
    public cerSysDate?: Moment,
    public cerCreatedBy?: string,
    public cerFileName?: string,
    public cerServiceApplicant?: string,
    public cerSigningPerson?: string,
    public cerSigningPersonPosition?: string,
    public cerCertificateType?: string,
    public cerCheckBoxValues?: string,
    public cerRequestNumber?: string,
    public cerRequestDate?: Moment,
    public cerIsDeactivated?: boolean,
    public cerState?: string,
    public cerHasObligation?: boolean,
    public cerDocumentState?: string,
    public rezmaEntity?: IRezmaEntity
  ) {
    this.cerIsDeactivated = this.cerIsDeactivated || false;
    this.cerHasObligation = this.cerHasObligation || false;
  }
}
