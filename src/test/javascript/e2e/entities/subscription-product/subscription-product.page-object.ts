import { element, by, ElementFinder } from 'protractor';

export class SubscriptionProductComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-subscription-product div table .btn-danger'));
  title = element.all(by.css('jhi-subscription-product div h2#page-heading span')).first();
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

export class SubscriptionProductUpdatePage {
  pageTitle = element(by.id('jhi-subscription-product-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  productSubsriptionSeqIdInput = element(by.id('field_productSubsriptionSeqId'));
  orderIdInput = element(by.id('field_orderId'));
  subscriptionIdInput = element(by.id('field_subscriptionId'));
  productIdInput = element(by.id('field_productId'));
  productNameInput = element(by.id('field_productName'));
  activationDateInput = element(by.id('field_activationDate'));
  endDateInput = element(by.id('field_endDate'));
  secondMsisdnInput = element(by.id('field_secondMsisdn'));
  secondImsiInput = element(by.id('field_secondImsi'));
  quantityInput = element(by.id('field_quantity'));
  terminationReasonCodeInput = element(by.id('field_terminationReasonCode'));
  offerIdInput = element(by.id('field_offerId'));
  offerNameInput = element(by.id('field_offerName'));
  offerTypeInput = element(by.id('field_offerType'));
  matrixxCatalogIdInput = element(by.id('field_matrixxCatalogId'));
  matrixxResourceIdInput = element(by.id('field_matrixxResourceId'));
  matrixxObjectIdInput = element(by.id('field_matrixxObjectId'));
  salesChannelInput = element(by.id('field_salesChannel'));
  contractIdInput = element(by.id('field_contractId'));
  autoRenewalInput = element(by.id('field_autoRenewal'));
  autoPayInput = element(by.id('field_autoPay'));
  remarksInput = element(by.id('field_remarks'));
  vendorProvisionIndInput = element(by.id('field_vendorProvisionInd'));
  createdDateInput = element(by.id('field_createdDate'));
  createdByInput = element(by.id('field_createdBy'));
  lastUpdatedDateInput = element(by.id('field_lastUpdatedDate'));
  lastUpdatedByInput = element(by.id('field_lastUpdatedBy'));
  tenantIdInput = element(by.id('field_tenantId'));

  custSubscriptionSelect = element(by.id('field_custSubscription'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setProductSubsriptionSeqIdInput(productSubsriptionSeqId: string): Promise<void> {
    await this.productSubsriptionSeqIdInput.sendKeys(productSubsriptionSeqId);
  }

  async getProductSubsriptionSeqIdInput(): Promise<string> {
    return await this.productSubsriptionSeqIdInput.getAttribute('value');
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

  async setActivationDateInput(activationDate: string): Promise<void> {
    await this.activationDateInput.sendKeys(activationDate);
  }

  async getActivationDateInput(): Promise<string> {
    return await this.activationDateInput.getAttribute('value');
  }

  async setEndDateInput(endDate: string): Promise<void> {
    await this.endDateInput.sendKeys(endDate);
  }

  async getEndDateInput(): Promise<string> {
    return await this.endDateInput.getAttribute('value');
  }

  async setSecondMsisdnInput(secondMsisdn: string): Promise<void> {
    await this.secondMsisdnInput.sendKeys(secondMsisdn);
  }

  async getSecondMsisdnInput(): Promise<string> {
    return await this.secondMsisdnInput.getAttribute('value');
  }

  async setSecondImsiInput(secondImsi: string): Promise<void> {
    await this.secondImsiInput.sendKeys(secondImsi);
  }

  async getSecondImsiInput(): Promise<string> {
    return await this.secondImsiInput.getAttribute('value');
  }

  async setQuantityInput(quantity: string): Promise<void> {
    await this.quantityInput.sendKeys(quantity);
  }

  async getQuantityInput(): Promise<string> {
    return await this.quantityInput.getAttribute('value');
  }

  async setTerminationReasonCodeInput(terminationReasonCode: string): Promise<void> {
    await this.terminationReasonCodeInput.sendKeys(terminationReasonCode);
  }

  async getTerminationReasonCodeInput(): Promise<string> {
    return await this.terminationReasonCodeInput.getAttribute('value');
  }

  async setOfferIdInput(offerId: string): Promise<void> {
    await this.offerIdInput.sendKeys(offerId);
  }

  async getOfferIdInput(): Promise<string> {
    return await this.offerIdInput.getAttribute('value');
  }

  async setOfferNameInput(offerName: string): Promise<void> {
    await this.offerNameInput.sendKeys(offerName);
  }

  async getOfferNameInput(): Promise<string> {
    return await this.offerNameInput.getAttribute('value');
  }

  async setOfferTypeInput(offerType: string): Promise<void> {
    await this.offerTypeInput.sendKeys(offerType);
  }

  async getOfferTypeInput(): Promise<string> {
    return await this.offerTypeInput.getAttribute('value');
  }

  async setMatrixxCatalogIdInput(matrixxCatalogId: string): Promise<void> {
    await this.matrixxCatalogIdInput.sendKeys(matrixxCatalogId);
  }

  async getMatrixxCatalogIdInput(): Promise<string> {
    return await this.matrixxCatalogIdInput.getAttribute('value');
  }

  async setMatrixxResourceIdInput(matrixxResourceId: string): Promise<void> {
    await this.matrixxResourceIdInput.sendKeys(matrixxResourceId);
  }

  async getMatrixxResourceIdInput(): Promise<string> {
    return await this.matrixxResourceIdInput.getAttribute('value');
  }

  async setMatrixxObjectIdInput(matrixxObjectId: string): Promise<void> {
    await this.matrixxObjectIdInput.sendKeys(matrixxObjectId);
  }

  async getMatrixxObjectIdInput(): Promise<string> {
    return await this.matrixxObjectIdInput.getAttribute('value');
  }

  async setSalesChannelInput(salesChannel: string): Promise<void> {
    await this.salesChannelInput.sendKeys(salesChannel);
  }

  async getSalesChannelInput(): Promise<string> {
    return await this.salesChannelInput.getAttribute('value');
  }

  async setContractIdInput(contractId: string): Promise<void> {
    await this.contractIdInput.sendKeys(contractId);
  }

  async getContractIdInput(): Promise<string> {
    return await this.contractIdInput.getAttribute('value');
  }

  getAutoRenewalInput(): ElementFinder {
    return this.autoRenewalInput;
  }

  getAutoPayInput(): ElementFinder {
    return this.autoPayInput;
  }

  async setRemarksInput(remarks: string): Promise<void> {
    await this.remarksInput.sendKeys(remarks);
  }

  async getRemarksInput(): Promise<string> {
    return await this.remarksInput.getAttribute('value');
  }

  getVendorProvisionIndInput(): ElementFinder {
    return this.vendorProvisionIndInput;
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

export class SubscriptionProductDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-subscriptionProduct-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-subscriptionProduct'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
