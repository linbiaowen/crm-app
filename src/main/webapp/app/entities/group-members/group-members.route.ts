import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGroupMembers, GroupMembers } from 'app/shared/model/group-members.model';
import { GroupMembersService } from './group-members.service';
import { GroupMembersComponent } from './group-members.component';
import { GroupMembersDetailComponent } from './group-members-detail.component';
import { GroupMembersUpdateComponent } from './group-members-update.component';

@Injectable({ providedIn: 'root' })
export class GroupMembersResolve implements Resolve<IGroupMembers> {
  constructor(private service: GroupMembersService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGroupMembers> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((groupMembers: HttpResponse<GroupMembers>) => {
          if (groupMembers.body) {
            return of(groupMembers.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new GroupMembers());
  }
}

export const groupMembersRoute: Routes = [
  {
    path: '',
    component: GroupMembersComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.groupMembers.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: GroupMembersDetailComponent,
    resolve: {
      groupMembers: GroupMembersResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.groupMembers.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: GroupMembersUpdateComponent,
    resolve: {
      groupMembers: GroupMembersResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.groupMembers.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: GroupMembersUpdateComponent,
    resolve: {
      groupMembers: GroupMembersResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.groupMembers.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
