import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IModelCategory, ModelCategory } from 'app/shared/model/model-category.model';
import { ModelCategoryService } from './model-category.service';
import { ModelCategoryComponent } from './model-category.component';
import { ModelCategoryDetailComponent } from './model-category-detail.component';
import { ModelCategoryUpdateComponent } from './model-category-update.component';

@Injectable({ providedIn: 'root' })
export class ModelCategoryResolve implements Resolve<IModelCategory> {
  constructor(private service: ModelCategoryService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IModelCategory> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((modelCategory: HttpResponse<ModelCategory>) => {
          if (modelCategory.body) {
            return of(modelCategory.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ModelCategory());
  }
}

export const modelCategoryRoute: Routes = [
  {
    path: '',
    component: ModelCategoryComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.modelCategory.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ModelCategoryDetailComponent,
    resolve: {
      modelCategory: ModelCategoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.modelCategory.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ModelCategoryUpdateComponent,
    resolve: {
      modelCategory: ModelCategoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.modelCategory.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ModelCategoryUpdateComponent,
    resolve: {
      modelCategory: ModelCategoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.modelCategory.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
