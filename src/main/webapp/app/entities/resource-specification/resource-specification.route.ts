import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IResourceSpecification, ResourceSpecification } from 'app/shared/model/resource-specification.model';
import { ResourceSpecificationService } from './resource-specification.service';
import { ResourceSpecificationComponent } from './resource-specification.component';
import { ResourceSpecificationDetailComponent } from './resource-specification-detail.component';
import { ResourceSpecificationUpdateComponent } from './resource-specification-update.component';

@Injectable({ providedIn: 'root' })
export class ResourceSpecificationResolve implements Resolve<IResourceSpecification> {
  constructor(private service: ResourceSpecificationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IResourceSpecification> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((resourceSpecification: HttpResponse<ResourceSpecification>) => {
          if (resourceSpecification.body) {
            return of(resourceSpecification.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ResourceSpecification());
  }
}

export const resourceSpecificationRoute: Routes = [
  {
    path: '',
    component: ResourceSpecificationComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.resourceSpecification.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ResourceSpecificationDetailComponent,
    resolve: {
      resourceSpecification: ResourceSpecificationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.resourceSpecification.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ResourceSpecificationUpdateComponent,
    resolve: {
      resourceSpecification: ResourceSpecificationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.resourceSpecification.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ResourceSpecificationUpdateComponent,
    resolve: {
      resourceSpecification: ResourceSpecificationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.resourceSpecification.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
