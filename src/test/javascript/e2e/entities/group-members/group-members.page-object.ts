import { element, by, ElementFinder } from 'protractor';

export class GroupMembersComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-group-members div table .btn-danger'));
  title = element.all(by.css('jhi-group-members div h2#page-heading span')).first();
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

export class GroupMembersUpdatePage {
  pageTitle = element(by.id('jhi-group-members-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  groupIdInput = element(by.id('field_groupId'));
  subscriptionIdInput = element(by.id('field_subscriptionId'));
  msisdnInput = element(by.id('field_msisdn'));
  groupRoleSelect = element(by.id('field_groupRole'));
  endReasonCodeInput = element(by.id('field_endReasonCode'));
  remarksInput = element(by.id('field_remarks'));
  startDateInput = element(by.id('field_startDate'));
  endDateInput = element(by.id('field_endDate'));
  createdDateInput = element(by.id('field_createdDate'));
  createdByInput = element(by.id('field_createdBy'));
  lastUpdatedDateInput = element(by.id('field_lastUpdatedDate'));
  lastUpdatedByInput = element(by.id('field_lastUpdatedBy'));
  tenantIdInput = element(by.id('field_tenantId'));

  subscriptionGroupSelect = element(by.id('field_subscriptionGroup'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setGroupIdInput(groupId: string): Promise<void> {
    await this.groupIdInput.sendKeys(groupId);
  }

  async getGroupIdInput(): Promise<string> {
    return await this.groupIdInput.getAttribute('value');
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

  async setGroupRoleSelect(groupRole: string): Promise<void> {
    await this.groupRoleSelect.sendKeys(groupRole);
  }

  async getGroupRoleSelect(): Promise<string> {
    return await this.groupRoleSelect.element(by.css('option:checked')).getText();
  }

  async groupRoleSelectLastOption(): Promise<void> {
    await this.groupRoleSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setEndReasonCodeInput(endReasonCode: string): Promise<void> {
    await this.endReasonCodeInput.sendKeys(endReasonCode);
  }

  async getEndReasonCodeInput(): Promise<string> {
    return await this.endReasonCodeInput.getAttribute('value');
  }

  async setRemarksInput(remarks: string): Promise<void> {
    await this.remarksInput.sendKeys(remarks);
  }

  async getRemarksInput(): Promise<string> {
    return await this.remarksInput.getAttribute('value');
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

  async subscriptionGroupSelectLastOption(): Promise<void> {
    await this.subscriptionGroupSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async subscriptionGroupSelectOption(option: string): Promise<void> {
    await this.subscriptionGroupSelect.sendKeys(option);
  }

  getSubscriptionGroupSelect(): ElementFinder {
    return this.subscriptionGroupSelect;
  }

  async getSubscriptionGroupSelectedOption(): Promise<string> {
    return await this.subscriptionGroupSelect.element(by.css('option:checked')).getText();
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

export class GroupMembersDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-groupMembers-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-groupMembers'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
