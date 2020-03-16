import { element, by, ElementFinder } from 'protractor';

export class SubsItemDeliveryComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-subs-item-delivery div table .btn-danger'));
  title = element.all(by.css('jhi-subs-item-delivery div h2#page-heading span')).first();
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

export class SubsItemDeliveryUpdatePage {
  pageTitle = element(by.id('jhi-subs-item-delivery-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  deliveryIdInput = element(by.id('field_deliveryId'));
  orderIdInput = element(by.id('field_orderId'));
  subscriptionIdInput = element(by.id('field_subscriptionId'));
  subscriptionItemIdInput = element(by.id('field_subscriptionItemId'));
  deliveryStatusInput = element(by.id('field_deliveryStatus'));
  deliveryMethodIdInput = element(by.id('field_deliveryMethodId'));
  deliveryRefCodeInput = element(by.id('field_deliveryRefCode'));
  fileGenerationDateInput = element(by.id('field_fileGenerationDate'));
  fileReceivedDateInput = element(by.id('field_fileReceivedDate'));
  deliveryDateInput = element(by.id('field_deliveryDate'));
  remarksInput = element(by.id('field_remarks'));
  createdDateInput = element(by.id('field_createdDate'));
  createdByInput = element(by.id('field_createdBy'));
  lastUpdatedDateInput = element(by.id('field_lastUpdatedDate'));
  lastUpdatedByInput = element(by.id('field_lastUpdatedBy'));
  tenantIdInput = element(by.id('field_tenantId'));

  orderMasterSelect = element(by.id('field_orderMaster'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setDeliveryIdInput(deliveryId: string): Promise<void> {
    await this.deliveryIdInput.sendKeys(deliveryId);
  }

  async getDeliveryIdInput(): Promise<string> {
    return await this.deliveryIdInput.getAttribute('value');
  }

  async setOrderIdInput(orderId: string): Promise<void> {
    await this.orderIdInput.sendKeys(orderId);
  }

  async getOrderIdInput(): Promise<string> {
    return await this.orderIdInput.getAttribute('value');
  }

  async setSubscriptionIdInput(subscriptionId: string): Promise<void> {
    await this.subscriptionIdInput.sendKeys(subscriptionId);
  }

  async getSubscriptionIdInput(): Promise<string> {
    return await this.subscriptionIdInput.getAttribute('value');
  }

  async setSubscriptionItemIdInput(subscriptionItemId: string): Promise<void> {
    await this.subscriptionItemIdInput.sendKeys(subscriptionItemId);
  }

  async getSubscriptionItemIdInput(): Promise<string> {
    return await this.subscriptionItemIdInput.getAttribute('value');
  }

  async setDeliveryStatusInput(deliveryStatus: string): Promise<void> {
    await this.deliveryStatusInput.sendKeys(deliveryStatus);
  }

  async getDeliveryStatusInput(): Promise<string> {
    return await this.deliveryStatusInput.getAttribute('value');
  }

  async setDeliveryMethodIdInput(deliveryMethodId: string): Promise<void> {
    await this.deliveryMethodIdInput.sendKeys(deliveryMethodId);
  }

  async getDeliveryMethodIdInput(): Promise<string> {
    return await this.deliveryMethodIdInput.getAttribute('value');
  }

  async setDeliveryRefCodeInput(deliveryRefCode: string): Promise<void> {
    await this.deliveryRefCodeInput.sendKeys(deliveryRefCode);
  }

  async getDeliveryRefCodeInput(): Promise<string> {
    return await this.deliveryRefCodeInput.getAttribute('value');
  }

  async setFileGenerationDateInput(fileGenerationDate: string): Promise<void> {
    await this.fileGenerationDateInput.sendKeys(fileGenerationDate);
  }

  async getFileGenerationDateInput(): Promise<string> {
    return await this.fileGenerationDateInput.getAttribute('value');
  }

  async setFileReceivedDateInput(fileReceivedDate: string): Promise<void> {
    await this.fileReceivedDateInput.sendKeys(fileReceivedDate);
  }

  async getFileReceivedDateInput(): Promise<string> {
    return await this.fileReceivedDateInput.getAttribute('value');
  }

  async setDeliveryDateInput(deliveryDate: string): Promise<void> {
    await this.deliveryDateInput.sendKeys(deliveryDate);
  }

  async getDeliveryDateInput(): Promise<string> {
    return await this.deliveryDateInput.getAttribute('value');
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

export class SubsItemDeliveryDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-subsItemDelivery-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-subsItemDelivery'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
