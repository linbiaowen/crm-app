import { element, by, ElementFinder } from 'protractor';

export class OfferComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-offer div table .btn-danger'));
  title = element.all(by.css('jhi-offer div h2#page-heading span')).first();
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

export class OfferUpdatePage {
  pageTitle = element(by.id('jhi-offer-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  offerIdInput = element(by.id('field_offerId'));
  offerExternalIdInput = element(by.id('field_offerExternalId'));
  offerNameInput = element(by.id('field_offerName'));
  offerNameChiInput = element(by.id('field_offerNameChi'));
  offerTypeSelect = element(by.id('field_offerType'));
  offerPriceInput = element(by.id('field_offerPrice'));
  customerSegmentsInput = element(by.id('field_customerSegments'));
  customerClassesInput = element(by.id('field_customerClasses'));
  salesChannelsInput = element(by.id('field_salesChannels'));
  startDateInput = element(by.id('field_startDate'));
  endDateInput = element(by.id('field_endDate'));
  childOfferIdsInput = element(by.id('field_childOfferIds'));
  productSpecIdsInput = element(by.id('field_productSpecIds'));
  advancePaymentIdsInput = element(by.id('field_advancePaymentIds'));
  createdDateInput = element(by.id('field_createdDate'));
  createdByInput = element(by.id('field_createdBy'));
  lastUpdatedDateInput = element(by.id('field_lastUpdatedDate'));
  lastUpdatedByInput = element(by.id('field_lastUpdatedBy'));
  tenantIdInput = element(by.id('field_tenantId'));

  offerSpecSelect = element(by.id('field_offerSpec'));
  parentOfferSelect = element(by.id('field_parentOffer'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setOfferIdInput(offerId: string): Promise<void> {
    await this.offerIdInput.sendKeys(offerId);
  }

  async getOfferIdInput(): Promise<string> {
    return await this.offerIdInput.getAttribute('value');
  }

  async setOfferExternalIdInput(offerExternalId: string): Promise<void> {
    await this.offerExternalIdInput.sendKeys(offerExternalId);
  }

  async getOfferExternalIdInput(): Promise<string> {
    return await this.offerExternalIdInput.getAttribute('value');
  }

  async setOfferNameInput(offerName: string): Promise<void> {
    await this.offerNameInput.sendKeys(offerName);
  }

  async getOfferNameInput(): Promise<string> {
    return await this.offerNameInput.getAttribute('value');
  }

  async setOfferNameChiInput(offerNameChi: string): Promise<void> {
    await this.offerNameChiInput.sendKeys(offerNameChi);
  }

  async getOfferNameChiInput(): Promise<string> {
    return await this.offerNameChiInput.getAttribute('value');
  }

  async setOfferTypeSelect(offerType: string): Promise<void> {
    await this.offerTypeSelect.sendKeys(offerType);
  }

  async getOfferTypeSelect(): Promise<string> {
    return await this.offerTypeSelect.element(by.css('option:checked')).getText();
  }

  async offerTypeSelectLastOption(): Promise<void> {
    await this.offerTypeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setOfferPriceInput(offerPrice: string): Promise<void> {
    await this.offerPriceInput.sendKeys(offerPrice);
  }

  async getOfferPriceInput(): Promise<string> {
    return await this.offerPriceInput.getAttribute('value');
  }

  async setCustomerSegmentsInput(customerSegments: string): Promise<void> {
    await this.customerSegmentsInput.sendKeys(customerSegments);
  }

  async getCustomerSegmentsInput(): Promise<string> {
    return await this.customerSegmentsInput.getAttribute('value');
  }

  async setCustomerClassesInput(customerClasses: string): Promise<void> {
    await this.customerClassesInput.sendKeys(customerClasses);
  }

  async getCustomerClassesInput(): Promise<string> {
    return await this.customerClassesInput.getAttribute('value');
  }

  async setSalesChannelsInput(salesChannels: string): Promise<void> {
    await this.salesChannelsInput.sendKeys(salesChannels);
  }

  async getSalesChannelsInput(): Promise<string> {
    return await this.salesChannelsInput.getAttribute('value');
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

  async setChildOfferIdsInput(childOfferIds: string): Promise<void> {
    await this.childOfferIdsInput.sendKeys(childOfferIds);
  }

  async getChildOfferIdsInput(): Promise<string> {
    return await this.childOfferIdsInput.getAttribute('value');
  }

  async setProductSpecIdsInput(productSpecIds: string): Promise<void> {
    await this.productSpecIdsInput.sendKeys(productSpecIds);
  }

  async getProductSpecIdsInput(): Promise<string> {
    return await this.productSpecIdsInput.getAttribute('value');
  }

  async setAdvancePaymentIdsInput(advancePaymentIds: string): Promise<void> {
    await this.advancePaymentIdsInput.sendKeys(advancePaymentIds);
  }

  async getAdvancePaymentIdsInput(): Promise<string> {
    return await this.advancePaymentIdsInput.getAttribute('value');
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

  async offerSpecSelectLastOption(): Promise<void> {
    await this.offerSpecSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async offerSpecSelectOption(option: string): Promise<void> {
    await this.offerSpecSelect.sendKeys(option);
  }

  getOfferSpecSelect(): ElementFinder {
    return this.offerSpecSelect;
  }

  async getOfferSpecSelectedOption(): Promise<string> {
    return await this.offerSpecSelect.element(by.css('option:checked')).getText();
  }

  async parentOfferSelectLastOption(): Promise<void> {
    await this.parentOfferSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async parentOfferSelectOption(option: string): Promise<void> {
    await this.parentOfferSelect.sendKeys(option);
  }

  getParentOfferSelect(): ElementFinder {
    return this.parentOfferSelect;
  }

  async getParentOfferSelectedOption(): Promise<string> {
    return await this.parentOfferSelect.element(by.css('option:checked')).getText();
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

export class OfferDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-offer-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-offer'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
