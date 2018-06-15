public class MatrixEntry {
    private int Row;
    private int Col;
    private int Data;
    private MatrixEntry nextCol;
    private MatrixEntry nextRow;

    public MatrixEntry(int row,int col,int data){
        this.Row = row;
        this.Col = col;
        this.Data = data;
        this.nextCol = null;
        this.nextRow = null;
    }

    public MatrixEntry(int row, int col, int data, MatrixEntry nECol, MatrixEntry nERow){
        this.Row = row;
        this.Col = col;
        this.Data = data;
        this.nextCol = nECol;
        this.nextRow = nERow;
    }

    public int getColumn(){
        return Col;
    }

    public void setColumn(int col){
        Col = col;
    }

    public int getRow(){
        return Row;
    }

    public void setRow(int row){
        Row = row;
    }

    public int getData(){
        return Data;
    }

    public void setData(int dt){
        Data = dt;
    }

    public MatrixEntry getNextColumn(){
        return nextCol;
    }

    public void setNextColumn(MatrixEntry el){
        nextCol = el;
    }

    public MatrixEntry getNextRow(){
        return nextRow;
    }

    public void setNextRow(MatrixEntry el){
        nextRow = el;
    }
}
