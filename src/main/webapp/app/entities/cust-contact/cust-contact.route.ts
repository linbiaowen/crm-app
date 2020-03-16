import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICustContact, CustContact } from 'app/shared/model/cust-contact.model';
import { CustContactService } from './cust-contact.service';
import { CustContactComponent } from './cust-contact.component';
import { CustContactDetailComponent } from './cust-contact-detail.component';
import { CustContactUpdateComponent } from './cust-contact-update.component';

@Injectable({ providedIn: 'root' })
export class CustContactResolve implements Resolve<ICustContact> {
  constructor(private service: CustContactService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICustContact> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((custContact: HttpResponse<CustContact>) => {
          if (custContact.body) {
            return of(custContact.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CustContact());
  }
}

export const custContactRoute: Routes = [
  {
    path: '',
    component: CustContactComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.custContact.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CustContactDetailComponent,
    resolve: {
      custContact: CustContactResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.custContact.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CustContactUpdateComponent,
    resolve: {
      custContact: CustContactResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.custContact.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CustContactUpdateComponent,
    resolve: {
      custContact: CustContactResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.custContact.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
