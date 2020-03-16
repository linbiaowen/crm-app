import { element, by, ElementFinder } from 'protractor';

export class OfferAdvancePaymentComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-offer-advance-payment div table .btn-danger'));
  title = element.all(by.css('jhi-offer-advance-payment div h2#page-heading span')).first();
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

export class OfferAdvancePaymentUpdatePage {
  pageTitle = element(by.id('jhi-offer-advance-payment-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  advancePaymentIdInput = element(by.id('field_advancePaymentId'));
  offerIdInput = element(by.id('field_offerId'));
  advancePaymentMonthsInput = element(by.id('field_advancePaymentMonths'));
  offerPriceInput = element(by.id('field_offerPrice'));
  offerDiscountInput = element(by.id('field_offerDiscount'));
  statusSelect = element(by.id('field_status'));
  createdDateInput = element(by.id('field_createdDate'));
  createdByInput = element(by.id('field_createdBy'));
  lastUpdatedDateInput = element(by.id('field_lastUpdatedDate'));
  lastUpdatedByInput = element(by.id('field_lastUpdatedBy'));
  tenantIdInput = element(by.id('field_tenantId'));

  offerSelect = element(by.id('field_offer'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setAdvancePaymentIdInput(advancePaymentId: string): Promise<void> {
    await this.advancePaymentIdInput.sendKeys(advancePaymentId);
  }

  async getAdvancePaymentIdInput(): Promise<string> {
    return await this.advancePaymentIdInput.getAttribute('value');
  }

  async setOfferIdInput(offerId: string): Promise<void> {
    await this.offerIdInput.sendKeys(offerId);
  }

  async getOfferIdInput(): Promise<string> {
    return await this.offerIdInput.getAttribute('value');
  }

  async setAdvancePaymentMonthsInput(advancePaymentMonths: string): Promise<void> {
    await this.advancePaymentMonthsInput.sendKeys(advancePaymentMonths);
  }

  async getAdvancePaymentMonthsInput(): Promise<string> {
    return await this.advancePaymentMonthsInput.getAttribute('value');
  }

  async setOfferPriceInput(offerPrice: string): Promise<void> {
    await this.offerPriceInput.sendKeys(offerPrice);
  }

  async getOfferPriceInput(): Promise<string> {
    return await this.offerPriceInput.getAttribute('value');
  }

  async setOfferDiscountInput(offerDiscount: string): Promise<void> {
    await this.offerDiscountInput.sendKeys(offerDiscount);
  }

  async getOfferDiscountInput(): Promise<string> {
    return await this.offerDiscountInput.getAttribute('value');
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

  async offerSelectLastOption(): Promise<void> {
    await this.offerSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async offerSelectOption(option: string): Promise<void> {
    await this.offerSelect.sendKeys(option);
  }

  getOfferSelect(): ElementFinder {
    return this.offerSelect;
  }

  async getOfferSelectedOption(): Promise<string> {
    return await this.offerSelect.element(by.css('option:checked')).getText();
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

export class OfferAdvancePaymentDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-offerAdvancePayment-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-offerAdvancePayment'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
