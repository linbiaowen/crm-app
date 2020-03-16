import { element, by, ElementFinder } from 'protractor';

export class SupremeMasterComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-supreme-master div table .btn-danger'));
  title = element.all(by.css('jhi-supreme-master div h2#page-heading span')).first();
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

export class SupremeMasterUpdatePage {
  pageTitle = element(by.id('jhi-supreme-master-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  supremeSeqIdInput = element(by.id('field_supremeSeqId'));
  subscriptionIdInput = element(by.id('field_subscriptionId'));
  msisdnInput = element(by.id('field_msisdn'));
  startDateInput = element(by.id('field_startDate'));
  endDateInput = element(by.id('field_endDate'));
  membershipServiceTypeSelect = element(by.id('field_membershipServiceType'));
  peCodeInput = element(by.id('field_peCode'));
  personalExecDirectLineInput = element(by.id('field_personalExecDirectLine'));
  personalExecNameInput = element(by.id('field_personalExecName'));
  createdDateInput = element(by.id('field_createdDate'));
  createdByInput = element(by.id('field_createdBy'));
  lastUpdatedDateInput = element(by.id('field_lastUpdatedDate'));
  lastUpdatedByInput = element(by.id('field_lastUpdatedBy'));
  tenantIdInput = element(by.id('field_tenantId'));

  custSubscriptionSelect = element(by.id('field_custSubscription'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setSupremeSeqIdInput(supremeSeqId: string): Promise<void> {
    await this.supremeSeqIdInput.sendKeys(supremeSeqId);
  }

  async getSupremeSeqIdInput(): Promise<string> {
    return await this.supremeSeqIdInput.getAttribute('value');
  }

  async setSubscriptionIdInput(subscriptionId: string): Promise<void> {
    await this.subscriptionIdInput.sendKeys(subscriptionId);
  }

  async getSubscriptionIdInput(): Promise<string> {
    return await this.subscriptionIdInput.getAttribute('value');
  }

  async setMsisdnInput(msisdn: string): Promise<void> {
    await this.msisdnInput.sendKeys(msisdn);
  }

  async getMsisdnInput(): Promise<string> {
    return await this.msisdnInput.getAttribute('value');
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

  async setMembershipServiceTypeSelect(membershipServiceType: string): Promise<void> {
    await this.membershipServiceTypeSelect.sendKeys(membershipServiceType);
  }

  async getMembershipServiceTypeSelect(): Promise<string> {
    return await this.membershipServiceTypeSelect.element(by.css('option:checked')).getText();
  }

  async membershipServiceTypeSelectLastOption(): Promise<void> {
    await this.membershipServiceTypeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setPeCodeInput(peCode: string): Promise<void> {
    await this.peCodeInput.sendKeys(peCode);
  }

  async getPeCodeInput(): Promise<string> {
    return await this.peCodeInput.getAttribute('value');
  }

  async setPersonalExecDirectLineInput(personalExecDirectLine: string): Promise<void> {
    await this.personalExecDirectLineInput.sendKeys(personalExecDirectLine);
  }

  async getPersonalExecDirectLineInput(): Promise<string> {
    return await this.personalExecDirectLineInput.getAttribute('value');
  }

  async setPersonalExecNameInput(personalExecName: string): Promise<void> {
    await this.personalExecNameInput.sendKeys(personalExecName);
  }

  async getPersonalExecNameInput(): Promise<string> {
    return await this.personalExecNameInput.getAttribute('value');
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

export class SupremeMasterDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-supremeMaster-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-supremeMaster'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
