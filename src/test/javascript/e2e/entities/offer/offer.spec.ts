import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { OfferComponentsPage, OfferDeleteDialog, OfferUpdatePage } from './offer.page-object';

const expect = chai.expect;

describe('Offer e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let offerComponentsPage: OfferComponentsPage;
  let offerUpdatePage: OfferUpdatePage;
  let offerDeleteDialog: OfferDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Offers', async () => {
    await navBarPage.goToEntity('offer');
    offerComponentsPage = new OfferComponentsPage();
    await browser.wait(ec.visibilityOf(offerComponentsPage.title), 5000);
    expect(await offerComponentsPage.getTitle()).to.eq('crmwebApp.offer.home.title');
    await browser.wait(ec.or(ec.visibilityOf(offerComponentsPage.entities), ec.visibilityOf(offerComponentsPage.noResult)), 1000);
  });

  it('should load create Offer page', async () => {
    await offerComponentsPage.clickOnCreateButton();
    offerUpdatePage = new OfferUpdatePage();
    expect(await offerUpdatePage.getPageTitle()).to.eq('crmwebApp.offer.home.createOrEditLabel');
    await offerUpdatePage.cancel();
  });

  it('should create and save Offers', async () => {
    const nbButtonsBeforeCreate = await offerComponentsPage.countDeleteButtons();

    await offerComponentsPage.clickOnCreateButton();

    await promise.all([
      offerUpdatePage.setOfferIdInput('offerId'),
      offerUpdatePage.setOfferExternalIdInput('offerExternalId'),
      offerUpdatePage.setOfferNameInput('offerName'),
      offerUpdatePage.setOfferNameChiInput('offerNameChi'),
      offerUpdatePage.offerTypeSelectLastOption(),
      offerUpdatePage.setOfferPriceInput('5'),
      offerUpdatePage.setCustomerSegmentsInput('customerSegments'),
      offerUpdatePage.setCustomerClassesInput('customerClasses'),
      offerUpdatePage.setSalesChannelsInput('salesChannels'),
      offerUpdatePage.setStartDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      offerUpdatePage.setEndDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      offerUpdatePage.setChildOfferIdsInput('childOfferIds'),
      offerUpdatePage.setProductSpecIdsInput('productSpecIds'),
      offerUpdatePage.setAdvancePaymentIdsInput('advancePaymentIds'),
      offerUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      offerUpdatePage.setCreatedByInput('createdBy'),
      offerUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      offerUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      offerUpdatePage.setTenantIdInput('tenantId'),
      offerUpdatePage.offerSpecSelectLastOption()
      // offerUpdatePage.parentOfferSelectLastOption(),
    ]);

    expect(await offerUpdatePage.getOfferIdInput()).to.eq('offerId', 'Expected OfferId value to be equals to offerId');
    expect(await offerUpdatePage.getOfferExternalIdInput()).to.eq(
      'offerExternalId',
      'Expected OfferExternalId value to be equals to offerExternalId'
    );
    expect(await offerUpdatePage.getOfferNameInput()).to.eq('offerName', 'Expected OfferName value to be equals to offerName');
    expect(await offerUpdatePage.getOfferNameChiInput()).to.eq('offerNameChi', 'Expected OfferNameChi value to be equals to offerNameChi');
    expect(await offerUpdatePage.getOfferPriceInput()).to.eq('5', 'Expected offerPrice value to be equals to 5');
    expect(await offerUpdatePage.getCustomerSegmentsInput()).to.eq(
      'customerSegments',
      'Expected CustomerSegments value to be equals to customerSegments'
    );
    expect(await offerUpdatePage.getCustomerClassesInput()).to.eq(
      'customerClasses',
      'Expected CustomerClasses value to be equals to customerClasses'
    );
    expect(await offerUpdatePage.getSalesChannelsInput()).to.eq(
      'salesChannels',
      'Expected SalesChannels value to be equals to salesChannels'
    );
    expect(await offerUpdatePage.getStartDateInput()).to.contain('2001-01-01T02:30', 'Expected startDate value to be equals to 2000-12-31');
    expect(await offerUpdatePage.getEndDateInput()).to.contain('2001-01-01T02:30', 'Expected endDate value to be equals to 2000-12-31');
    expect(await offerUpdatePage.getChildOfferIdsInput()).to.eq(
      'childOfferIds',
      'Expected ChildOfferIds value to be equals to childOfferIds'
    );
    expect(await offerUpdatePage.getProductSpecIdsInput()).to.eq(
      'productSpecIds',
      'Expected ProductSpecIds value to be equals to productSpecIds'
    );
    expect(await offerUpdatePage.getAdvancePaymentIdsInput()).to.eq(
      'advancePaymentIds',
      'Expected AdvancePaymentIds value to be equals to advancePaymentIds'
    );
    expect(await offerUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await offerUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await offerUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await offerUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await offerUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await offerUpdatePage.save();
    expect(await offerUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await offerComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Offer', async () => {
    const nbButtonsBeforeDelete = await offerComponentsPage.countDeleteButtons();
    await offerComponentsPage.clickOnLastDeleteButton();

    offerDeleteDialog = new OfferDeleteDialog();
    expect(await offerDeleteDialog.getDialogTitle()).to.eq('crmwebApp.offer.delete.question');
    await offerDeleteDialog.clickOnConfirmButton();

    expect(await offerComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
