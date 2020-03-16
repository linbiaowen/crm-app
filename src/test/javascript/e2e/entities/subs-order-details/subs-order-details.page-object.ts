import { element, by, ElementFinder } from 'protractor';

export class SubsOrderDetailsComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-subs-order-details div table .btn-danger'));
  title = element.all(by.css('jhi-subs-order-details div h2#page-heading span')).first();
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

export class SubsOrderDetailsUpdatePage {
  pageTitle = element(by.id('jhi-subs-order-details-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  subsOrderSeqIdInput = element(by.id('field_subsOrderSeqId'));
  subscriptionIdInput = element(by.id('field_subscriptionId'));
  startDateInput = element(by.id('field_startDate'));
  endDateInput = element(by.id('field_endDate'));
  orderIdInput = element(by.id('field_orderId'));
  ssaNbrInput = element(by.id('field_ssaNbr'));
  primaryMsisdnInput = element(by.id('field_primaryMsisdn'));
  iccidInput = element(by.id('field_iccid'));
  imsiInput = element(by.id('field_imsi'));
  simVerifiedInput = element(by.id('field_simVerified'));
  simVerifiedDateInput = element(by.id('field_simVerifiedDate'));
  billingAcctIdInput = element(by.id('field_billingAcctId'));
  billCycleIdInput = element(by.id('field_billCycleId'));
  mnpRequestedDateInput = element(by.id('field_mnpRequestedDate'));
  mnpTicketInput = element(by.id('field_mnpTicket'));
  mnpPortInSessionInput = element(by.id('field_mnpPortInSession'));
  mnpOriginalIdInput = element(by.id('field_mnpOriginalId'));
  mnpCustNameInput = element(by.id('field_mnpCustName'));
  mnpIdNbrInput = element(by.id('field_mnpIdNbr'));
  mnpIdTypeInput = element(by.id('field_mnpIdType'));
  hthkMsisdnInput = element(by.id('field_hthkMsisdn'));
  langSelect = element(by.id('field_lang'));
  offerIdInput = element(by.id('field_offerId'));
  offerNameInput = element(by.id('field_offerName'));
  matrixxCatalogIdInput = element(by.id('field_matrixxCatalogId'));
  matrixxResourceIdInput = element(by.id('field_matrixxResourceId'));
  matrixxObjectIdInput = element(by.id('field_matrixxObjectId'));
  salesChannelInput = element(by.id('field_salesChannel'));
  advancePaymentMonthsInput = element(by.id('field_advancePaymentMonths'));
  offerPriceInput = element(by.id('field_offerPrice'));
  networkTypeInput = element(by.id('field_networkType'));
  servicetypeSelect = element(by.id('field_servicetype'));
  offerPlanCodeInput = element(by.id('field_offerPlanCode'));
  serviceInPersonInput = element(by.id('field_serviceInPerson'));
  fcmTokenInput = element(by.id('field_fcmToken'));
  remarksInput = element(by.id('field_remarks'));
  cdVersionInput = element(by.id('field_cdVersion'));
  createdDateInput = element(by.id('field_createdDate'));
  createdByInput = element(by.id('field_createdBy'));
  lastUpdatedDateInput = element(by.id('field_lastUpdatedDate'));
  lastUpdatedByInput = element(by.id('field_lastUpdatedBy'));
  tenantIdInput = element(by.id('field_tenantId'));

  custSubscriptionSelect = element(by.id('field_custSubscription'));
  orderMasterSelect = element(by.id('field_orderMaster'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setSubsOrderSeqIdInput(subsOrderSeqId: string): Promise<void> {
    await this.subsOrderSeqIdInput.sendKeys(subsOrderSeqId);
  }

  async getSubsOrderSeqIdInput(): Promise<string> {
    return await this.subsOrderSeqIdInput.getAttribute('value');
  }

  async setSubscriptionIdInput(subscriptionId: string): Promise<void> {
    await this.subscriptionIdInput.sendKeys(subscriptionId);
  }

  async getSubscriptionIdInput(): Promise<string> {
    return await this.subscriptionIdInput.getAttribute('value');
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

  async setOrderIdInput(orderId: string): Promise<void> {
    await this.orderIdInput.sendKeys(orderId);
  }

  async getOrderIdInput(): Promise<string> {
    return await this.orderIdInput.getAttribute('value');
  }

  async setSsaNbrInput(ssaNbr: string): Promise<void> {
    await this.ssaNbrInput.sendKeys(ssaNbr);
  }

  async getSsaNbrInput(): Promise<string> {
    return await this.ssaNbrInput.getAttribute('value');
  }

  async setPrimaryMsisdnInput(primaryMsisdn: string): Promise<void> {
    await this.primaryMsisdnInput.sendKeys(primaryMsisdn);
  }

  async getPrimaryMsisdnInput(): Promise<string> {
    return await this.primaryMsisdnInput.getAttribute('value');
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

  getSimVerifiedInput(): ElementFinder {
    return this.simVerifiedInput;
  }

  async setSimVerifiedDateInput(simVerifiedDate: string): Promise<void> {
    await this.simVerifiedDateInput.sendKeys(simVerifiedDate);
  }

  async getSimVerifiedDateInput(): Promise<string> {
    return await this.simVerifiedDateInput.getAttribute('value');
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

  async setMnpRequestedDateInput(mnpRequestedDate: string): Promise<void> {
    await this.mnpRequestedDateInput.sendKeys(mnpRequestedDate);
  }

  async getMnpRequestedDateInput(): Promise<string> {
    return await this.mnpRequestedDateInput.getAttribute('value');
  }

  async setMnpTicketInput(mnpTicket: string): Promise<void> {
    await this.mnpTicketInput.sendKeys(mnpTicket);
  }

  async getMnpTicketInput(): Promise<string> {
    return await this.mnpTicketInput.getAttribute('value');
  }

  async setMnpPortInSessionInput(mnpPortInSession: string): Promise<void> {
    await this.mnpPortInSessionInput.sendKeys(mnpPortInSession);
  }

  async getMnpPortInSessionInput(): Promise<string> {
    return await this.mnpPortInSessionInput.getAttribute('value');
  }

  async setMnpOriginalIdInput(mnpOriginalId: string): Promise<void> {
    await this.mnpOriginalIdInput.sendKeys(mnpOriginalId);
  }

  async getMnpOriginalIdInput(): Promise<string> {
    return await this.mnpOriginalIdInput.getAttribute('value');
  }

  async setMnpCustNameInput(mnpCustName: string): Promise<void> {
    await this.mnpCustNameInput.sendKeys(mnpCustName);
  }

  async getMnpCustNameInput(): Promise<string> {
    return await this.mnpCustNameInput.getAttribute('value');
  }

  async setMnpIdNbrInput(mnpIdNbr: string): Promise<void> {
    await this.mnpIdNbrInput.sendKeys(mnpIdNbr);
  }

  async getMnpIdNbrInput(): Promise<string> {
    return await this.mnpIdNbrInput.getAttribute('value');
  }

  async setMnpIdTypeInput(mnpIdType: string): Promise<void> {
    await this.mnpIdTypeInput.sendKeys(mnpIdType);
  }

  async getMnpIdTypeInput(): Promise<string> {
    return await this.mnpIdTypeInput.getAttribute('value');
  }

  getHthkMsisdnInput(): ElementFinder {
    return this.hthkMsisdnInput;
  }

  async setLangSelect(lang: string): Promise<void> {
    await this.langSelect.sendKeys(lang);
  }

  async getLangSelect(): Promise<string> {
    return await this.langSelect.element(by.css('option:checked')).getText();
  }

  async langSelectLastOption(): Promise<void> {
    await this.langSelect
      .all(by.tagName('option'))
      .last()
      .click();
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

  async setNetworkTypeInput(networkType: string): Promise<void> {
    await this.networkTypeInput.sendKeys(networkType);
  }

  async getNetworkTypeInput(): Promise<string> {
    return await this.networkTypeInput.getAttribute('value');
  }

  async setServicetypeSelect(servicetype: string): Promise<void> {
    await this.servicetypeSelect.sendKeys(servicetype);
  }

  async getServicetypeSelect(): Promise<string> {
    return await this.servicetypeSelect.element(by.css('option:checked')).getText();
  }

  async servicetypeSelectLastOption(): Promise<void> {
    await this.servicetypeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setOfferPlanCodeInput(offerPlanCode: string): Promise<void> {
    await this.offerPlanCodeInput.sendKeys(offerPlanCode);
  }

  async getOfferPlanCodeInput(): Promise<string> {
    return await this.offerPlanCodeInput.getAttribute('value');
  }

  async setServiceInPersonInput(serviceInPerson: string): Promise<void> {
    await this.serviceInPersonInput.sendKeys(serviceInPerson);
  }

  async getServiceInPersonInput(): Promise<string> {
    return await this.serviceInPersonInput.getAttribute('value');
  }

  async setFcmTokenInput(fcmToken: string): Promise<void> {
    await this.fcmTokenInput.sendKeys(fcmToken);
  }

  async getFcmTokenInput(): Promise<string> {
    return await this.fcmTokenInput.getAttribute('value');
  }

  async setRemarksInput(remarks: string): Promise<void> {
    await this.remarksInput.sendKeys(remarks);
  }

  async getRemarksInput(): Promise<string> {
    return await this.remarksInput.getAttribute('value');
  }

  async setCdVersionInput(cdVersion: string): Promise<void> {
    await this.cdVersionInput.sendKeys(cdVersion);
  }

  async getCdVersionInput(): Promise<string> {
    return await this.cdVersionInput.getAttribute('value');
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

export class SubsOrderDetailsDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-subsOrderDetails-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-subsOrderDetails'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
