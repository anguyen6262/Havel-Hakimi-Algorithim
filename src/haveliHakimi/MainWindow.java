package haveliHakimi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import stackImplementation.ArrayStack;

public class MainWindow {
    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 600;
    private ArrayStack<State> stateStack;
    
    public MainWindow(){
        stateStack = new ArrayStack<>();
    }

    private boolean haveliHakimi(ArrayList<Integer> degreeSequence) {   
        if(firstTheorem(degreeSequence) == true && firstDegree(degreeSequence) == true){
            Collections.sort(degreeSequence, Collections.reverseOrder());
            stateStack.push(new State(degreeSequence));
            System.out.println(degreeSequence);
                for(int i = 0; i < degreeSequence.size(); i++){
                    Collections.sort(degreeSequence, Collections.reverseOrder());
                    for(int j = 1; j <= degreeSequence.get(0); j++) {
                        degreeSequence.set(j, degreeSequence.get(j) - 1); 
                    }
                    degreeSequence.remove(0);
                    Collections.sort(degreeSequence, Collections.reverseOrder());
                    System.out.println(degreeSequence);
                    stateStack.push(new State(degreeSequence));
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

    public static void main(String[] args) {
        MainWindow window = new MainWindow();
        System.out.println(window.haveliHakimi(new ArrayList<>(Arrays.asList(4,5,4,3,3,3,4))));
    }
}
