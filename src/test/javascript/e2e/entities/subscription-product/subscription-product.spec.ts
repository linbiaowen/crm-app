import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  SubscriptionProductComponentsPage,
  SubscriptionProductDeleteDialog,
  SubscriptionProductUpdatePage
} from './subscription-product.page-object';

const expect = chai.expect;

describe('SubscriptionProduct e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let subscriptionProductComponentsPage: SubscriptionProductComponentsPage;
  let subscriptionProductUpdatePage: SubscriptionProductUpdatePage;
  let subscriptionProductDeleteDialog: SubscriptionProductDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load SubscriptionProducts', async () => {
    await navBarPage.goToEntity('subscription-product');
    subscriptionProductComponentsPage = new SubscriptionProductComponentsPage();
    await browser.wait(ec.visibilityOf(subscriptionProductComponentsPage.title), 5000);
    expect(await subscriptionProductComponentsPage.getTitle()).to.eq('crmwebApp.subscriptionProduct.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(subscriptionProductComponentsPage.entities), ec.visibilityOf(subscriptionProductComponentsPage.noResult)),
      1000
    );
  });

  it('should load create SubscriptionProduct page', async () => {
    await subscriptionProductComponentsPage.clickOnCreateButton();
    subscriptionProductUpdatePage = new SubscriptionProductUpdatePage();
    expect(await subscriptionProductUpdatePage.getPageTitle()).to.eq('crmwebApp.subscriptionProduct.home.createOrEditLabel');
    await subscriptionProductUpdatePage.cancel();
  });

  it('should create and save SubscriptionProducts', async () => {
    const nbButtonsBeforeCreate = await subscriptionProductComponentsPage.countDeleteButtons();

    await subscriptionProductComponentsPage.clickOnCreateButton();

    await promise.all([
      subscriptionProductUpdatePage.setProductSubsriptionSeqIdInput('5'),
      subscriptionProductUpdatePage.setOrderIdInput('orderId'),
      subscriptionProductUpdatePage.setSubscriptionIdInput('subscriptionId'),
      subscriptionProductUpdatePage.setProductIdInput('productId'),
      subscriptionProductUpdatePage.setProductNameInput('productName'),
      subscriptionProductUpdatePage.setActivationDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      subscriptionProductUpdatePage.setEndDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      subscriptionProductUpdatePage.setSecondMsisdnInput('secondMsisdn'),
      subscriptionProductUpdatePage.setSecondImsiInput('secondImsi'),
      subscriptionProductUpdatePage.setQuantityInput('5'),
      subscriptionProductUpdatePage.setTerminationReasonCodeInput('terminationReasonCode'),
      subscriptionProductUpdatePage.setOfferIdInput('offerId'),
      subscriptionProductUpdatePage.setOfferNameInput('offerName'),
      subscriptionProductUpdatePage.setOfferTypeInput('offerType'),
      subscriptionProductUpdatePage.setMatrixxCatalogIdInput('matrixxCatalogId'),
      subscriptionProductUpdatePage.setMatrixxResourceIdInput('matrixxResourceId'),
      subscriptionProductUpdatePage.setMatrixxObjectIdInput('matrixxObjectId'),
      subscriptionProductUpdatePage.setSalesChannelInput('salesChannel'),
      subscriptionProductUpdatePage.setContractIdInput('contractId'),
      subscriptionProductUpdatePage.setRemarksInput('remarks'),
      subscriptionProductUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      subscriptionProductUpdatePage.setCreatedByInput('createdBy'),
      subscriptionProductUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      subscriptionProductUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      subscriptionProductUpdatePage.setTenantIdInput('tenantId'),
      subscriptionProductUpdatePage.custSubscriptionSelectLastOption()
    ]);

    expect(await subscriptionProductUpdatePage.getProductSubsriptionSeqIdInput()).to.eq(
      '5',
      'Expected productSubsriptionSeqId value to be equals to 5'
    );
    expect(await subscriptionProductUpdatePage.getOrderIdInput()).to.eq('orderId', 'Expected OrderId value to be equals to orderId');
    expect(await subscriptionProductUpdatePage.getSubscriptionIdInput()).to.eq(
      'subscriptionId',
      'Expected SubscriptionId value to be equals to subscriptionId'
    );
    expect(await subscriptionProductUpdatePage.getProductIdInput()).to.eq(
      'productId',
      'Expected ProductId value to be equals to productId'
    );
    expect(await subscriptionProductUpdatePage.getProductNameInput()).to.eq(
      'productName',
      'Expected ProductName value to be equals to productName'
    );
    expect(await subscriptionProductUpdatePage.getActivationDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected activationDate value to be equals to 2000-12-31'
    );
    expect(await subscriptionProductUpdatePage.getEndDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected endDate value to be equals to 2000-12-31'
    );
    expect(await subscriptionProductUpdatePage.getSecondMsisdnInput()).to.eq(
      'secondMsisdn',
      'Expected SecondMsisdn value to be equals to secondMsisdn'
    );
    expect(await subscriptionProductUpdatePage.getSecondImsiInput()).to.eq(
      'secondImsi',
      'Expected SecondImsi value to be equals to secondImsi'
    );
    expect(await subscriptionProductUpdatePage.getQuantityInput()).to.eq('5', 'Expected quantity value to be equals to 5');
    expect(await subscriptionProductUpdatePage.getTerminationReasonCodeInput()).to.eq(
      'terminationReasonCode',
      'Expected TerminationReasonCode value to be equals to terminationReasonCode'
    );
    expect(await subscriptionProductUpdatePage.getOfferIdInput()).to.eq('offerId', 'Expected OfferId value to be equals to offerId');
    expect(await subscriptionProductUpdatePage.getOfferNameInput()).to.eq(
      'offerName',
      'Expected OfferName value to be equals to offerName'
    );
    expect(await subscriptionProductUpdatePage.getOfferTypeInput()).to.eq(
      'offerType',
      'Expected OfferType value to be equals to offerType'
    );
    expect(await subscriptionProductUpdatePage.getMatrixxCatalogIdInput()).to.eq(
      'matrixxCatalogId',
      'Expected MatrixxCatalogId value to be equals to matrixxCatalogId'
    );
    expect(await subscriptionProductUpdatePage.getMatrixxResourceIdInput()).to.eq(
      'matrixxResourceId',
      'Expected MatrixxResourceId value to be equals to matrixxResourceId'
    );
    expect(await subscriptionProductUpdatePage.getMatrixxObjectIdInput()).to.eq(
      'matrixxObjectId',
      'Expected MatrixxObjectId value to be equals to matrixxObjectId'
    );
    expect(await subscriptionProductUpdatePage.getSalesChannelInput()).to.eq(
      'salesChannel',
      'Expected SalesChannel value to be equals to salesChannel'
    );
    expect(await subscriptionProductUpdatePage.getContractIdInput()).to.eq(
      'contractId',
      'Expected ContractId value to be equals to contractId'
    );
    const selectedAutoRenewal = subscriptionProductUpdatePage.getAutoRenewalInput();
    if (await selectedAutoRenewal.isSelected()) {
      await subscriptionProductUpdatePage.getAutoRenewalInput().click();
      expect(await subscriptionProductUpdatePage.getAutoRenewalInput().isSelected(), 'Expected autoRenewal not to be selected').to.be.false;
    } else {
      await subscriptionProductUpdatePage.getAutoRenewalInput().click();
      expect(await subscriptionProductUpdatePage.getAutoRenewalInput().isSelected(), 'Expected autoRenewal to be selected').to.be.true;
    }
    const selectedAutoPay = subscriptionProductUpdatePage.getAutoPayInput();
    if (await selectedAutoPay.isSelected()) {
      await subscriptionProductUpdatePage.getAutoPayInput().click();
      expect(await subscriptionProductUpdatePage.getAutoPayInput().isSelected(), 'Expected autoPay not to be selected').to.be.false;
    } else {
      await subscriptionProductUpdatePage.getAutoPayInput().click();
      expect(await subscriptionProductUpdatePage.getAutoPayInput().isSelected(), 'Expected autoPay to be selected').to.be.true;
    }
    expect(await subscriptionProductUpdatePage.getRemarksInput()).to.eq('remarks', 'Expected Remarks value to be equals to remarks');
    const selectedVendorProvisionInd = subscriptionProductUpdatePage.getVendorProvisionIndInput();
    if (await selectedVendorProvisionInd.isSelected()) {
      await subscriptionProductUpdatePage.getVendorProvisionIndInput().click();
      expect(
        await subscriptionProductUpdatePage.getVendorProvisionIndInput().isSelected(),
        'Expected vendorProvisionInd not to be selected'
      ).to.be.false;
    } else {
      await subscriptionProductUpdatePage.getVendorProvisionIndInput().click();
      expect(await subscriptionProductUpdatePage.getVendorProvisionIndInput().isSelected(), 'Expected vendorProvisionInd to be selected').to
        .be.true;
    }
    expect(await subscriptionProductUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await subscriptionProductUpdatePage.getCreatedByInput()).to.eq(
      'createdBy',
      'Expected CreatedBy value to be equals to createdBy'
    );
    expect(await subscriptionProductUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await subscriptionProductUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await subscriptionProductUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await subscriptionProductUpdatePage.save();
    expect(await subscriptionProductUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await subscriptionProductComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last SubscriptionProduct', async () => {
    const nbButtonsBeforeDelete = await subscriptionProductComponentsPage.countDeleteButtons();
    await subscriptionProductComponentsPage.clickOnLastDeleteButton();

    subscriptionProductDeleteDialog = new SubscriptionProductDeleteDialog();
    expect(await subscriptionProductDeleteDialog.getDialogTitle()).to.eq('crmwebApp.subscriptionProduct.delete.question');
    await subscriptionProductDeleteDialog.clickOnConfirmButton();

    expect(await subscriptionProductComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
