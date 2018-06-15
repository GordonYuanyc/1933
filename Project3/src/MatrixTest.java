public class MatrixTest {
    public static void main(String args[]){
        SparseIntMatrix mat1 = new SparseIntMatrix(1000,1000,"matrix1_data.txt");
        SparseIntMatrix mat2 = new SparseIntMatrix(1000,1000,"matrix2_data.txt");
        SparseIntMatrix mat3 = new SparseIntMatrix(1000,1000,"matrix2_noise.txt");
        SparseIntMatrix mat4 = new SparseIntMatrix(1000,1000,"matrix2_data.txt");
        mat4.minus(mat3);

        MatrixViewer.show(mat1);
        MatrixViewer.show(mat2);
        MatrixViewer.show(mat4);
    }
}
