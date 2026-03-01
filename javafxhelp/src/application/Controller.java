package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.TextFlow;
import java.lang.Math;
import java.lang.*;

import application.study.Graph;

public class Controller {

	@FXML

	public Pane drawingPane;
	public Line newLine;
	public Slider newSlider;

	public double scaleRate = 1;
	public double detail = 1;
	public double scale = 20;

	public void plot() {

		// works by calculating the first and second step and their coordinates
		// and then connecting the coordinates with a Line object

		// "resolution" can be increased with "detail"
		// "detail" is the distance between each step
		// increasing "detail" eats a lot of ram though, do not get it less than 1

		// "scale" is like zooming in, however, weird stuff happens with at points at
		// high rate of change (try x cubed and zoom in)

		for (double i = -384; i <= 384; i = i + detail) {

			// "i" is the each step, starts at -1*resolution/2 and ends at resolution/2.
			// This is to make it so center of the screen is the (0,0) in the coordinate
			// system

			double y, nexty; // "nexty" is to calculate the line's ending y coord.
			y = -1 * scale * MathFunction(i / scale);
			nexty = -1 * scale * MathFunction((i + detail) / scale);

			Line newLine = new Line((i) + 384, y + 384, (i + detail) + 384, nexty + 384);

			newLine.setStroke(Color.WHITE);
			newLine.setOpacity(0.5);
			newLine.setScaleX(scale / 10);
			newLine.setScaleY(scale / 10);
			newLine.setId("sigmaLine");

			drawingPane.getChildren().add(newLine);
		}
	}

	public double MathFunction(double x) { // the mathematical function to plot and
		double y;

		y = 10 * Math.sin(0.1 * x * Math.PI);

		return y;
	}

	public void changeScaleRate() {
		scaleRate = newSlider.getValue();
		System.out.println("slider" + scaleRate);
	}

	public void increaseScale() {
		scale = scale + scaleRate;
		drawingPane.getChildren().removeIf(node -> "sigmaLine".equals(node.getId()));
		plot();
	}

	public void decreaseScale() {
		scale = scale - scaleRate;	
		drawingPane.getChildren().removeIf(node -> "sigmaLine".equals(node.getId()));
		plot();
	}

	public void increaseDetail() {
		detail = detail - 1;
		drawingPane.getChildren().removeIf(node -> "sigmaLine".equals(node.getId()));
		plot();
	}

	public void decreaseDetail() {
		detail = detail + 1;
		drawingPane.getChildren().removeIf(node -> "sigmaLine".equals(node.getId()));
		plot();
	}

	public void moveUp() { // WIP - to move up the graph
		double i = newLine.getTranslateX();
		System.out.println(i);
	}

}
