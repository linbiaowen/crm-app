import { element, by, ElementFinder } from 'protractor';

export class OfferPromotionComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-offer-promotion div table .btn-danger'));
  title = element.all(by.css('jhi-offer-promotion div h2#page-heading span')).first();
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

export class OfferPromotionUpdatePage {
  pageTitle = element(by.id('jhi-offer-promotion-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  promoCodeInput = element(by.id('field_promoCode'));
  offerIdInput = element(by.id('field_offerId'));
  startDateInput = element(by.id('field_startDate'));
  endDateInput = element(by.id('field_endDate'));
  promoTypeInput = element(by.id('field_promoType'));
  offerPriceInput = element(by.id('field_offerPrice'));
  offerDiscountInput = element(by.id('field_offerDiscount'));
  freeDataOfferIdInput = element(by.id('field_freeDataOfferId'));
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

  async setPromoCodeInput(promoCode: string): Promise<void> {
    await this.promoCodeInput.sendKeys(promoCode);
  }

  async getPromoCodeInput(): Promise<string> {
    return await this.promoCodeInput.getAttribute('value');
  }

  async setOfferIdInput(offerId: string): Promise<void> {
    await this.offerIdInput.sendKeys(offerId);
  }

  async getOfferIdInput(): Promise<string> {
    return await this.offerIdInput.getAttribute('value');
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

  async setPromoTypeInput(promoType: string): Promise<void> {
    await this.promoTypeInput.sendKeys(promoType);
  }

  async getPromoTypeInput(): Promise<string> {
    return await this.promoTypeInput.getAttribute('value');
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

  async setFreeDataOfferIdInput(freeDataOfferId: string): Promise<void> {
    await this.freeDataOfferIdInput.sendKeys(freeDataOfferId);
  }

  async getFreeDataOfferIdInput(): Promise<string> {
    return await this.freeDataOfferIdInput.getAttribute('value');
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

export class OfferPromotionDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-offerPromotion-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-offerPromotion'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
