import { element, by, ElementFinder } from 'protractor';

export class VoiceServiceSpecComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-voice-service-spec div table .btn-danger'));
  title = element.all(by.css('jhi-voice-service-spec div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class VoiceServiceSpecUpdatePage {
  pageTitle = element(by.id('jhi-voice-service-spec-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  voiceSpecIdInput = element(by.id('field_voiceSpecId'));
  serviceSpecIdInput = element(by.id('field_serviceSpecId'));
  roamingIncomingEnabledInput = element(by.id('field_roamingIncomingEnabled'));
  roamingOutgoingEnabledInput = element(by.id('field_roamingOutgoingEnabled'));
  iddEnabledInput = element(by.id('field_iddEnabled'));
  iddOptionsInput = element(by.id('field_iddOptions'));
  callForwardEnabledInput = element(by.id('field_callForwardEnabled'));
  callWaitingEnabledInput = element(by.id('field_callWaitingEnabled'));
  clipEnabledInput = element(by.id('field_clipEnabled'));
  callBarringEnabledInput = element(by.id('field_callBarringEnabled'));
  mvrsEnabledInput = element(by.id('field_mvrsEnabled'));
  specialCallServicesInput = element(by.id('field_specialCallServices'));
  callRoutingSupportedInput = element(by.id('field_callRoutingSupported'));
  prbtSupportedInput = element(by.id('field_prbtSupported'));
  hrbtSupportedInput = element(by.id('field_hrbtSupported'));
  createdDateInput = element(by.id('field_createdDate'));
  createdByInput = element(by.id('field_createdBy'));
  lastUpdatedDateInput = element(by.id('field_lastUpdatedDate'));
  lastUpdatedByInput = element(by.id('field_lastUpdatedBy'));
  tenantIdInput = element(by.id('field_tenantId'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setVoiceSpecIdInput(voiceSpecId: string): Promise<void> {
    await this.voiceSpecIdInput.sendKeys(voiceSpecId);
  }

  async getVoiceSpecIdInput(): Promise<string> {
    return await this.voiceSpecIdInput.getAttribute('value');
  }

  async setServiceSpecIdInput(serviceSpecId: string): Promise<void> {
    await this.serviceSpecIdInput.sendKeys(serviceSpecId);
  }

  async getServiceSpecIdInput(): Promise<string> {
    return await this.serviceSpecIdInput.getAttribute('value');
  }

  getRoamingIncomingEnabledInput(): ElementFinder {
    return this.roamingIncomingEnabledInput;
  }

  getRoamingOutgoingEnabledInput(): ElementFinder {
    return this.roamingOutgoingEnabledInput;
  }

  getIddEnabledInput(): ElementFinder {
    return this.iddEnabledInput;
  }

  async setIddOptionsInput(iddOptions: string): Promise<void> {
    await this.iddOptionsInput.sendKeys(iddOptions);
  }

  async getIddOptionsInput(): Promise<string> {
    return await this.iddOptionsInput.getAttribute('value');
  }

  getCallForwardEnabledInput(): ElementFinder {
    return this.callForwardEnabledInput;
  }

  getCallWaitingEnabledInput(): ElementFinder {
    return this.callWaitingEnabledInput;
  }

  getClipEnabledInput(): ElementFinder {
    return this.clipEnabledInput;
  }

  getCallBarringEnabledInput(): ElementFinder {
    return this.callBarringEnabledInput;
  }

  getMvrsEnabledInput(): ElementFinder {
    return this.mvrsEnabledInput;
  }

  async setSpecialCallServicesInput(specialCallServices: string): Promise<void> {
    await this.specialCallServicesInput.sendKeys(specialCallServices);
  }

  async getSpecialCallServicesInput(): Promise<string> {
    return await this.specialCallServicesInput.getAttribute('value');
  }

  getCallRoutingSupportedInput(): ElementFinder {
    return this.callRoutingSupportedInput;
  }

  getPrbtSupportedInput(): ElementFinder {
    return this.prbtSupportedInput;
  }

  getHrbtSupportedInput(): ElementFinder {
    return this.hrbtSupportedInput;
  }

  async setCreatedDateInput(createdDate: string): Promise<void> {
    await this.createdDateInput.sendKeys(createdDate);
  }

  async getCreatedDateInput(): Promise<string> {
    return await this.createdDateInput.getAttribute('value');
  }

  async setCreatedByInput(createdBy: string): Promise<void> {
    await this.createdByInput.sendKeys(createdBy);
  }

  async getCreatedByInput(): Promise<string> {
    return await this.createdByInput.getAttribute('value');
  }

  async setLastUpdatedDateInput(lastUpdatedDate: string): Promise<void> {
    await this.lastUpdatedDateInput.sendKeys(lastUpdatedDate);
  }

  async getLastUpdatedDateInput(): Promise<string> {
    return await this.lastUpdatedDateInput.getAttribute('value');
  }

  async setLastUpdatedByInput(lastUpdatedBy: string): Promise<void> {
    await this.lastUpdatedByInput.sendKeys(lastUpdatedBy);
  }

  async getLastUpdatedByInput(): Promise<string> {
    return await this.lastUpdatedByInput.getAttribute('value');
  }

  async setTenantIdInput(tenantId: string): Promise<void> {
    await this.tenantIdInput.sendKeys(tenantId);
  }

  async getTenantIdInput(): Promise<string> {
    return await this.tenantIdInput.getAttribute('value');
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class VoiceServiceSpecDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-voiceServiceSpec-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-voiceServiceSpec'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
