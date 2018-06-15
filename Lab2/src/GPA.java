public class GPA{

    public static void main(String args[]){

	double all = 0.0;
	double allCredit = 0.0;
	double gpa;

	for (int i = 0; i < args.length; i ++){
	    if (i % 2 == 0){
		if (args[i].equals("a")){		
		    all = all + 4.0*Double.valueOf(args[i+1]);
		}else if (args[i].equals("a-")){
		    all = all + 3.667*Double.valueOf(args[i+1]);
		}else if (args[i].equals("b+")){
		    all = all + 3.333*Double.valueOf(args[i+1]);
		}else if (args[i].equals("b")){
		    all = all + 3.0*Double.valueOf(args[i+1]);
		}else if (args[i].equals("b-")){
		    all = all + 2.667*Double.valueOf(args[i+1]);
		}else if (args[i].equals("c+")){
		    all = all + 2.333*Double.valueOf(args[i+1]);
		}else if (args[i].equals("c")){
		    all = all + 2.0*Double.valueOf(args[i+1]);
		}else if (args[i].equals("c-")){
		    all = all + 1.667*Double.valueOf(args[i+1]);
		}else if (args[i].equals("d+")){
		    all = all + 1.333*Double.valueOf(args[i+1]);
		}else if (args[i].equals("d")){
		    all = all + 1.0*Double.valueOf(args[i+1]);
		}else if (args[i].equals("f")){
		    all = all + 0.0*Double.valueOf(args[i+1]);
		}
	    }else if (i % 2 == 1){
		allCredit = allCredit + Double.valueOf(args[i]);
	    }
        }
	
	gpa = all/allCredit;
	System.out.format("%.3f", gpa);
//	System.out.println(gpa);
    }

}
