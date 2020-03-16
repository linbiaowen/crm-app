import { element, by, ElementFinder } from 'protractor';

export class CustCommOptoutMasterComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-cust-comm-optout-master div table .btn-danger'));
  title = element.all(by.css('jhi-cust-comm-optout-master div h2#page-heading span')).first();
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

export class CustCommOptoutMasterUpdatePage {
  pageTitle = element(by.id('jhi-cust-comm-optout-master-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  optoutIdInput = element(by.id('field_optoutId'));
  custAcctIdInput = element(by.id('field_custAcctId'));
  subscriptionIdInput = element(by.id('field_subscriptionId'));
  primaryMobNbrInput = element(by.id('field_primaryMobNbr'));
  optoutTypeIdInput = element(by.id('field_optoutTypeId'));
  optoutMediaIdInput = element(by.id('field_optoutMediaId'));
  optoutStatusInput = element(by.id('field_optoutStatus'));
  optoutStartDateInput = element(by.id('field_optoutStartDate'));
  optoutEndDateInput = element(by.id('field_optoutEndDate'));
  createdDateInput = element(by.id('field_createdDate'));
  createdByInput = element(by.id('field_createdBy'));
  lastUpdatedDateInput = element(by.id('field_lastUpdatedDate'));
  lastUpdatedByInput = element(by.id('field_lastUpdatedBy'));
  tenantIdInput = element(by.id('field_tenantId'));

  customerSelect = element(by.id('field_customer'));
  custSubscriptionSelect = element(by.id('field_custSubscription'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setOptoutIdInput(optoutId: string): Promise<void> {
    await this.optoutIdInput.sendKeys(optoutId);
  }

  async getOptoutIdInput(): Promise<string> {
    return await this.optoutIdInput.getAttribute('value');
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

  async setPrimaryMobNbrInput(primaryMobNbr: string): Promise<void> {
    await this.primaryMobNbrInput.sendKeys(primaryMobNbr);
  }

  async getPrimaryMobNbrInput(): Promise<string> {
    return await this.primaryMobNbrInput.getAttribute('value');
  }

  async setOptoutTypeIdInput(optoutTypeId: string): Promise<void> {
    await this.optoutTypeIdInput.sendKeys(optoutTypeId);
  }

  async getOptoutTypeIdInput(): Promise<string> {
    return await this.optoutTypeIdInput.getAttribute('value');
  }

  async setOptoutMediaIdInput(optoutMediaId: string): Promise<void> {
    await this.optoutMediaIdInput.sendKeys(optoutMediaId);
  }

  async getOptoutMediaIdInput(): Promise<string> {
    return await this.optoutMediaIdInput.getAttribute('value');
  }

  async setOptoutStatusInput(optoutStatus: string): Promise<void> {
    await this.optoutStatusInput.sendKeys(optoutStatus);
  }

  async getOptoutStatusInput(): Promise<string> {
    return await this.optoutStatusInput.getAttribute('value');
  }

  async setOptoutStartDateInput(optoutStartDate: string): Promise<void> {
    await this.optoutStartDateInput.sendKeys(optoutStartDate);
  }

  async getOptoutStartDateInput(): Promise<string> {
    return await this.optoutStartDateInput.getAttribute('value');
  }

  async setOptoutEndDateInput(optoutEndDate: string): Promise<void> {
    await this.optoutEndDateInput.sendKeys(optoutEndDate);
  }

  async getOptoutEndDateInput(): Promise<string> {
    return await this.optoutEndDateInput.getAttribute('value');
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

export class CustCommOptoutMasterDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-custCommOptoutMaster-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-custCommOptoutMaster'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
