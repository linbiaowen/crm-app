import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISalesChannelMaster } from 'app/shared/model/sales-channel-master.model';

@Component({
  selector: 'jhi-sales-channel-master-detail',
  templateUrl: './sales-channel-master-detail.component.html'
})
export class SalesChannelMasterDetailComponent implements OnInit {
  salesChannelMaster: ISalesChannelMaster | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ salesChannelMaster }) => (this.salesChannelMaster = salesChannelMaster));
  }

  previousState(): void {
    window.history.back();
  }
}
