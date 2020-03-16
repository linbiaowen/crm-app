import { element, by, ElementFinder } from 'protractor';

export class ModelCategoryComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-model-category div table .btn-danger'));
  title = element.all(by.css('jhi-model-category div h2#page-heading span')).first();
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

export class ModelCategoryUpdatePage {
  pageTitle = element(by.id('jhi-model-category-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  modelCateInput = element(by.id('field_modelCate'));
  parentModelCateInput = element(by.id('field_parentModelCate'));
  modelCateDescInput = element(by.id('field_modelCateDesc'));
  createdDateInput = element(by.id('field_createdDate'));
  createdByInput = element(by.id('field_createdBy'));
  lastUpdatedDateInput = element(by.id('field_lastUpdatedDate'));
  lastUpdatedByInput = element(by.id('field_lastUpdatedBy'));
  tenantIdInput = element(by.id('field_tenantId'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setModelCateInput(modelCate: string): Promise<void> {
    await this.modelCateInput.sendKeys(modelCate);
  }

  async getModelCateInput(): Promise<string> {
    return await this.modelCateInput.getAttribute('value');
  }

  async setParentModelCateInput(parentModelCate: string): Promise<void> {
    await this.parentModelCateInput.sendKeys(parentModelCate);
  }

  async getParentModelCateInput(): Promise<string> {
    return await this.parentModelCateInput.getAttribute('value');
  }

  async setModelCateDescInput(modelCateDesc: string): Promise<void> {
    await this.modelCateDescInput.sendKeys(modelCateDesc);
  }

  async getModelCateDescInput(): Promise<string> {
    return await this.modelCateDescInput.getAttribute('value');
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

export class ModelCategoryDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-modelCategory-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-modelCategory'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
