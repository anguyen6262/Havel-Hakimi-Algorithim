package haveliHakimi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.ui.Button;
import stackImplementation.ArrayStack;

public class MainWindow {
    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 600;
    private ArrayStack<State> stateStack;
    // private Button nextButton;
    // private Button previousButton;
    private CanvasWindow canvas;
    
    public MainWindow(){
        canvas = new CanvasWindow("Haveli Hakimi", CANVAS_WIDTH, CANVAS_HEIGHT);
        stateStack = new ArrayStack<>();
        haveliHakimi(canvas,new ArrayList<>(Arrays.asList(4,5,4,3,3,3,4)));
        // setupUI();
    }

    private boolean haveliHakimi(CanvasWindow canvas, ArrayList<Integer> degreeSequence) {   
        if(firstTheorem(degreeSequence) == true && firstDegree(degreeSequence) == true){
            Collections.sort(degreeSequence, Collections.reverseOrder());
            stateStack.push(new State(canvas, degreeSequence));
            System.out.println(degreeSequence);
                for(int i = 0; i < degreeSequence.size(); i++){
                    Collections.sort(degreeSequence, Collections.reverseOrder());
                    for(int j = 1; j <= degreeSequence.get(0); j++) {
                        degreeSequence.set(j, degreeSequence.get(j) - 1); 
                    }
                    degreeSequence.remove(0);
                    Collections.sort(degreeSequence, Collections.reverseOrder());
                    System.out.println(degreeSequence);
                    stateStack.push(new State(canvas,degreeSequence));
            }
            return true;
        }
        System.out.println("Degree sequence is not graphical"); 
        return false;
    }

    private boolean firstTheorem(ArrayList<Integer> degreeSequence) {
        int total = 0;
        for(int num: degreeSequence){
            total += num;
        }
        if(degreeSequence.size() % 2 != 0 && total % 2 != 0){
            System.out.println("Failed First Theorem of Graph Theorem");
            return false;
        }
        return true;
    }

    private boolean firstDegree(ArrayList<Integer> degreeSequence) {
        Collections.sort(degreeSequence);
        if(degreeSequence.get(0) > degreeSequence.size()) {
            System.out.println("First vertex has a higher degree than the order of the graph");
            return false;
        }
        return true;
    }
    
    public int getCanvasWidth() {
        return CANVAS_WIDTH;
    }

    public int getCanvasHeight(){
        return CANVAS_HEIGHT;
    }

    // private void setupUI() {
    //     nextButton = new Button("Next");
    //     nextButton.onClick(() -> nextState());
    //     previousButton = new Button("Previous");
    //     previousButton.onClick(()->previousState());
    // }

    // private void nextState(CanvasWindow canvas){
    //     canvas.remove();
    //     canvas.add(stateStack.pop());
        
    // } 
    
    // private void previousState(){

    // }
    public static void main(String[] args) {
        MainWindow window = new MainWindow();
        System.out.println(window.stateStack); 
        // canvas.add(stateStack.poll());
        // System.out.println(window.haveliHakimi(new ArrayList<>(Arrays.asList(4,5,4,3,3,3,4))));
    }
}
