import { element, by, ElementFinder } from 'protractor';

export class BillCycleComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-bill-cycle div table .btn-danger'));
  title = element.all(by.css('jhi-bill-cycle div h2#page-heading span')).first();
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

export class BillCycleUpdatePage {
  pageTitle = element(by.id('jhi-bill-cycle-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  billCycleIdInput = element(by.id('field_billCycleId'));
  billCycleDescInput = element(by.id('field_billCycleDesc'));
  billCycleInput = element(by.id('field_billCycle'));
  billFrequencyInput = element(by.id('field_billFrequency'));
  billCycleStartDateInput = element(by.id('field_billCycleStartDate'));
  billCycleEndDateInput = element(by.id('field_billCycleEndDate'));
  dueDateOffsetInput = element(by.id('field_dueDateOffset'));
  directDebitProcessDayInput = element(by.id('field_directDebitProcessDay'));
  createdDateInput = element(by.id('field_createdDate'));
  createdByInput = element(by.id('field_createdBy'));
  lastUpdatedDateInput = element(by.id('field_lastUpdatedDate'));
  lastUpdatedByInput = element(by.id('field_lastUpdatedBy'));
  tenantIdInput = element(by.id('field_tenantId'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setBillCycleIdInput(billCycleId: string): Promise<void> {
    await this.billCycleIdInput.sendKeys(billCycleId);
  }

  async getBillCycleIdInput(): Promise<string> {
    return await this.billCycleIdInput.getAttribute('value');
  }

  async setBillCycleDescInput(billCycleDesc: string): Promise<void> {
    await this.billCycleDescInput.sendKeys(billCycleDesc);
  }

  async getBillCycleDescInput(): Promise<string> {
    return await this.billCycleDescInput.getAttribute('value');
  }

  async setBillCycleInput(billCycle: string): Promise<void> {
    await this.billCycleInput.sendKeys(billCycle);
  }

  async getBillCycleInput(): Promise<string> {
    return await this.billCycleInput.getAttribute('value');
  }

  async setBillFrequencyInput(billFrequency: string): Promise<void> {
    await this.billFrequencyInput.sendKeys(billFrequency);
  }

  async getBillFrequencyInput(): Promise<string> {
    return await this.billFrequencyInput.getAttribute('value');
  }

  async setBillCycleStartDateInput(billCycleStartDate: string): Promise<void> {
    await this.billCycleStartDateInput.sendKeys(billCycleStartDate);
  }

  async getBillCycleStartDateInput(): Promise<string> {
    return await this.billCycleStartDateInput.getAttribute('value');
  }

  async setBillCycleEndDateInput(billCycleEndDate: string): Promise<void> {
    await this.billCycleEndDateInput.sendKeys(billCycleEndDate);
  }

  async getBillCycleEndDateInput(): Promise<string> {
    return await this.billCycleEndDateInput.getAttribute('value');
  }

  async setDueDateOffsetInput(dueDateOffset: string): Promise<void> {
    await this.dueDateOffsetInput.sendKeys(dueDateOffset);
  }

  async getDueDateOffsetInput(): Promise<string> {
    return await this.dueDateOffsetInput.getAttribute('value');
  }

  async setDirectDebitProcessDayInput(directDebitProcessDay: string): Promise<void> {
    await this.directDebitProcessDayInput.sendKeys(directDebitProcessDay);
  }

  async getDirectDebitProcessDayInput(): Promise<string> {
    return await this.directDebitProcessDayInput.getAttribute('value');
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

export class BillCycleDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-billCycle-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-billCycle'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
