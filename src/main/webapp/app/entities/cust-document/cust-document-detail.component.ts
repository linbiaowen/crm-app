import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICustDocument } from 'app/shared/model/cust-document.model';

@Component({
  selector: 'jhi-cust-document-detail',
  templateUrl: './cust-document-detail.component.html'
})
export class CustDocumentDetailComponent implements OnInit {
  custDocument: ICustDocument | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ custDocument }) => (this.custDocument = custDocument));
  }

  previousState(): void {
    window.history.back();
  }
}
