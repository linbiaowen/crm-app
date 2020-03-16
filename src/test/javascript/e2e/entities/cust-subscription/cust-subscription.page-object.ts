import { element, by, ElementFinder } from 'protractor';

export class CustSubscriptionComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-cust-subscription div table .btn-danger'));
  title = element.all(by.css('jhi-cust-subscription div h2#page-heading span')).first();
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

export class CustSubscriptionUpdatePage {
  pageTitle = element(by.id('jhi-cust-subscription-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  subscriptionIdInput = element(by.id('field_subscriptionId'));
  activationDateInput = element(by.id('field_activationDate'));
  subsEndDateInput = element(by.id('field_subsEndDate'));
  subsPurchaseDateInput = element(by.id('field_subsPurchaseDate'));
  origServiceStartDateInput = element(by.id('field_origServiceStartDate'));
  statusSelect = element(by.id('field_status'));
  primarySubsIndInput = element(by.id('field_primarySubsInd'));
  subsLastStatusCodeInput = element(by.id('field_subsLastStatusCode'));
  lastStatusUpdatedDateInput = element(by.id('field_lastStatusUpdatedDate'));
  custAcctIdInput = element(by.id('field_custAcctId'));
  billingAcctIdInput = element(by.id('field_billingAcctId'));
  billCycleIdInput = element(by.id('field_billCycleId'));
  orderIdInput = element(by.id('field_orderId'));
  matrixxObjectIdInput = element(by.id('field_matrixxObjectId'));
  subscriberNameInput = element(by.id('field_subscriberName'));
  subsDeptNameInput = element(by.id('field_subsDeptName'));
  selfCarePasswordInput = element(by.id('field_selfCarePassword'));
  subsCategoryInput = element(by.id('field_subsCategory'));
  createdDateInput = element(by.id('field_createdDate'));
  createdByInput = element(by.id('field_createdBy'));
  lastUpdatedDateInput = element(by.id('field_lastUpdatedDate'));
  lastUpdatedByInput = element(by.id('field_lastUpdatedBy'));
  tenantIdInput = element(by.id('field_tenantId'));

  customerSelect = element(by.id('field_customer'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setSubscriptionIdInput(subscriptionId: string): Promise<void> {
    await this.subscriptionIdInput.sendKeys(subscriptionId);
  }

  async getSubscriptionIdInput(): Promise<string> {
    return await this.subscriptionIdInput.getAttribute('value');
  }

  async setActivationDateInput(activationDate: string): Promise<void> {
    await this.activationDateInput.sendKeys(activationDate);
  }

  async getActivationDateInput(): Promise<string> {
    return await this.activationDateInput.getAttribute('value');
  }

  async setSubsEndDateInput(subsEndDate: string): Promise<void> {
    await this.subsEndDateInput.sendKeys(subsEndDate);
  }

  async getSubsEndDateInput(): Promise<string> {
    return await this.subsEndDateInput.getAttribute('value');
  }

  async setSubsPurchaseDateInput(subsPurchaseDate: string): Promise<void> {
    await this.subsPurchaseDateInput.sendKeys(subsPurchaseDate);
  }

  async getSubsPurchaseDateInput(): Promise<string> {
    return await this.subsPurchaseDateInput.getAttribute('value');
  }

  async setOrigServiceStartDateInput(origServiceStartDate: string): Promise<void> {
    await this.origServiceStartDateInput.sendKeys(origServiceStartDate);
  }

  async getOrigServiceStartDateInput(): Promise<string> {
    return await this.origServiceStartDateInput.getAttribute('value');
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

  getPrimarySubsIndInput(): ElementFinder {
    return this.primarySubsIndInput;
  }

  async setSubsLastStatusCodeInput(subsLastStatusCode: string): Promise<void> {
    await this.subsLastStatusCodeInput.sendKeys(subsLastStatusCode);
  }

  async getSubsLastStatusCodeInput(): Promise<string> {
    return await this.subsLastStatusCodeInput.getAttribute('value');
  }

  async setLastStatusUpdatedDateInput(lastStatusUpdatedDate: string): Promise<void> {
    await this.lastStatusUpdatedDateInput.sendKeys(lastStatusUpdatedDate);
  }

  async getLastStatusUpdatedDateInput(): Promise<string> {
    return await this.lastStatusUpdatedDateInput.getAttribute('value');
  }

  async setCustAcctIdInput(custAcctId: string): Promise<void> {
    await this.custAcctIdInput.sendKeys(custAcctId);
  }

  async getCustAcctIdInput(): Promise<string> {
    return await this.custAcctIdInput.getAttribute('value');
  }

  async setBillingAcctIdInput(billingAcctId: string): Promise<void> {
    await this.billingAcctIdInput.sendKeys(billingAcctId);
  }

  async getBillingAcctIdInput(): Promise<string> {
    return await this.billingAcctIdInput.getAttribute('value');
  }

  async setBillCycleIdInput(billCycleId: string): Promise<void> {
    await this.billCycleIdInput.sendKeys(billCycleId);
  }

  async getBillCycleIdInput(): Promise<string> {
    return await this.billCycleIdInput.getAttribute('value');
  }

  async setOrderIdInput(orderId: string): Promise<void> {
    await this.orderIdInput.sendKeys(orderId);
  }

  async getOrderIdInput(): Promise<string> {
    return await this.orderIdInput.getAttribute('value');
  }

  async setMatrixxObjectIdInput(matrixxObjectId: string): Promise<void> {
    await this.matrixxObjectIdInput.sendKeys(matrixxObjectId);
  }

  async getMatrixxObjectIdInput(): Promise<string> {
    return await this.matrixxObjectIdInput.getAttribute('value');
  }

  async setSubscriberNameInput(subscriberName: string): Promise<void> {
    await this.subscriberNameInput.sendKeys(subscriberName);
  }

  async getSubscriberNameInput(): Promise<string> {
    return await this.subscriberNameInput.getAttribute('value');
  }

  async setSubsDeptNameInput(subsDeptName: string): Promise<void> {
    await this.subsDeptNameInput.sendKeys(subsDeptName);
  }

  async getSubsDeptNameInput(): Promise<string> {
    return await this.subsDeptNameInput.getAttribute('value');
  }

  async setSelfCarePasswordInput(selfCarePassword: string): Promise<void> {
    await this.selfCarePasswordInput.sendKeys(selfCarePassword);
  }

  async getSelfCarePasswordInput(): Promise<string> {
    return await this.selfCarePasswordInput.getAttribute('value');
  }

  async setSubsCategoryInput(subsCategory: string): Promise<void> {
    await this.subsCategoryInput.sendKeys(subsCategory);
  }

  async getSubsCategoryInput(): Promise<string> {
    return await this.subsCategoryInput.getAttribute('value');
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

export class CustSubscriptionDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-custSubscription-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-custSubscription'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
