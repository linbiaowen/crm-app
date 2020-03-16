import { element, by, ElementFinder } from 'protractor';

export class OrderProcessConfigComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-order-process-config div table .btn-danger'));
  title = element.all(by.css('jhi-order-process-config div h2#page-heading span')).first();
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

export class OrderProcessConfigUpdatePage {
  pageTitle = element(by.id('jhi-order-process-config-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  orderTypeInput = element(by.id('field_orderType'));
  subOrderTypeInput = element(by.id('field_subOrderType'));
  orderNatureInput = element(by.id('field_orderNature'));
  processNameInput = element(by.id('field_processName'));
  childProcessNameInput = element(by.id('field_childProcessName'));
  createdDateInput = element(by.id('field_createdDate'));
  createdByInput = element(by.id('field_createdBy'));
  lastUpdatedDateInput = element(by.id('field_lastUpdatedDate'));
  lastUpdatedByInput = element(by.id('field_lastUpdatedBy'));
  tenantIdInput = element(by.id('field_tenantId'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setOrderTypeInput(orderType: string): Promise<void> {
    await this.orderTypeInput.sendKeys(orderType);
  }

  async getOrderTypeInput(): Promise<string> {
    return await this.orderTypeInput.getAttribute('value');
  }

  async setSubOrderTypeInput(subOrderType: string): Promise<void> {
    await this.subOrderTypeInput.sendKeys(subOrderType);
  }

  async getSubOrderTypeInput(): Promise<string> {
    return await this.subOrderTypeInput.getAttribute('value');
  }

  async setOrderNatureInput(orderNature: string): Promise<void> {
    await this.orderNatureInput.sendKeys(orderNature);
  }

  async getOrderNatureInput(): Promise<string> {
    return await this.orderNatureInput.getAttribute('value');
  }

  async setProcessNameInput(processName: string): Promise<void> {
    await this.processNameInput.sendKeys(processName);
  }

  async getProcessNameInput(): Promise<string> {
    return await this.processNameInput.getAttribute('value');
  }

  async setChildProcessNameInput(childProcessName: string): Promise<void> {
    await this.childProcessNameInput.sendKeys(childProcessName);
  }

  async getChildProcessNameInput(): Promise<string> {
    return await this.childProcessNameInput.getAttribute('value');
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

export class OrderProcessConfigDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-orderProcessConfig-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-orderProcessConfig'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
