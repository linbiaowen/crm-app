import { element, by, ElementFinder } from 'protractor';

export class OfferSpecificationComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-offer-specification div table .btn-danger'));
  title = element.all(by.css('jhi-offer-specification div h2#page-heading span')).first();
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

export class OfferSpecificationUpdatePage {
  pageTitle = element(by.id('jhi-offer-specification-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  offerSpecIdInput = element(by.id('field_offerSpecId'));
  offerIdInput = element(by.id('field_offerId'));
  startDateInput = element(by.id('field_startDate'));
  endDateInput = element(by.id('field_endDate'));
  limitedActivationPeriodInput = element(by.id('field_limitedActivationPeriod'));
  allowedActivationStartDateInput = element(by.id('field_allowedActivationStartDate'));
  allowedActivationEndDateInput = element(by.id('field_allowedActivationEndDate'));
  isGroupSharingOfferInput = element(by.id('field_isGroupSharingOffer'));
  isMnpOfferInput = element(by.id('field_isMnpOffer'));
  autoRenewalInput = element(by.id('field_autoRenewal'));
  transferAllowedInput = element(by.id('field_transferAllowed'));
  infoSharingAllowedInput = element(by.id('field_infoSharingAllowed'));
  infoSharingOptionsInput = element(by.id('field_infoSharingOptions'));
  offerPeriodInput = element(by.id('field_offerPeriod'));
  offerPeriodTermInput = element(by.id('field_offerPeriodTerm'));
  paymentTypeInput = element(by.id('field_paymentType'));
  createdDateInput = element(by.id('field_createdDate'));
  createdByInput = element(by.id('field_createdBy'));
  lastUpdatedDateInput = element(by.id('field_lastUpdatedDate'));
  lastUpdatedByInput = element(by.id('field_lastUpdatedBy'));
  tenantIdInput = element(by.id('field_tenantId'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setOfferSpecIdInput(offerSpecId: string): Promise<void> {
    await this.offerSpecIdInput.sendKeys(offerSpecId);
  }

  async getOfferSpecIdInput(): Promise<string> {
    return await this.offerSpecIdInput.getAttribute('value');
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

  getLimitedActivationPeriodInput(): ElementFinder {
    return this.limitedActivationPeriodInput;
  }

  async setAllowedActivationStartDateInput(allowedActivationStartDate: string): Promise<void> {
    await this.allowedActivationStartDateInput.sendKeys(allowedActivationStartDate);
  }

  async getAllowedActivationStartDateInput(): Promise<string> {
    return await this.allowedActivationStartDateInput.getAttribute('value');
  }

  async setAllowedActivationEndDateInput(allowedActivationEndDate: string): Promise<void> {
    await this.allowedActivationEndDateInput.sendKeys(allowedActivationEndDate);
  }

  async getAllowedActivationEndDateInput(): Promise<string> {
    return await this.allowedActivationEndDateInput.getAttribute('value');
  }

  getIsGroupSharingOfferInput(): ElementFinder {
    return this.isGroupSharingOfferInput;
  }

  getIsMnpOfferInput(): ElementFinder {
    return this.isMnpOfferInput;
  }

  getAutoRenewalInput(): ElementFinder {
    return this.autoRenewalInput;
  }

  getTransferAllowedInput(): ElementFinder {
    return this.transferAllowedInput;
  }

  getInfoSharingAllowedInput(): ElementFinder {
    return this.infoSharingAllowedInput;
  }

  async setInfoSharingOptionsInput(infoSharingOptions: string): Promise<void> {
    await this.infoSharingOptionsInput.sendKeys(infoSharingOptions);
  }

  async getInfoSharingOptionsInput(): Promise<string> {
    return await this.infoSharingOptionsInput.getAttribute('value');
  }

  async setOfferPeriodInput(offerPeriod: string): Promise<void> {
    await this.offerPeriodInput.sendKeys(offerPeriod);
  }

  async getOfferPeriodInput(): Promise<string> {
    return await this.offerPeriodInput.getAttribute('value');
  }

  async setOfferPeriodTermInput(offerPeriodTerm: string): Promise<void> {
    await this.offerPeriodTermInput.sendKeys(offerPeriodTerm);
  }

  async getOfferPeriodTermInput(): Promise<string> {
    return await this.offerPeriodTermInput.getAttribute('value');
  }

  async setPaymentTypeInput(paymentType: string): Promise<void> {
    await this.paymentTypeInput.sendKeys(paymentType);
  }

  async getPaymentTypeInput(): Promise<string> {
    return await this.paymentTypeInput.getAttribute('value');
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

export class OfferSpecificationDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-offerSpecification-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-offerSpecification'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
