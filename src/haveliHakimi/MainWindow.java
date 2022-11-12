package haveliHakimi;

import edu.macalester.graphics.Rectangle;

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

    public int getCanvasWidth() {
        return CANVAS_WIDTH;
    }

    public int getCanvasHeight(){
        return CANVAS_HEIGHT;
    }

    public static void main(String[] args) {
        
    }
}
