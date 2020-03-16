import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { OfferPromotionComponentsPage, OfferPromotionDeleteDialog, OfferPromotionUpdatePage } from './offer-promotion.page-object';

const expect = chai.expect;

describe('OfferPromotion e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let offerPromotionComponentsPage: OfferPromotionComponentsPage;
  let offerPromotionUpdatePage: OfferPromotionUpdatePage;
  let offerPromotionDeleteDialog: OfferPromotionDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load OfferPromotions', async () => {
    await navBarPage.goToEntity('offer-promotion');
    offerPromotionComponentsPage = new OfferPromotionComponentsPage();
    await browser.wait(ec.visibilityOf(offerPromotionComponentsPage.title), 5000);
    expect(await offerPromotionComponentsPage.getTitle()).to.eq('crmwebApp.offerPromotion.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(offerPromotionComponentsPage.entities), ec.visibilityOf(offerPromotionComponentsPage.noResult)),
      1000
    );
  });

  it('should load create OfferPromotion page', async () => {
    await offerPromotionComponentsPage.clickOnCreateButton();
    offerPromotionUpdatePage = new OfferPromotionUpdatePage();
    expect(await offerPromotionUpdatePage.getPageTitle()).to.eq('crmwebApp.offerPromotion.home.createOrEditLabel');
    await offerPromotionUpdatePage.cancel();
  });

  it('should create and save OfferPromotions', async () => {
    const nbButtonsBeforeCreate = await offerPromotionComponentsPage.countDeleteButtons();

    await offerPromotionComponentsPage.clickOnCreateButton();

    await promise.all([
      offerPromotionUpdatePage.setPromoCodeInput('promoCode'),
      offerPromotionUpdatePage.setOfferIdInput('offerId'),
      offerPromotionUpdatePage.setStartDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      offerPromotionUpdatePage.setEndDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      offerPromotionUpdatePage.setPromoTypeInput('promoType'),
      offerPromotionUpdatePage.setOfferPriceInput('5'),
      offerPromotionUpdatePage.setOfferDiscountInput('5'),
      offerPromotionUpdatePage.setFreeDataOfferIdInput('freeDataOfferId'),
      offerPromotionUpdatePage.statusSelectLastOption(),
      offerPromotionUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      offerPromotionUpdatePage.setCreatedByInput('createdBy'),
      offerPromotionUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      offerPromotionUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      offerPromotionUpdatePage.setTenantIdInput('tenantId'),
      offerPromotionUpdatePage.offerSelectLastOption()
    ]);

    expect(await offerPromotionUpdatePage.getPromoCodeInput()).to.eq('promoCode', 'Expected PromoCode value to be equals to promoCode');
    expect(await offerPromotionUpdatePage.getOfferIdInput()).to.eq('offerId', 'Expected OfferId value to be equals to offerId');
    expect(await offerPromotionUpdatePage.getStartDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected startDate value to be equals to 2000-12-31'
    );
    expect(await offerPromotionUpdatePage.getEndDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected endDate value to be equals to 2000-12-31'
    );
    expect(await offerPromotionUpdatePage.getPromoTypeInput()).to.eq('promoType', 'Expected PromoType value to be equals to promoType');
    expect(await offerPromotionUpdatePage.getOfferPriceInput()).to.eq('5', 'Expected offerPrice value to be equals to 5');
    expect(await offerPromotionUpdatePage.getOfferDiscountInput()).to.eq('5', 'Expected offerDiscount value to be equals to 5');
    expect(await offerPromotionUpdatePage.getFreeDataOfferIdInput()).to.eq(
      'freeDataOfferId',
      'Expected FreeDataOfferId value to be equals to freeDataOfferId'
    );
    expect(await offerPromotionUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await offerPromotionUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await offerPromotionUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await offerPromotionUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await offerPromotionUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await offerPromotionUpdatePage.save();
    expect(await offerPromotionUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await offerPromotionComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last OfferPromotion', async () => {
    const nbButtonsBeforeDelete = await offerPromotionComponentsPage.countDeleteButtons();
    await offerPromotionComponentsPage.clickOnLastDeleteButton();

    offerPromotionDeleteDialog = new OfferPromotionDeleteDialog();
    expect(await offerPromotionDeleteDialog.getDialogTitle()).to.eq('crmwebApp.offerPromotion.delete.question');
    await offerPromotionDeleteDialog.clickOnConfirmButton();

    expect(await offerPromotionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
