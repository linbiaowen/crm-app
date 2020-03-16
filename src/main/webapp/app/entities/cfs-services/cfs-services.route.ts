import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICfsServices, CfsServices } from 'app/shared/model/cfs-services.model';
import { CfsServicesService } from './cfs-services.service';
import { CfsServicesComponent } from './cfs-services.component';
import { CfsServicesDetailComponent } from './cfs-services-detail.component';
import { CfsServicesUpdateComponent } from './cfs-services-update.component';

@Injectable({ providedIn: 'root' })
export class CfsServicesResolve implements Resolve<ICfsServices> {
  constructor(private service: CfsServicesService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICfsServices> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((cfsServices: HttpResponse<CfsServices>) => {
          if (cfsServices.body) {
            return of(cfsServices.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CfsServices());
  }
}

export const cfsServicesRoute: Routes = [
  {
    path: '',
    component: CfsServicesComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.cfsServices.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CfsServicesDetailComponent,
    resolve: {
      cfsServices: CfsServicesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.cfsServices.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CfsServicesUpdateComponent,
    resolve: {
      cfsServices: CfsServicesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.cfsServices.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CfsServicesUpdateComponent,
    resolve: {
      cfsServices: CfsServicesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.cfsServices.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
