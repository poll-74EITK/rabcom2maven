/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javafx.scene.control.Skin;

/**
 *
 * @author Pavel Papsh
 */
public class FillProgressIndicator extends ProgressCircleIndicator{
    public FillProgressIndicator() {
        this.getStylesheets().add(RingProgressIndicator.class.getResource("../resources/styles/fillprogress.css").toExternalForm());
        this.getStyleClass().add("fillindicator");
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new FillProgressIndicatorSkin(this);
    }
}
