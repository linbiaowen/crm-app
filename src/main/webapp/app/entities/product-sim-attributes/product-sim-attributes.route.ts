import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProductSimAttributes, ProductSimAttributes } from 'app/shared/model/product-sim-attributes.model';
import { ProductSimAttributesService } from './product-sim-attributes.service';
import { ProductSimAttributesComponent } from './product-sim-attributes.component';
import { ProductSimAttributesDetailComponent } from './product-sim-attributes-detail.component';
import { ProductSimAttributesUpdateComponent } from './product-sim-attributes-update.component';

@Injectable({ providedIn: 'root' })
export class ProductSimAttributesResolve implements Resolve<IProductSimAttributes> {
  constructor(private service: ProductSimAttributesService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProductSimAttributes> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((productSimAttributes: HttpResponse<ProductSimAttributes>) => {
          if (productSimAttributes.body) {
            return of(productSimAttributes.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ProductSimAttributes());
  }
}

export const productSimAttributesRoute: Routes = [
  {
    path: '',
    component: ProductSimAttributesComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.productSimAttributes.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProductSimAttributesDetailComponent,
    resolve: {
      productSimAttributes: ProductSimAttributesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.productSimAttributes.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProductSimAttributesUpdateComponent,
    resolve: {
      productSimAttributes: ProductSimAttributesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.productSimAttributes.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProductSimAttributesUpdateComponent,
    resolve: {
      productSimAttributes: ProductSimAttributesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.productSimAttributes.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
