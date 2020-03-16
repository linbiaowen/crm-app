import { element, by, ElementFinder } from 'protractor';

export class CustContactComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-cust-contact div table .btn-danger'));
  title = element.all(by.css('jhi-cust-contact div h2#page-heading span')).first();
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

export class CustContactUpdatePage {
  pageTitle = element(by.id('jhi-cust-contact-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  contactIdInput = element(by.id('field_contactId'));
  accountIdInput = element(by.id('field_accountId'));
  accountTypeSelect = element(by.id('field_accountType'));
  contactTypeSelect = element(by.id('field_contactType'));
  contactPersonInput = element(by.id('field_contactPerson'));
  contactPhoneInput = element(by.id('field_contactPhone'));
  contactEmailInput = element(by.id('field_contactEmail'));
  statusSelect = element(by.id('field_status'));
  createdDateInput = element(by.id('field_createdDate'));
  createdByInput = element(by.id('field_createdBy'));
  lastUpdatedDateInput = element(by.id('field_lastUpdatedDate'));
  lastUpdatedByInput = element(by.id('field_lastUpdatedBy'));
  tenantIdInput = element(by.id('field_tenantId'));

  customerSelect = element(by.id('field_customer'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setContactIdInput(contactId: string): Promise<void> {
    await this.contactIdInput.sendKeys(contactId);
  }

  async getContactIdInput(): Promise<string> {
    return await this.contactIdInput.getAttribute('value');
  }

  async setAccountIdInput(accountId: string): Promise<void> {
    await this.accountIdInput.sendKeys(accountId);
  }

  async getAccountIdInput(): Promise<string> {
    return await this.accountIdInput.getAttribute('value');
  }

  async setAccountTypeSelect(accountType: string): Promise<void> {
    await this.accountTypeSelect.sendKeys(accountType);
  }

  async getAccountTypeSelect(): Promise<string> {
    return await this.accountTypeSelect.element(by.css('option:checked')).getText();
  }

  async accountTypeSelectLastOption(): Promise<void> {
    await this.accountTypeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setContactTypeSelect(contactType: string): Promise<void> {
    await this.contactTypeSelect.sendKeys(contactType);
  }

  async getContactTypeSelect(): Promise<string> {
    return await this.contactTypeSelect.element(by.css('option:checked')).getText();
  }

  async contactTypeSelectLastOption(): Promise<void> {
    await this.contactTypeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setContactPersonInput(contactPerson: string): Promise<void> {
    await this.contactPersonInput.sendKeys(contactPerson);
  }

  async getContactPersonInput(): Promise<string> {
    return await this.contactPersonInput.getAttribute('value');
  }

  async setContactPhoneInput(contactPhone: string): Promise<void> {
    await this.contactPhoneInput.sendKeys(contactPhone);
  }

  async getContactPhoneInput(): Promise<string> {
    return await this.contactPhoneInput.getAttribute('value');
  }

  async setContactEmailInput(contactEmail: string): Promise<void> {
    await this.contactEmailInput.sendKeys(contactEmail);
  }

  async getContactEmailInput(): Promise<string> {
    return await this.contactEmailInput.getAttribute('value');
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

  async customerSelectLastOption(): Promise<void> {
    await this.customerSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async customerSelectOption(option: string): Promise<void> {
    await this.customerSelect.sendKeys(option);
  }

  getCustomerSelect(): ElementFinder {
    return this.customerSelect;
  }

  async getCustomerSelectedOption(): Promise<string> {
    return await this.customerSelect.element(by.css('option:checked')).getText();
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

export class CustContactDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-custContact-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-custContact'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
