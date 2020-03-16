import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICustCommOptoutMaster } from 'app/shared/model/cust-comm-optout-master.model';

@Component({
  selector: 'jhi-cust-comm-optout-master-detail',
  templateUrl: './cust-comm-optout-master-detail.component.html'
})
export class CustCommOptoutMasterDetailComponent implements OnInit {
  custCommOptoutMaster: ICustCommOptoutMaster | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ custCommOptoutMaster }) => (this.custCommOptoutMaster = custCommOptoutMaster));
  }

  previousState(): void {
    window.history.back();
  }
}
