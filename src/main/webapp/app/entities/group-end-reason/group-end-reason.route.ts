import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGroupEndReason, GroupEndReason } from 'app/shared/model/group-end-reason.model';
import { GroupEndReasonService } from './group-end-reason.service';
import { GroupEndReasonComponent } from './group-end-reason.component';
import { GroupEndReasonDetailComponent } from './group-end-reason-detail.component';
import { GroupEndReasonUpdateComponent } from './group-end-reason-update.component';

@Injectable({ providedIn: 'root' })
export class GroupEndReasonResolve implements Resolve<IGroupEndReason> {
  constructor(private service: GroupEndReasonService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGroupEndReason> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((groupEndReason: HttpResponse<GroupEndReason>) => {
          if (groupEndReason.body) {
            return of(groupEndReason.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new GroupEndReason());
  }
}

export const groupEndReasonRoute: Routes = [
  {
    path: '',
    component: GroupEndReasonComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.groupEndReason.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: GroupEndReasonDetailComponent,
    resolve: {
      groupEndReason: GroupEndReasonResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.groupEndReason.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: GroupEndReasonUpdateComponent,
    resolve: {
      groupEndReason: GroupEndReasonResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.groupEndReason.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: GroupEndReasonUpdateComponent,
    resolve: {
      groupEndReason: GroupEndReasonResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.groupEndReason.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
