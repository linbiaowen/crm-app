import { element, by, ElementFinder } from 'protractor';

export class ProductComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-product div table .btn-danger'));
  title = element.all(by.css('jhi-product div h2#page-heading span')).first();
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

export class ProductUpdatePage {
  pageTitle = element(by.id('jhi-product-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  productIdInput = element(by.id('field_productId'));
  productNameInput = element(by.id('field_productName'));
  productNameChiInput = element(by.id('field_productNameChi'));
  productDescInput = element(by.id('field_productDesc'));
  productDescChiInput = element(by.id('field_productDescChi'));
  productCateSelect = element(by.id('field_productCate'));
  productNatureSelect = element(by.id('field_productNature'));
  productFamilySelect = element(by.id('field_productFamily'));
  productTypeSelect = element(by.id('field_productType'));
  modelCodeInput = element(by.id('field_modelCode'));
  startDateInput = element(by.id('field_startDate'));
  endDateInput = element(by.id('field_endDate'));
  independentlyOrderableInput = element(by.id('field_independentlyOrderable'));
  networkProvisionRequiredInput = element(by.id('field_networkProvisionRequired'));
  changeEntitlementAllowedInput = element(by.id('field_changeEntitlementAllowed'));
  createdDateInput = element(by.id('field_createdDate'));
  createdByInput = element(by.id('field_createdBy'));
  lastUpdatedDateInput = element(by.id('field_lastUpdatedDate'));
  lastUpdatedByInput = element(by.id('field_lastUpdatedBy'));
  tenantIdInput = element(by.id('field_tenantId'));

  productSpecSelect = element(by.id('field_productSpec'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
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

  async setProductNameChiInput(productNameChi: string): Promise<void> {
    await this.productNameChiInput.sendKeys(productNameChi);
  }

  async getProductNameChiInput(): Promise<string> {
    return await this.productNameChiInput.getAttribute('value');
  }

  async setProductDescInput(productDesc: string): Promise<void> {
    await this.productDescInput.sendKeys(productDesc);
  }

  async getProductDescInput(): Promise<string> {
    return await this.productDescInput.getAttribute('value');
  }

  async setProductDescChiInput(productDescChi: string): Promise<void> {
    await this.productDescChiInput.sendKeys(productDescChi);
  }

  async getProductDescChiInput(): Promise<string> {
    return await this.productDescChiInput.getAttribute('value');
  }

  async setProductCateSelect(productCate: string): Promise<void> {
    await this.productCateSelect.sendKeys(productCate);
  }

  async getProductCateSelect(): Promise<string> {
    return await this.productCateSelect.element(by.css('option:checked')).getText();
  }

  async productCateSelectLastOption(): Promise<void> {
    await this.productCateSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setProductNatureSelect(productNature: string): Promise<void> {
    await this.productNatureSelect.sendKeys(productNature);
  }

  async getProductNatureSelect(): Promise<string> {
    return await this.productNatureSelect.element(by.css('option:checked')).getText();
  }

  async productNatureSelectLastOption(): Promise<void> {
    await this.productNatureSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setProductFamilySelect(productFamily: string): Promise<void> {
    await this.productFamilySelect.sendKeys(productFamily);
  }

  async getProductFamilySelect(): Promise<string> {
    return await this.productFamilySelect.element(by.css('option:checked')).getText();
  }

  async productFamilySelectLastOption(): Promise<void> {
    await this.productFamilySelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setProductTypeSelect(productType: string): Promise<void> {
    await this.productTypeSelect.sendKeys(productType);
  }

  async getProductTypeSelect(): Promise<string> {
    return await this.productTypeSelect.element(by.css('option:checked')).getText();
  }

  async productTypeSelectLastOption(): Promise<void> {
    await this.productTypeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setModelCodeInput(modelCode: string): Promise<void> {
    await this.modelCodeInput.sendKeys(modelCode);
  }

  async getModelCodeInput(): Promise<string> {
    return await this.modelCodeInput.getAttribute('value');
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

  getIndependentlyOrderableInput(): ElementFinder {
    return this.independentlyOrderableInput;
  }

  getNetworkProvisionRequiredInput(): ElementFinder {
    return this.networkProvisionRequiredInput;
  }

  getChangeEntitlementAllowedInput(): ElementFinder {
    return this.changeEntitlementAllowedInput;
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

  async productSpecSelectLastOption(): Promise<void> {
    await this.productSpecSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async productSpecSelectOption(option: string): Promise<void> {
    await this.productSpecSelect.sendKeys(option);
  }

  getProductSpecSelect(): ElementFinder {
    return this.productSpecSelect;
  }

  async getProductSpecSelectedOption(): Promise<string> {
    return await this.productSpecSelect.element(by.css('option:checked')).getText();
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

export class ProductDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-product-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-product'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
