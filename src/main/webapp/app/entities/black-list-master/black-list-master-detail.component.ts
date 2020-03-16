import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBlackListMaster } from 'app/shared/model/black-list-master.model';

@Component({
  selector: 'jhi-black-list-master-detail',
  templateUrl: './black-list-master-detail.component.html'
})
export class BlackListMasterDetailComponent implements OnInit {
  blackListMaster: IBlackListMaster | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ blackListMaster }) => (this.blackListMaster = blackListMaster));
  }

  previousState(): void {
    window.history.back();
  }
}
