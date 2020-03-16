import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { VoiceServiceSpecComponentsPage, VoiceServiceSpecDeleteDialog, VoiceServiceSpecUpdatePage } from './voice-service-spec.page-object';

const expect = chai.expect;

describe('VoiceServiceSpec e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let voiceServiceSpecComponentsPage: VoiceServiceSpecComponentsPage;
  let voiceServiceSpecUpdatePage: VoiceServiceSpecUpdatePage;
  let voiceServiceSpecDeleteDialog: VoiceServiceSpecDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load VoiceServiceSpecs', async () => {
    await navBarPage.goToEntity('voice-service-spec');
    voiceServiceSpecComponentsPage = new VoiceServiceSpecComponentsPage();
    await browser.wait(ec.visibilityOf(voiceServiceSpecComponentsPage.title), 5000);
    expect(await voiceServiceSpecComponentsPage.getTitle()).to.eq('crmwebApp.voiceServiceSpec.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(voiceServiceSpecComponentsPage.entities), ec.visibilityOf(voiceServiceSpecComponentsPage.noResult)),
      1000
    );
  });

  it('should load create VoiceServiceSpec page', async () => {
    await voiceServiceSpecComponentsPage.clickOnCreateButton();
    voiceServiceSpecUpdatePage = new VoiceServiceSpecUpdatePage();
    expect(await voiceServiceSpecUpdatePage.getPageTitle()).to.eq('crmwebApp.voiceServiceSpec.home.createOrEditLabel');
    await voiceServiceSpecUpdatePage.cancel();
  });

  it('should create and save VoiceServiceSpecs', async () => {
    const nbButtonsBeforeCreate = await voiceServiceSpecComponentsPage.countDeleteButtons();

    await voiceServiceSpecComponentsPage.clickOnCreateButton();

    await promise.all([
      voiceServiceSpecUpdatePage.setVoiceSpecIdInput('5'),
      voiceServiceSpecUpdatePage.setServiceSpecIdInput('serviceSpecId'),
      voiceServiceSpecUpdatePage.setIddOptionsInput('iddOptions'),
      voiceServiceSpecUpdatePage.setSpecialCallServicesInput('specialCallServices'),
      voiceServiceSpecUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      voiceServiceSpecUpdatePage.setCreatedByInput('createdBy'),
      voiceServiceSpecUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      voiceServiceSpecUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      voiceServiceSpecUpdatePage.setTenantIdInput('tenantId')
    ]);

    expect(await voiceServiceSpecUpdatePage.getVoiceSpecIdInput()).to.eq('5', 'Expected voiceSpecId value to be equals to 5');
    expect(await voiceServiceSpecUpdatePage.getServiceSpecIdInput()).to.eq(
      'serviceSpecId',
      'Expected ServiceSpecId value to be equals to serviceSpecId'
    );
    const selectedRoamingIncomingEnabled = voiceServiceSpecUpdatePage.getRoamingIncomingEnabledInput();
    if (await selectedRoamingIncomingEnabled.isSelected()) {
      await voiceServiceSpecUpdatePage.getRoamingIncomingEnabledInput().click();
      expect(
        await voiceServiceSpecUpdatePage.getRoamingIncomingEnabledInput().isSelected(),
        'Expected roamingIncomingEnabled not to be selected'
      ).to.be.false;
    } else {
      await voiceServiceSpecUpdatePage.getRoamingIncomingEnabledInput().click();
      expect(
        await voiceServiceSpecUpdatePage.getRoamingIncomingEnabledInput().isSelected(),
        'Expected roamingIncomingEnabled to be selected'
      ).to.be.true;
    }
    const selectedRoamingOutgoingEnabled = voiceServiceSpecUpdatePage.getRoamingOutgoingEnabledInput();
    if (await selectedRoamingOutgoingEnabled.isSelected()) {
      await voiceServiceSpecUpdatePage.getRoamingOutgoingEnabledInput().click();
      expect(
        await voiceServiceSpecUpdatePage.getRoamingOutgoingEnabledInput().isSelected(),
        'Expected roamingOutgoingEnabled not to be selected'
      ).to.be.false;
    } else {
      await voiceServiceSpecUpdatePage.getRoamingOutgoingEnabledInput().click();
      expect(
        await voiceServiceSpecUpdatePage.getRoamingOutgoingEnabledInput().isSelected(),
        'Expected roamingOutgoingEnabled to be selected'
      ).to.be.true;
    }
    const selectedIddEnabled = voiceServiceSpecUpdatePage.getIddEnabledInput();
    if (await selectedIddEnabled.isSelected()) {
      await voiceServiceSpecUpdatePage.getIddEnabledInput().click();
      expect(await voiceServiceSpecUpdatePage.getIddEnabledInput().isSelected(), 'Expected iddEnabled not to be selected').to.be.false;
    } else {
      await voiceServiceSpecUpdatePage.getIddEnabledInput().click();
      expect(await voiceServiceSpecUpdatePage.getIddEnabledInput().isSelected(), 'Expected iddEnabled to be selected').to.be.true;
    }
    expect(await voiceServiceSpecUpdatePage.getIddOptionsInput()).to.eq(
      'iddOptions',
      'Expected IddOptions value to be equals to iddOptions'
    );
    const selectedCallForwardEnabled = voiceServiceSpecUpdatePage.getCallForwardEnabledInput();
    if (await selectedCallForwardEnabled.isSelected()) {
      await voiceServiceSpecUpdatePage.getCallForwardEnabledInput().click();
      expect(await voiceServiceSpecUpdatePage.getCallForwardEnabledInput().isSelected(), 'Expected callForwardEnabled not to be selected')
        .to.be.false;
    } else {
      await voiceServiceSpecUpdatePage.getCallForwardEnabledInput().click();
      expect(await voiceServiceSpecUpdatePage.getCallForwardEnabledInput().isSelected(), 'Expected callForwardEnabled to be selected').to.be
        .true;
    }
    const selectedCallWaitingEnabled = voiceServiceSpecUpdatePage.getCallWaitingEnabledInput();
    if (await selectedCallWaitingEnabled.isSelected()) {
      await voiceServiceSpecUpdatePage.getCallWaitingEnabledInput().click();
      expect(await voiceServiceSpecUpdatePage.getCallWaitingEnabledInput().isSelected(), 'Expected callWaitingEnabled not to be selected')
        .to.be.false;
    } else {
      await voiceServiceSpecUpdatePage.getCallWaitingEnabledInput().click();
      expect(await voiceServiceSpecUpdatePage.getCallWaitingEnabledInput().isSelected(), 'Expected callWaitingEnabled to be selected').to.be
        .true;
    }
    const selectedClipEnabled = voiceServiceSpecUpdatePage.getClipEnabledInput();
    if (await selectedClipEnabled.isSelected()) {
      await voiceServiceSpecUpdatePage.getClipEnabledInput().click();
      expect(await voiceServiceSpecUpdatePage.getClipEnabledInput().isSelected(), 'Expected clipEnabled not to be selected').to.be.false;
    } else {
      await voiceServiceSpecUpdatePage.getClipEnabledInput().click();
      expect(await voiceServiceSpecUpdatePage.getClipEnabledInput().isSelected(), 'Expected clipEnabled to be selected').to.be.true;
    }
    const selectedCallBarringEnabled = voiceServiceSpecUpdatePage.getCallBarringEnabledInput();
    if (await selectedCallBarringEnabled.isSelected()) {
      await voiceServiceSpecUpdatePage.getCallBarringEnabledInput().click();
      expect(await voiceServiceSpecUpdatePage.getCallBarringEnabledInput().isSelected(), 'Expected callBarringEnabled not to be selected')
        .to.be.false;
    } else {
      await voiceServiceSpecUpdatePage.getCallBarringEnabledInput().click();
      expect(await voiceServiceSpecUpdatePage.getCallBarringEnabledInput().isSelected(), 'Expected callBarringEnabled to be selected').to.be
        .true;
    }
    const selectedMvrsEnabled = voiceServiceSpecUpdatePage.getMvrsEnabledInput();
    if (await selectedMvrsEnabled.isSelected()) {
      await voiceServiceSpecUpdatePage.getMvrsEnabledInput().click();
      expect(await voiceServiceSpecUpdatePage.getMvrsEnabledInput().isSelected(), 'Expected mvrsEnabled not to be selected').to.be.false;
    } else {
      await voiceServiceSpecUpdatePage.getMvrsEnabledInput().click();
      expect(await voiceServiceSpecUpdatePage.getMvrsEnabledInput().isSelected(), 'Expected mvrsEnabled to be selected').to.be.true;
    }
    expect(await voiceServiceSpecUpdatePage.getSpecialCallServicesInput()).to.eq(
      'specialCallServices',
      'Expected SpecialCallServices value to be equals to specialCallServices'
    );
    const selectedCallRoutingSupported = voiceServiceSpecUpdatePage.getCallRoutingSupportedInput();
    if (await selectedCallRoutingSupported.isSelected()) {
      await voiceServiceSpecUpdatePage.getCallRoutingSupportedInput().click();
      expect(
        await voiceServiceSpecUpdatePage.getCallRoutingSupportedInput().isSelected(),
        'Expected callRoutingSupported not to be selected'
      ).to.be.false;
    } else {
      await voiceServiceSpecUpdatePage.getCallRoutingSupportedInput().click();
      expect(await voiceServiceSpecUpdatePage.getCallRoutingSupportedInput().isSelected(), 'Expected callRoutingSupported to be selected')
        .to.be.true;
    }
    const selectedPrbtSupported = voiceServiceSpecUpdatePage.getPrbtSupportedInput();
    if (await selectedPrbtSupported.isSelected()) {
      await voiceServiceSpecUpdatePage.getPrbtSupportedInput().click();
      expect(await voiceServiceSpecUpdatePage.getPrbtSupportedInput().isSelected(), 'Expected prbtSupported not to be selected').to.be
        .false;
    } else {
      await voiceServiceSpecUpdatePage.getPrbtSupportedInput().click();
      expect(await voiceServiceSpecUpdatePage.getPrbtSupportedInput().isSelected(), 'Expected prbtSupported to be selected').to.be.true;
    }
    const selectedHrbtSupported = voiceServiceSpecUpdatePage.getHrbtSupportedInput();
    if (await selectedHrbtSupported.isSelected()) {
      await voiceServiceSpecUpdatePage.getHrbtSupportedInput().click();
      expect(await voiceServiceSpecUpdatePage.getHrbtSupportedInput().isSelected(), 'Expected hrbtSupported not to be selected').to.be
        .false;
    } else {
      await voiceServiceSpecUpdatePage.getHrbtSupportedInput().click();
      expect(await voiceServiceSpecUpdatePage.getHrbtSupportedInput().isSelected(), 'Expected hrbtSupported to be selected').to.be.true;
    }
    expect(await voiceServiceSpecUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await voiceServiceSpecUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await voiceServiceSpecUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await voiceServiceSpecUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await voiceServiceSpecUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await voiceServiceSpecUpdatePage.save();
    expect(await voiceServiceSpecUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await voiceServiceSpecComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last VoiceServiceSpec', async () => {
    const nbButtonsBeforeDelete = await voiceServiceSpecComponentsPage.countDeleteButtons();
    await voiceServiceSpecComponentsPage.clickOnLastDeleteButton();

    voiceServiceSpecDeleteDialog = new VoiceServiceSpecDeleteDialog();
    expect(await voiceServiceSpecDeleteDialog.getDialogTitle()).to.eq('crmwebApp.voiceServiceSpec.delete.question');
    await voiceServiceSpecDeleteDialog.clickOnConfirmButton();

    expect(await voiceServiceSpecComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
