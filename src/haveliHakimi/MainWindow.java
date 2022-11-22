package haveliHakimi;

import java.util.ArrayList;
import java.util.Collections;

// import edu.macalester.graphics.Rectangle;

public class MainWindow {
    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 600;

    public MainWindow(){
    }
    
    // private void drawFrame(CanvasWindow canvas){
    //     Rectangle frame = new Rectangle(CANVAS_WIDTH/4, CANVAS_HEIGHT/4, CANVAS_WIDTH/2, CANVAS_HEIGHT/2);
    //     frame.setStrokeWidth(20);
    //     canvas.add(frame);
    // }
    
    private boolean isGraphical(ArrayList<Integer> degreeSequence) {
        if(check1(degreeSequence) == true && check2(degreeSequence) == true && haveliHakimi(degreeSequence) == true) {
            return true;
        }
        System.out.println("Degree sequence is not graphical");
        return false;
    }

    private boolean haveliHakimi(ArrayList<Integer> degreeSequence) {   
        System.out.println(degreeSequence);
            for(int i = 0; i < degreeSequence.size(); i++){
                Collections.sort(degreeSequence, Collections.reverseOrder());
                for(int j = 1; j <= degreeSequence.get(0); j++) {
                    degreeSequence.set(j, degreeSequence.get(j) - 1); 
                }
                degreeSequence.remove(0);
                Collections.sort(degreeSequence, Collections.reverseOrder());
                System.out.println(degreeSequence);
        }
        return true;
    }

    private boolean check1(ArrayList<Integer> degreeSequence) {
        int total = 0;
        for(int num: degreeSequence){
            total += num;
        }
        if(degreeSequence.size() % 2 != 0 && total % 2 != 0){
            return false;
        }
        return true;
    }

    private boolean check2(ArrayList<Integer> degreeSequence) {
        Collections.sort(degreeSequence);
        if(degreeSequence.get(0) > degreeSequence.size()) {
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
        
    }
}
