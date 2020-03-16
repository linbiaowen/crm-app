import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDataServiceSpec, DataServiceSpec } from 'app/shared/model/data-service-spec.model';
import { DataServiceSpecService } from './data-service-spec.service';
import { DataServiceSpecComponent } from './data-service-spec.component';
import { DataServiceSpecDetailComponent } from './data-service-spec-detail.component';
import { DataServiceSpecUpdateComponent } from './data-service-spec-update.component';

@Injectable({ providedIn: 'root' })
export class DataServiceSpecResolve implements Resolve<IDataServiceSpec> {
  constructor(private service: DataServiceSpecService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDataServiceSpec> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((dataServiceSpec: HttpResponse<DataServiceSpec>) => {
          if (dataServiceSpec.body) {
            return of(dataServiceSpec.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DataServiceSpec());
  }
}

export const dataServiceSpecRoute: Routes = [
  {
    path: '',
    component: DataServiceSpecComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.dataServiceSpec.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DataServiceSpecDetailComponent,
    resolve: {
      dataServiceSpec: DataServiceSpecResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.dataServiceSpec.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DataServiceSpecUpdateComponent,
    resolve: {
      dataServiceSpec: DataServiceSpecResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.dataServiceSpec.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DataServiceSpecUpdateComponent,
    resolve: {
      dataServiceSpec: DataServiceSpecResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.dataServiceSpec.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
