package havelHakimi;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Line;
import edu.macalester.graphics.GraphicsText;

public class State {
    List<Color> colors= new ArrayList<>(Arrays.asList(Color.BLUE, Color.RED, Color.GREEN, Color.ORANGE, Color.PINK, Color.GRAY, Color.CYAN));
    Map<Integer, Ellipse> verticesMap;
    ArrayList<Integer> degreeSequence;
    
    public State(ArrayList<Integer> degreeSequence){
        verticesMap = new HashMap<>();
        this.degreeSequence = degreeSequence;
    }

    private void drawVertices(CanvasWindow canvas, int intNumVertices) {
        double radius = 100;
        double numVertices = intNumVertices;
        for (int i = 0; i < numVertices; i++) {
            double theta = i / numVertices * 2 * Math.PI;
            Ellipse vertex = new Ellipse(radius * Math.cos(theta) + 500, radius * Math.sin(theta) + 285, 30, 30);
            vertex.setFillColor(Color.black);
            canvas.add(vertex);
            verticesMap.put(i, vertex);
        }
    }

    private void drawEdges(CanvasWindow canvas, Graph graph, ArrayList<Integer> degreeSequence) {
        List<Integer> degreeSequenceCopy = new ArrayList<Integer>();
        for (Integer x : degreeSequence) {
            degreeSequenceCopy.add(x);
        }
        Map<Integer, Integer> savingDegrees = new HashMap<Integer, Integer>();
        List<Integer> markedVertices = new ArrayList<>();
        while (Collections.max(degreeSequenceCopy) > 0) {
            int maximum = Collections.max(degreeSequenceCopy);
            int maxLocation = degreeSequenceCopy.indexOf(maximum);
            degreeSequenceCopy.set(maxLocation, 0);
            for (int i = 0; i < maximum; i++) {
                int nextMax = Collections.max(degreeSequenceCopy);
                int nextMaxLocation = degreeSequenceCopy.indexOf(nextMax);
                markedVertices.add(nextMaxLocation);
                savingDegrees.put(nextMaxLocation, nextMax - 1);
                degreeSequenceCopy.set(nextMaxLocation, 0);
                graph.addEdge(maxLocation, nextMaxLocation);
                Line edge = new Line(verticesMap.get(maxLocation).getCenter(), verticesMap.get(nextMaxLocation).getCenter());
                canvas.add(edge);
            }
            for (int location : markedVertices) {
                degreeSequenceCopy.set(location, savingDegrees.get(location));
            }
            markedVertices.clear();
        }
    }
 
    private void drawText(CanvasWindow canvas) {
        for(int i = 0; i < verticesMap.size(); i++){
            verticesMap.get(i).getCenter().getX();
            Integer num = i;
            GraphicsText text = new GraphicsText(num.toString(), verticesMap.get(i).getCenter().getX()-4, verticesMap.get(i).getCenter().getY()+4);
            text.setFillColor(colors.get(i));
            canvas.add(text);
        }
    }

    public void run(CanvasWindow canvas){
        Graph graph = new Graph(this.degreeSequence.size());
        drawVertices(canvas, this.degreeSequence.size());
        drawEdges(canvas, graph, this.degreeSequence);
        drawText(canvas);
    }    
    
    public String toString(){
        return degreeSequence.toString();
    }
}

