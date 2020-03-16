import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { OfferDiscountComponentsPage, OfferDiscountDeleteDialog, OfferDiscountUpdatePage } from './offer-discount.page-object';

const expect = chai.expect;

describe('OfferDiscount e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let offerDiscountComponentsPage: OfferDiscountComponentsPage;
  let offerDiscountUpdatePage: OfferDiscountUpdatePage;
  let offerDiscountDeleteDialog: OfferDiscountDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load OfferDiscounts', async () => {
    await navBarPage.goToEntity('offer-discount');
    offerDiscountComponentsPage = new OfferDiscountComponentsPage();
    await browser.wait(ec.visibilityOf(offerDiscountComponentsPage.title), 5000);
    expect(await offerDiscountComponentsPage.getTitle()).to.eq('crmwebApp.offerDiscount.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(offerDiscountComponentsPage.entities), ec.visibilityOf(offerDiscountComponentsPage.noResult)),
      1000
    );
  });

  it('should load create OfferDiscount page', async () => {
    await offerDiscountComponentsPage.clickOnCreateButton();
    offerDiscountUpdatePage = new OfferDiscountUpdatePage();
    expect(await offerDiscountUpdatePage.getPageTitle()).to.eq('crmwebApp.offerDiscount.home.createOrEditLabel');
    await offerDiscountUpdatePage.cancel();
  });

  it('should create and save OfferDiscounts', async () => {
    const nbButtonsBeforeCreate = await offerDiscountComponentsPage.countDeleteButtons();

    await offerDiscountComponentsPage.clickOnCreateButton();

    await promise.all([
      offerDiscountUpdatePage.setDiscountCodeInput('discountCode'),
      offerDiscountUpdatePage.setOfferIdInput('offerId'),
      offerDiscountUpdatePage.setStartDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      offerDiscountUpdatePage.setEndDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      offerDiscountUpdatePage.setDiscountTypeInput('discountType'),
      offerDiscountUpdatePage.setOfferDiscountInput('5'),
      offerDiscountUpdatePage.statusSelectLastOption(),
      offerDiscountUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      offerDiscountUpdatePage.setCreatedByInput('createdBy'),
      offerDiscountUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      offerDiscountUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      offerDiscountUpdatePage.setTenantIdInput('tenantId'),
      offerDiscountUpdatePage.offerSelectLastOption()
    ]);

    expect(await offerDiscountUpdatePage.getDiscountCodeInput()).to.eq(
      'discountCode',
      'Expected DiscountCode value to be equals to discountCode'
    );
    expect(await offerDiscountUpdatePage.getOfferIdInput()).to.eq('offerId', 'Expected OfferId value to be equals to offerId');
    expect(await offerDiscountUpdatePage.getStartDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected startDate value to be equals to 2000-12-31'
    );
    expect(await offerDiscountUpdatePage.getEndDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected endDate value to be equals to 2000-12-31'
    );
    expect(await offerDiscountUpdatePage.getDiscountTypeInput()).to.eq(
      'discountType',
      'Expected DiscountType value to be equals to discountType'
    );
    expect(await offerDiscountUpdatePage.getOfferDiscountInput()).to.eq('5', 'Expected offerDiscount value to be equals to 5');
    expect(await offerDiscountUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await offerDiscountUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await offerDiscountUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await offerDiscountUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await offerDiscountUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await offerDiscountUpdatePage.save();
    expect(await offerDiscountUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await offerDiscountComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last OfferDiscount', async () => {
    const nbButtonsBeforeDelete = await offerDiscountComponentsPage.countDeleteButtons();
    await offerDiscountComponentsPage.clickOnLastDeleteButton();

    offerDiscountDeleteDialog = new OfferDiscountDeleteDialog();
    expect(await offerDiscountDeleteDialog.getDialogTitle()).to.eq('crmwebApp.offerDiscount.delete.question');
    await offerDiscountDeleteDialog.clickOnConfirmButton();

    expect(await offerDiscountComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
