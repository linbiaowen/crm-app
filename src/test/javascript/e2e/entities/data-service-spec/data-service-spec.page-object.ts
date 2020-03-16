import { element, by, ElementFinder } from 'protractor';

export class DataServiceSpecComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-data-service-spec div table .btn-danger'));
  title = element.all(by.css('jhi-data-service-spec div h2#page-heading span')).first();
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

export class DataServiceSpecUpdatePage {
  pageTitle = element(by.id('jhi-data-service-spec-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  dataSpecIdInput = element(by.id('field_dataSpecId'));
  serviceSpecIdInput = element(by.id('field_serviceSpecId'));
  maxEntitlementInput = element(by.id('field_maxEntitlement'));
  maxAccessSpeedInput = element(by.id('field_maxAccessSpeed'));
  throttledSpeedInput = element(by.id('field_throttledSpeed'));
  upstreamSpeedInput = element(by.id('field_upstreamSpeed'));
  downstreamSpeedInput = element(by.id('field_downstreamSpeed'));
  socialSitesInput = element(by.id('field_socialSites'));
  entertainmentPackOptionsInput = element(by.id('field_entertainmentPackOptions'));
  roamingDataSpeedInput = element(by.id('field_roamingDataSpeed'));
  roamingDataVolumeInput = element(by.id('field_roamingDataVolume'));
  createdDateInput = element(by.id('field_createdDate'));
  createdByInput = element(by.id('field_createdBy'));
  lastUpdatedDateInput = element(by.id('field_lastUpdatedDate'));
  lastUpdatedByInput = element(by.id('field_lastUpdatedBy'));
  tenantIdInput = element(by.id('field_tenantId'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setDataSpecIdInput(dataSpecId: string): Promise<void> {
    await this.dataSpecIdInput.sendKeys(dataSpecId);
  }

  async getDataSpecIdInput(): Promise<string> {
    return await this.dataSpecIdInput.getAttribute('value');
  }

  async setServiceSpecIdInput(serviceSpecId: string): Promise<void> {
    await this.serviceSpecIdInput.sendKeys(serviceSpecId);
  }

  async getServiceSpecIdInput(): Promise<string> {
    return await this.serviceSpecIdInput.getAttribute('value');
  }

  async setMaxEntitlementInput(maxEntitlement: string): Promise<void> {
    await this.maxEntitlementInput.sendKeys(maxEntitlement);
  }

  async getMaxEntitlementInput(): Promise<string> {
    return await this.maxEntitlementInput.getAttribute('value');
  }

  async setMaxAccessSpeedInput(maxAccessSpeed: string): Promise<void> {
    await this.maxAccessSpeedInput.sendKeys(maxAccessSpeed);
  }

  async getMaxAccessSpeedInput(): Promise<string> {
    return await this.maxAccessSpeedInput.getAttribute('value');
  }

  async setThrottledSpeedInput(throttledSpeed: string): Promise<void> {
    await this.throttledSpeedInput.sendKeys(throttledSpeed);
  }

  async getThrottledSpeedInput(): Promise<string> {
    return await this.throttledSpeedInput.getAttribute('value');
  }

  async setUpstreamSpeedInput(upstreamSpeed: string): Promise<void> {
    await this.upstreamSpeedInput.sendKeys(upstreamSpeed);
  }

  async getUpstreamSpeedInput(): Promise<string> {
    return await this.upstreamSpeedInput.getAttribute('value');
  }

  async setDownstreamSpeedInput(downstreamSpeed: string): Promise<void> {
    await this.downstreamSpeedInput.sendKeys(downstreamSpeed);
  }

  async getDownstreamSpeedInput(): Promise<string> {
    return await this.downstreamSpeedInput.getAttribute('value');
  }

  async setSocialSitesInput(socialSites: string): Promise<void> {
    await this.socialSitesInput.sendKeys(socialSites);
  }

  async getSocialSitesInput(): Promise<string> {
    return await this.socialSitesInput.getAttribute('value');
  }

  async setEntertainmentPackOptionsInput(entertainmentPackOptions: string): Promise<void> {
    await this.entertainmentPackOptionsInput.sendKeys(entertainmentPackOptions);
  }

  async getEntertainmentPackOptionsInput(): Promise<string> {
    return await this.entertainmentPackOptionsInput.getAttribute('value');
  }

  async setRoamingDataSpeedInput(roamingDataSpeed: string): Promise<void> {
    await this.roamingDataSpeedInput.sendKeys(roamingDataSpeed);
  }

  async getRoamingDataSpeedInput(): Promise<string> {
    return await this.roamingDataSpeedInput.getAttribute('value');
  }

  async setRoamingDataVolumeInput(roamingDataVolume: string): Promise<void> {
    await this.roamingDataVolumeInput.sendKeys(roamingDataVolume);
  }

  async getRoamingDataVolumeInput(): Promise<string> {
    return await this.roamingDataVolumeInput.getAttribute('value');
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

export class DataServiceSpecDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-dataServiceSpec-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-dataServiceSpec'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
