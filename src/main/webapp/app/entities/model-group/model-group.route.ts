import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IModelGroup, ModelGroup } from 'app/shared/model/model-group.model';
import { ModelGroupService } from './model-group.service';
import { ModelGroupComponent } from './model-group.component';
import { ModelGroupDetailComponent } from './model-group-detail.component';
import { ModelGroupUpdateComponent } from './model-group-update.component';

@Injectable({ providedIn: 'root' })
export class ModelGroupResolve implements Resolve<IModelGroup> {
  constructor(private service: ModelGroupService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IModelGroup> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((modelGroup: HttpResponse<ModelGroup>) => {
          if (modelGroup.body) {
            return of(modelGroup.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ModelGroup());
  }
}

export const modelGroupRoute: Routes = [
  {
    path: '',
    component: ModelGroupComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.modelGroup.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ModelGroupDetailComponent,
    resolve: {
      modelGroup: ModelGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.modelGroup.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ModelGroupUpdateComponent,
    resolve: {
      modelGroup: ModelGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.modelGroup.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ModelGroupUpdateComponent,
    resolve: {
      modelGroup: ModelGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.modelGroup.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
