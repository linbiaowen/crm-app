import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICfsServiceSpec, CfsServiceSpec } from 'app/shared/model/cfs-service-spec.model';
import { CfsServiceSpecService } from './cfs-service-spec.service';
import { CfsServiceSpecComponent } from './cfs-service-spec.component';
import { CfsServiceSpecDetailComponent } from './cfs-service-spec-detail.component';
import { CfsServiceSpecUpdateComponent } from './cfs-service-spec-update.component';

@Injectable({ providedIn: 'root' })
export class CfsServiceSpecResolve implements Resolve<ICfsServiceSpec> {
  constructor(private service: CfsServiceSpecService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICfsServiceSpec> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((cfsServiceSpec: HttpResponse<CfsServiceSpec>) => {
          if (cfsServiceSpec.body) {
            return of(cfsServiceSpec.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CfsServiceSpec());
  }
}

export const cfsServiceSpecRoute: Routes = [
  {
    path: '',
    component: CfsServiceSpecComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.cfsServiceSpec.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CfsServiceSpecDetailComponent,
    resolve: {
      cfsServiceSpec: CfsServiceSpecResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.cfsServiceSpec.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CfsServiceSpecUpdateComponent,
    resolve: {
      cfsServiceSpec: CfsServiceSpecResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.cfsServiceSpec.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CfsServiceSpecUpdateComponent,
    resolve: {
      cfsServiceSpec: CfsServiceSpecResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.cfsServiceSpec.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
