publoc class Image{
    private int width;
    private int height;
    private ArrayList<ArrayList<Color>> pixels;

    public Image(int width, int height){
        this.width = width;
        this.height =  height;
    }

    public int getHeight(){
        return this.height;
    }

    public int getWidth(){
        return this.width;
    }

    // Get color at (x, y)
    public Color getColor(int x, int y) {
        return pixels.get(y).get(x); // rows = y, cols = x
    }

    // Set color at (x, y)
    public void setColor(int x, int y, Color color) {
        pixels.get(y).set(x, color);
    }
}