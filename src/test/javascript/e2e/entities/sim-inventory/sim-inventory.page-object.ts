import { element, by, ElementFinder } from 'protractor';

export class SimInventoryComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-sim-inventory div table .btn-danger'));
  title = element.all(by.css('jhi-sim-inventory div h2#page-heading span')).first();
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

export class SimInventoryUpdatePage {
  pageTitle = element(by.id('jhi-sim-inventory-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  iccidInput = element(by.id('field_iccid'));
  imsiInput = element(by.id('field_imsi'));
  kiInput = element(by.id('field_ki'));
  k4snoInput = element(by.id('field_k4sno'));
  opsnoInput = element(by.id('field_opsno'));
  offerIdInput = element(by.id('field_offerId'));
  offerNameInput = element(by.id('field_offerName'));
  statusSelect = element(by.id('field_status'));
  offerValidFromDateInput = element(by.id('field_offerValidFromDate'));
  offerValidToDateInput = element(by.id('field_offerValidToDate'));
  createdDateInput = element(by.id('field_createdDate'));
  createdByInput = element(by.id('field_createdBy'));
  lastUpdatedDateInput = element(by.id('field_lastUpdatedDate'));
  lastUpdatedByInput = element(by.id('field_lastUpdatedBy'));
  tenantIdInput = element(by.id('field_tenantId'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
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

  async setKiInput(ki: string): Promise<void> {
    await this.kiInput.sendKeys(ki);
  }

  async getKiInput(): Promise<string> {
    return await this.kiInput.getAttribute('value');
  }

  async setK4snoInput(k4sno: string): Promise<void> {
    await this.k4snoInput.sendKeys(k4sno);
  }

  async getK4snoInput(): Promise<string> {
    return await this.k4snoInput.getAttribute('value');
  }

  async setOpsnoInput(opsno: string): Promise<void> {
    await this.opsnoInput.sendKeys(opsno);
  }

  async getOpsnoInput(): Promise<string> {
    return await this.opsnoInput.getAttribute('value');
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

  async setOfferValidFromDateInput(offerValidFromDate: string): Promise<void> {
    await this.offerValidFromDateInput.sendKeys(offerValidFromDate);
  }

  async getOfferValidFromDateInput(): Promise<string> {
    return await this.offerValidFromDateInput.getAttribute('value');
  }

  async setOfferValidToDateInput(offerValidToDate: string): Promise<void> {
    await this.offerValidToDateInput.sendKeys(offerValidToDate);
  }

  async getOfferValidToDateInput(): Promise<string> {
    return await this.offerValidToDateInput.getAttribute('value');
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

export class SimInventoryDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-simInventory-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-simInventory'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
