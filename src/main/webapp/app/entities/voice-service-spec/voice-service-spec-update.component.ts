import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IVoiceServiceSpec, VoiceServiceSpec } from 'app/shared/model/voice-service-spec.model';
import { VoiceServiceSpecService } from './voice-service-spec.service';

@Component({
  selector: 'jhi-voice-service-spec-update',
  templateUrl: './voice-service-spec-update.component.html'
})
export class VoiceServiceSpecUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    voiceSpecId: [null, [Validators.required]],
    serviceSpecId: [null, [Validators.required]],
    roamingIncomingEnabled: [],
    roamingOutgoingEnabled: [],
    iddEnabled: [],
    iddOptions: [],
    callForwardEnabled: [],
    callWaitingEnabled: [],
    clipEnabled: [],
    callBarringEnabled: [],
    mvrsEnabled: [],
    specialCallServices: [],
    callRoutingSupported: [],
    prbtSupported: [],
    hrbtSupported: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdatedDate: [null, [Validators.required]],
    lastUpdatedBy: [null, [Validators.required]],
    tenantId: [null, [Validators.required]]
  });

  constructor(
    protected voiceServiceSpecService: VoiceServiceSpecService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ voiceServiceSpec }) => {
      if (!voiceServiceSpec.id) {
        const today = moment().startOf('day');
        voiceServiceSpec.createdDate = today;
        voiceServiceSpec.lastUpdatedDate = today;
      }

      this.updateForm(voiceServiceSpec);
    });
  }

  updateForm(voiceServiceSpec: IVoiceServiceSpec): void {
    this.editForm.patchValue({
      id: voiceServiceSpec.id,
      voiceSpecId: voiceServiceSpec.voiceSpecId,
      serviceSpecId: voiceServiceSpec.serviceSpecId,
      roamingIncomingEnabled: voiceServiceSpec.roamingIncomingEnabled,
      roamingOutgoingEnabled: voiceServiceSpec.roamingOutgoingEnabled,
      iddEnabled: voiceServiceSpec.iddEnabled,
      iddOptions: voiceServiceSpec.iddOptions,
      callForwardEnabled: voiceServiceSpec.callForwardEnabled,
      callWaitingEnabled: voiceServiceSpec.callWaitingEnabled,
      clipEnabled: voiceServiceSpec.clipEnabled,
      callBarringEnabled: voiceServiceSpec.callBarringEnabled,
      mvrsEnabled: voiceServiceSpec.mvrsEnabled,
      specialCallServices: voiceServiceSpec.specialCallServices,
      callRoutingSupported: voiceServiceSpec.callRoutingSupported,
      prbtSupported: voiceServiceSpec.prbtSupported,
      hrbtSupported: voiceServiceSpec.hrbtSupported,
      createdDate: voiceServiceSpec.createdDate ? voiceServiceSpec.createdDate.format(DATE_TIME_FORMAT) : null,
      createdBy: voiceServiceSpec.createdBy,
      lastUpdatedDate: voiceServiceSpec.lastUpdatedDate ? voiceServiceSpec.lastUpdatedDate.format(DATE_TIME_FORMAT) : null,
      lastUpdatedBy: voiceServiceSpec.lastUpdatedBy,
      tenantId: voiceServiceSpec.tenantId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const voiceServiceSpec = this.createFromForm();
    if (voiceServiceSpec.id !== undefined) {
      this.subscribeToSaveResponse(this.voiceServiceSpecService.update(voiceServiceSpec));
    } else {
      this.subscribeToSaveResponse(this.voiceServiceSpecService.create(voiceServiceSpec));
    }
  }

  private createFromForm(): IVoiceServiceSpec {
    return {
      ...new VoiceServiceSpec(),
      id: this.editForm.get(['id'])!.value,
      voiceSpecId: this.editForm.get(['voiceSpecId'])!.value,
      serviceSpecId: this.editForm.get(['serviceSpecId'])!.value,
      roamingIncomingEnabled: this.editForm.get(['roamingIncomingEnabled'])!.value,
      roamingOutgoingEnabled: this.editForm.get(['roamingOutgoingEnabled'])!.value,
      iddEnabled: this.editForm.get(['iddEnabled'])!.value,
      iddOptions: this.editForm.get(['iddOptions'])!.value,
      callForwardEnabled: this.editForm.get(['callForwardEnabled'])!.value,
      callWaitingEnabled: this.editForm.get(['callWaitingEnabled'])!.value,
      clipEnabled: this.editForm.get(['clipEnabled'])!.value,
      callBarringEnabled: this.editForm.get(['callBarringEnabled'])!.value,
      mvrsEnabled: this.editForm.get(['mvrsEnabled'])!.value,
      specialCallServices: this.editForm.get(['specialCallServices'])!.value,
      callRoutingSupported: this.editForm.get(['callRoutingSupported'])!.value,
      prbtSupported: this.editForm.get(['prbtSupported'])!.value,
      hrbtSupported: this.editForm.get(['hrbtSupported'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value
        ? moment(this.editForm.get(['createdDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
      lastUpdatedDate: this.editForm.get(['lastUpdatedDate'])!.value
        ? moment(this.editForm.get(['lastUpdatedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastUpdatedBy: this.editForm.get(['lastUpdatedBy'])!.value,
      tenantId: this.editForm.get(['tenantId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVoiceServiceSpec>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
