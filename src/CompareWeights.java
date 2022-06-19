import java.util.Comparator;

public static class CompareWeights implements Comparator<String>{

    @Override
    public int compare(String str1, String str2){
        String[] arr1 = str1.split(",");
        String[] arr2 = str2.split(",");
        int result = 0;
        int w1 = Integer.parseInt(arr1[2]);
        int w2 = Integer.parseInt(arr2[2]);

        if(w1 < w2){
            result = -1;
        }else if(w1 > w2){
            result = 1;
        }
        return result;
    }
    public static void main(String[] args) {
        //Comparator<String> c = new CompareWeights();
        //System.out.println(c.compare("7,5,3","8,3,2"));
    }
}