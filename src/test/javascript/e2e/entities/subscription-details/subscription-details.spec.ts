import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  SubscriptionDetailsComponentsPage,
  SubscriptionDetailsDeleteDialog,
  SubscriptionDetailsUpdatePage
} from './subscription-details.page-object';

const expect = chai.expect;

describe('SubscriptionDetails e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let subscriptionDetailsComponentsPage: SubscriptionDetailsComponentsPage;
  let subscriptionDetailsUpdatePage: SubscriptionDetailsUpdatePage;
  let subscriptionDetailsDeleteDialog: SubscriptionDetailsDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load SubscriptionDetails', async () => {
    await navBarPage.goToEntity('subscription-details');
    subscriptionDetailsComponentsPage = new SubscriptionDetailsComponentsPage();
    await browser.wait(ec.visibilityOf(subscriptionDetailsComponentsPage.title), 5000);
    expect(await subscriptionDetailsComponentsPage.getTitle()).to.eq('crmwebApp.subscriptionDetails.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(subscriptionDetailsComponentsPage.entities), ec.visibilityOf(subscriptionDetailsComponentsPage.noResult)),
      1000
    );
  });

  it('should load create SubscriptionDetails page', async () => {
    await subscriptionDetailsComponentsPage.clickOnCreateButton();
    subscriptionDetailsUpdatePage = new SubscriptionDetailsUpdatePage();
    expect(await subscriptionDetailsUpdatePage.getPageTitle()).to.eq('crmwebApp.subscriptionDetails.home.createOrEditLabel');
    await subscriptionDetailsUpdatePage.cancel();
  });

  it('should create and save SubscriptionDetails', async () => {
    const nbButtonsBeforeCreate = await subscriptionDetailsComponentsPage.countDeleteButtons();

    await subscriptionDetailsComponentsPage.clickOnCreateButton();

    await promise.all([
      subscriptionDetailsUpdatePage.setSubsDetailIdInput('5'),
      subscriptionDetailsUpdatePage.setSubscriptionIdInput('subscriptionId'),
      subscriptionDetailsUpdatePage.setStartDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      subscriptionDetailsUpdatePage.setEndDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      subscriptionDetailsUpdatePage.setOrderIdInput('orderId'),
      subscriptionDetailsUpdatePage.setSsaNbrInput('ssaNbr'),
      subscriptionDetailsUpdatePage.setPrimaryMsisdnInput('primaryMsisdn'),
      subscriptionDetailsUpdatePage.setIccidInput('iccid'),
      subscriptionDetailsUpdatePage.setImsiInput('imsi'),
      subscriptionDetailsUpdatePage.setMnpRequestedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      subscriptionDetailsUpdatePage.langSelectLastOption(),
      subscriptionDetailsUpdatePage.setBaseOfferIdInput('baseOfferId'),
      subscriptionDetailsUpdatePage.setBaseOfferNameInput('baseOfferName'),
      subscriptionDetailsUpdatePage.setMatrixxCatalogIdInput('matrixxCatalogId'),
      subscriptionDetailsUpdatePage.setMatrixxResourceIdInput('matrixxResourceId'),
      subscriptionDetailsUpdatePage.setMatrixxObjectIdInput('matrixxObjectId'),
      subscriptionDetailsUpdatePage.setSalesChannelInput('salesChannel'),
      subscriptionDetailsUpdatePage.setAdvancePaymentMonthsInput('5'),
      subscriptionDetailsUpdatePage.setOfferPriceInput('5'),
      subscriptionDetailsUpdatePage.setNetworkTypeInput('networkType'),
      subscriptionDetailsUpdatePage.setServiceTypeInput('serviceType'),
      subscriptionDetailsUpdatePage.setOfferPlanCodeInput('offerPlanCode'),
      subscriptionDetailsUpdatePage.setServiceInPersonInput('serviceInPerson'),
      subscriptionDetailsUpdatePage.setFcmTokenInput('fcmToken'),
      subscriptionDetailsUpdatePage.setRemarksInput('remarks'),
      subscriptionDetailsUpdatePage.setCdVersionInput('cdVersion'),
      subscriptionDetailsUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      subscriptionDetailsUpdatePage.setCreatedByInput('createdBy'),
      subscriptionDetailsUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      subscriptionDetailsUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      subscriptionDetailsUpdatePage.setTenantIdInput('tenantId'),
      subscriptionDetailsUpdatePage.custSubscriptionSelectLastOption()
    ]);

    expect(await subscriptionDetailsUpdatePage.getSubsDetailIdInput()).to.eq('5', 'Expected subsDetailId value to be equals to 5');
    expect(await subscriptionDetailsUpdatePage.getSubscriptionIdInput()).to.eq(
      'subscriptionId',
      'Expected SubscriptionId value to be equals to subscriptionId'
    );
    expect(await subscriptionDetailsUpdatePage.getStartDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected startDate value to be equals to 2000-12-31'
    );
    expect(await subscriptionDetailsUpdatePage.getEndDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected endDate value to be equals to 2000-12-31'
    );
    expect(await subscriptionDetailsUpdatePage.getOrderIdInput()).to.eq('orderId', 'Expected OrderId value to be equals to orderId');
    expect(await subscriptionDetailsUpdatePage.getSsaNbrInput()).to.eq('ssaNbr', 'Expected SsaNbr value to be equals to ssaNbr');
    expect(await subscriptionDetailsUpdatePage.getPrimaryMsisdnInput()).to.eq(
      'primaryMsisdn',
      'Expected PrimaryMsisdn value to be equals to primaryMsisdn'
    );
    expect(await subscriptionDetailsUpdatePage.getIccidInput()).to.eq('iccid', 'Expected Iccid value to be equals to iccid');
    expect(await subscriptionDetailsUpdatePage.getImsiInput()).to.eq('imsi', 'Expected Imsi value to be equals to imsi');
    expect(await subscriptionDetailsUpdatePage.getMnpRequestedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected mnpRequestedDate value to be equals to 2000-12-31'
    );
    expect(await subscriptionDetailsUpdatePage.getBaseOfferIdInput()).to.eq(
      'baseOfferId',
      'Expected BaseOfferId value to be equals to baseOfferId'
    );
    expect(await subscriptionDetailsUpdatePage.getBaseOfferNameInput()).to.eq(
      'baseOfferName',
      'Expected BaseOfferName value to be equals to baseOfferName'
    );
    expect(await subscriptionDetailsUpdatePage.getMatrixxCatalogIdInput()).to.eq(
      'matrixxCatalogId',
      'Expected MatrixxCatalogId value to be equals to matrixxCatalogId'
    );
    expect(await subscriptionDetailsUpdatePage.getMatrixxResourceIdInput()).to.eq(
      'matrixxResourceId',
      'Expected MatrixxResourceId value to be equals to matrixxResourceId'
    );
    expect(await subscriptionDetailsUpdatePage.getMatrixxObjectIdInput()).to.eq(
      'matrixxObjectId',
      'Expected MatrixxObjectId value to be equals to matrixxObjectId'
    );
    expect(await subscriptionDetailsUpdatePage.getSalesChannelInput()).to.eq(
      'salesChannel',
      'Expected SalesChannel value to be equals to salesChannel'
    );
    expect(await subscriptionDetailsUpdatePage.getAdvancePaymentMonthsInput()).to.eq(
      '5',
      'Expected advancePaymentMonths value to be equals to 5'
    );
    expect(await subscriptionDetailsUpdatePage.getOfferPriceInput()).to.eq('5', 'Expected offerPrice value to be equals to 5');
    expect(await subscriptionDetailsUpdatePage.getNetworkTypeInput()).to.eq(
      'networkType',
      'Expected NetworkType value to be equals to networkType'
    );
    expect(await subscriptionDetailsUpdatePage.getServiceTypeInput()).to.eq(
      'serviceType',
      'Expected ServiceType value to be equals to serviceType'
    );
    expect(await subscriptionDetailsUpdatePage.getOfferPlanCodeInput()).to.eq(
      'offerPlanCode',
      'Expected OfferPlanCode value to be equals to offerPlanCode'
    );
    expect(await subscriptionDetailsUpdatePage.getServiceInPersonInput()).to.eq(
      'serviceInPerson',
      'Expected ServiceInPerson value to be equals to serviceInPerson'
    );
    expect(await subscriptionDetailsUpdatePage.getFcmTokenInput()).to.eq('fcmToken', 'Expected FcmToken value to be equals to fcmToken');
    expect(await subscriptionDetailsUpdatePage.getRemarksInput()).to.eq('remarks', 'Expected Remarks value to be equals to remarks');
    expect(await subscriptionDetailsUpdatePage.getCdVersionInput()).to.eq(
      'cdVersion',
      'Expected CdVersion value to be equals to cdVersion'
    );
    expect(await subscriptionDetailsUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await subscriptionDetailsUpdatePage.getCreatedByInput()).to.eq(
      'createdBy',
      'Expected CreatedBy value to be equals to createdBy'
    );
    expect(await subscriptionDetailsUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await subscriptionDetailsUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await subscriptionDetailsUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await subscriptionDetailsUpdatePage.save();
    expect(await subscriptionDetailsUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await subscriptionDetailsComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last SubscriptionDetails', async () => {
    const nbButtonsBeforeDelete = await subscriptionDetailsComponentsPage.countDeleteButtons();
    await subscriptionDetailsComponentsPage.clickOnLastDeleteButton();

    subscriptionDetailsDeleteDialog = new SubscriptionDetailsDeleteDialog();
    expect(await subscriptionDetailsDeleteDialog.getDialogTitle()).to.eq('crmwebApp.subscriptionDetails.delete.question');
    await subscriptionDetailsDeleteDialog.clickOnConfirmButton();

    expect(await subscriptionDetailsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
