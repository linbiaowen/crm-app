import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IOfferSpecification, OfferSpecification } from 'app/shared/model/offer-specification.model';
import { OfferSpecificationService } from './offer-specification.service';
import { OfferSpecificationComponent } from './offer-specification.component';
import { OfferSpecificationDetailComponent } from './offer-specification-detail.component';
import { OfferSpecificationUpdateComponent } from './offer-specification-update.component';

@Injectable({ providedIn: 'root' })
export class OfferSpecificationResolve implements Resolve<IOfferSpecification> {
  constructor(private service: OfferSpecificationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOfferSpecification> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((offerSpecification: HttpResponse<OfferSpecification>) => {
          if (offerSpecification.body) {
            return of(offerSpecification.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new OfferSpecification());
  }
}

export const offerSpecificationRoute: Routes = [
  {
    path: '',
    component: OfferSpecificationComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.offerSpecification.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: OfferSpecificationDetailComponent,
    resolve: {
      offerSpecification: OfferSpecificationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.offerSpecification.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: OfferSpecificationUpdateComponent,
    resolve: {
      offerSpecification: OfferSpecificationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.offerSpecification.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: OfferSpecificationUpdateComponent,
    resolve: {
      offerSpecification: OfferSpecificationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.offerSpecification.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
