import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IVoiceServiceSpec, VoiceServiceSpec } from 'app/shared/model/voice-service-spec.model';
import { VoiceServiceSpecService } from './voice-service-spec.service';
import { VoiceServiceSpecComponent } from './voice-service-spec.component';
import { VoiceServiceSpecDetailComponent } from './voice-service-spec-detail.component';
import { VoiceServiceSpecUpdateComponent } from './voice-service-spec-update.component';

@Injectable({ providedIn: 'root' })
export class VoiceServiceSpecResolve implements Resolve<IVoiceServiceSpec> {
  constructor(private service: VoiceServiceSpecService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IVoiceServiceSpec> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((voiceServiceSpec: HttpResponse<VoiceServiceSpec>) => {
          if (voiceServiceSpec.body) {
            return of(voiceServiceSpec.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new VoiceServiceSpec());
  }
}

export const voiceServiceSpecRoute: Routes = [
  {
    path: '',
    component: VoiceServiceSpecComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'crmwebApp.voiceServiceSpec.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: VoiceServiceSpecDetailComponent,
    resolve: {
      voiceServiceSpec: VoiceServiceSpecResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.voiceServiceSpec.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: VoiceServiceSpecUpdateComponent,
    resolve: {
      voiceServiceSpec: VoiceServiceSpecResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.voiceServiceSpec.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: VoiceServiceSpecUpdateComponent,
    resolve: {
      voiceServiceSpec: VoiceServiceSpecResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'crmwebApp.voiceServiceSpec.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
