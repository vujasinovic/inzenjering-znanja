package com.optogo.view.dialog;

import com.optogo.controller.PatientController;
import com.optogo.model.Patient;
import com.optogo.view.scene.PatientEditorScene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PatientEditorDialog extends Stage {
    private PatientEditorScene scene;
    private static final String TITLE = "Edit Patient";

    public PatientEditorDialog(Patient patient, Stage parent) {
        scene = PatientEditorScene.create(patient);

        setTitle(TITLE);
        setScene(scene);
        initOwner(parent);
        initModality(Modality.APPLICATION_MODAL);
    }

    public boolean isCanceled() {
        return getController().isCanceled();
    }

    public PatientController getController() {
        return scene.getController();
    }

    public static PatientEditorDialog edit(Patient patient, Stage parent) {
        PatientEditorDialog dialog = new PatientEditorDialog(patient, parent);
        dialog.showAndWait();
        return dialog;
    }

    public static PatientEditorDialog create(Stage parent) {
        PatientEditorDialog dialog = new PatientEditorDialog(null, parent);
        dialog.showAndWait();
        return dialog;
    }

}
