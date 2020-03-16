import { element, by, ElementFinder } from 'protractor';

export class ProductSpecificationComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-product-specification div table .btn-danger'));
  title = element.all(by.css('jhi-product-specification div h2#page-heading span')).first();
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

export class ProductSpecificationUpdatePage {
  pageTitle = element(by.id('jhi-product-specification-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  productSpecIdInput = element(by.id('field_productSpecId'));
  productIdInput = element(by.id('field_productId'));
  serviceSpecIdInput = element(by.id('field_serviceSpecId'));
  productSpecTypeSelect = element(by.id('field_productSpecType'));
  skuTypeSelect = element(by.id('field_skuType'));
  simTypeSelect = element(by.id('field_simType'));
  boxTypeSelect = element(by.id('field_boxType'));
  createdDateInput = element(by.id('field_createdDate'));
  createdByInput = element(by.id('field_createdBy'));
  lastUpdatedDateInput = element(by.id('field_lastUpdatedDate'));
  lastUpdatedByInput = element(by.id('field_lastUpdatedBy'));
  tenantIdInput = element(by.id('field_tenantId'));

  voiceSelect = element(by.id('field_voice'));
  dataSelect = element(by.id('field_data'));
  smsSelect = element(by.id('field_sms'));
  offerSelect = element(by.id('field_offer'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setProductSpecIdInput(productSpecId: string): Promise<void> {
    await this.productSpecIdInput.sendKeys(productSpecId);
  }

  async getProductSpecIdInput(): Promise<string> {
    return await this.productSpecIdInput.getAttribute('value');
  }

  async setProductIdInput(productId: string): Promise<void> {
    await this.productIdInput.sendKeys(productId);
  }

  async getProductIdInput(): Promise<string> {
    return await this.productIdInput.getAttribute('value');
  }

  async setServiceSpecIdInput(serviceSpecId: string): Promise<void> {
    await this.serviceSpecIdInput.sendKeys(serviceSpecId);
  }

  async getServiceSpecIdInput(): Promise<string> {
    return await this.serviceSpecIdInput.getAttribute('value');
  }

  async setProductSpecTypeSelect(productSpecType: string): Promise<void> {
    await this.productSpecTypeSelect.sendKeys(productSpecType);
  }

  async getProductSpecTypeSelect(): Promise<string> {
    return await this.productSpecTypeSelect.element(by.css('option:checked')).getText();
  }

  async productSpecTypeSelectLastOption(): Promise<void> {
    await this.productSpecTypeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setSkuTypeSelect(skuType: string): Promise<void> {
    await this.skuTypeSelect.sendKeys(skuType);
  }

  async getSkuTypeSelect(): Promise<string> {
    return await this.skuTypeSelect.element(by.css('option:checked')).getText();
  }

  async skuTypeSelectLastOption(): Promise<void> {
    await this.skuTypeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setSimTypeSelect(simType: string): Promise<void> {
    await this.simTypeSelect.sendKeys(simType);
  }

  async getSimTypeSelect(): Promise<string> {
    return await this.simTypeSelect.element(by.css('option:checked')).getText();
  }

  async simTypeSelectLastOption(): Promise<void> {
    await this.simTypeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setBoxTypeSelect(boxType: string): Promise<void> {
    await this.boxTypeSelect.sendKeys(boxType);
  }

  async getBoxTypeSelect(): Promise<string> {
    return await this.boxTypeSelect.element(by.css('option:checked')).getText();
  }

  async boxTypeSelectLastOption(): Promise<void> {
    await this.boxTypeSelect
      .all(by.tagName('option'))
      .last()
      .click();
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

  async voiceSelectLastOption(): Promise<void> {
    await this.voiceSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async voiceSelectOption(option: string): Promise<void> {
    await this.voiceSelect.sendKeys(option);
  }

  getVoiceSelect(): ElementFinder {
    return this.voiceSelect;
  }

  async getVoiceSelectedOption(): Promise<string> {
    return await this.voiceSelect.element(by.css('option:checked')).getText();
  }

  async dataSelectLastOption(): Promise<void> {
    await this.dataSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async dataSelectOption(option: string): Promise<void> {
    await this.dataSelect.sendKeys(option);
  }

  getDataSelect(): ElementFinder {
    return this.dataSelect;
  }

  async getDataSelectedOption(): Promise<string> {
    return await this.dataSelect.element(by.css('option:checked')).getText();
  }

  async smsSelectLastOption(): Promise<void> {
    await this.smsSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async smsSelectOption(option: string): Promise<void> {
    await this.smsSelect.sendKeys(option);
  }

  getSmsSelect(): ElementFinder {
    return this.smsSelect;
  }

  async getSmsSelectedOption(): Promise<string> {
    return await this.smsSelect.element(by.css('option:checked')).getText();
  }

  async offerSelectLastOption(): Promise<void> {
    await this.offerSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async offerSelectOption(option: string): Promise<void> {
    await this.offerSelect.sendKeys(option);
  }

  getOfferSelect(): ElementFinder {
    return this.offerSelect;
  }

  async getOfferSelectedOption(): Promise<string> {
    return await this.offerSelect.element(by.css('option:checked')).getText();
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

export class ProductSpecificationDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-productSpecification-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-productSpecification'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
