// package hackerrank;

// import java.io.BufferedReader;
// import java.io.BufferedWriter;
// import java.io.FileWriter;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;

// public class AbsolutePermutation {

//     // thieu truong hop, ko phai chi can k = 0 || k= n/2 la lam duoc
// //   public static List<Integer> absolutePermutation(int n, int k) {
// //     if(k!=0 && (n%2 !=0 || k!=n/2)) {
// //         return Arrays.asList(-1);
// //     }
// //     List<Integer> list = new ArrayList<>();

// //     if(k == 0 ){
// //         for(int i= 1; i <= n; i++) {
// //             list.add(i);
// //         }
        
// //         return list;
// //     }
// //     else {
// //         for(int i= n/2+1;i<=n; i++) {
// //             list.add(i);
// //         }
// //         for(int i=1;i<=n/2;i++) {
// //             list.add(i);
// //         }
        
// //         return list;
// //     }

// //     }
//     public static List<Integer> absolutePermutation(int n, int k) {
//         int[] arr = new int[n];
//         for(int i=0;i<n;i++) {
//             arr[i] = i+1;
//         }

//         for(int i=0;i<n;i++) {
//             if(Math.abs(arr[i]-i-1) !=k)
//         }
//     }

//   public static void main(String[] args) throws IOException {
//     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//     BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("text.txt"));

//     int t = Integer.parseInt(bufferedReader.readLine().trim());

//     for (int tItr = 0; tItr < t; tItr++) {
//         String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

//         int n = Integer.parseInt(firstMultipleInput[0]);

//         int k = Integer.parseInt(firstMultipleInput[1]);

//         List<Integer> result = absolutePermutation(n, k);

//         for (int i = 0; i < result.size(); i++) {
//             bufferedWriter.write(String.valueOf(result.get(i)));

//             if (i != result.size() - 1) {
//                 bufferedWriter.write(" ");
//             }
//         }

//         bufferedWriter.newLine();
//     }

//     bufferedReader.close();
//     bufferedWriter.close();
// }
// }