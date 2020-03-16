import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ICustomer, Customer } from 'app/shared/model/customer.model';
import { CustomerService } from './customer.service';

@Component({
  selector: 'jhi-customer-update',
  templateUrl: './customer-update.component.html'
})
export class CustomerUpdateComponent implements OnInit {
  isSaving = false;
  parentcustomers: ICustomer[] = [];
  birthDateDp: any;

  editForm = this.fb.group({
    id: [],
    custAcctId: [null, [Validators.required]],
    parentCustAcctId: [],
    acctStatus: [null, [Validators.required]],
    acctStartDate: [null, [Validators.required]],
    acctEndDate: [],
    cabsAcctId: [],
    title: [],
    givenName: [],
    familyName: [],
    givenNameChi: [],
    familyNameChi: [],
    aliasName: [],
    gender: [],
    birthDate: [],
    idType: [],
    idNumber: [],
    companyNameEng: [],
    companyNameChi: [],
    unlimitedCompany: [],
    lang: [null, [Validators.required]],
    selfCareUserId: [],
    selfCarePassword: [],
    ivrPin: [],
    maritialStatus: [],
    customerSegment: [],
    customerClass: [],
    billingAcctId: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]],
    parentCustomer: []
  });

  constructor(protected customerService: CustomerService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ customer }) => {
      if (!customer.id) {
        const today = moment().startOf('day');
        customer.acctStartDate = today;
        customer.acctEndDate = today;
        customer.createdDate = today;
        customer.lastUpdatedDate = today;
      }

      this.updateForm(customer);

      this.customerService
        .query({ filter: 'customer-is-null' })
        .pipe(
          map((res: HttpResponse<ICustomer[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ICustomer[]) => {
          if (!customer.parentCustomer || !customer.parentCustomer.id) {
            this.parentcustomers = resBody;
          } else {
            this.customerService
              .find(customer.parentCustomer.id)
              .pipe(
                map((subRes: HttpResponse<ICustomer>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ICustomer[]) => (this.parentcustomers = concatRes));
          }
        });
    });
  }

  updateForm(customer: ICustomer): void {
    this.editForm.patchValue({
      id: customer.id,
      custAcctId: customer.custAcctId,
      parentCustAcctId: customer.parentCustAcctId,
      acctStatus: customer.acctStatus,
      acctStartDate: customer.acctStartDate ? customer.acctStartDate.format(DATE_TIME_FORMAT) : null,
      acctEndDate: customer.acctEndDate ? customer.acctEndDate.format(DATE_TIME_FORMAT) : null,
      cabsAcctId: customer.cabsAcctId,
      title: customer.title,
      givenName: customer.givenName,
      familyName: customer.familyName,
      givenNameChi: customer.givenNameChi,
      familyNameChi: customer.familyNameChi,
      aliasName: customer.aliasName,
      gender: customer.gender,
      birthDate: customer.birthDate,
      idType: customer.idType,
      idNumber: customer.idNumber,
      companyNameEng: customer.companyNameEng,
      companyNameChi: customer.companyNameChi,
      unlimitedCompany: customer.unlimitedCompany,
      lang: customer.lang,
      selfCareUserId: customer.selfCareUserId,
      selfCarePassword: customer.selfCarePassword,
      ivrPin: customer.ivrPin,
      maritialStatus: customer.maritialStatus,
      customerSegment: customer.customerSegment,
      customerClass: customer.customerClass,
      billingAcctId: customer.billingAcctId,
      createdDate: customer.createdDate ? customer.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: customer.createdBy,
      lastUpdatedDate: customer.lastUpdatedDate ? customer.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: customer.lastUpdatedBy,
      tenantId: customer.tenantId,
      parentCustomer: customer.parentCustomer
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const customer = this.createFromForm();
    if (customer.id !== undefined) {
      this.subscribeToSaveResponse(this.customerService.update(customer));
    } else {
      this.subscribeToSaveResponse(this.customerService.create(customer));
    }
  }

  private createFromForm(): ICustomer {
    return {
      ...new Customer(),
      id: this.editForm.get(['id'])!.value,
      custAcctId: this.editForm.get(['custAcctId'])!.value,
      parentCustAcctId: this.editForm.get(['parentCustAcctId'])!.value,
      acctStatus: this.editForm.get(['acctStatus'])!.value,
      acctStartDate: this.editForm.get(['acctStartDate'])!.value
        ? moment(this.editForm.get(['acctStartDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      acctEndDate: this.editForm.get(['acctEndDate'])!.value
        ? moment(this.editForm.get(['acctEndDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      cabsAcctId: this.editForm.get(['cabsAcctId'])!.value,
      title: this.editForm.get(['title'])!.value,
      givenName: this.editForm.get(['givenName'])!.value,
      familyName: this.editForm.get(['familyName'])!.value,
      givenNameChi: this.editForm.get(['givenNameChi'])!.value,
      familyNameChi: this.editForm.get(['familyNameChi'])!.value,
      aliasName: this.editForm.get(['aliasName'])!.value,
      gender: this.editForm.get(['gender'])!.value,
      birthDate: this.editForm.get(['birthDate'])!.value,
      idType: this.editForm.get(['idType'])!.value,
      idNumber: this.editForm.get(['idNumber'])!.value,
      companyNameEng: this.editForm.get(['companyNameEng'])!.value,
      companyNameChi: this.editForm.get(['companyNameChi'])!.value,
      unlimitedCompany: this.editForm.get(['unlimitedCompany'])!.value,
      lang: this.editForm.get(['lang'])!.value,
      selfCareUserId: this.editForm.get(['selfCareUserId'])!.value,
      selfCarePassword: this.editForm.get(['selfCarePassword'])!.value,
      ivrPin: this.editForm.get(['ivrPin'])!.value,
      maritialStatus: this.editForm.get(['maritialStatus'])!.value,
      customerSegment: this.editForm.get(['customerSegment'])!.value,
      customerClass: this.editForm.get(['customerClass'])!.value,
      billingAcctId: this.editForm.get(['billingAcctId'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value
        ? moment(this.editForm.get(['createdDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
      lastUpdatedDate: this.editForm.get(['lastUpdatedDate'])!.value
        ? moment(this.editForm.get(['lastUpdatedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastUpdatedBy: this.editForm.get(['lastUpdatedBy'])!.value,
      tenantId: this.editForm.get(['tenantId'])!.value,
      parentCustomer: this.editForm.get(['parentCustomer'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICustomer>>): void {
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

  trackById(index: number, item: ICustomer): any {
    return item.id;
  }
}
