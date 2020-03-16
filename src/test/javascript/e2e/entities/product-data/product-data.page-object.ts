import { element, by, ElementFinder } from 'protractor';

export class ProductDataComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-product-data div table .btn-danger'));
  title = element.all(by.css('jhi-product-data div h2#page-heading span')).first();
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

export class ProductDataUpdatePage {
  pageTitle = element(by.id('jhi-product-data-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  dataIdInput = element(by.id('field_dataId'));
  productSpecIdInput = element(by.id('field_productSpecId'));
  unitInput = element(by.id('field_unit'));
  volumeInput = element(by.id('field_volume'));
  dataSlabInput = element(by.id('field_dataSlab'));
  dataSpeedCategoryInput = element(by.id('field_dataSpeedCategory'));
  specicalPackTypeInput = element(by.id('field_specicalPackType'));
  dataServiceTypeInput = element(by.id('field_dataServiceType'));
  roamingRegionsInput = element(by.id('field_roamingRegions'));
  roamingCountriesInput = element(by.id('field_roamingCountries'));
  roamingDayPassTypeInput = element(by.id('field_roamingDayPassType'));
  roamingPackValidPeriodTypeInput = element(by.id('field_roamingPackValidPeriodType'));
  roamingPackPeriodInput = element(by.id('field_roamingPackPeriod'));
  roamingPackTermInput = element(by.id('field_roamingPackTerm'));
  minTransferQuotaInput = element(by.id('field_minTransferQuota'));
  maxTransferQuotaInput = element(by.id('field_maxTransferQuota'));
  minRetentionQuotaInput = element(by.id('field_minRetentionQuota'));
  minTpTransferQuotaInput = element(by.id('field_minTpTransferQuota'));
  maxTpTransferQuotaInput = element(by.id('field_maxTpTransferQuota'));
  minTpRetentionQuotaInput = element(by.id('field_minTpRetentionQuota'));
  createdDateInput = element(by.id('field_createdDate'));
  createdByInput = element(by.id('field_createdBy'));
  lastUpdatedDateInput = element(by.id('field_lastUpdatedDate'));
  lastUpdatedByInput = element(by.id('field_lastUpdatedBy'));
  tenantIdInput = element(by.id('field_tenantId'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setDataIdInput(dataId: string): Promise<void> {
    await this.dataIdInput.sendKeys(dataId);
  }

  async getDataIdInput(): Promise<string> {
    return await this.dataIdInput.getAttribute('value');
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

  async setDataSlabInput(dataSlab: string): Promise<void> {
    await this.dataSlabInput.sendKeys(dataSlab);
  }

  async getDataSlabInput(): Promise<string> {
    return await this.dataSlabInput.getAttribute('value');
  }

  async setDataSpeedCategoryInput(dataSpeedCategory: string): Promise<void> {
    await this.dataSpeedCategoryInput.sendKeys(dataSpeedCategory);
  }

  async getDataSpeedCategoryInput(): Promise<string> {
    return await this.dataSpeedCategoryInput.getAttribute('value');
  }

  async setSpecicalPackTypeInput(specicalPackType: string): Promise<void> {
    await this.specicalPackTypeInput.sendKeys(specicalPackType);
  }

  async getSpecicalPackTypeInput(): Promise<string> {
    return await this.specicalPackTypeInput.getAttribute('value');
  }

  async setDataServiceTypeInput(dataServiceType: string): Promise<void> {
    await this.dataServiceTypeInput.sendKeys(dataServiceType);
  }

  async getDataServiceTypeInput(): Promise<string> {
    return await this.dataServiceTypeInput.getAttribute('value');
  }

  async setRoamingRegionsInput(roamingRegions: string): Promise<void> {
    await this.roamingRegionsInput.sendKeys(roamingRegions);
  }

  async getRoamingRegionsInput(): Promise<string> {
    return await this.roamingRegionsInput.getAttribute('value');
  }

  async setRoamingCountriesInput(roamingCountries: string): Promise<void> {
    await this.roamingCountriesInput.sendKeys(roamingCountries);
  }

  async getRoamingCountriesInput(): Promise<string> {
    return await this.roamingCountriesInput.getAttribute('value');
  }

  async setRoamingDayPassTypeInput(roamingDayPassType: string): Promise<void> {
    await this.roamingDayPassTypeInput.sendKeys(roamingDayPassType);
  }

  async getRoamingDayPassTypeInput(): Promise<string> {
    return await this.roamingDayPassTypeInput.getAttribute('value');
  }

  async setRoamingPackValidPeriodTypeInput(roamingPackValidPeriodType: string): Promise<void> {
    await this.roamingPackValidPeriodTypeInput.sendKeys(roamingPackValidPeriodType);
  }

  async getRoamingPackValidPeriodTypeInput(): Promise<string> {
    return await this.roamingPackValidPeriodTypeInput.getAttribute('value');
  }

  async setRoamingPackPeriodInput(roamingPackPeriod: string): Promise<void> {
    await this.roamingPackPeriodInput.sendKeys(roamingPackPeriod);
  }

  async getRoamingPackPeriodInput(): Promise<string> {
    return await this.roamingPackPeriodInput.getAttribute('value');
  }

  async setRoamingPackTermInput(roamingPackTerm: string): Promise<void> {
    await this.roamingPackTermInput.sendKeys(roamingPackTerm);
  }

  async getRoamingPackTermInput(): Promise<string> {
    return await this.roamingPackTermInput.getAttribute('value');
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

  async setMinTpTransferQuotaInput(minTpTransferQuota: string): Promise<void> {
    await this.minTpTransferQuotaInput.sendKeys(minTpTransferQuota);
  }

  async getMinTpTransferQuotaInput(): Promise<string> {
    return await this.minTpTransferQuotaInput.getAttribute('value');
  }

  async setMaxTpTransferQuotaInput(maxTpTransferQuota: string): Promise<void> {
    await this.maxTpTransferQuotaInput.sendKeys(maxTpTransferQuota);
  }

  async getMaxTpTransferQuotaInput(): Promise<string> {
    return await this.maxTpTransferQuotaInput.getAttribute('value');
  }

  async setMinTpRetentionQuotaInput(minTpRetentionQuota: string): Promise<void> {
    await this.minTpRetentionQuotaInput.sendKeys(minTpRetentionQuota);
  }

  async getMinTpRetentionQuotaInput(): Promise<string> {
    return await this.minTpRetentionQuotaInput.getAttribute('value');
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

export class ProductDataDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-productData-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-productData'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
