import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ICustAddress, CustAddress } from 'app/shared/model/cust-address.model';
import { CustAddressService } from './cust-address.service';
import { ICustomer } from 'app/shared/model/customer.model';
import { CustomerService } from 'app/entities/customer/customer.service';

@Component({
  selector: 'jhi-cust-address-update',
  templateUrl: './cust-address-update.component.html'
})
export class CustAddressUpdateComponent implements OnInit {
  isSaving = false;
  customers: ICustomer[] = [];

  editForm = this.fb.group({
    id: [],
    addressId: [null, [Validators.required]],
    accountId: [null, [Validators.required]],
    accountType: [null, [Validators.required]],
    addressType: [null, [Validators.required]],
    addressLang: [null, [Validators.required]],
    formattedAddress: [null, [Validators.required]],
    room: [],
    floor: [],
    block: [],
    building: [],
    streetEstate: [],
    district: [],
    region: [],
    address1: [],
    address2: [],
    address3: [],
    address4: [],
    address5: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]],
    customer: []
  });

  constructor(
    protected custAddressService: CustAddressService,
    protected customerService: CustomerService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ custAddress }) => {
      if (!custAddress.id) {
        const today = moment().startOf('day');
        custAddress.createdDate = today;
        custAddress.lastUpdatedDate = today;
      }

      this.updateForm(custAddress);

      this.customerService.query().subscribe((res: HttpResponse<ICustomer[]>) => (this.customers = res.body || []));
    });
  }

  updateForm(custAddress: ICustAddress): void {
    this.editForm.patchValue({
      id: custAddress.id,
      addressId: custAddress.addressId,
      accountId: custAddress.accountId,
      accountType: custAddress.accountType,
      addressType: custAddress.addressType,
      addressLang: custAddress.addressLang,
      formattedAddress: custAddress.formattedAddress,
      room: custAddress.room,
      floor: custAddress.floor,
      block: custAddress.block,
      building: custAddress.building,
      streetEstate: custAddress.streetEstate,
      district: custAddress.district,
      region: custAddress.region,
      address1: custAddress.address1,
      address2: custAddress.address2,
      address3: custAddress.address3,
      address4: custAddress.address4,
      address5: custAddress.address5,
      createdDate: custAddress.createdDate ? custAddress.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: custAddress.createdBy,
      lastUpdatedDate: custAddress.lastUpdatedDate ? custAddress.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: custAddress.lastUpdatedBy,
      tenantId: custAddress.tenantId,
      customer: custAddress.customer
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const custAddress = this.createFromForm();
    if (custAddress.id !== undefined) {
      this.subscribeToSaveResponse(this.custAddressService.update(custAddress));
    } else {
      this.subscribeToSaveResponse(this.custAddressService.create(custAddress));
    }
  }

  private createFromForm(): ICustAddress {
    return {
      ...new CustAddress(),
      id: this.editForm.get(['id'])!.value,
      addressId: this.editForm.get(['addressId'])!.value,
      accountId: this.editForm.get(['accountId'])!.value,
      accountType: this.editForm.get(['accountType'])!.value,
      addressType: this.editForm.get(['addressType'])!.value,
      addressLang: this.editForm.get(['addressLang'])!.value,
      formattedAddress: this.editForm.get(['formattedAddress'])!.value,
      room: this.editForm.get(['room'])!.value,
      floor: this.editForm.get(['floor'])!.value,
      block: this.editForm.get(['block'])!.value,
      building: this.editForm.get(['building'])!.value,
      streetEstate: this.editForm.get(['streetEstate'])!.value,
      district: this.editForm.get(['district'])!.value,
      region: this.editForm.get(['region'])!.value,
      address1: this.editForm.get(['address1'])!.value,
      address2: this.editForm.get(['address2'])!.value,
      address3: this.editForm.get(['address3'])!.value,
      address4: this.editForm.get(['address4'])!.value,
      address5: this.editForm.get(['address5'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value
        ? moment(this.editForm.get(['createdDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
      lastUpdatedDate: this.editForm.get(['lastUpdatedDate'])!.value
        ? moment(this.editForm.get(['lastUpdatedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastUpdatedBy: this.editForm.get(['lastUpdatedBy'])!.value,
      tenantId: this.editForm.get(['tenantId'])!.value,
      customer: this.editForm.get(['customer'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICustAddress>>): void {
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
