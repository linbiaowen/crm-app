import { element, by, ElementFinder } from 'protractor';

export class ResourceSpecificationComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-resource-specification div table .btn-danger'));
  title = element.all(by.css('jhi-resource-specification div h2#page-heading span')).first();
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

export class ResourceSpecificationUpdatePage {
  pageTitle = element(by.id('jhi-resource-specification-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  resourceSpecIdInput = element(by.id('field_resourceSpecId'));
  resourceTypeSelect = element(by.id('field_resourceType'));
  serviceSpecIdInput = element(by.id('field_serviceSpecId'));
  rfsInput = element(by.id('field_rfs'));
  rfsParmsInput = element(by.id('field_rfsParms'));
  remarksInput = element(by.id('field_remarks'));
  createdDateInput = element(by.id('field_createdDate'));
  createdByInput = element(by.id('field_createdBy'));
  lastUpdatedDateInput = element(by.id('field_lastUpdatedDate'));
  lastUpdatedByInput = element(by.id('field_lastUpdatedBy'));
  tenantIdInput = element(by.id('field_tenantId'));

  cfsServiceSpecSelect = element(by.id('field_cfsServiceSpec'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setResourceSpecIdInput(resourceSpecId: string): Promise<void> {
    await this.resourceSpecIdInput.sendKeys(resourceSpecId);
  }

  async getResourceSpecIdInput(): Promise<string> {
    return await this.resourceSpecIdInput.getAttribute('value');
  }

  async setResourceTypeSelect(resourceType: string): Promise<void> {
    await this.resourceTypeSelect.sendKeys(resourceType);
  }

  async getResourceTypeSelect(): Promise<string> {
    return await this.resourceTypeSelect.element(by.css('option:checked')).getText();
  }

  async resourceTypeSelectLastOption(): Promise<void> {
    await this.resourceTypeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setServiceSpecIdInput(serviceSpecId: string): Promise<void> {
    await this.serviceSpecIdInput.sendKeys(serviceSpecId);
  }

  async getServiceSpecIdInput(): Promise<string> {
    return await this.serviceSpecIdInput.getAttribute('value');
  }

  async setRfsInput(rfs: string): Promise<void> {
    await this.rfsInput.sendKeys(rfs);
  }

  async getRfsInput(): Promise<string> {
    return await this.rfsInput.getAttribute('value');
  }

  async setRfsParmsInput(rfsParms: string): Promise<void> {
    await this.rfsParmsInput.sendKeys(rfsParms);
  }

  async getRfsParmsInput(): Promise<string> {
    return await this.rfsParmsInput.getAttribute('value');
  }

  async setRemarksInput(remarks: string): Promise<void> {
    await this.remarksInput.sendKeys(remarks);
  }

  async getRemarksInput(): Promise<string> {
    return await this.remarksInput.getAttribute('value');
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

export class ResourceSpecificationDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-resourceSpecification-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-resourceSpecification'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
