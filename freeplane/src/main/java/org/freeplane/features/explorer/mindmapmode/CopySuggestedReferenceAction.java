package org.freeplane.features.explorer.mindmapmode;

import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;

import org.freeplane.core.ui.AFreeplaneAction;
import org.freeplane.features.clipboard.ClipboardController;
import org.freeplane.features.map.NodeModel;
import org.freeplane.features.mode.Controller;

public class CopySuggestedReferenceAction extends AFreeplaneAction {
	private MapExplorerController explorer;

	public CopySuggestedReferenceAction(final MapExplorerController explorer) {
		super("CopySuggestedReferenceAction");
		this.explorer = explorer;
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void actionPerformed(final ActionEvent e) {
		final Controller controller = Controller.getCurrentController();
		final NodeModel selected = controller.getSelection().getSelected();
		final ClipboardController clipboardController = Controller.getCurrentModeController().getExtension(
		    ClipboardController.class);
		String suggestedString = explorer.getNodeReferenceSuggestion(selected);
		clipboardController.setClipboardContents(new StringSelection(suggestedString));
		controller.getViewController().out(suggestedString);
	}
}
