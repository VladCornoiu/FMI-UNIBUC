/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proiectgeometrie;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ProiectGeometrie extends Application {
    private ArrayList<Point> points;
    private Point point;
    private GraphicsContext gc;
    
    private void Initiate() {
        points = new ArrayList<>();
        point = new Point(-300, -300);
    }
    
    @Override
    public void start(Stage primaryStage) {
        Initiate();
        
        primaryStage.setTitle("Geometry Project - Convex Hull Extended");
        
        // GRID //
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        // CANVAS //
        Canvas canvas = new Canvas(800, 600);
        gc = canvas.getGraphicsContext2D();
        canvas.setStyle("-fx-border-color: black;");
        setGraphicsContextStyle(gc);
        grid.add(canvas, 0, 0, 1, 5);
        
        // FIELDS //
        TextField xPolygonTextField = new TextField();
        grid.add(xPolygonTextField, 1, 0);
        TextField yPolygonTextField = new TextField();
        grid.add(yPolygonTextField, 2, 0);
        
        TextField xPointTextField = new TextField();
        grid.add(xPointTextField, 1, 2);
        TextField yPointTextField = new TextField();
        grid.add(yPointTextField, 2, 2);
        
        // BUTTONS //
        Button addPolygonPointButton = new Button("Add Point");
        addPolygonPointButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                addPolygonPoint(
                    xPolygonTextField.getText(),
                    yPolygonTextField.getText()
                );
            }
        });
        grid.add(addPolygonPointButton, 1, 1, 2, 1);
        
        Button setExteriorPointButton = new Button("Set Point");
        setExteriorPointButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                setExteriorPoint(
                    xPointTextField.getText(),
                    yPointTextField.getText()
                );
            }
        });
        grid.add(setExteriorPointButton, 1, 3, 2, 1);
        
        Button resetPolygonButton = new Button("Reset");
        resetPolygonButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                reset();
            }
        });
        grid.add(resetPolygonButton, 1, 4, 2, 1);
        
        // SCENE //
        Scene scene = new Scene(grid, 1024, 768);
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }
    
    private void setGraphicsContextStyle(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(4);
    }
    
    private void addPolygonPoint(String xString, String yString) {
        double x = 0;
        double y = 0;
        
        try {
            x = Double.parseDouble(xString);
            y = Double.parseDouble(yString);
        }
        catch(NumberFormatException ex) {
            x = 0;
            y = 0;
        }
        
        points.add(new Point(x, y));
        updatePolygon(x, y);
    }
    
    private void setExteriorPoint(String xString, String yString) {
        double x = 0;
        double y = 0;
        
        try {
            x = Double.parseDouble(xString);
            y = Double.parseDouble(yString);
        }
        catch(NumberFormatException ex) {
            x = 0;
            y = 0;
        }
        
        point = new Point(x, y);
        updatePolygon();
    }
    
    private void updatePolygon(double x, double y) {
        drawRect(x, y);
    }
    
    private void updatePolygon() {
        int numberOfPoints = points.size();
        for (int i = 0;i < numberOfPoints;i++) {
            drawRect(points.get(i).x, points.get(i).y);
        }
        
        Point[] pts = new Point[points.size() + 1];
        for (int i = 0; i < points.size(); ++i)
            pts[i + 1] = points.get(i);
        pts[0] = new Point(-1000,-1000);
        points = Algorithm.ConvexHull(pts);
        
        numberOfPoints = points.size();
        double[] x = new double[numberOfPoints];
        double[] y = new double[numberOfPoints];
        
        for (int i = 0;i < numberOfPoints;i++) {
            x[i] = points.get(i).x;
            y[i] = points.get(i).y;
        }
        
        gc.strokePolygon(x, y, numberOfPoints);
        
        drawRect(point.x, point.y);
        points.add(point);
        
        Point[] updatedPts = new Point[points.size() + 1];
        for (int i = 0; i < points.size(); ++i)
            updatedPts[i + 1] = points.get(i);
        updatedPts[0] = new Point(-1000,-1000);
        points = Algorithm.ConvexHull(updatedPts);
        numberOfPoints = points.size();
        x = new double[numberOfPoints];
        y = new double[numberOfPoints];
       
        gc.setLineWidth(2);
        gc.setStroke(Color.BLACK);
        gc.setFont(Font.font("Verdana", 20));
                
        
        for (Integer i = 0; i < numberOfPoints;i++) {
            x[i] = points.get(i).x;
            y[i] = points.get(i).y;
            gc.strokeText(i.toString(), x[i] + 4,  y[i] + 4, 20);
        }        
        gc.setStroke(Color.YELLOW);
        gc.strokePolygon(x, y, numberOfPoints);
        
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(4);
    }
    
    private void reset() {
        points.clear();
        gc.clearRect(0, 0, 800, 600);
    }

    private void drawRect(double x, double y) {
        int size = 8;
        gc.fillRect(x - size / 2, y - size / 2, size, size);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}