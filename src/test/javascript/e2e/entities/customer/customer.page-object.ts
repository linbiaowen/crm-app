import { element, by, ElementFinder } from 'protractor';

export class CustomerComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-customer div table .btn-danger'));
  title = element.all(by.css('jhi-customer div h2#page-heading span')).first();
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

export class CustomerUpdatePage {
  pageTitle = element(by.id('jhi-customer-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  custAcctIdInput = element(by.id('field_custAcctId'));
  parentCustAcctIdInput = element(by.id('field_parentCustAcctId'));
  acctStatusSelect = element(by.id('field_acctStatus'));
  acctStartDateInput = element(by.id('field_acctStartDate'));
  acctEndDateInput = element(by.id('field_acctEndDate'));
  cabsAcctIdInput = element(by.id('field_cabsAcctId'));
  titleInput = element(by.id('field_title'));
  givenNameInput = element(by.id('field_givenName'));
  familyNameInput = element(by.id('field_familyName'));
  givenNameChiInput = element(by.id('field_givenNameChi'));
  familyNameChiInput = element(by.id('field_familyNameChi'));
  aliasNameInput = element(by.id('field_aliasName'));
  genderInput = element(by.id('field_gender'));
  birthDateInput = element(by.id('field_birthDate'));
  idTypeInput = element(by.id('field_idType'));
  idNumberInput = element(by.id('field_idNumber'));
  companyNameEngInput = element(by.id('field_companyNameEng'));
  companyNameChiInput = element(by.id('field_companyNameChi'));
  unlimitedCompanyInput = element(by.id('field_unlimitedCompany'));
  langSelect = element(by.id('field_lang'));
  selfCareUserIdInput = element(by.id('field_selfCareUserId'));
  selfCarePasswordInput = element(by.id('field_selfCarePassword'));
  ivrPinInput = element(by.id('field_ivrPin'));
  maritialStatusInput = element(by.id('field_maritialStatus'));
  customerSegmentSelect = element(by.id('field_customerSegment'));
  customerClassInput = element(by.id('field_customerClass'));
  billingAcctIdInput = element(by.id('field_billingAcctId'));
  createdDateInput = element(by.id('field_createdDate'));
  createdByInput = element(by.id('field_createdBy'));
  lastUpdatedDateInput = element(by.id('field_lastUpdatedDate'));
  lastUpdatedByInput = element(by.id('field_lastUpdatedBy'));
  tenantIdInput = element(by.id('field_tenantId'));

  parentCustomerSelect = element(by.id('field_parentCustomer'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setCustAcctIdInput(custAcctId: string): Promise<void> {
    await this.custAcctIdInput.sendKeys(custAcctId);
  }

  async getCustAcctIdInput(): Promise<string> {
    return await this.custAcctIdInput.getAttribute('value');
  }

  async setParentCustAcctIdInput(parentCustAcctId: string): Promise<void> {
    await this.parentCustAcctIdInput.sendKeys(parentCustAcctId);
  }

  async getParentCustAcctIdInput(): Promise<string> {
    return await this.parentCustAcctIdInput.getAttribute('value');
  }

  async setAcctStatusSelect(acctStatus: string): Promise<void> {
    await this.acctStatusSelect.sendKeys(acctStatus);
  }

  async getAcctStatusSelect(): Promise<string> {
    return await this.acctStatusSelect.element(by.css('option:checked')).getText();
  }

  async acctStatusSelectLastOption(): Promise<void> {
    await this.acctStatusSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setAcctStartDateInput(acctStartDate: string): Promise<void> {
    await this.acctStartDateInput.sendKeys(acctStartDate);
  }

  async getAcctStartDateInput(): Promise<string> {
    return await this.acctStartDateInput.getAttribute('value');
  }

  async setAcctEndDateInput(acctEndDate: string): Promise<void> {
    await this.acctEndDateInput.sendKeys(acctEndDate);
  }

  async getAcctEndDateInput(): Promise<string> {
    return await this.acctEndDateInput.getAttribute('value');
  }

  async setCabsAcctIdInput(cabsAcctId: string): Promise<void> {
    await this.cabsAcctIdInput.sendKeys(cabsAcctId);
  }

  async getCabsAcctIdInput(): Promise<string> {
    return await this.cabsAcctIdInput.getAttribute('value');
  }

  async setTitleInput(title: string): Promise<void> {
    await this.titleInput.sendKeys(title);
  }

  async getTitleInput(): Promise<string> {
    return await this.titleInput.getAttribute('value');
  }

  async setGivenNameInput(givenName: string): Promise<void> {
    await this.givenNameInput.sendKeys(givenName);
  }

  async getGivenNameInput(): Promise<string> {
    return await this.givenNameInput.getAttribute('value');
  }

  async setFamilyNameInput(familyName: string): Promise<void> {
    await this.familyNameInput.sendKeys(familyName);
  }

  async getFamilyNameInput(): Promise<string> {
    return await this.familyNameInput.getAttribute('value');
  }

  async setGivenNameChiInput(givenNameChi: string): Promise<void> {
    await this.givenNameChiInput.sendKeys(givenNameChi);
  }

  async getGivenNameChiInput(): Promise<string> {
    return await this.givenNameChiInput.getAttribute('value');
  }

  async setFamilyNameChiInput(familyNameChi: string): Promise<void> {
    await this.familyNameChiInput.sendKeys(familyNameChi);
  }

  async getFamilyNameChiInput(): Promise<string> {
    return await this.familyNameChiInput.getAttribute('value');
  }

  async setAliasNameInput(aliasName: string): Promise<void> {
    await this.aliasNameInput.sendKeys(aliasName);
  }

  async getAliasNameInput(): Promise<string> {
    return await this.aliasNameInput.getAttribute('value');
  }

  async setGenderInput(gender: string): Promise<void> {
    await this.genderInput.sendKeys(gender);
  }

  async getGenderInput(): Promise<string> {
    return await this.genderInput.getAttribute('value');
  }

  async setBirthDateInput(birthDate: string): Promise<void> {
    await this.birthDateInput.sendKeys(birthDate);
  }

  async getBirthDateInput(): Promise<string> {
    return await this.birthDateInput.getAttribute('value');
  }

  async setIdTypeInput(idType: string): Promise<void> {
    await this.idTypeInput.sendKeys(idType);
  }

  async getIdTypeInput(): Promise<string> {
    return await this.idTypeInput.getAttribute('value');
  }

  async setIdNumberInput(idNumber: string): Promise<void> {
    await this.idNumberInput.sendKeys(idNumber);
  }

  async getIdNumberInput(): Promise<string> {
    return await this.idNumberInput.getAttribute('value');
  }

  async setCompanyNameEngInput(companyNameEng: string): Promise<void> {
    await this.companyNameEngInput.sendKeys(companyNameEng);
  }

  async getCompanyNameEngInput(): Promise<string> {
    return await this.companyNameEngInput.getAttribute('value');
  }

  async setCompanyNameChiInput(companyNameChi: string): Promise<void> {
    await this.companyNameChiInput.sendKeys(companyNameChi);
  }

  async getCompanyNameChiInput(): Promise<string> {
    return await this.companyNameChiInput.getAttribute('value');
  }

  getUnlimitedCompanyInput(): ElementFinder {
    return this.unlimitedCompanyInput;
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

  async setSelfCareUserIdInput(selfCareUserId: string): Promise<void> {
    await this.selfCareUserIdInput.sendKeys(selfCareUserId);
  }

  async getSelfCareUserIdInput(): Promise<string> {
    return await this.selfCareUserIdInput.getAttribute('value');
  }

  async setSelfCarePasswordInput(selfCarePassword: string): Promise<void> {
    await this.selfCarePasswordInput.sendKeys(selfCarePassword);
  }

  async getSelfCarePasswordInput(): Promise<string> {
    return await this.selfCarePasswordInput.getAttribute('value');
  }

  async setIvrPinInput(ivrPin: string): Promise<void> {
    await this.ivrPinInput.sendKeys(ivrPin);
  }

  async getIvrPinInput(): Promise<string> {
    return await this.ivrPinInput.getAttribute('value');
  }

  async setMaritialStatusInput(maritialStatus: string): Promise<void> {
    await this.maritialStatusInput.sendKeys(maritialStatus);
  }

  async getMaritialStatusInput(): Promise<string> {
    return await this.maritialStatusInput.getAttribute('value');
  }

  async setCustomerSegmentSelect(customerSegment: string): Promise<void> {
    await this.customerSegmentSelect.sendKeys(customerSegment);
  }

  async getCustomerSegmentSelect(): Promise<string> {
    return await this.customerSegmentSelect.element(by.css('option:checked')).getText();
  }

  async customerSegmentSelectLastOption(): Promise<void> {
    await this.customerSegmentSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setCustomerClassInput(customerClass: string): Promise<void> {
    await this.customerClassInput.sendKeys(customerClass);
  }

  async getCustomerClassInput(): Promise<string> {
    return await this.customerClassInput.getAttribute('value');
  }

  async setBillingAcctIdInput(billingAcctId: string): Promise<void> {
    await this.billingAcctIdInput.sendKeys(billingAcctId);
  }

  async getBillingAcctIdInput(): Promise<string> {
    return await this.billingAcctIdInput.getAttribute('value');
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

  async parentCustomerSelectLastOption(): Promise<void> {
    await this.parentCustomerSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async parentCustomerSelectOption(option: string): Promise<void> {
    await this.parentCustomerSelect.sendKeys(option);
  }

  getParentCustomerSelect(): ElementFinder {
    return this.parentCustomerSelect;
  }

  async getParentCustomerSelectedOption(): Promise<string> {
    return await this.parentCustomerSelect.element(by.css('option:checked')).getText();
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

export class CustomerDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-customer-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-customer'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
