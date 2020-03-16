import { element, by, ElementFinder } from 'protractor';

export class SubsDeliveryItemDetailsComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-subs-delivery-item-details div table .btn-danger'));
  title = element.all(by.css('jhi-subs-delivery-item-details div h2#page-heading span')).first();
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

export class SubsDeliveryItemDetailsUpdatePage {
  pageTitle = element(by.id('jhi-subs-delivery-item-details-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  subscriptionItemIdInput = element(by.id('field_subscriptionItemId'));
  deliveryIdInput = element(by.id('field_deliveryId'));
  productIdInput = element(by.id('field_productId'));
  productNameInput = element(by.id('field_productName'));
  deviceTypeInput = element(by.id('field_deviceType'));
  deviceModelInput = element(by.id('field_deviceModel'));
  deviceSerialNbrInput = element(by.id('field_deviceSerialNbr'));
  imeiInput = element(by.id('field_imei'));
  productThemeInput = element(by.id('field_productTheme'));
  createdDateInput = element(by.id('field_createdDate'));
  createdByInput = element(by.id('field_createdBy'));
  lastUpdatedDateInput = element(by.id('field_lastUpdatedDate'));
  lastUpdatedByInput = element(by.id('field_lastUpdatedBy'));
  tenantIdInput = element(by.id('field_tenantId'));

  subsItemDeliverySelect = element(by.id('field_subsItemDelivery'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setSubscriptionItemIdInput(subscriptionItemId: string): Promise<void> {
    await this.subscriptionItemIdInput.sendKeys(subscriptionItemId);
  }

  async getSubscriptionItemIdInput(): Promise<string> {
    return await this.subscriptionItemIdInput.getAttribute('value');
  }

  async setDeliveryIdInput(deliveryId: string): Promise<void> {
    await this.deliveryIdInput.sendKeys(deliveryId);
  }

  async getDeliveryIdInput(): Promise<string> {
    return await this.deliveryIdInput.getAttribute('value');
  }

  async setProductIdInput(productId: string): Promise<void> {
    await this.productIdInput.sendKeys(productId);
  }

  async getProductIdInput(): Promise<string> {
    return await this.productIdInput.getAttribute('value');
  }

  async setProductNameInput(productName: string): Promise<void> {
    await this.productNameInput.sendKeys(productName);
  }

  async getProductNameInput(): Promise<string> {
    return await this.productNameInput.getAttribute('value');
  }

  async setDeviceTypeInput(deviceType: string): Promise<void> {
    await this.deviceTypeInput.sendKeys(deviceType);
  }

  async getDeviceTypeInput(): Promise<string> {
    return await this.deviceTypeInput.getAttribute('value');
  }

  async setDeviceModelInput(deviceModel: string): Promise<void> {
    await this.deviceModelInput.sendKeys(deviceModel);
  }

  async getDeviceModelInput(): Promise<string> {
    return await this.deviceModelInput.getAttribute('value');
  }

  async setDeviceSerialNbrInput(deviceSerialNbr: string): Promise<void> {
    await this.deviceSerialNbrInput.sendKeys(deviceSerialNbr);
  }

  async getDeviceSerialNbrInput(): Promise<string> {
    return await this.deviceSerialNbrInput.getAttribute('value');
  }

  async setImeiInput(imei: string): Promise<void> {
    await this.imeiInput.sendKeys(imei);
  }

  async getImeiInput(): Promise<string> {
    return await this.imeiInput.getAttribute('value');
  }

  async setProductThemeInput(productTheme: string): Promise<void> {
    await this.productThemeInput.sendKeys(productTheme);
  }

  async getProductThemeInput(): Promise<string> {
    return await this.productThemeInput.getAttribute('value');
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

  async subsItemDeliverySelectLastOption(): Promise<void> {
    await this.subsItemDeliverySelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async subsItemDeliverySelectOption(option: string): Promise<void> {
    await this.subsItemDeliverySelect.sendKeys(option);
  }

  getSubsItemDeliverySelect(): ElementFinder {
    return this.subsItemDeliverySelect;
  }

  async getSubsItemDeliverySelectedOption(): Promise<string> {
    return await this.subsItemDeliverySelect.element(by.css('option:checked')).getText();
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

export class SubsDeliveryItemDetailsDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-subsDeliveryItemDetails-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-subsDeliveryItemDetails'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
