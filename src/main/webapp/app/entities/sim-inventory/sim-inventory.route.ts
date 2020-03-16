import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISimInventory, SimInventory } from 'app/shared/model/sim-inventory.model';
import { SimInventoryService } from './sim-inventory.service';
import { SimInventoryComponent } from './sim-inventory.component';
import { SimInventoryDetailComponent } from './sim-inventory-detail.component';
import { SimInventoryUpdateComponent } from './sim-inventory-update.component';

@Injectable({ providedIn: 'root' })
export class SimInventoryResolve implements Resolve<ISimInventory> {
  constructor(private service: SimInventoryService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISimInventory> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((simInventory: HttpResponse<SimInventory>) => {
          if (simInventory.body) {
            return of(simInventory.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SimInventory());
  }
}

export const simInventoryRoute: Routes = [
  {
    path: '',
    component: SimInventoryComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.simInventory.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SimInventoryDetailComponent,
    resolve: {
      simInventory: SimInventoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.simInventory.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SimInventoryUpdateComponent,
    resolve: {
      simInventory: SimInventoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.simInventory.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SimInventoryUpdateComponent,
    resolve: {
      simInventory: SimInventoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.simInventory.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
