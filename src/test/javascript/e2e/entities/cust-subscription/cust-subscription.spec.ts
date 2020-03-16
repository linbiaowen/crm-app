import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { CustSubscriptionComponentsPage, CustSubscriptionDeleteDialog, CustSubscriptionUpdatePage } from './cust-subscription.page-object';

const expect = chai.expect;

describe('CustSubscription e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let custSubscriptionComponentsPage: CustSubscriptionComponentsPage;
  let custSubscriptionUpdatePage: CustSubscriptionUpdatePage;
  let custSubscriptionDeleteDialog: CustSubscriptionDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load CustSubscriptions', async () => {
    await navBarPage.goToEntity('cust-subscription');
    custSubscriptionComponentsPage = new CustSubscriptionComponentsPage();
    await browser.wait(ec.visibilityOf(custSubscriptionComponentsPage.title), 5000);
    expect(await custSubscriptionComponentsPage.getTitle()).to.eq('crmwebApp.custSubscription.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(custSubscriptionComponentsPage.entities), ec.visibilityOf(custSubscriptionComponentsPage.noResult)),
      1000
    );
  });

  it('should load create CustSubscription page', async () => {
    await custSubscriptionComponentsPage.clickOnCreateButton();
    custSubscriptionUpdatePage = new CustSubscriptionUpdatePage();
    expect(await custSubscriptionUpdatePage.getPageTitle()).to.eq('crmwebApp.custSubscription.home.createOrEditLabel');
    await custSubscriptionUpdatePage.cancel();
  });

  it('should create and save CustSubscriptions', async () => {
    const nbButtonsBeforeCreate = await custSubscriptionComponentsPage.countDeleteButtons();

    await custSubscriptionComponentsPage.clickOnCreateButton();

    await promise.all([
      custSubscriptionUpdatePage.setSubscriptionIdInput('subscriptionId'),
      custSubscriptionUpdatePage.setActivationDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      custSubscriptionUpdatePage.setSubsEndDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      custSubscriptionUpdatePage.setSubsPurchaseDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      custSubscriptionUpdatePage.setOrigServiceStartDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      custSubscriptionUpdatePage.statusSelectLastOption(),
      custSubscriptionUpdatePage.setSubsLastStatusCodeInput('subsLastStatusCode'),
      custSubscriptionUpdatePage.setLastStatusUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      custSubscriptionUpdatePage.setCustAcctIdInput('custAcctId'),
      custSubscriptionUpdatePage.setBillingAcctIdInput('billingAcctId'),
      custSubscriptionUpdatePage.setBillCycleIdInput('5'),
      custSubscriptionUpdatePage.setOrderIdInput('orderId'),
      custSubscriptionUpdatePage.setMatrixxObjectIdInput('matrixxObjectId'),
      custSubscriptionUpdatePage.setSubscriberNameInput('subscriberName'),
      custSubscriptionUpdatePage.setSubsDeptNameInput('subsDeptName'),
      custSubscriptionUpdatePage.setSelfCarePasswordInput('selfCarePassword'),
      custSubscriptionUpdatePage.setSubsCategoryInput('subsCategory'),
      custSubscriptionUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      custSubscriptionUpdatePage.setCreatedByInput('createdBy'),
      custSubscriptionUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      custSubscriptionUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      custSubscriptionUpdatePage.setTenantIdInput('tenantId'),
      custSubscriptionUpdatePage.customerSelectLastOption()
    ]);

    expect(await custSubscriptionUpdatePage.getSubscriptionIdInput()).to.eq(
      'subscriptionId',
      'Expected SubscriptionId value to be equals to subscriptionId'
    );
    expect(await custSubscriptionUpdatePage.getActivationDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected activationDate value to be equals to 2000-12-31'
    );
    expect(await custSubscriptionUpdatePage.getSubsEndDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected subsEndDate value to be equals to 2000-12-31'
    );
    expect(await custSubscriptionUpdatePage.getSubsPurchaseDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected subsPurchaseDate value to be equals to 2000-12-31'
    );
    expect(await custSubscriptionUpdatePage.getOrigServiceStartDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected origServiceStartDate value to be equals to 2000-12-31'
    );
    const selectedPrimarySubsInd = custSubscriptionUpdatePage.getPrimarySubsIndInput();
    if (await selectedPrimarySubsInd.isSelected()) {
      await custSubscriptionUpdatePage.getPrimarySubsIndInput().click();
      expect(await custSubscriptionUpdatePage.getPrimarySubsIndInput().isSelected(), 'Expected primarySubsInd not to be selected').to.be
        .false;
    } else {
      await custSubscriptionUpdatePage.getPrimarySubsIndInput().click();
      expect(await custSubscriptionUpdatePage.getPrimarySubsIndInput().isSelected(), 'Expected primarySubsInd to be selected').to.be.true;
    }
    expect(await custSubscriptionUpdatePage.getSubsLastStatusCodeInput()).to.eq(
      'subsLastStatusCode',
      'Expected SubsLastStatusCode value to be equals to subsLastStatusCode'
    );
    expect(await custSubscriptionUpdatePage.getLastStatusUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastStatusUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await custSubscriptionUpdatePage.getCustAcctIdInput()).to.eq(
      'custAcctId',
      'Expected CustAcctId value to be equals to custAcctId'
    );
    expect(await custSubscriptionUpdatePage.getBillingAcctIdInput()).to.eq(
      'billingAcctId',
      'Expected BillingAcctId value to be equals to billingAcctId'
    );
    expect(await custSubscriptionUpdatePage.getBillCycleIdInput()).to.eq('5', 'Expected billCycleId value to be equals to 5');
    expect(await custSubscriptionUpdatePage.getOrderIdInput()).to.eq('orderId', 'Expected OrderId value to be equals to orderId');
    expect(await custSubscriptionUpdatePage.getMatrixxObjectIdInput()).to.eq(
      'matrixxObjectId',
      'Expected MatrixxObjectId value to be equals to matrixxObjectId'
    );
    expect(await custSubscriptionUpdatePage.getSubscriberNameInput()).to.eq(
      'subscriberName',
      'Expected SubscriberName value to be equals to subscriberName'
    );
    expect(await custSubscriptionUpdatePage.getSubsDeptNameInput()).to.eq(
      'subsDeptName',
      'Expected SubsDeptName value to be equals to subsDeptName'
    );
    expect(await custSubscriptionUpdatePage.getSelfCarePasswordInput()).to.eq(
      'selfCarePassword',
      'Expected SelfCarePassword value to be equals to selfCarePassword'
    );
    expect(await custSubscriptionUpdatePage.getSubsCategoryInput()).to.eq(
      'subsCategory',
      'Expected SubsCategory value to be equals to subsCategory'
    );
    expect(await custSubscriptionUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await custSubscriptionUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await custSubscriptionUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await custSubscriptionUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await custSubscriptionUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await custSubscriptionUpdatePage.save();
    expect(await custSubscriptionUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await custSubscriptionComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last CustSubscription', async () => {
    const nbButtonsBeforeDelete = await custSubscriptionComponentsPage.countDeleteButtons();
    await custSubscriptionComponentsPage.clickOnLastDeleteButton();

    custSubscriptionDeleteDialog = new CustSubscriptionDeleteDialog();
    expect(await custSubscriptionDeleteDialog.getDialogTitle()).to.eq('crmwebApp.custSubscription.delete.question');
    await custSubscriptionDeleteDialog.clickOnConfirmButton();

    expect(await custSubscriptionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
