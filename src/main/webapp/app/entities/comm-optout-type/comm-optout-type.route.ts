import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICommOptoutType, CommOptoutType } from 'app/shared/model/comm-optout-type.model';
import { CommOptoutTypeService } from './comm-optout-type.service';
import { CommOptoutTypeComponent } from './comm-optout-type.component';
import { CommOptoutTypeDetailComponent } from './comm-optout-type-detail.component';
import { CommOptoutTypeUpdateComponent } from './comm-optout-type-update.component';

@Injectable({ providedIn: 'root' })
export class CommOptoutTypeResolve implements Resolve<ICommOptoutType> {
  constructor(private service: CommOptoutTypeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICommOptoutType> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((commOptoutType: HttpResponse<CommOptoutType>) => {
          if (commOptoutType.body) {
            return of(commOptoutType.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CommOptoutType());
  }
}

export const commOptoutTypeRoute: Routes = [
  {
    path: '',
    component: CommOptoutTypeComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.commOptoutType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CommOptoutTypeDetailComponent,
    resolve: {
      commOptoutType: CommOptoutTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.commOptoutType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CommOptoutTypeUpdateComponent,
    resolve: {
      commOptoutType: CommOptoutTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.commOptoutType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CommOptoutTypeUpdateComponent,
    resolve: {
      commOptoutType: CommOptoutTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.commOptoutType.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
