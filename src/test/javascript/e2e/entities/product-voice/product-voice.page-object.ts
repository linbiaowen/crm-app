import { element, by, ElementFinder } from 'protractor';

export class ProductVoiceComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-product-voice div table .btn-danger'));
  title = element.all(by.css('jhi-product-voice div h2#page-heading span')).first();
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

export class ProductVoiceUpdatePage {
  pageTitle = element(by.id('jhi-product-voice-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  voiceIdInput = element(by.id('field_voiceId'));
  productSpecIdInput = element(by.id('field_productSpecId'));
  unitInput = element(by.id('field_unit'));
  volumeInput = element(by.id('field_volume'));
  roamingFlagInput = element(by.id('field_roamingFlag'));
  minTransferQuotaInput = element(by.id('field_minTransferQuota'));
  maxTransferQuotaInput = element(by.id('field_maxTransferQuota'));
  minRetentionQuotaInput = element(by.id('field_minRetentionQuota'));
  createdDateInput = element(by.id('field_createdDate'));
  createdByInput = element(by.id('field_createdBy'));
  lastUpdatedDateInput = element(by.id('field_lastUpdatedDate'));
  lastUpdatedByInput = element(by.id('field_lastUpdatedBy'));
  tenantIdInput = element(by.id('field_tenantId'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setVoiceIdInput(voiceId: string): Promise<void> {
    await this.voiceIdInput.sendKeys(voiceId);
  }

  async getVoiceIdInput(): Promise<string> {
    return await this.voiceIdInput.getAttribute('value');
  }

  async setProductSpecIdInput(productSpecId: string): Promise<void> {
    await this.productSpecIdInput.sendKeys(productSpecId);
  }

  async getProductSpecIdInput(): Promise<string> {
    return await this.productSpecIdInput.getAttribute('value');
  }

  async setUnitInput(unit: string): Promise<void> {
    await this.unitInput.sendKeys(unit);
  }

  async getUnitInput(): Promise<string> {
    return await this.unitInput.getAttribute('value');
  }

  async setVolumeInput(volume: string): Promise<void> {
    await this.volumeInput.sendKeys(volume);
  }

  async getVolumeInput(): Promise<string> {
    return await this.volumeInput.getAttribute('value');
  }

  getRoamingFlagInput(): ElementFinder {
    return this.roamingFlagInput;
  }

  async setMinTransferQuotaInput(minTransferQuota: string): Promise<void> {
    await this.minTransferQuotaInput.sendKeys(minTransferQuota);
  }

  async getMinTransferQuotaInput(): Promise<string> {
    return await this.minTransferQuotaInput.getAttribute('value');
  }

  async setMaxTransferQuotaInput(maxTransferQuota: string): Promise<void> {
    await this.maxTransferQuotaInput.sendKeys(maxTransferQuota);
  }

  async getMaxTransferQuotaInput(): Promise<string> {
    return await this.maxTransferQuotaInput.getAttribute('value');
  }

  async setMinRetentionQuotaInput(minRetentionQuota: string): Promise<void> {
    await this.minRetentionQuotaInput.sendKeys(minRetentionQuota);
  }

  async getMinRetentionQuotaInput(): Promise<string> {
    return await this.minRetentionQuotaInput.getAttribute('value');
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

export class ProductVoiceDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-productVoice-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-productVoice'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
