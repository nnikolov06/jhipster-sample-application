export interface IEmail {
  id?: number;
  emlValue?: string;
  emlIsActive?: boolean;
}

export class Email implements IEmail {
  constructor(public id?: number, public emlValue?: string, public emlIsActive?: boolean) {
    this.emlIsActive = this.emlIsActive || false;
  }
}
