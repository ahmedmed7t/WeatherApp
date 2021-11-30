package com.medhat.weatherapp.cameraScreen;

public class CardDimentions {
    int heightFrame;
    int topFrame;
    int leftFrame;
    int widthFrame;
    int mainHeight;
    int mainWidth;

    public CardDimentions() {
    }

    public CardDimentions(int heightFrame, int topFrame, int leftFrame, int widthFrame, int mainHeight, int mainWidth) {
        this.heightFrame = heightFrame;
        this.topFrame = topFrame;
        this.leftFrame = leftFrame;
        this.widthFrame = widthFrame;
        this.mainHeight = mainHeight;
        this.mainWidth = mainWidth;
    }

    public int getHeightFrame() {
        return heightFrame;
    }

    public void setHeightFrame(int heightFrame) {
        this.heightFrame = heightFrame;
    }

    public int getTopFrame() {
        return topFrame;
    }

    public void setTopFrame(int topFrame) {
        this.topFrame = topFrame;
    }

    public int getLeftFrame() {
        return leftFrame;
    }

    public void setLeftFrame(int leftFrame) {
        this.leftFrame = leftFrame;
    }

    public int getWidthFrame() {
        return widthFrame;
    }

    public void setWidthFrame(int widthFrame) {
        this.widthFrame = widthFrame;
    }

    public int getMainHeight() {
        return mainHeight;
    }

    public void setMainHeight(int mainHeight) {
        this.mainHeight = mainHeight;
    }

    public int getMainWidth() {
        return mainWidth;
    }

    public void setMainWidth(int mainWidth) {
        this.mainWidth = mainWidth;
    }
}
