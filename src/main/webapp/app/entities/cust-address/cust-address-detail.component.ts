import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICustAddress } from 'app/shared/model/cust-address.model';

@Component({
  selector: 'jhi-cust-address-detail',
  templateUrl: './cust-address-detail.component.html'
})
export class CustAddressDetailComponent implements OnInit {
  custAddress: ICustAddress | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ custAddress }) => (this.custAddress = custAddress));
  }

  previousState(): void {
    window.history.back();
  }
}
