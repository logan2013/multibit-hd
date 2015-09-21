package org.multibit.hd.ui.views.wizards.change_pin;

import com.google.common.base.Optional;
import net.miginfocom.swing.MigLayout;
import org.multibit.hd.ui.events.view.ViewEvents;
import org.multibit.hd.ui.languages.MessageKey;
import org.multibit.hd.ui.views.components.*;
import org.multibit.hd.ui.views.components.panels.PanelDecorator;
import org.multibit.hd.ui.views.fonts.AwesomeIcon;
import org.multibit.hd.ui.views.wizards.AbstractHardwareWalletWizard;
import org.multibit.hd.ui.views.wizards.AbstractHardwareWalletWizardPanelView;
import org.multibit.hd.ui.views.wizards.AbstractWizard;
import org.multibit.hd.ui.views.wizards.WizardButton;

import javax.swing.*;

/**
 * <p>View to provide the following to UI:</p>
 * <ul>
 * <li>Change PIN: Confirm add PIN</li>
 * </ul>
 *
 * @since 0.0.5
 *  
 *
 *
 */
public class ChangePinConfirmAddPinPanelView extends AbstractHardwareWalletWizardPanelView<ChangePinWizardModel, ChangePinEnterPinPanelModel> {

  /**
   * @param wizard The wizard managing the states
   */
  public ChangePinConfirmAddPinPanelView(AbstractHardwareWalletWizard<ChangePinWizardModel> wizard, String panelName) {

    // Need to use the LOCK icon here because TH is visually confusing
    super(wizard, panelName, AwesomeIcon.LOCK, MessageKey.HARDWARE_CONFIRM_ADD_PIN_TITLE);

  }

  @Override
  public void newPanelModel() {

    // Nothing to bind

  }

  @Override
  public void initialiseContent(JPanel contentPanel) {

    contentPanel.setLayout(
      new MigLayout(
        Panels.migXYLayout(),
        "[]", // Column constraints
        "[]10[]" // Row constraints
      ));

    // Configure the hardware display
    addCurrentHardwareDisplay(contentPanel);

  }

  @Override
  protected void initialiseButtons(AbstractWizard<ChangePinWizardModel> wizard) {

    PanelDecorator.addExitCancelNext(this, wizard);

  }

  @Override
  public void fireInitialStateViewEvents() {

    // Initialise with "Unlock" disabled to force users to enter credentials
    ViewEvents.fireWizardButtonEnabledEvent(
      getPanelName(),
      WizardButton.NEXT,
      false
    );

  }

  @Override
  public void afterShow() {

    // Show the current Trezor display
    hardwareDisplayMaV.getView().setOperationText(MessageKey.HARDWARE_PRESS_CONFIRM_OPERATION, getWizardModel().getWalletMode().brand());
    hardwareDisplayMaV.getView().setDisplayText(MessageKey.TREZOR_ADD_PIN_DISPLAY);

  }

  @Override
  public void updateFromComponentModels(Optional componentModel) {

    // Do nothing we are a transitional view

  }

}