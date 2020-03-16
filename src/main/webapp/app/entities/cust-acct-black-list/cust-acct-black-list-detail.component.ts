import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICustAcctBlackList } from 'app/shared/model/cust-acct-black-list.model';

@Component({
  selector: 'jhi-cust-acct-black-list-detail',
  templateUrl: './cust-acct-black-list-detail.component.html'
})
export class CustAcctBlackListDetailComponent implements OnInit {
  custAcctBlackList: ICustAcctBlackList | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ custAcctBlackList }) => (this.custAcctBlackList = custAcctBlackList));
  }

  previousState(): void {
    window.history.back();
  }
}
