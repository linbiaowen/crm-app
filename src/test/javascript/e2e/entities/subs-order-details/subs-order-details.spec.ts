import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { SubsOrderDetailsComponentsPage, SubsOrderDetailsDeleteDialog, SubsOrderDetailsUpdatePage } from './subs-order-details.page-object';

const expect = chai.expect;

describe('SubsOrderDetails e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let subsOrderDetailsComponentsPage: SubsOrderDetailsComponentsPage;
  let subsOrderDetailsUpdatePage: SubsOrderDetailsUpdatePage;
  let subsOrderDetailsDeleteDialog: SubsOrderDetailsDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load SubsOrderDetails', async () => {
    await navBarPage.goToEntity('subs-order-details');
    subsOrderDetailsComponentsPage = new SubsOrderDetailsComponentsPage();
    await browser.wait(ec.visibilityOf(subsOrderDetailsComponentsPage.title), 5000);
    expect(await subsOrderDetailsComponentsPage.getTitle()).to.eq('crmwebApp.subsOrderDetails.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(subsOrderDetailsComponentsPage.entities), ec.visibilityOf(subsOrderDetailsComponentsPage.noResult)),
      1000
    );
  });

  it('should load create SubsOrderDetails page', async () => {
    await subsOrderDetailsComponentsPage.clickOnCreateButton();
    subsOrderDetailsUpdatePage = new SubsOrderDetailsUpdatePage();
    expect(await subsOrderDetailsUpdatePage.getPageTitle()).to.eq('crmwebApp.subsOrderDetails.home.createOrEditLabel');
    await subsOrderDetailsUpdatePage.cancel();
  });

  it('should create and save SubsOrderDetails', async () => {
    const nbButtonsBeforeCreate = await subsOrderDetailsComponentsPage.countDeleteButtons();

    await subsOrderDetailsComponentsPage.clickOnCreateButton();

    await promise.all([
      subsOrderDetailsUpdatePage.setSubsOrderSeqIdInput('5'),
      subsOrderDetailsUpdatePage.setSubscriptionIdInput('subscriptionId'),
      subsOrderDetailsUpdatePage.setStartDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      subsOrderDetailsUpdatePage.setEndDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      subsOrderDetailsUpdatePage.setOrderIdInput('orderId'),
      subsOrderDetailsUpdatePage.setSsaNbrInput('ssaNbr'),
      subsOrderDetailsUpdatePage.setPrimaryMsisdnInput('primaryMsisdn'),
      subsOrderDetailsUpdatePage.setIccidInput('iccid'),
      subsOrderDetailsUpdatePage.setImsiInput('imsi'),
      subsOrderDetailsUpdatePage.setSimVerifiedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      subsOrderDetailsUpdatePage.setBillingAcctIdInput('billingAcctId'),
      subsOrderDetailsUpdatePage.setBillCycleIdInput('5'),
      subsOrderDetailsUpdatePage.setMnpRequestedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      subsOrderDetailsUpdatePage.setMnpTicketInput('mnpTicket'),
      subsOrderDetailsUpdatePage.setMnpPortInSessionInput('mnpPortInSession'),
      subsOrderDetailsUpdatePage.setMnpOriginalIdInput('mnpOriginalId'),
      subsOrderDetailsUpdatePage.setMnpCustNameInput('mnpCustName'),
      subsOrderDetailsUpdatePage.setMnpIdNbrInput('mnpIdNbr'),
      subsOrderDetailsUpdatePage.setMnpIdTypeInput('mnpIdType'),
      subsOrderDetailsUpdatePage.langSelectLastOption(),
      subsOrderDetailsUpdatePage.setOfferIdInput('offerId'),
      subsOrderDetailsUpdatePage.setOfferNameInput('offerName'),
      subsOrderDetailsUpdatePage.setMatrixxCatalogIdInput('matrixxCatalogId'),
      subsOrderDetailsUpdatePage.setMatrixxResourceIdInput('matrixxResourceId'),
      subsOrderDetailsUpdatePage.setMatrixxObjectIdInput('matrixxObjectId'),
      subsOrderDetailsUpdatePage.setSalesChannelInput('salesChannel'),
      subsOrderDetailsUpdatePage.setAdvancePaymentMonthsInput('5'),
      subsOrderDetailsUpdatePage.setOfferPriceInput('5'),
      subsOrderDetailsUpdatePage.setNetworkTypeInput('networkType'),
      subsOrderDetailsUpdatePage.servicetypeSelectLastOption(),
      subsOrderDetailsUpdatePage.setOfferPlanCodeInput('offerPlanCode'),
      subsOrderDetailsUpdatePage.setServiceInPersonInput('serviceInPerson'),
      subsOrderDetailsUpdatePage.setFcmTokenInput('fcmToken'),
      subsOrderDetailsUpdatePage.setRemarksInput('remarks'),
      subsOrderDetailsUpdatePage.setCdVersionInput('cdVersion'),
      subsOrderDetailsUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      subsOrderDetailsUpdatePage.setCreatedByInput('createdBy'),
      subsOrderDetailsUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      subsOrderDetailsUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      subsOrderDetailsUpdatePage.setTenantIdInput('tenantId'),
      subsOrderDetailsUpdatePage.custSubscriptionSelectLastOption(),
      subsOrderDetailsUpdatePage.orderMasterSelectLastOption()
    ]);

    expect(await subsOrderDetailsUpdatePage.getSubsOrderSeqIdInput()).to.eq('5', 'Expected subsOrderSeqId value to be equals to 5');
    expect(await subsOrderDetailsUpdatePage.getSubscriptionIdInput()).to.eq(
      'subscriptionId',
      'Expected SubscriptionId value to be equals to subscriptionId'
    );
    expect(await subsOrderDetailsUpdatePage.getStartDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected startDate value to be equals to 2000-12-31'
    );
    expect(await subsOrderDetailsUpdatePage.getEndDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected endDate value to be equals to 2000-12-31'
    );
    expect(await subsOrderDetailsUpdatePage.getOrderIdInput()).to.eq('orderId', 'Expected OrderId value to be equals to orderId');
    expect(await subsOrderDetailsUpdatePage.getSsaNbrInput()).to.eq('ssaNbr', 'Expected SsaNbr value to be equals to ssaNbr');
    expect(await subsOrderDetailsUpdatePage.getPrimaryMsisdnInput()).to.eq(
      'primaryMsisdn',
      'Expected PrimaryMsisdn value to be equals to primaryMsisdn'
    );
    expect(await subsOrderDetailsUpdatePage.getIccidInput()).to.eq('iccid', 'Expected Iccid value to be equals to iccid');
    expect(await subsOrderDetailsUpdatePage.getImsiInput()).to.eq('imsi', 'Expected Imsi value to be equals to imsi');
    const selectedSimVerified = subsOrderDetailsUpdatePage.getSimVerifiedInput();
    if (await selectedSimVerified.isSelected()) {
      await subsOrderDetailsUpdatePage.getSimVerifiedInput().click();
      expect(await subsOrderDetailsUpdatePage.getSimVerifiedInput().isSelected(), 'Expected simVerified not to be selected').to.be.false;
    } else {
      await subsOrderDetailsUpdatePage.getSimVerifiedInput().click();
      expect(await subsOrderDetailsUpdatePage.getSimVerifiedInput().isSelected(), 'Expected simVerified to be selected').to.be.true;
    }
    expect(await subsOrderDetailsUpdatePage.getSimVerifiedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected simVerifiedDate value to be equals to 2000-12-31'
    );
    expect(await subsOrderDetailsUpdatePage.getBillingAcctIdInput()).to.eq(
      'billingAcctId',
      'Expected BillingAcctId value to be equals to billingAcctId'
    );
    expect(await subsOrderDetailsUpdatePage.getBillCycleIdInput()).to.eq('5', 'Expected billCycleId value to be equals to 5');
    expect(await subsOrderDetailsUpdatePage.getMnpRequestedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected mnpRequestedDate value to be equals to 2000-12-31'
    );
    expect(await subsOrderDetailsUpdatePage.getMnpTicketInput()).to.eq('mnpTicket', 'Expected MnpTicket value to be equals to mnpTicket');
    expect(await subsOrderDetailsUpdatePage.getMnpPortInSessionInput()).to.eq(
      'mnpPortInSession',
      'Expected MnpPortInSession value to be equals to mnpPortInSession'
    );
    expect(await subsOrderDetailsUpdatePage.getMnpOriginalIdInput()).to.eq(
      'mnpOriginalId',
      'Expected MnpOriginalId value to be equals to mnpOriginalId'
    );
    expect(await subsOrderDetailsUpdatePage.getMnpCustNameInput()).to.eq(
      'mnpCustName',
      'Expected MnpCustName value to be equals to mnpCustName'
    );
    expect(await subsOrderDetailsUpdatePage.getMnpIdNbrInput()).to.eq('mnpIdNbr', 'Expected MnpIdNbr value to be equals to mnpIdNbr');
    expect(await subsOrderDetailsUpdatePage.getMnpIdTypeInput()).to.eq('mnpIdType', 'Expected MnpIdType value to be equals to mnpIdType');
    const selectedHthkMsisdn = subsOrderDetailsUpdatePage.getHthkMsisdnInput();
    if (await selectedHthkMsisdn.isSelected()) {
      await subsOrderDetailsUpdatePage.getHthkMsisdnInput().click();
      expect(await subsOrderDetailsUpdatePage.getHthkMsisdnInput().isSelected(), 'Expected hthkMsisdn not to be selected').to.be.false;
    } else {
      await subsOrderDetailsUpdatePage.getHthkMsisdnInput().click();
      expect(await subsOrderDetailsUpdatePage.getHthkMsisdnInput().isSelected(), 'Expected hthkMsisdn to be selected').to.be.true;
    }
    expect(await subsOrderDetailsUpdatePage.getOfferIdInput()).to.eq('offerId', 'Expected OfferId value to be equals to offerId');
    expect(await subsOrderDetailsUpdatePage.getOfferNameInput()).to.eq('offerName', 'Expected OfferName value to be equals to offerName');
    expect(await subsOrderDetailsUpdatePage.getMatrixxCatalogIdInput()).to.eq(
      'matrixxCatalogId',
      'Expected MatrixxCatalogId value to be equals to matrixxCatalogId'
    );
    expect(await subsOrderDetailsUpdatePage.getMatrixxResourceIdInput()).to.eq(
      'matrixxResourceId',
      'Expected MatrixxResourceId value to be equals to matrixxResourceId'
    );
    expect(await subsOrderDetailsUpdatePage.getMatrixxObjectIdInput()).to.eq(
      'matrixxObjectId',
      'Expected MatrixxObjectId value to be equals to matrixxObjectId'
    );
    expect(await subsOrderDetailsUpdatePage.getSalesChannelInput()).to.eq(
      'salesChannel',
      'Expected SalesChannel value to be equals to salesChannel'
    );
    expect(await subsOrderDetailsUpdatePage.getAdvancePaymentMonthsInput()).to.eq(
      '5',
      'Expected advancePaymentMonths value to be equals to 5'
    );
    expect(await subsOrderDetailsUpdatePage.getOfferPriceInput()).to.eq('5', 'Expected offerPrice value to be equals to 5');
    expect(await subsOrderDetailsUpdatePage.getNetworkTypeInput()).to.eq(
      'networkType',
      'Expected NetworkType value to be equals to networkType'
    );
    expect(await subsOrderDetailsUpdatePage.getOfferPlanCodeInput()).to.eq(
      'offerPlanCode',
      'Expected OfferPlanCode value to be equals to offerPlanCode'
    );
    expect(await subsOrderDetailsUpdatePage.getServiceInPersonInput()).to.eq(
      'serviceInPerson',
      'Expected ServiceInPerson value to be equals to serviceInPerson'
    );
    expect(await subsOrderDetailsUpdatePage.getFcmTokenInput()).to.eq('fcmToken', 'Expected FcmToken value to be equals to fcmToken');
    expect(await subsOrderDetailsUpdatePage.getRemarksInput()).to.eq('remarks', 'Expected Remarks value to be equals to remarks');
    expect(await subsOrderDetailsUpdatePage.getCdVersionInput()).to.eq('cdVersion', 'Expected CdVersion value to be equals to cdVersion');
    expect(await subsOrderDetailsUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await subsOrderDetailsUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await subsOrderDetailsUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await subsOrderDetailsUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await subsOrderDetailsUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await subsOrderDetailsUpdatePage.save();
    expect(await subsOrderDetailsUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await subsOrderDetailsComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last SubsOrderDetails', async () => {
    const nbButtonsBeforeDelete = await subsOrderDetailsComponentsPage.countDeleteButtons();
    await subsOrderDetailsComponentsPage.clickOnLastDeleteButton();

    subsOrderDetailsDeleteDialog = new SubsOrderDetailsDeleteDialog();
    expect(await subsOrderDetailsDeleteDialog.getDialogTitle()).to.eq('crmwebApp.subsOrderDetails.delete.question');
    await subsOrderDetailsDeleteDialog.clickOnConfirmButton();

    expect(await subsOrderDetailsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
