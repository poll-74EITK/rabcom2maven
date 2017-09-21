/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javafx.scene.shape.Rectangle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 *
 * @author Pavel Papsh
 */
public class FillProgressIndicatorSkin implements Skin<FillProgressIndicator>{
        private final FillProgressIndicator indicator;
        private final StackPane container = new StackPane();
        private final Label percentLabel = new Label();
        private final Rectangle cover = new Rectangle();
        private final Circle borderCircle = new Circle();
        private final Circle fillerCircle = new Circle();

    private final Timeline transition = new Timeline();

    public FillProgressIndicatorSkin(final FillProgressIndicator indicator) {
        this.indicator = indicator;
        initContainer(indicator);
        updateRadii();
        initStyles();
        AnchorPane coverPane = new AnchorPane();
        cover.getStyleClass().add("fillindicator-filler-cover");
        cover.widthProperty().bind(coverPane.widthProperty());
        cover.setManaged(false);
        AnchorPane.setTopAnchor(cover, 0.0);
        AnchorPane.setLeftAnchor(cover, 0.0);
        AnchorPane.setRightAnchor(cover, 0.0);
        coverPane.getChildren().addAll(cover);

        this.indicator.indeterminateProperty().addListener((o, oldVal, newVal) -> {
            initIndeterminate(newVal);
        });
        this.indicator.progressProperty().addListener((o, oldVal, newVal) -> {
            setProgressLabel(newVal.intValue());
            this.cover.setHeight(coverPane.getHeight() * ((100 - newVal.intValue()) / 100d));
        });

        this.indicator.innerCircleRadiusProperty().addListener((e) -> {
            updateRadii();
        });
        coverPane.heightProperty().addListener((o, oldVal, newVal) -> {
        	 this.cover.setHeight(newVal.intValue() * ((100 - indicator.getProgress()) / 100d));
        });
        initLabel(indicator.getProgress());
        indicator.visibleProperty().addListener((o, oldVal, newVal) -> {
            if (newVal && this.indicator.isIndeterminate()) {
                transition.play();
            } else {
                transition.pause();
            }
        });

        this.container.getChildren().addAll(fillerCircle, coverPane, borderCircle, percentLabel);
        initTransition();
        initIndeterminate(indicator.isIndeterminate());
    }

    private void initContainer(final FillProgressIndicator indicator) {
        container.getStylesheets().addAll(indicator.getStylesheets());
        container.getStyleClass().addAll("circleindicator-container");
        container.setMaxHeight(Region.USE_PREF_SIZE);
        container.setMaxWidth(Region.USE_PREF_SIZE);
    }

    private void initTransition() {
        transition.setCycleCount(Timeline.INDEFINITE);
        transition.setAutoReverse(true);
        final KeyValue kv = new KeyValue(this.cover.heightProperty(), 0);
        transition.getKeyFrames().addAll(new KeyFrame(Duration.millis(1500), kv));

    }

    private void initStyles() {
        fillerCircle.getStyleClass().add("fillindicator-filler-circle");
        borderCircle.getStyleClass().add("fillindicator-border-circle");
    }

    private void updateRadii() {
        fillerCircle.setRadius(this.indicator.getInnerCircleRadius() + 5);
        borderCircle.setRadius(this.indicator.getInnerCircleRadius());
    }

    private void initLabel(int value) {
        setProgressLabel(value);
        percentLabel.getStyleClass().add("circleindicator-label");
    }

    private void setProgressLabel(int value) {
        if (value >= 0) {
            percentLabel.setText(String.format("%d%%", value));
        }
    }

    private void initIndeterminate(boolean newVal) {
        percentLabel.setVisible(!newVal);
        if (newVal && indicator.isVisible()) {
            transition.play();
        } else {
            transition.stop();
        }
    }

    @Override
    public FillProgressIndicator getSkinnable() {
        return indicator;
    }

    @Override
    public Node getNode() {
        return this.container;
    }

    @Override
    public void dispose() {
        transition.stop();
    }
}
