import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProductVoice, ProductVoice } from 'app/shared/model/product-voice.model';
import { ProductVoiceService } from './product-voice.service';
import { ProductVoiceComponent } from './product-voice.component';
import { ProductVoiceDetailComponent } from './product-voice-detail.component';
import { ProductVoiceUpdateComponent } from './product-voice-update.component';

@Injectable({ providedIn: 'root' })
export class ProductVoiceResolve implements Resolve<IProductVoice> {
  constructor(private service: ProductVoiceService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProductVoice> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((productVoice: HttpResponse<ProductVoice>) => {
          if (productVoice.body) {
            return of(productVoice.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ProductVoice());
  }
}

export const productVoiceRoute: Routes = [
  {
    path: '',
    component: ProductVoiceComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.productVoice.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProductVoiceDetailComponent,
    resolve: {
      productVoice: ProductVoiceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.productVoice.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProductVoiceUpdateComponent,
    resolve: {
      productVoice: ProductVoiceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.productVoice.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProductVoiceUpdateComponent,
    resolve: {
      productVoice: ProductVoiceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.productVoice.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
