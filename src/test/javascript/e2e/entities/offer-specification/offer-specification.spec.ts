import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  OfferSpecificationComponentsPage,
  OfferSpecificationDeleteDialog,
  OfferSpecificationUpdatePage
} from './offer-specification.page-object';

const expect = chai.expect;

describe('OfferSpecification e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let offerSpecificationComponentsPage: OfferSpecificationComponentsPage;
  let offerSpecificationUpdatePage: OfferSpecificationUpdatePage;
  let offerSpecificationDeleteDialog: OfferSpecificationDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load OfferSpecifications', async () => {
    await navBarPage.goToEntity('offer-specification');
    offerSpecificationComponentsPage = new OfferSpecificationComponentsPage();
    await browser.wait(ec.visibilityOf(offerSpecificationComponentsPage.title), 5000);
    expect(await offerSpecificationComponentsPage.getTitle()).to.eq('crmwebApp.offerSpecification.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(offerSpecificationComponentsPage.entities), ec.visibilityOf(offerSpecificationComponentsPage.noResult)),
      1000
    );
  });

  it('should load create OfferSpecification page', async () => {
    await offerSpecificationComponentsPage.clickOnCreateButton();
    offerSpecificationUpdatePage = new OfferSpecificationUpdatePage();
    expect(await offerSpecificationUpdatePage.getPageTitle()).to.eq('crmwebApp.offerSpecification.home.createOrEditLabel');
    await offerSpecificationUpdatePage.cancel();
  });

  it('should create and save OfferSpecifications', async () => {
    const nbButtonsBeforeCreate = await offerSpecificationComponentsPage.countDeleteButtons();

    await offerSpecificationComponentsPage.clickOnCreateButton();

    await promise.all([
      offerSpecificationUpdatePage.setOfferSpecIdInput('5'),
      offerSpecificationUpdatePage.setOfferIdInput('offerId'),
      offerSpecificationUpdatePage.setStartDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      offerSpecificationUpdatePage.setEndDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      offerSpecificationUpdatePage.setAllowedActivationStartDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      offerSpecificationUpdatePage.setAllowedActivationEndDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      offerSpecificationUpdatePage.setInfoSharingOptionsInput('infoSharingOptions'),
      offerSpecificationUpdatePage.setOfferPeriodInput('5'),
      offerSpecificationUpdatePage.setOfferPeriodTermInput('offerPeriodTerm'),
      offerSpecificationUpdatePage.setPaymentTypeInput('paymentType'),
      offerSpecificationUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      offerSpecificationUpdatePage.setCreatedByInput('createdBy'),
      offerSpecificationUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      offerSpecificationUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      offerSpecificationUpdatePage.setTenantIdInput('tenantId')
    ]);

    expect(await offerSpecificationUpdatePage.getOfferSpecIdInput()).to.eq('5', 'Expected offerSpecId value to be equals to 5');
    expect(await offerSpecificationUpdatePage.getOfferIdInput()).to.eq('offerId', 'Expected OfferId value to be equals to offerId');
    expect(await offerSpecificationUpdatePage.getStartDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected startDate value to be equals to 2000-12-31'
    );
    expect(await offerSpecificationUpdatePage.getEndDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected endDate value to be equals to 2000-12-31'
    );
    const selectedLimitedActivationPeriod = offerSpecificationUpdatePage.getLimitedActivationPeriodInput();
    if (await selectedLimitedActivationPeriod.isSelected()) {
      await offerSpecificationUpdatePage.getLimitedActivationPeriodInput().click();
      expect(
        await offerSpecificationUpdatePage.getLimitedActivationPeriodInput().isSelected(),
        'Expected limitedActivationPeriod not to be selected'
      ).to.be.false;
    } else {
      await offerSpecificationUpdatePage.getLimitedActivationPeriodInput().click();
      expect(
        await offerSpecificationUpdatePage.getLimitedActivationPeriodInput().isSelected(),
        'Expected limitedActivationPeriod to be selected'
      ).to.be.true;
    }
    expect(await offerSpecificationUpdatePage.getAllowedActivationStartDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected allowedActivationStartDate value to be equals to 2000-12-31'
    );
    expect(await offerSpecificationUpdatePage.getAllowedActivationEndDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected allowedActivationEndDate value to be equals to 2000-12-31'
    );
    const selectedIsGroupSharingOffer = offerSpecificationUpdatePage.getIsGroupSharingOfferInput();
    if (await selectedIsGroupSharingOffer.isSelected()) {
      await offerSpecificationUpdatePage.getIsGroupSharingOfferInput().click();
      expect(
        await offerSpecificationUpdatePage.getIsGroupSharingOfferInput().isSelected(),
        'Expected isGroupSharingOffer not to be selected'
      ).to.be.false;
    } else {
      await offerSpecificationUpdatePage.getIsGroupSharingOfferInput().click();
      expect(await offerSpecificationUpdatePage.getIsGroupSharingOfferInput().isSelected(), 'Expected isGroupSharingOffer to be selected')
        .to.be.true;
    }
    const selectedIsMnpOffer = offerSpecificationUpdatePage.getIsMnpOfferInput();
    if (await selectedIsMnpOffer.isSelected()) {
      await offerSpecificationUpdatePage.getIsMnpOfferInput().click();
      expect(await offerSpecificationUpdatePage.getIsMnpOfferInput().isSelected(), 'Expected isMnpOffer not to be selected').to.be.false;
    } else {
      await offerSpecificationUpdatePage.getIsMnpOfferInput().click();
      expect(await offerSpecificationUpdatePage.getIsMnpOfferInput().isSelected(), 'Expected isMnpOffer to be selected').to.be.true;
    }
    const selectedAutoRenewal = offerSpecificationUpdatePage.getAutoRenewalInput();
    if (await selectedAutoRenewal.isSelected()) {
      await offerSpecificationUpdatePage.getAutoRenewalInput().click();
      expect(await offerSpecificationUpdatePage.getAutoRenewalInput().isSelected(), 'Expected autoRenewal not to be selected').to.be.false;
    } else {
      await offerSpecificationUpdatePage.getAutoRenewalInput().click();
      expect(await offerSpecificationUpdatePage.getAutoRenewalInput().isSelected(), 'Expected autoRenewal to be selected').to.be.true;
    }
    const selectedTransferAllowed = offerSpecificationUpdatePage.getTransferAllowedInput();
    if (await selectedTransferAllowed.isSelected()) {
      await offerSpecificationUpdatePage.getTransferAllowedInput().click();
      expect(await offerSpecificationUpdatePage.getTransferAllowedInput().isSelected(), 'Expected transferAllowed not to be selected').to.be
        .false;
    } else {
      await offerSpecificationUpdatePage.getTransferAllowedInput().click();
      expect(await offerSpecificationUpdatePage.getTransferAllowedInput().isSelected(), 'Expected transferAllowed to be selected').to.be
        .true;
    }
    const selectedInfoSharingAllowed = offerSpecificationUpdatePage.getInfoSharingAllowedInput();
    if (await selectedInfoSharingAllowed.isSelected()) {
      await offerSpecificationUpdatePage.getInfoSharingAllowedInput().click();
      expect(await offerSpecificationUpdatePage.getInfoSharingAllowedInput().isSelected(), 'Expected infoSharingAllowed not to be selected')
        .to.be.false;
    } else {
      await offerSpecificationUpdatePage.getInfoSharingAllowedInput().click();
      expect(await offerSpecificationUpdatePage.getInfoSharingAllowedInput().isSelected(), 'Expected infoSharingAllowed to be selected').to
        .be.true;
    }
    expect(await offerSpecificationUpdatePage.getInfoSharingOptionsInput()).to.eq(
      'infoSharingOptions',
      'Expected InfoSharingOptions value to be equals to infoSharingOptions'
    );
    expect(await offerSpecificationUpdatePage.getOfferPeriodInput()).to.eq('5', 'Expected offerPeriod value to be equals to 5');
    expect(await offerSpecificationUpdatePage.getOfferPeriodTermInput()).to.eq(
      'offerPeriodTerm',
      'Expected OfferPeriodTerm value to be equals to offerPeriodTerm'
    );
    expect(await offerSpecificationUpdatePage.getPaymentTypeInput()).to.eq(
      'paymentType',
      'Expected PaymentType value to be equals to paymentType'
    );
    expect(await offerSpecificationUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await offerSpecificationUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await offerSpecificationUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await offerSpecificationUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await offerSpecificationUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await offerSpecificationUpdatePage.save();
    expect(await offerSpecificationUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await offerSpecificationComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last OfferSpecification', async () => {
    const nbButtonsBeforeDelete = await offerSpecificationComponentsPage.countDeleteButtons();
    await offerSpecificationComponentsPage.clickOnLastDeleteButton();

    offerSpecificationDeleteDialog = new OfferSpecificationDeleteDialog();
    expect(await offerSpecificationDeleteDialog.getDialogTitle()).to.eq('crmwebApp.offerSpecification.delete.question');
    await offerSpecificationDeleteDialog.clickOnConfirmButton();

    expect(await offerSpecificationComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
