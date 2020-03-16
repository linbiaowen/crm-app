import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICustDocument, CustDocument } from 'app/shared/model/cust-document.model';
import { CustDocumentService } from './cust-document.service';
import { CustDocumentComponent } from './cust-document.component';
import { CustDocumentDetailComponent } from './cust-document-detail.component';
import { CustDocumentUpdateComponent } from './cust-document-update.component';

@Injectable({ providedIn: 'root' })
export class CustDocumentResolve implements Resolve<ICustDocument> {
  constructor(private service: CustDocumentService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICustDocument> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((custDocument: HttpResponse<CustDocument>) => {
          if (custDocument.body) {
            return of(custDocument.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CustDocument());
  }
}

export const custDocumentRoute: Routes = [
  {
    path: '',
    component: CustDocumentComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.custDocument.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CustDocumentDetailComponent,
    resolve: {
      custDocument: CustDocumentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.custDocument.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CustDocumentUpdateComponent,
    resolve: {
      custDocument: CustDocumentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.custDocument.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CustDocumentUpdateComponent,
    resolve: {
      custDocument: CustDocumentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.custDocument.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
