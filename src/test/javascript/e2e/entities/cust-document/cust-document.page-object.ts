import { element, by, ElementFinder } from 'protractor';

export class CustDocumentComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-cust-document div table .btn-danger'));
  title = element.all(by.css('jhi-cust-document div h2#page-heading span')).first();
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

export class CustDocumentUpdatePage {
  pageTitle = element(by.id('jhi-cust-document-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  custDocIdInput = element(by.id('field_custDocId'));
  custAcctIdInput = element(by.id('field_custAcctId'));
  subscriptionIdInput = element(by.id('field_subscriptionId'));
  docTypeSelect = element(by.id('field_docType'));
  docIdNumberInput = element(by.id('field_docIdNumber'));
  docDataStoreIdInput = element(by.id('field_docDataStoreId'));
  createdDateInput = element(by.id('field_createdDate'));
  createdByInput = element(by.id('field_createdBy'));
  lastUpdatedDateInput = element(by.id('field_lastUpdatedDate'));
  lastUpdatedByInput = element(by.id('field_lastUpdatedBy'));
  tenantIdInput = element(by.id('field_tenantId'));

  docDataStoreSelect = element(by.id('field_docDataStore'));
  customerSelect = element(by.id('field_customer'));
  custSubscriptionSelect = element(by.id('field_custSubscription'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setCustDocIdInput(custDocId: string): Promise<void> {
    await this.custDocIdInput.sendKeys(custDocId);
  }

  async getCustDocIdInput(): Promise<string> {
    return await this.custDocIdInput.getAttribute('value');
  }

  async setCustAcctIdInput(custAcctId: string): Promise<void> {
    await this.custAcctIdInput.sendKeys(custAcctId);
  }

  async getCustAcctIdInput(): Promise<string> {
    return await this.custAcctIdInput.getAttribute('value');
  }

  async setSubscriptionIdInput(subscriptionId: string): Promise<void> {
    await this.subscriptionIdInput.sendKeys(subscriptionId);
  }

  async getSubscriptionIdInput(): Promise<string> {
    return await this.subscriptionIdInput.getAttribute('value');
  }

  async setDocTypeSelect(docType: string): Promise<void> {
    await this.docTypeSelect.sendKeys(docType);
  }

  async getDocTypeSelect(): Promise<string> {
    return await this.docTypeSelect.element(by.css('option:checked')).getText();
  }

  async docTypeSelectLastOption(): Promise<void> {
    await this.docTypeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setDocIdNumberInput(docIdNumber: string): Promise<void> {
    await this.docIdNumberInput.sendKeys(docIdNumber);
  }

  async getDocIdNumberInput(): Promise<string> {
    return await this.docIdNumberInput.getAttribute('value');
  }

  async setDocDataStoreIdInput(docDataStoreId: string): Promise<void> {
    await this.docDataStoreIdInput.sendKeys(docDataStoreId);
  }

  async getDocDataStoreIdInput(): Promise<string> {
    return await this.docDataStoreIdInput.getAttribute('value');
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

  async docDataStoreSelectLastOption(): Promise<void> {
    await this.docDataStoreSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async docDataStoreSelectOption(option: string): Promise<void> {
    await this.docDataStoreSelect.sendKeys(option);
  }

  getDocDataStoreSelect(): ElementFinder {
    return this.docDataStoreSelect;
  }

  async getDocDataStoreSelectedOption(): Promise<string> {
    return await this.docDataStoreSelect.element(by.css('option:checked')).getText();
  }

  async customerSelectLastOption(): Promise<void> {
    await this.customerSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async customerSelectOption(option: string): Promise<void> {
    await this.customerSelect.sendKeys(option);
  }

  getCustomerSelect(): ElementFinder {
    return this.customerSelect;
  }

  async getCustomerSelectedOption(): Promise<string> {
    return await this.customerSelect.element(by.css('option:checked')).getText();
  }

  async custSubscriptionSelectLastOption(): Promise<void> {
    await this.custSubscriptionSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async custSubscriptionSelectOption(option: string): Promise<void> {
    await this.custSubscriptionSelect.sendKeys(option);
  }

  getCustSubscriptionSelect(): ElementFinder {
    return this.custSubscriptionSelect;
  }

  async getCustSubscriptionSelectedOption(): Promise<string> {
    return await this.custSubscriptionSelect.element(by.css('option:checked')).getText();
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

export class CustDocumentDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-custDocument-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-custDocument'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
