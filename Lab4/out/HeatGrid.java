import java.lang.Math;
public class HeatGrid {
    private int m;
    private int n;
    private int[][] heatGrid;

    public HeatGrid(int width,int height){
        this.n = width;
        this.m = height;
        this.heatGrid = new int[m][n];
    }
    public boolean placeSource(String src,int x,int y){
        if(x>n||y>m||heatGrid[y][x]!=0){
            return false;
        }else{
            if(src =="l"){
                heatGrid[y][x]+=1;
            }else if(src =="c"){
                heatGrid[y][x]+=4;
                for(int i=x-1;i<=x+1;i++){
                    heatGrid[y-1][i]+=decayHeat(0.5,4,1);
                    heatGrid[y+1][i]+=decayHeat(0.5,4,1);
                    if(i!=x){
                        heatGrid[y][i]+=decayHeat(0.5,4,1);
                    }
                }
                for(int i=x-2;i<=x+2;i++){
                    heatGrid[y-2][i]+=decayHeat(0.5,4,2);
                    heatGrid[y+2][i]+=decayHeat(0.5,4,2);
                    if(i<x-1||i>x+1){
                        heatGrid[y-1][i]+=decayHeat(0.5,4,2);
                        heatGrid[y+1][i]+=decayHeat(0.5,4,2);
                        heatGrid[y][i]+=decayHeat(0.5,4,2);
                    }
                }
            }else if(src=="f"){
                heatGrid[x][y]+=10;
                for(int i=0;i<m;i++){
                    for(int j=0;j<n;j++){
                        if((Math.abs(x-i)<1.1&&Math.abs(x-i)>0)||(Math.abs(y-j)<1.1&&Math.abs(y-j)>0)){
                            heatGrid[i][j]+=decayHeat(0.35,10,1);
                        }else if((Math.abs(x-i)<2.1&&Math.abs(x-i)>1.1)||(Math.abs(y-j)<2.1&&Math.abs(y-j)>1.1)){
                            heatGrid[i][j]+=decayHeat(0.35,10,2);
                        }else if((Math.abs(x-i)<3.1&&Math.abs(x-i)>2.1)||(Math.abs(y-j)<3.1&&Math.abs(y-j)>2.1)){
                            heatGrid[i][j]+=decayHeat(0.35,10,3);
                        }else if((Math.abs(x-i)<4.1&&Math.abs(x-i)>3.1)||(Math.abs(y-j)<4.1&&Math.abs(y-j)>3.1)){
                            heatGrid[i][j]+=decayHeat(0.35,10,4);
                        }
                    }
                }
            }else if(src=="i"){
                heatGrid[x][y]+=-2;
                for(int i=0;i<m;i++){
                    for(int j=0;j<n;j++){
                        if((Math.abs(x-i)<1.1&&Math.abs(x-i)>0)||(Math.abs(y-j)<1.1&&Math.abs(y-j)>0)){
                            heatGrid[i][j]+=decayHeat(0.5,-2,1);
                        }
                    }
                }
            }else if(src=="r"){
                heatGrid[x][y]+=-8;
                for(int i=0;i<m;i++){
                    for(int j=0;j<n;j++){
                        if((Math.abs(x-i)<1.1&&Math.abs(x-i)>0)||(Math.abs(y-j)<1.1&&Math.abs(y-j)>0)){
                            heatGrid[i][j]+=decayHeat(0.2,-8,1);
                        }else if((Math.abs(x-i)<2.1&&Math.abs(x-i)>1.1)||(Math.abs(y-j)<2.1&&Math.abs(y-j)>1.1)){
                            heatGrid[i][j]+=decayHeat(0.2,-8,2);
                        }else if((Math.abs(x-i)<3.1&&Math.abs(x-i)>2.1)||(Math.abs(y-j)<3.1&&Math.abs(y-j)>2.1)){
                            heatGrid[i][j]+=decayHeat(0.2,-8,3);
                        }
                    }
                }
            }else if(src=="g"){
                heatGrid[x][y]+=-20;
                for(int i=0;i<m;i++){
                    for(int j=0;j<n;j++){
                        if((Math.abs(x-i)<1.1&&Math.abs(x-i)>0)||(Math.abs(y-j)<1.1&&Math.abs(y-j)>0)){
                            heatGrid[i][j]+=decayHeat(0.15,-20,1);
                        }else if((Math.abs(x-i)<2.1&&Math.abs(x-i)>1.1)||(Math.abs(y-j)<2.1&&Math.abs(y-j)>1.1)){
                            heatGrid[i][j]+=decayHeat(0.15,-20,2);
                        }else if((Math.abs(x-i)<3.1&&Math.abs(x-i)>2.1)||(Math.abs(y-j)<3.1&&Math.abs(y-j)>2.1)){
                            heatGrid[i][j]+=decayHeat(0.15,-20,3);
                        }else if((Math.abs(x-i)<4.1&&Math.abs(x-i)>3.1)||(Math.abs(y-j)<4.1&&Math.abs(y-j)>3.1)){
                            heatGrid[i][j]+=decayHeat(0.15,-20,4);
                        }else if((Math.abs(x-i)<4.1&&Math.abs(x-i)>3.1)||(Math.abs(y-j)<4.1&&Math.abs(y-j)>3.1)){
                            heatGrid[i][j]+=decayHeat(0.15,-20,5);
                        }
                    }
                }
            }
            return true;
        }
    }
    public int[][] getHeats(){
        return heatGrid;
    }
    public int getHeat(int x,int y){
        return heatGrid[x][y];
    }
    public int getNetHeat(){
        int cont = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(heatGrid[i][j]!=0){
                    cont+=heatGrid[i][j];
                }
            }
        }
        return cont;
    }
    private int decayHeat(double decay,int heat, int distance){
        return (int)(heat*Math.exp(-1*decay*distance));
    }
}
