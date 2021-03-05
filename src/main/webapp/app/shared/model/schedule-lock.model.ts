import { Moment } from 'moment';

export interface IScheduleLock {
  id?: number;
  slcName?: string;
  slcLockUntil?: Moment;
  slcLockedAt?: Moment;
  slcLockedBy?: string;
}

export class ScheduleLock implements IScheduleLock {
  constructor(
    public id?: number,
    public slcName?: string,
    public slcLockUntil?: Moment,
    public slcLockedAt?: Moment,
    public slcLockedBy?: string
  ) {}
}
