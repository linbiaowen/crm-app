import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { OfferProductComponentsPage, OfferProductDeleteDialog, OfferProductUpdatePage } from './offer-product.page-object';

const expect = chai.expect;

describe('OfferProduct e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let offerProductComponentsPage: OfferProductComponentsPage;
  let offerProductUpdatePage: OfferProductUpdatePage;
  let offerProductDeleteDialog: OfferProductDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load OfferProducts', async () => {
    await navBarPage.goToEntity('offer-product');
    offerProductComponentsPage = new OfferProductComponentsPage();
    await browser.wait(ec.visibilityOf(offerProductComponentsPage.title), 5000);
    expect(await offerProductComponentsPage.getTitle()).to.eq('crmwebApp.offerProduct.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(offerProductComponentsPage.entities), ec.visibilityOf(offerProductComponentsPage.noResult)),
      1000
    );
  });

  it('should load create OfferProduct page', async () => {
    await offerProductComponentsPage.clickOnCreateButton();
    offerProductUpdatePage = new OfferProductUpdatePage();
    expect(await offerProductUpdatePage.getPageTitle()).to.eq('crmwebApp.offerProduct.home.createOrEditLabel');
    await offerProductUpdatePage.cancel();
  });

  it('should create and save OfferProducts', async () => {
    const nbButtonsBeforeCreate = await offerProductComponentsPage.countDeleteButtons();

    await offerProductComponentsPage.clickOnCreateButton();

    await promise.all([
      offerProductUpdatePage.setRecSeqIdInput('5'),
      offerProductUpdatePage.setProductIdInput('productId'),
      offerProductUpdatePage.setOfferIdInput('offerId'),
      offerProductUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      offerProductUpdatePage.setCreatedByInput('createdBy'),
      offerProductUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      offerProductUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      offerProductUpdatePage.setTenantIdInput('tenantId'),
      offerProductUpdatePage.offerSelectLastOption()
    ]);

    expect(await offerProductUpdatePage.getRecSeqIdInput()).to.eq('5', 'Expected recSeqId value to be equals to 5');
    expect(await offerProductUpdatePage.getProductIdInput()).to.eq('productId', 'Expected ProductId value to be equals to productId');
    expect(await offerProductUpdatePage.getOfferIdInput()).to.eq('offerId', 'Expected OfferId value to be equals to offerId');
    expect(await offerProductUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await offerProductUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await offerProductUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await offerProductUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await offerProductUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await offerProductUpdatePage.save();
    expect(await offerProductUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await offerProductComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last OfferProduct', async () => {
    const nbButtonsBeforeDelete = await offerProductComponentsPage.countDeleteButtons();
    await offerProductComponentsPage.clickOnLastDeleteButton();

    offerProductDeleteDialog = new OfferProductDeleteDialog();
    expect(await offerProductDeleteDialog.getDialogTitle()).to.eq('crmwebApp.offerProduct.delete.question');
    await offerProductDeleteDialog.clickOnConfirmButton();

    expect(await offerProductComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
