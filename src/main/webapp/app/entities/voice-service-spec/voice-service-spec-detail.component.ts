import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVoiceServiceSpec } from 'app/shared/model/voice-service-spec.model';

@Component({
  selector: 'jhi-voice-service-spec-detail',
  templateUrl: './voice-service-spec-detail.component.html'
})
export class VoiceServiceSpecDetailComponent implements OnInit {
  voiceServiceSpec: IVoiceServiceSpec | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ voiceServiceSpec }) => (this.voiceServiceSpec = voiceServiceSpec));
  }

  previousState(): void {
    window.history.back();
  }
}
