package example;
public class Average {

        /**
         * Returns the average of an array of numbers
         * @param the array of integer numbers
         * @return the average of the numbers
         */
        public static float average(int[] nums) {
            float result = 0;
            // Add your code
            int sum = 0;
            for (int num: nums) {
                sum += num;
            }

            result = sum/nums.length;
            return result;
        }

        public static void main(String[] args) {
            // Add your code
            int number[] = {1, 2, 3, 4, 5};
            float result = average(number);
            System.out.println(result);
        }
}
