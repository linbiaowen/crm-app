import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  OfferAdvancePaymentComponentsPage,
  OfferAdvancePaymentDeleteDialog,
  OfferAdvancePaymentUpdatePage
} from './offer-advance-payment.page-object';

const expect = chai.expect;

describe('OfferAdvancePayment e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let offerAdvancePaymentComponentsPage: OfferAdvancePaymentComponentsPage;
  let offerAdvancePaymentUpdatePage: OfferAdvancePaymentUpdatePage;
  let offerAdvancePaymentDeleteDialog: OfferAdvancePaymentDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load OfferAdvancePayments', async () => {
    await navBarPage.goToEntity('offer-advance-payment');
    offerAdvancePaymentComponentsPage = new OfferAdvancePaymentComponentsPage();
    await browser.wait(ec.visibilityOf(offerAdvancePaymentComponentsPage.title), 5000);
    expect(await offerAdvancePaymentComponentsPage.getTitle()).to.eq('crmwebApp.offerAdvancePayment.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(offerAdvancePaymentComponentsPage.entities), ec.visibilityOf(offerAdvancePaymentComponentsPage.noResult)),
      1000
    );
  });

  it('should load create OfferAdvancePayment page', async () => {
    await offerAdvancePaymentComponentsPage.clickOnCreateButton();
    offerAdvancePaymentUpdatePage = new OfferAdvancePaymentUpdatePage();
    expect(await offerAdvancePaymentUpdatePage.getPageTitle()).to.eq('crmwebApp.offerAdvancePayment.home.createOrEditLabel');
    await offerAdvancePaymentUpdatePage.cancel();
  });

  it('should create and save OfferAdvancePayments', async () => {
    const nbButtonsBeforeCreate = await offerAdvancePaymentComponentsPage.countDeleteButtons();

    await offerAdvancePaymentComponentsPage.clickOnCreateButton();

    await promise.all([
      offerAdvancePaymentUpdatePage.setAdvancePaymentIdInput('5'),
      offerAdvancePaymentUpdatePage.setOfferIdInput('offerId'),
      offerAdvancePaymentUpdatePage.setAdvancePaymentMonthsInput('5'),
      offerAdvancePaymentUpdatePage.setOfferPriceInput('5'),
      offerAdvancePaymentUpdatePage.setOfferDiscountInput('5'),
      offerAdvancePaymentUpdatePage.statusSelectLastOption(),
      offerAdvancePaymentUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      offerAdvancePaymentUpdatePage.setCreatedByInput('createdBy'),
      offerAdvancePaymentUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      offerAdvancePaymentUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      offerAdvancePaymentUpdatePage.setTenantIdInput('tenantId'),
      offerAdvancePaymentUpdatePage.offerSelectLastOption()
    ]);

    expect(await offerAdvancePaymentUpdatePage.getAdvancePaymentIdInput()).to.eq('5', 'Expected advancePaymentId value to be equals to 5');
    expect(await offerAdvancePaymentUpdatePage.getOfferIdInput()).to.eq('offerId', 'Expected OfferId value to be equals to offerId');
    expect(await offerAdvancePaymentUpdatePage.getAdvancePaymentMonthsInput()).to.eq(
      '5',
      'Expected advancePaymentMonths value to be equals to 5'
    );
    expect(await offerAdvancePaymentUpdatePage.getOfferPriceInput()).to.eq('5', 'Expected offerPrice value to be equals to 5');
    expect(await offerAdvancePaymentUpdatePage.getOfferDiscountInput()).to.eq('5', 'Expected offerDiscount value to be equals to 5');
    expect(await offerAdvancePaymentUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await offerAdvancePaymentUpdatePage.getCreatedByInput()).to.eq(
      'createdBy',
      'Expected CreatedBy value to be equals to createdBy'
    );
    expect(await offerAdvancePaymentUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await offerAdvancePaymentUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await offerAdvancePaymentUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await offerAdvancePaymentUpdatePage.save();
    expect(await offerAdvancePaymentUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await offerAdvancePaymentComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last OfferAdvancePayment', async () => {
    const nbButtonsBeforeDelete = await offerAdvancePaymentComponentsPage.countDeleteButtons();
    await offerAdvancePaymentComponentsPage.clickOnLastDeleteButton();

    offerAdvancePaymentDeleteDialog = new OfferAdvancePaymentDeleteDialog();
    expect(await offerAdvancePaymentDeleteDialog.getDialogTitle()).to.eq('crmwebApp.offerAdvancePayment.delete.question');
    await offerAdvancePaymentDeleteDialog.clickOnConfirmButton();

    expect(await offerAdvancePaymentComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
