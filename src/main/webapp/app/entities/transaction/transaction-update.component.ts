import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ITransaction, Transaction } from 'app/shared/model/transaction.model';
import { TransactionService } from './transaction.service';
import { IObligation } from 'app/shared/model/obligation.model';
import { ObligationService } from 'app/entities/obligation/obligation.service';

@Component({
  selector: 'jhi-transaction-update',
  templateUrl: './transaction-update.component.html',
})
export class TransactionUpdateComponent implements OnInit {
  isSaving = false;
  obligations: IObligation[] = [];

  editForm = this.fb.group({
    id: [],
    trnNumber: [],
    trnDate: [],
    trnSystem: [],
    trnAmount: [],
    trnCreationDate: [],
    trnSysDate: [],
    trnCreatedBy: [],
    trnType: [],
    trnCode: [],
    trnStatus: [],
    trnDescription: [],
    trnPaymentType: [],
    obligation: [],
  });

  constructor(
    protected transactionService: TransactionService,
    protected obligationService: ObligationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ transaction }) => {
      if (!transaction.id) {
        const today = moment().startOf('day');
        transaction.trnDate = today;
        transaction.trnCreationDate = today;
        transaction.trnSysDate = today;
      }

      this.updateForm(transaction);

      this.obligationService.query().subscribe((res: HttpResponse<IObligation[]>) => (this.obligations = res.body || []));
    });
  }

  updateForm(transaction: ITransaction): void {
    this.editForm.patchValue({
      id: transaction.id,
      trnNumber: transaction.trnNumber,
      trnDate: transaction.trnDate ? transaction.trnDate.format(DATE_TIME_FORMAT) : null,
      trnSystem: transaction.trnSystem,
      trnAmount: transaction.trnAmount,
      trnCreationDate: transaction.trnCreationDate ? transaction.trnCreationDate.format(DATE_TIME_FORMAT) : null,
      trnSysDate: transaction.trnSysDate ? transaction.trnSysDate.format(DATE_TIME_FORMAT) : null,
      trnCreatedBy: transaction.trnCreatedBy,
      trnType: transaction.trnType,
      trnCode: transaction.trnCode,
      trnStatus: transaction.trnStatus,
      trnDescription: transaction.trnDescription,
      trnPaymentType: transaction.trnPaymentType,
      obligation: transaction.obligation,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const transaction = this.createFromForm();
    if (transaction.id !== undefined) {
      this.subscribeToSaveResponse(this.transactionService.update(transaction));
    } else {
      this.subscribeToSaveResponse(this.transactionService.create(transaction));
    }
  }

  private createFromForm(): ITransaction {
    return {
      ...new Transaction(),
      id: this.editForm.get(['id'])!.value,
      trnNumber: this.editForm.get(['trnNumber'])!.value,
      trnDate: this.editForm.get(['trnDate'])!.value ? moment(this.editForm.get(['trnDate'])!.value, DATE_TIME_FORMAT) : undefined,
      trnSystem: this.editForm.get(['trnSystem'])!.value,
      trnAmount: this.editForm.get(['trnAmount'])!.value,
      trnCreationDate: this.editForm.get(['trnCreationDate'])!.value
        ? moment(this.editForm.get(['trnCreationDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      trnSysDate: this.editForm.get(['trnSysDate'])!.value ? moment(this.editForm.get(['trnSysDate'])!.value, DATE_TIME_FORMAT) : undefined,
      trnCreatedBy: this.editForm.get(['trnCreatedBy'])!.value,
      trnType: this.editForm.get(['trnType'])!.value,
      trnCode: this.editForm.get(['trnCode'])!.value,
      trnStatus: this.editForm.get(['trnStatus'])!.value,
      trnDescription: this.editForm.get(['trnDescription'])!.value,
      trnPaymentType: this.editForm.get(['trnPaymentType'])!.value,
      obligation: this.editForm.get(['obligation'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITransaction>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IObligation): any {
    return item.id;
  }
}
