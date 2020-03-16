import { element, by, ElementFinder } from 'protractor';

export class ProductSimAttributesComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-product-sim-attributes div table .btn-danger'));
  title = element.all(by.css('jhi-product-sim-attributes div h2#page-heading span')).first();
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

export class ProductSimAttributesUpdatePage {
  pageTitle = element(by.id('jhi-product-sim-attributes-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  simTypeSelect = element(by.id('field_simType'));
  imsiRangeFromInput = element(by.id('field_imsiRangeFrom'));
  imsiRangeToInput = element(by.id('field_imsiRangeTo'));
  simRepositoryRefInput = element(by.id('field_simRepositoryRef'));
  createdDateInput = element(by.id('field_createdDate'));
  createdByInput = element(by.id('field_createdBy'));
  lastUpdatedDateInput = element(by.id('field_lastUpdatedDate'));
  lastUpdatedByInput = element(by.id('field_lastUpdatedBy'));
  tenantIdInput = element(by.id('field_tenantId'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
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

  async setImsiRangeFromInput(imsiRangeFrom: string): Promise<void> {
    await this.imsiRangeFromInput.sendKeys(imsiRangeFrom);
  }

  async getImsiRangeFromInput(): Promise<string> {
    return await this.imsiRangeFromInput.getAttribute('value');
  }

  async setImsiRangeToInput(imsiRangeTo: string): Promise<void> {
    await this.imsiRangeToInput.sendKeys(imsiRangeTo);
  }

  async getImsiRangeToInput(): Promise<string> {
    return await this.imsiRangeToInput.getAttribute('value');
  }

  async setSimRepositoryRefInput(simRepositoryRef: string): Promise<void> {
    await this.simRepositoryRefInput.sendKeys(simRepositoryRef);
  }

  async getSimRepositoryRefInput(): Promise<string> {
    return await this.simRepositoryRefInput.getAttribute('value');
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

export class ProductSimAttributesDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-productSimAttributes-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-productSimAttributes'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
