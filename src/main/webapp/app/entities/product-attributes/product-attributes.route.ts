import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProductAttributes, ProductAttributes } from 'app/shared/model/product-attributes.model';
import { ProductAttributesService } from './product-attributes.service';
import { ProductAttributesComponent } from './product-attributes.component';
import { ProductAttributesDetailComponent } from './product-attributes-detail.component';
import { ProductAttributesUpdateComponent } from './product-attributes-update.component';

@Injectable({ providedIn: 'root' })
export class ProductAttributesResolve implements Resolve<IProductAttributes> {
  constructor(private service: ProductAttributesService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProductAttributes> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((productAttributes: HttpResponse<ProductAttributes>) => {
          if (productAttributes.body) {
            return of(productAttributes.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ProductAttributes());
  }
}

export const productAttributesRoute: Routes = [
  {
    path: '',
    component: ProductAttributesComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.productAttributes.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProductAttributesDetailComponent,
    resolve: {
      productAttributes: ProductAttributesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.productAttributes.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProductAttributesUpdateComponent,
    resolve: {
      productAttributes: ProductAttributesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.productAttributes.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProductAttributesUpdateComponent,
    resolve: {
      productAttributes: ProductAttributesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.productAttributes.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
