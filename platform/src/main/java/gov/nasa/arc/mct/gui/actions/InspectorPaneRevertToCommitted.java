/*******************************************************************************
 * Mission Control Technologies, Copyright (c) 2009-2012, United States Government
 * as represented by the Administrator of the National Aeronautics and Space 
 * Administration. All rights reserved.
 *
 * The MCT platform is licensed under the Apache License, Version 2.0 (the 
 * "License"); you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT 
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the 
 * License for the specific language governing permissions and limitations under 
 * the License.
 *
 * MCT includes source code licensed under additional open source licenses. See 
 * the MCT Open Source Licenses file included with this distribution or the About 
 * MCT Licenses dialog available at runtime from the MCT Help menu for additional 
 * information. 
 *******************************************************************************/
package gov.nasa.arc.mct.gui.actions;

import gov.nasa.arc.mct.gui.ActionContext;
import gov.nasa.arc.mct.gui.ContextAwareAction;
import gov.nasa.arc.mct.gui.View;
import gov.nasa.arc.mct.gui.housing.Inspector;

import java.awt.event.ActionEvent;
import java.util.Collection;

import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class InspectorPaneRevertToCommitted extends ContextAwareAction {
    private static final String TEXT = "Revert to Saved";
    
    public InspectorPaneRevertToCommitted() {
        super(TEXT);
    }

    private View view;
    private Inspector inspectionArea;
    @Override
    public boolean canHandle(ActionContext context) {
        Collection<View> selectedManifestations = context.getSelectedManifestations();
        if (selectedManifestations.isEmpty())
            return false;
        
        view = selectedManifestations.iterator().next();
        inspectionArea = (Inspector) SwingUtilities.getAncestorOfClass(Inspector.class, view);
        return inspectionArea != null;
    }

    @Override
    public boolean isEnabled() {
        return view.getManifestedComponent().isStale();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        inspectionArea.refreshCurrentlyShowingView();
    }

}
