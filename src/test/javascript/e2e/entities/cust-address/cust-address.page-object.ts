import { element, by, ElementFinder } from 'protractor';

export class CustAddressComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-cust-address div table .btn-danger'));
  title = element.all(by.css('jhi-cust-address div h2#page-heading span')).first();
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

export class CustAddressUpdatePage {
  pageTitle = element(by.id('jhi-cust-address-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  addressIdInput = element(by.id('field_addressId'));
  accountIdInput = element(by.id('field_accountId'));
  accountTypeSelect = element(by.id('field_accountType'));
  addressTypeSelect = element(by.id('field_addressType'));
  addressLangSelect = element(by.id('field_addressLang'));
  formattedAddressInput = element(by.id('field_formattedAddress'));
  roomInput = element(by.id('field_room'));
  floorInput = element(by.id('field_floor'));
  blockInput = element(by.id('field_block'));
  buildingInput = element(by.id('field_building'));
  streetEstateInput = element(by.id('field_streetEstate'));
  districtInput = element(by.id('field_district'));
  regionInput = element(by.id('field_region'));
  address1Input = element(by.id('field_address1'));
  address2Input = element(by.id('field_address2'));
  address3Input = element(by.id('field_address3'));
  address4Input = element(by.id('field_address4'));
  address5Input = element(by.id('field_address5'));
  createdDateInput = element(by.id('field_createdDate'));
  createdByInput = element(by.id('field_createdBy'));
  lastUpdatedDateInput = element(by.id('field_lastUpdatedDate'));
  lastUpdatedByInput = element(by.id('field_lastUpdatedBy'));
  tenantIdInput = element(by.id('field_tenantId'));

  customerSelect = element(by.id('field_customer'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setAddressIdInput(addressId: string): Promise<void> {
    await this.addressIdInput.sendKeys(addressId);
  }

  async getAddressIdInput(): Promise<string> {
    return await this.addressIdInput.getAttribute('value');
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

  async setAddressTypeSelect(addressType: string): Promise<void> {
    await this.addressTypeSelect.sendKeys(addressType);
  }

  async getAddressTypeSelect(): Promise<string> {
    return await this.addressTypeSelect.element(by.css('option:checked')).getText();
  }

  async addressTypeSelectLastOption(): Promise<void> {
    await this.addressTypeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setAddressLangSelect(addressLang: string): Promise<void> {
    await this.addressLangSelect.sendKeys(addressLang);
  }

  async getAddressLangSelect(): Promise<string> {
    return await this.addressLangSelect.element(by.css('option:checked')).getText();
  }

  async addressLangSelectLastOption(): Promise<void> {
    await this.addressLangSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  getFormattedAddressInput(): ElementFinder {
    return this.formattedAddressInput;
  }

  async setRoomInput(room: string): Promise<void> {
    await this.roomInput.sendKeys(room);
  }

  async getRoomInput(): Promise<string> {
    return await this.roomInput.getAttribute('value');
  }

  async setFloorInput(floor: string): Promise<void> {
    await this.floorInput.sendKeys(floor);
  }

  async getFloorInput(): Promise<string> {
    return await this.floorInput.getAttribute('value');
  }

  async setBlockInput(block: string): Promise<void> {
    await this.blockInput.sendKeys(block);
  }

  async getBlockInput(): Promise<string> {
    return await this.blockInput.getAttribute('value');
  }

  async setBuildingInput(building: string): Promise<void> {
    await this.buildingInput.sendKeys(building);
  }

  async getBuildingInput(): Promise<string> {
    return await this.buildingInput.getAttribute('value');
  }

  async setStreetEstateInput(streetEstate: string): Promise<void> {
    await this.streetEstateInput.sendKeys(streetEstate);
  }

  async getStreetEstateInput(): Promise<string> {
    return await this.streetEstateInput.getAttribute('value');
  }

  async setDistrictInput(district: string): Promise<void> {
    await this.districtInput.sendKeys(district);
  }

  async getDistrictInput(): Promise<string> {
    return await this.districtInput.getAttribute('value');
  }

  async setRegionInput(region: string): Promise<void> {
    await this.regionInput.sendKeys(region);
  }

  async getRegionInput(): Promise<string> {
    return await this.regionInput.getAttribute('value');
  }

  async setAddress1Input(address1: string): Promise<void> {
    await this.address1Input.sendKeys(address1);
  }

  async getAddress1Input(): Promise<string> {
    return await this.address1Input.getAttribute('value');
  }

  async setAddress2Input(address2: string): Promise<void> {
    await this.address2Input.sendKeys(address2);
  }

  async getAddress2Input(): Promise<string> {
    return await this.address2Input.getAttribute('value');
  }

  async setAddress3Input(address3: string): Promise<void> {
    await this.address3Input.sendKeys(address3);
  }

  async getAddress3Input(): Promise<string> {
    return await this.address3Input.getAttribute('value');
  }

  async setAddress4Input(address4: string): Promise<void> {
    await this.address4Input.sendKeys(address4);
  }

  async getAddress4Input(): Promise<string> {
    return await this.address4Input.getAttribute('value');
  }

  async setAddress5Input(address5: string): Promise<void> {
    await this.address5Input.sendKeys(address5);
  }

  async getAddress5Input(): Promise<string> {
    return await this.address5Input.getAttribute('value');
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

export class CustAddressDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-custAddress-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-custAddress'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
