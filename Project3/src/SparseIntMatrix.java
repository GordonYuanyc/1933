/*
Part4:
(a):
For a SparseIntMatrix, each non-zero element needs 5 memory unit. m non-zero elements
requires 5m memory units.
For a 2-D array, each element requires 1 unit. So, a N*N 2-D array requires N^2 memory units.
(b):
When N=100,000 and m=1,000,000:
Memory needed for SparseIntMatrix:5,000,000;
Memory needed for 2-D array:10,000,000,000;
So: SparseIntMatrix is more space-efficient.

When the space needed is the same:5*m=N*N;
plug in N=100,000: m=2,000,000,000;
So: when there are more than 2,000,000,000 elements,where m=2,000,000,000, the 2-D array is
more space-efficient.

 */

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class SparseIntMatrix {
    private int rowNumber;
    private int colNumber;
    private MatrixEntry[] rowData;// = new MatrixEntry[rowNumber];//first element of each row;
    private MatrixEntry[] colData;// = new MatrixEntry[colNumber];//first element of each col;

    public SparseIntMatrix(int numRows,int numCols){
        this.rowNumber = numRows;
        this.colNumber = numCols;
        this.rowData = new MatrixEntry[rowNumber];
        this.colData = new MatrixEntry[colNumber];

    }

    public SparseIntMatrix(int numRows,int numCols,String inputFile){
        this.rowNumber = numRows;
        this.colNumber = numCols;
        this.rowData = new MatrixEntry[rowNumber];
        this.colData = new MatrixEntry[colNumber];
        String string1="src/"+inputFile;
        File file = new File(string1);
        ArrayList<String> listA= new ArrayList<String>();
        listA = readFile(file);
        for(int i=0;i<listA.size();i++){
            String tempString = listA.get(i);
            String[] tempArray = tempString.split(",");
            int[] tempEntryArray = new int[3];
            tempEntryArray[0]=Integer.parseInt(tempArray[0]);
            tempEntryArray[1]=Integer.parseInt(tempArray[1]);
            tempEntryArray[2]=Integer.parseInt(tempArray[2]);
            int rowNum=tempEntryArray[0]-1;
            int colNum=tempEntryArray[1]-1;
            int dtNum=tempEntryArray[2];
            setElement(rowNum,colNum,dtNum);

            //this noted part also works and doesn't need the setElement method
            /*MatrixEntry newEntry=new MatrixEntry(rowNum,colNum,dtNum,null,null);
            if(rowData[rowNum]==null){
                MatrixEntry temp1=new MatrixEntry(0,0,0,newEntry,null);
                MatrixEntry temp2=new MatrixEntry(0,0,0,null,newEntry);
                rowData[rowNum]=temp1;
                colData[colNum]=temp2;
            }else if(rowData[rowNum]!=null){
                MatrixEntry temp1=rowData[rowNum];
                while(temp1.getNextColumn()!=null&&temp1.getNextColumn().getColumn()<colNum){
                    temp1=temp1.getNextColumn();
                }
                if(temp1.getNextColumn()==null){
                    temp1.setNextColumn(newEntry);
                    if(colData[colNum]==null){
                        colData[colNum]=new MatrixEntry(0,0,0,null,newEntry);
                    }else{
                        MatrixEntry temp2=colData[colNum];
                        while(temp2.getNextRow()!=null&&temp2.getNextRow().getRow()<rowNum){
                            temp2=temp2.getNextRow();
                        }
                        if(temp2.getNextRow()==null){
                            temp2.setNextRow(newEntry);
                        }else{
                            newEntry.setNextRow(temp2.getNextRow());
                            temp2.setNextRow(newEntry);
                        }
                    }
                }else if(temp1.getNextColumn().getColumn()>colNum){
                    newEntry.setNextColumn(temp1.getNextColumn());
                    temp1.setNextColumn(newEntry);
                    MatrixEntry temp2=colData[colNum];
                    while(temp2.getNextRow()!=null&&temp2.getNextRow().getRow()<rowNum){
                        temp2=temp2.getNextRow();
                    }
                    if(temp2.getNextRow()==null){
                        temp2.setNextRow(newEntry);
                    }else{
                        newEntry.setNextRow(temp2.getNextRow());
                        temp2.setNextRow(newEntry);
                    }
                }
            }*/
        }
    }

    private ArrayList<String> readFile(File input){
        Scanner s;
        ArrayList<String> listA= new ArrayList<String>();
        try {
            s = new Scanner(input);
        } catch(Exception e) {
            System.out.println("There was an error opening the file");
            return null;
        }
        while (s.hasNextLine()) {
            String line = s.nextLine();
            listA.add(line);
        }
        s.close();
        return listA;
    }

    public int getElement(int row,int col){
        int result=0;
        if(getEntryAt(row,col)!=null){
            result=getEntryAt(row,col).getData();
        }else{
            result=0;
        }
        return result;
    }

    public boolean setElement(int row,int col,int data){
        boolean cont = true;
        MatrixEntry newEntry=new MatrixEntry(row,col,data,null,null);
        if(row<0||col<0||row>rowNumber-1||col>colNumber-1){
            cont = false;
        }else if(rowData[row]!=null&&colData[col]!=null){
            MatrixEntry check=getEntryAt(row,col);
            if(check!=null){
                getEntryAt(row,col).setData(data);
            }else{
                MatrixEntry tempRow=rowData[row];
                MatrixEntry tempCol=colData[col];
                while(tempRow.getNextColumn()!=null&&tempRow.getNextColumn().getColumn()<col){
                    tempRow=tempRow.getNextColumn();
                }
                while(tempCol.getNextRow()!=null&&tempCol.getNextRow().getRow()<row){
                    tempCol=tempCol.getNextRow();
                }
                if(tempRow.getNextColumn()!=null){
                    newEntry.setNextColumn(tempRow.getNextColumn());
                    tempRow.setNextColumn(newEntry);
                }else{
                    tempRow.setNextColumn(newEntry);
                }
                if(tempCol.getNextRow()!=null){
                    newEntry.setNextRow(tempCol.getNextRow());
                    tempCol.setNextRow(newEntry);
                }else{
                    tempCol.setNextRow(newEntry);
                }
            }
        } else if(rowData[row]!=null||colData[col]!=null){
            if(rowData[row]==null&&colData[col]!=null) {
                rowData[row] = new MatrixEntry(0, 0, 0, newEntry, null);
                MatrixEntry temp = colData[col];
                while (temp.getNextRow() != null && temp.getNextRow().getRow() < row) {
                    temp = temp.getNextRow();
                }
                if (temp.getNextRow() != null) {
                    newEntry.setNextRow(temp.getNextRow());
                    temp.setNextRow(newEntry);
                    rowData[row] = new MatrixEntry(0, 0, 0, newEntry, null);
                } else {
                    temp.setNextRow(newEntry);
                }
            }
            if(rowData[row]!=null&&colData[col]==null){
                colData[col]=new MatrixEntry(0,0,0,null,newEntry);
                MatrixEntry temp=rowData[row];
                while(temp.getNextColumn()!=null&&temp.getNextColumn().getColumn()<col){
                    temp=temp.getNextColumn();
                }
                if(temp.getNextColumn()!=null){
                    newEntry.setNextColumn(temp.getNextColumn());
                    temp.setNextColumn(newEntry);
                    colData[col]=new MatrixEntry(0,0,0,null,newEntry);
                }else{
                    temp.setNextColumn(newEntry);
                }
            }
        }else if(rowData[row]==null&&colData[col]==null){
            rowData[row]=new MatrixEntry(0,0,0,newEntry,null);
            colData[col]=new MatrixEntry(0,0,0,null,newEntry);
        }
        return cont;
    }

    public int getNumCols(){
        return colNumber;
    }

    public int getNumRows(){
        return rowNumber;
    }

    public boolean plus(SparseIntMatrix otherMat){
        boolean cont = false;
        for(int i=0;i<rowNumber;i++){
            for(int j=0;j<colNumber;j++){
                if(getEntryAt(i,j)!=null&&otherMat.getEntryAt(i,j)!=null){
                    int temp1=getEntryAt(i,j).getData();
                    int temp2=otherMat.getEntryAt(i,j).getData();
                    temp1=temp1+temp2;
                    getEntryAt(i,j).setData(temp1);
                    cont =true;
                }
            }
        }
        return cont;
    }

    public boolean minus(SparseIntMatrix otherMat){
        boolean cont = false;
        for(int i=0;i<rowNumber;i++){
            for(int j=0;j<colNumber;j++){
                if(getEntryAt(i,j)!=null&&otherMat.getEntryAt(i,j)!=null){
                    int temp1=getEntryAt(i,j).getData();
                    int temp2=otherMat.getEntryAt(i,j).getData();
                    temp1=temp1-temp2;
                    getEntryAt(i,j).setData(temp1);
                    cont =true;
                }
            }
        }
        return cont;
    }

    private MatrixEntry getEntryAt(int row,int col){
        MatrixEntry currentEntry=null;
        if(rowData[row]==null||colData[col]==null||
                row<0||col<0||row>rowNumber||col>colNumber){
            currentEntry = null;
        }else{
            currentEntry=rowData[row];
            while(currentEntry.getNextColumn()!=null&&currentEntry.getNextColumn().getColumn()<col){
                currentEntry=currentEntry.getNextColumn();
            }
            if(currentEntry.getNextColumn()==null){
                currentEntry=null;
            }else if(currentEntry.getNextColumn().getColumn()==col){
                currentEntry=currentEntry.getNextColumn();
            }else{
                currentEntry=null;
            }
        }
        return currentEntry;
    }
}
