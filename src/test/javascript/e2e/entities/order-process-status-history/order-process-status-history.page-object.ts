import { element, by, ElementFinder } from 'protractor';

export class OrderProcessStatusHistoryComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-order-process-status-history div table .btn-danger'));
  title = element.all(by.css('jhi-order-process-status-history div h2#page-heading span')).first();
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

export class OrderProcessStatusHistoryUpdatePage {
  pageTitle = element(by.id('jhi-order-process-status-history-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  orderIdInput = element(by.id('field_orderId'));
  entryOrderStatusSelect = element(by.id('field_entryOrderStatus'));
  exitOrderStatusSelect = element(by.id('field_exitOrderStatus'));
  statusUpdatedDateInput = element(by.id('field_statusUpdatedDate'));
  processNameInput = element(by.id('field_processName'));
  statusSelect = element(by.id('field_status'));
  remarksInput = element(by.id('field_remarks'));
  createdDateInput = element(by.id('field_createdDate'));
  createdByInput = element(by.id('field_createdBy'));
  lastUpdatedDateInput = element(by.id('field_lastUpdatedDate'));
  lastUpdatedByInput = element(by.id('field_lastUpdatedBy'));
  tenantIdInput = element(by.id('field_tenantId'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setOrderIdInput(orderId: string): Promise<void> {
    await this.orderIdInput.sendKeys(orderId);
  }

  async getOrderIdInput(): Promise<string> {
    return await this.orderIdInput.getAttribute('value');
  }

  async setEntryOrderStatusSelect(entryOrderStatus: string): Promise<void> {
    await this.entryOrderStatusSelect.sendKeys(entryOrderStatus);
  }

  async getEntryOrderStatusSelect(): Promise<string> {
    return await this.entryOrderStatusSelect.element(by.css('option:checked')).getText();
  }

  async entryOrderStatusSelectLastOption(): Promise<void> {
    await this.entryOrderStatusSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setExitOrderStatusSelect(exitOrderStatus: string): Promise<void> {
    await this.exitOrderStatusSelect.sendKeys(exitOrderStatus);
  }

  async getExitOrderStatusSelect(): Promise<string> {
    return await this.exitOrderStatusSelect.element(by.css('option:checked')).getText();
  }

  async exitOrderStatusSelectLastOption(): Promise<void> {
    await this.exitOrderStatusSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setStatusUpdatedDateInput(statusUpdatedDate: string): Promise<void> {
    await this.statusUpdatedDateInput.sendKeys(statusUpdatedDate);
  }

  async getStatusUpdatedDateInput(): Promise<string> {
    return await this.statusUpdatedDateInput.getAttribute('value');
  }

  async setProcessNameInput(processName: string): Promise<void> {
    await this.processNameInput.sendKeys(processName);
  }

  async getProcessNameInput(): Promise<string> {
    return await this.processNameInput.getAttribute('value');
  }

  async setStatusSelect(status: string): Promise<void> {
    await this.statusSelect.sendKeys(status);
  }

  async getStatusSelect(): Promise<string> {
    return await this.statusSelect.element(by.css('option:checked')).getText();
  }

  async statusSelectLastOption(): Promise<void> {
    await this.statusSelect
      .all(by.tagName('option'))
      .last()
      .click();
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

export class OrderProcessStatusHistoryDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-orderProcessStatusHistory-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-orderProcessStatusHistory'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
