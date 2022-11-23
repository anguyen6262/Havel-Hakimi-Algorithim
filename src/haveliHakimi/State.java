package haveliHakimi;

import java.awt.Color;
import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Point;
import stackImplementation.ArrayStack;
import edu.macalester.graphics.Line;
import edu.macalester.graphics.GraphicsText;

public class State {
    List<Color> colors= new ArrayList<>(Arrays.asList(Color.BLUE, Color.RED, Color.GREEN, Color.ORANGE, Color.PINK, Color.GRAY, Color.CYAN));
    private MainWindow window = new MainWindow();
    private int canvasHeight = window.getCanvasHeight();
    private int canvasWidth = window.getCanvasWidth();
    Map<Integer, Ellipse> verticesMap;
    Map<Integer, Line> edgesMap;

    public State(){
        verticesMap = new HashMap<>();
        edgesMap = new HashMap<>();
    }

    private void drawVertices(CanvasWindow canvas, int numVertices) {
        int xPos = canvasWidth/4;
        double yPos = canvasHeight/2;
        for(Integer i = 0; i < numVertices; i++){
            createVertex(canvas, i, xPos, yPos);
            createText(canvas, i.toString(), xPos+11, yPos+20,colors.get(i));
            xPos+=70;
            if(i % 2 ==1) {
                yPos+=Math.ceil(Math.random()*200);
            } else {
                yPos-=Math.ceil(Math.random()*200);
            }
        }   
    }

    private void drawEdges(CanvasWindow canvas, Graph graph, ArrayList<Integer> degreeSequence) {
        Collections.sort(degreeSequence, Collections.reverseOrder());
        for(int i = 0; i < degreeSequence.size() - 1; i++){
            if(degreeSequence.get(i) > graph.degree(i)){
                for(int j = 1; j < degreeSequence.size(); j++){
                    if(degreeSequence.get(j) > graph.degree(j)) {
                        createEdge(canvas, graph, i, j);
                    }
                }
            }
        }
    }

    private void createVertex(CanvasWindow canvas, int num, int xPos, double yPos) {
        Ellipse vertex = new Ellipse(xPos, yPos, 30, 30);
        vertex.setFillColor(Color.black);
        canvas.add(vertex);
        verticesMap.put(num, vertex);
    }

    private void createEdge(CanvasWindow canvas, Graph graph, int source, int target) {
        graph.addEdge(source, target);
        Line edge = new Line(verticesMap.get(source).getCenter(), verticesMap.get(target).getCenter());
        canvas.add(edge);
    }
    
    private void createText(CanvasWindow canvas, String num, int xPos, double yPos, Color color) {
        GraphicsText text = new GraphicsText(num, xPos, yPos);
        text.setFillColor(color);
        canvas.add(text);
    }

    public static void main(String[] args) {
        CanvasWindow canvas = new CanvasWindow("State", 800, 600);
        State state = new State();
        Graph graph = new Graph(7);
        // graph.addEdge(1, 4);
        // graph.addEdge(2, 1);
        // graph.addEdge(3, 4);
        // graph.addEdge(3, 2);
        state.drawVertices(canvas,graph.V());
        state.drawEdges(canvas,graph, new ArrayList<>(Arrays.asList(4,5,4,3,3,3,4)));
        System.out.println(graph.toString());            
    }   
}

