import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProductSpecification, ProductSpecification } from 'app/shared/model/product-specification.model';
import { ProductSpecificationService } from './product-specification.service';
import { ProductSpecificationComponent } from './product-specification.component';
import { ProductSpecificationDetailComponent } from './product-specification-detail.component';
import { ProductSpecificationUpdateComponent } from './product-specification-update.component';

@Injectable({ providedIn: 'root' })
export class ProductSpecificationResolve implements Resolve<IProductSpecification> {
  constructor(private service: ProductSpecificationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProductSpecification> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((productSpecification: HttpResponse<ProductSpecification>) => {
          if (productSpecification.body) {
            return of(productSpecification.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ProductSpecification());
  }
}

export const productSpecificationRoute: Routes = [
  {
    path: '',
    component: ProductSpecificationComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.productSpecification.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProductSpecificationDetailComponent,
    resolve: {
      productSpecification: ProductSpecificationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.productSpecification.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProductSpecificationUpdateComponent,
    resolve: {
      productSpecification: ProductSpecificationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.productSpecification.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProductSpecificationUpdateComponent,
    resolve: {
      productSpecification: ProductSpecificationResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.productSpecification.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
