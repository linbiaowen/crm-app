import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICustContact } from 'app/shared/model/cust-contact.model';

@Component({
  selector: 'jhi-cust-contact-detail',
  templateUrl: './cust-contact-detail.component.html'
})
export class CustContactDetailComponent implements OnInit {
  custContact: ICustContact | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ custContact }) => (this.custContact = custContact));
  }

  previousState(): void {
    window.history.back();
  }
}
