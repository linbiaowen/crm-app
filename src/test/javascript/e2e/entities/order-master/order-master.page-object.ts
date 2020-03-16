import { element, by, ElementFinder } from 'protractor';

export class OrderMasterComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-order-master div table .btn-danger'));
  title = element.all(by.css('jhi-order-master div h2#page-heading span')).first();
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

export class OrderMasterUpdatePage {
  pageTitle = element(by.id('jhi-order-master-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  orderIdInput = element(by.id('field_orderId'));
  custAcctIdInput = element(by.id('field_custAcctId'));
  subscriptionIdInput = element(by.id('field_subscriptionId'));
  orderTypeSelect = element(by.id('field_orderType'));
  subOrderTypeSelect = element(by.id('field_subOrderType'));
  orderNatureSelect = element(by.id('field_orderNature'));
  isChangePlanInput = element(by.id('field_isChangePlan'));
  orderStatusSelect = element(by.id('field_orderStatus'));
  remarksInput = element(by.id('field_remarks'));
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

  async setOrderIdInput(orderId: string): Promise<void> {
    await this.orderIdInput.sendKeys(orderId);
  }

  async getOrderIdInput(): Promise<string> {
    return await this.orderIdInput.getAttribute('value');
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

  async setOrderTypeSelect(orderType: string): Promise<void> {
    await this.orderTypeSelect.sendKeys(orderType);
  }

  async getOrderTypeSelect(): Promise<string> {
    return await this.orderTypeSelect.element(by.css('option:checked')).getText();
  }

  async orderTypeSelectLastOption(): Promise<void> {
    await this.orderTypeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setSubOrderTypeSelect(subOrderType: string): Promise<void> {
    await this.subOrderTypeSelect.sendKeys(subOrderType);
  }

  async getSubOrderTypeSelect(): Promise<string> {
    return await this.subOrderTypeSelect.element(by.css('option:checked')).getText();
  }

  async subOrderTypeSelectLastOption(): Promise<void> {
    await this.subOrderTypeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setOrderNatureSelect(orderNature: string): Promise<void> {
    await this.orderNatureSelect.sendKeys(orderNature);
  }

  async getOrderNatureSelect(): Promise<string> {
    return await this.orderNatureSelect.element(by.css('option:checked')).getText();
  }

  async orderNatureSelectLastOption(): Promise<void> {
    await this.orderNatureSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  getIsChangePlanInput(): ElementFinder {
    return this.isChangePlanInput;
  }

  async setOrderStatusSelect(orderStatus: string): Promise<void> {
    await this.orderStatusSelect.sendKeys(orderStatus);
  }

  async getOrderStatusSelect(): Promise<string> {
    return await this.orderStatusSelect.element(by.css('option:checked')).getText();
  }

  async orderStatusSelectLastOption(): Promise<void> {
    await this.orderStatusSelect
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

export class OrderMasterDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-orderMaster-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-orderMaster'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
