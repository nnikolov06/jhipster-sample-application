export interface ICertificateEmergencyText {
  id?: number;
  cetText?: string;
  cetIsActive?: boolean;
}

export class CertificateEmergencyText implements ICertificateEmergencyText {
  constructor(public id?: number, public cetText?: string, public cetIsActive?: boolean) {
    this.cetIsActive = this.cetIsActive || false;
  }
}
