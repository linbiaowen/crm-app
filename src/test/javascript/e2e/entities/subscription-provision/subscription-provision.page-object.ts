import { element, by, ElementFinder } from 'protractor';

export class SubscriptionProvisionComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-subscription-provision div table .btn-danger'));
  title = element.all(by.css('jhi-subscription-provision div h2#page-heading span')).first();
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

export class SubscriptionProvisionUpdatePage {
  pageTitle = element(by.id('jhi-subscription-provision-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  provisionSeqIdInput = element(by.id('field_provisionSeqId'));
  subscriptionIdInput = element(by.id('field_subscriptionId'));
  orderIdInput = element(by.id('field_orderId'));
  productIdInput = element(by.id('field_productId'));
  msisdnInput = element(by.id('field_msisdn'));
  iccidInput = element(by.id('field_iccid'));
  imsiInput = element(by.id('field_imsi'));
  serviceSpecIdInput = element(by.id('field_serviceSpecId'));
  resourceSpecIdInput = element(by.id('field_resourceSpecId'));
  rfsInput = element(by.id('field_rfs'));
  provisionStatusInput = element(by.id('field_provisionStatus'));
  provisionStatusDescInput = element(by.id('field_provisionStatusDesc'));
  provisionResponseInput = element(by.id('field_provisionResponse'));
  startDateInput = element(by.id('field_startDate'));
  endDateInput = element(by.id('field_endDate'));
  createdDateInput = element(by.id('field_createdDate'));
  createdByInput = element(by.id('field_createdBy'));
  lastUpdatedDateInput = element(by.id('field_lastUpdatedDate'));
  lastUpdatedByInput = element(by.id('field_lastUpdatedBy'));
  tenantIdInput = element(by.id('field_tenantId'));

  orderMasterSelect = element(by.id('field_orderMaster'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setProvisionSeqIdInput(provisionSeqId: string): Promise<void> {
    await this.provisionSeqIdInput.sendKeys(provisionSeqId);
  }

  async getProvisionSeqIdInput(): Promise<string> {
    return await this.provisionSeqIdInput.getAttribute('value');
  }

  async setSubscriptionIdInput(subscriptionId: string): Promise<void> {
    await this.subscriptionIdInput.sendKeys(subscriptionId);
  }

  async getSubscriptionIdInput(): Promise<string> {
    return await this.subscriptionIdInput.getAttribute('value');
  }

  async setOrderIdInput(orderId: string): Promise<void> {
    await this.orderIdInput.sendKeys(orderId);
  }

  async getOrderIdInput(): Promise<string> {
    return await this.orderIdInput.getAttribute('value');
  }

  async setProductIdInput(productId: string): Promise<void> {
    await this.productIdInput.sendKeys(productId);
  }

  async getProductIdInput(): Promise<string> {
    return await this.productIdInput.getAttribute('value');
  }

  async setMsisdnInput(msisdn: string): Promise<void> {
    await this.msisdnInput.sendKeys(msisdn);
  }

  async getMsisdnInput(): Promise<string> {
    return await this.msisdnInput.getAttribute('value');
  }

  async setIccidInput(iccid: string): Promise<void> {
    await this.iccidInput.sendKeys(iccid);
  }

  async getIccidInput(): Promise<string> {
    return await this.iccidInput.getAttribute('value');
  }

  async setImsiInput(imsi: string): Promise<void> {
    await this.imsiInput.sendKeys(imsi);
  }

  async getImsiInput(): Promise<string> {
    return await this.imsiInput.getAttribute('value');
  }

  async setServiceSpecIdInput(serviceSpecId: string): Promise<void> {
    await this.serviceSpecIdInput.sendKeys(serviceSpecId);
  }

  async getServiceSpecIdInput(): Promise<string> {
    return await this.serviceSpecIdInput.getAttribute('value');
  }

  async setResourceSpecIdInput(resourceSpecId: string): Promise<void> {
    await this.resourceSpecIdInput.sendKeys(resourceSpecId);
  }

  async getResourceSpecIdInput(): Promise<string> {
    return await this.resourceSpecIdInput.getAttribute('value');
  }

  async setRfsInput(rfs: string): Promise<void> {
    await this.rfsInput.sendKeys(rfs);
  }

  async getRfsInput(): Promise<string> {
    return await this.rfsInput.getAttribute('value');
  }

  async setProvisionStatusInput(provisionStatus: string): Promise<void> {
    await this.provisionStatusInput.sendKeys(provisionStatus);
  }

  async getProvisionStatusInput(): Promise<string> {
    return await this.provisionStatusInput.getAttribute('value');
  }

  async setProvisionStatusDescInput(provisionStatusDesc: string): Promise<void> {
    await this.provisionStatusDescInput.sendKeys(provisionStatusDesc);
  }

  async getProvisionStatusDescInput(): Promise<string> {
    return await this.provisionStatusDescInput.getAttribute('value');
  }

  async setProvisionResponseInput(provisionResponse: string): Promise<void> {
    await this.provisionResponseInput.sendKeys(provisionResponse);
  }

  async getProvisionResponseInput(): Promise<string> {
    return await this.provisionResponseInput.getAttribute('value');
  }

  async setStartDateInput(startDate: string): Promise<void> {
    await this.startDateInput.sendKeys(startDate);
  }

  async getStartDateInput(): Promise<string> {
    return await this.startDateInput.getAttribute('value');
  }

  async setEndDateInput(endDate: string): Promise<void> {
    await this.endDateInput.sendKeys(endDate);
  }

  async getEndDateInput(): Promise<string> {
    return await this.endDateInput.getAttribute('value');
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

  async orderMasterSelectLastOption(): Promise<void> {
    await this.orderMasterSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async orderMasterSelectOption(option: string): Promise<void> {
    await this.orderMasterSelect.sendKeys(option);
  }

  getOrderMasterSelect(): ElementFinder {
    return this.orderMasterSelect;
  }

  async getOrderMasterSelectedOption(): Promise<string> {
    return await this.orderMasterSelect.element(by.css('option:checked')).getText();
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

export class SubscriptionProvisionDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-subscriptionProvision-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-subscriptionProvision'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
