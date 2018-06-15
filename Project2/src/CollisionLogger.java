public class CollisionLogger {
    private int screenWidth;
    private int screenHeight;
    private int bucketWidth;
    public int n,m;
    int[][] bucketHolder = new int[n][m];
    
    public CollisionLogger(int screenWidth, int screenHeight, int bucketWidth) {
    	this.screenWidth = screenWidth;
    	this.screenHeight = screenHeight;
    	this.bucketWidth = bucketWidth;
    	bucketHolder = new int[screenWidth/bucketWidth][screenHeight/bucketWidth];
    }
     /**
     * This method records the collision event between two balls. Specifically, in increments the bucket corresponding to
     * their x and y positions to reflect that a collision occurred in that bucket.
     */
    public void collide(Ball one, Ball two) {
    	double x = (one.getXPos()+two.getXPos())/2;
    	double y = (one.getYPos()+two.getYPos())/2;
        double p = (x-x%bucketWidth)/bucketWidth;
    	double q = (y-y%bucketWidth)/bucketWidth;
    	int n = (int) p;
    	int m = (int) q;
    	bucketHolder[n][m]=bucketHolder[n][m] + 1;
    }
    /**
     * Returns the heat map scaled such that the bucket with the largest number of collisions has value 255,
     * and buckets without any collisions have value 0.
     */
    public int[][] getNormalizedHeatMap() {
        int[][] normalizedHeatMap = new int[screenWidth/bucketWidth][screenHeight/bucketWidth];
    	double max=0;
    	for(int i=0;i<screenWidth/bucketWidth;i++){
    	    for(int j=0;j<screenHeight/bucketWidth;j++){
    	        if (bucketHolder[i][j]>max){
    	            max = bucketHolder[i][j];
                }
            }
        }
        double q;
        for(int i=0;i<screenWidth/bucketWidth;i++) {
            for (int j = 0; j < screenHeight/bucketWidth; j++) {
                q = (bucketHolder[i][j]/max)*255;
                int k = (int)q;
                normalizedHeatMap[i][j] = k;
            }
        }
        return normalizedHeatMap;
    }
}
