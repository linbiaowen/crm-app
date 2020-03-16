import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGroupType, GroupType } from 'app/shared/model/group-type.model';
import { GroupTypeService } from './group-type.service';
import { GroupTypeComponent } from './group-type.component';
import { GroupTypeDetailComponent } from './group-type-detail.component';
import { GroupTypeUpdateComponent } from './group-type-update.component';

@Injectable({ providedIn: 'root' })
export class GroupTypeResolve implements Resolve<IGroupType> {
  constructor(private service: GroupTypeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGroupType> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((groupType: HttpResponse<GroupType>) => {
          if (groupType.body) {
            return of(groupType.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new GroupType());
  }
}

export const groupTypeRoute: Routes = [
  {
    path: '',
    component: GroupTypeComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.groupType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: GroupTypeDetailComponent,
    resolve: {
      groupType: GroupTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.groupType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: GroupTypeUpdateComponent,
    resolve: {
      groupType: GroupTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.groupType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: GroupTypeUpdateComponent,
    resolve: {
      groupType: GroupTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.groupType.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
