import { element, by, ElementFinder } from 'protractor';

export class ModelGroupComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-model-group div table .btn-danger'));
  title = element.all(by.css('jhi-model-group div h2#page-heading span')).first();
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

export class ModelGroupUpdatePage {
  pageTitle = element(by.id('jhi-model-group-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  modelGroupInput = element(by.id('field_modelGroup'));
  groupDescInput = element(by.id('field_groupDesc'));
  listPriceInput = element(by.id('field_listPrice'));
  brandInput = element(by.id('field_brand'));
  modelInput = element(by.id('field_model'));
  origCountryInput = element(by.id('field_origCountry'));
  networkInput = element(by.id('field_network'));
  cameraInput = element(by.id('field_camera'));
  memCardSlotInput = element(by.id('field_memCardSlot'));
  dataTransferInput = element(by.id('field_dataTransfer'));
  warrantyInput = element(by.id('field_warranty'));
  warrantyProviderInput = element(by.id('field_warrantyProvider'));
  modelCateInput = element(by.id('field_modelCate'));
  remarksInput = element(by.id('field_remarks'));
  remarksEndDateInput = element(by.id('field_remarksEndDate'));
  brandChiInput = element(by.id('field_brandChi'));
  modelChiInput = element(by.id('field_modelChi'));
  origCountryChiInput = element(by.id('field_origCountryChi'));
  networkChiInput = element(by.id('field_networkChi'));
  cameraChiInput = element(by.id('field_cameraChi'));
  memCardSlotChiInput = element(by.id('field_memCardSlotChi'));
  dataTransferChiInput = element(by.id('field_dataTransferChi'));
  warrantyChiInput = element(by.id('field_warrantyChi'));
  warrantyProviderChiInput = element(by.id('field_warrantyProviderChi'));
  modelCateChiInput = element(by.id('field_modelCateChi'));
  remarksChiInput = element(by.id('field_remarksChi'));
  remarksChiEndDateInput = element(by.id('field_remarksChiEndDate'));
  couponFlagInput = element(by.id('field_couponFlag'));
  createdDateInput = element(by.id('field_createdDate'));
  createdByInput = element(by.id('field_createdBy'));
  lastUpdatedDateInput = element(by.id('field_lastUpdatedDate'));
  lastUpdatedByInput = element(by.id('field_lastUpdatedBy'));
  tenantIdInput = element(by.id('field_tenantId'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setModelGroupInput(modelGroup: string): Promise<void> {
    await this.modelGroupInput.sendKeys(modelGroup);
  }

  async getModelGroupInput(): Promise<string> {
    return await this.modelGroupInput.getAttribute('value');
  }

  async setGroupDescInput(groupDesc: string): Promise<void> {
    await this.groupDescInput.sendKeys(groupDesc);
  }

  async getGroupDescInput(): Promise<string> {
    return await this.groupDescInput.getAttribute('value');
  }

  async setListPriceInput(listPrice: string): Promise<void> {
    await this.listPriceInput.sendKeys(listPrice);
  }

  async getListPriceInput(): Promise<string> {
    return await this.listPriceInput.getAttribute('value');
  }

  async setBrandInput(brand: string): Promise<void> {
    await this.brandInput.sendKeys(brand);
  }

  async getBrandInput(): Promise<string> {
    return await this.brandInput.getAttribute('value');
  }

  async setModelInput(model: string): Promise<void> {
    await this.modelInput.sendKeys(model);
  }

  async getModelInput(): Promise<string> {
    return await this.modelInput.getAttribute('value');
  }

  async setOrigCountryInput(origCountry: string): Promise<void> {
    await this.origCountryInput.sendKeys(origCountry);
  }

  async getOrigCountryInput(): Promise<string> {
    return await this.origCountryInput.getAttribute('value');
  }

  async setNetworkInput(network: string): Promise<void> {
    await this.networkInput.sendKeys(network);
  }

  async getNetworkInput(): Promise<string> {
    return await this.networkInput.getAttribute('value');
  }

  async setCameraInput(camera: string): Promise<void> {
    await this.cameraInput.sendKeys(camera);
  }

  async getCameraInput(): Promise<string> {
    return await this.cameraInput.getAttribute('value');
  }

  async setMemCardSlotInput(memCardSlot: string): Promise<void> {
    await this.memCardSlotInput.sendKeys(memCardSlot);
  }

  async getMemCardSlotInput(): Promise<string> {
    return await this.memCardSlotInput.getAttribute('value');
  }

  async setDataTransferInput(dataTransfer: string): Promise<void> {
    await this.dataTransferInput.sendKeys(dataTransfer);
  }

  async getDataTransferInput(): Promise<string> {
    return await this.dataTransferInput.getAttribute('value');
  }

  async setWarrantyInput(warranty: string): Promise<void> {
    await this.warrantyInput.sendKeys(warranty);
  }

  async getWarrantyInput(): Promise<string> {
    return await this.warrantyInput.getAttribute('value');
  }

  async setWarrantyProviderInput(warrantyProvider: string): Promise<void> {
    await this.warrantyProviderInput.sendKeys(warrantyProvider);
  }

  async getWarrantyProviderInput(): Promise<string> {
    return await this.warrantyProviderInput.getAttribute('value');
  }

  async setModelCateInput(modelCate: string): Promise<void> {
    await this.modelCateInput.sendKeys(modelCate);
  }

  async getModelCateInput(): Promise<string> {
    return await this.modelCateInput.getAttribute('value');
  }

  async setRemarksInput(remarks: string): Promise<void> {
    await this.remarksInput.sendKeys(remarks);
  }

  async getRemarksInput(): Promise<string> {
    return await this.remarksInput.getAttribute('value');
  }

  async setRemarksEndDateInput(remarksEndDate: string): Promise<void> {
    await this.remarksEndDateInput.sendKeys(remarksEndDate);
  }

  async getRemarksEndDateInput(): Promise<string> {
    return await this.remarksEndDateInput.getAttribute('value');
  }

  async setBrandChiInput(brandChi: string): Promise<void> {
    await this.brandChiInput.sendKeys(brandChi);
  }

  async getBrandChiInput(): Promise<string> {
    return await this.brandChiInput.getAttribute('value');
  }

  async setModelChiInput(modelChi: string): Promise<void> {
    await this.modelChiInput.sendKeys(modelChi);
  }

  async getModelChiInput(): Promise<string> {
    return await this.modelChiInput.getAttribute('value');
  }

  async setOrigCountryChiInput(origCountryChi: string): Promise<void> {
    await this.origCountryChiInput.sendKeys(origCountryChi);
  }

  async getOrigCountryChiInput(): Promise<string> {
    return await this.origCountryChiInput.getAttribute('value');
  }

  async setNetworkChiInput(networkChi: string): Promise<void> {
    await this.networkChiInput.sendKeys(networkChi);
  }

  async getNetworkChiInput(): Promise<string> {
    return await this.networkChiInput.getAttribute('value');
  }

  async setCameraChiInput(cameraChi: string): Promise<void> {
    await this.cameraChiInput.sendKeys(cameraChi);
  }

  async getCameraChiInput(): Promise<string> {
    return await this.cameraChiInput.getAttribute('value');
  }

  async setMemCardSlotChiInput(memCardSlotChi: string): Promise<void> {
    await this.memCardSlotChiInput.sendKeys(memCardSlotChi);
  }

  async getMemCardSlotChiInput(): Promise<string> {
    return await this.memCardSlotChiInput.getAttribute('value');
  }

  async setDataTransferChiInput(dataTransferChi: string): Promise<void> {
    await this.dataTransferChiInput.sendKeys(dataTransferChi);
  }

  async getDataTransferChiInput(): Promise<string> {
    return await this.dataTransferChiInput.getAttribute('value');
  }

  async setWarrantyChiInput(warrantyChi: string): Promise<void> {
    await this.warrantyChiInput.sendKeys(warrantyChi);
  }

  async getWarrantyChiInput(): Promise<string> {
    return await this.warrantyChiInput.getAttribute('value');
  }

  async setWarrantyProviderChiInput(warrantyProviderChi: string): Promise<void> {
    await this.warrantyProviderChiInput.sendKeys(warrantyProviderChi);
  }

  async getWarrantyProviderChiInput(): Promise<string> {
    return await this.warrantyProviderChiInput.getAttribute('value');
  }

  async setModelCateChiInput(modelCateChi: string): Promise<void> {
    await this.modelCateChiInput.sendKeys(modelCateChi);
  }

  async getModelCateChiInput(): Promise<string> {
    return await this.modelCateChiInput.getAttribute('value');
  }

  async setRemarksChiInput(remarksChi: string): Promise<void> {
    await this.remarksChiInput.sendKeys(remarksChi);
  }

  async getRemarksChiInput(): Promise<string> {
    return await this.remarksChiInput.getAttribute('value');
  }

  async setRemarksChiEndDateInput(remarksChiEndDate: string): Promise<void> {
    await this.remarksChiEndDateInput.sendKeys(remarksChiEndDate);
  }

  async getRemarksChiEndDateInput(): Promise<string> {
    return await this.remarksChiEndDateInput.getAttribute('value');
  }

  async setCouponFlagInput(couponFlag: string): Promise<void> {
    await this.couponFlagInput.sendKeys(couponFlag);
  }

  async getCouponFlagInput(): Promise<string> {
    return await this.couponFlagInput.getAttribute('value');
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

export class ModelGroupDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-modelGroup-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-modelGroup'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
