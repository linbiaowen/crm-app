import { element, by, ElementFinder } from 'protractor';

export class CfsServicesComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-cfs-services div table .btn-danger'));
  title = element.all(by.css('jhi-cfs-services div h2#page-heading span')).first();
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

export class CfsServicesUpdatePage {
  pageTitle = element(by.id('jhi-cfs-services-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  serviceIdInput = element(by.id('field_serviceId'));
  serviceNameInput = element(by.id('field_serviceName'));
  createdDateInput = element(by.id('field_createdDate'));
  createdByInput = element(by.id('field_createdBy'));
  lastUpdatedDateInput = element(by.id('field_lastUpdatedDate'));
  lastUpdatedByInput = element(by.id('field_lastUpdatedBy'));
  tenantIdInput = element(by.id('field_tenantId'));

  cfsServiceSpecSelect = element(by.id('field_cfsServiceSpec'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setServiceIdInput(serviceId: string): Promise<void> {
    await this.serviceIdInput.sendKeys(serviceId);
  }

  async getServiceIdInput(): Promise<string> {
    return await this.serviceIdInput.getAttribute('value');
  }

  async setServiceNameInput(serviceName: string): Promise<void> {
    await this.serviceNameInput.sendKeys(serviceName);
  }

  async getServiceNameInput(): Promise<string> {
    return await this.serviceNameInput.getAttribute('value');
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

  async cfsServiceSpecSelectLastOption(): Promise<void> {
    await this.cfsServiceSpecSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async cfsServiceSpecSelectOption(option: string): Promise<void> {
    await this.cfsServiceSpecSelect.sendKeys(option);
  }

  getCfsServiceSpecSelect(): ElementFinder {
    return this.cfsServiceSpecSelect;
  }

  async getCfsServiceSpecSelectedOption(): Promise<string> {
    return await this.cfsServiceSpecSelect.element(by.css('option:checked')).getText();
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

export class CfsServicesDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-cfsServices-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-cfsServices'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
