import { Moment } from 'moment';

export interface IModelGroup {
  id?: string;
  modelGroup?: string;
  groupDesc?: string;
  listPrice?: number;
  brand?: string;
  model?: string;
  origCountry?: string;
  network?: string;
  camera?: string;
  memCardSlot?: string;
  dataTransfer?: string;
  warranty?: string;
  warrantyProvider?: string;
  modelCate?: string;
  remarks?: string;
  remarksEndDate?: string;
  brandChi?: string;
  modelChi?: string;
  origCountryChi?: string;
  networkChi?: string;
  cameraChi?: string;
  memCardSlotChi?: string;
  dataTransferChi?: string;
  warrantyChi?: string;
  warrantyProviderChi?: string;
  modelCateChi?: string;
  remarksChi?: string;
  remarksChiEndDate?: string;
  couponFlag?: string;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdatedDate?: Moment;
  lastUpdatedBy?: string;
  tenantId?: string;
}

export class ModelGroup implements IModelGroup {
  constructor(
    public id?: string,
    public modelGroup?: string,
    public groupDesc?: string,
    public listPrice?: number,
    public brand?: string,
    public model?: string,
    public origCountry?: string,
    public network?: string,
    public camera?: string,
    public memCardSlot?: string,
    public dataTransfer?: string,
    public warranty?: string,
    public warrantyProvider?: string,
    public modelCate?: string,
    public remarks?: string,
    public remarksEndDate?: string,
    public brandChi?: string,
    public modelChi?: string,
    public origCountryChi?: string,
    public networkChi?: string,
    public cameraChi?: string,
    public memCardSlotChi?: string,
    public dataTransferChi?: string,
    public warrantyChi?: string,
    public warrantyProviderChi?: string,
    public modelCateChi?: string,
    public remarksChi?: string,
    public remarksChiEndDate?: string,
    public couponFlag?: string,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdatedDate?: Moment,
    public lastUpdatedBy?: string,
    public tenantId?: string
  ) {}
}
