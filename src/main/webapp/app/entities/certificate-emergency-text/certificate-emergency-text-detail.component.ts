import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICertificateEmergencyText } from 'app/shared/model/certificate-emergency-text.model';

@Component({
  selector: 'jhi-certificate-emergency-text-detail',
  templateUrl: './certificate-emergency-text-detail.component.html',
})
export class CertificateEmergencyTextDetailComponent implements OnInit {
  certificateEmergencyText: ICertificateEmergencyText | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ certificateEmergencyText }) => (this.certificateEmergencyText = certificateEmergencyText));
  }

  previousState(): void {
    window.history.back();
  }
}
