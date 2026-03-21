public class DataCleaner {
    public static void main(String[] args) {
        int[] sensorData = {85, -5, 92, 0, 105, 999, 88, 76};
        int validSum = 0;    // 有效数据总和
        int validCount = 0;  // 有效数据个数

        // 遍历数组，按规则处理数据
        for (int data : sensorData) {
            // 规则3：致命错误，遇到999直接终止流程
            if (data == 999) {
                System.out.println("致命错误：传感器掉线，终止处理");
                break;
            }
            // 规则2：无效数据，0/负数/大于100（非999），跳过并打印警告
            if (data <= 0 || data > 100) {
                System.out.println("警告：发现越界数据[" + data + "]，已跳过");
                continue;
            }
            // 规则1：正常范围1-100，计入有效数据
            validSum += data;
            validCount++;
        }

        // 规则4：最终输出
        if (validCount > 0) {
            // 避免整数除法陷阱，将总和转为double再计算
            double average = (double) validSum / validCount;
            System.out.println("有效数据平均值为：" + average);
        } else {
            System.out.println("无有效数据");
        }
    }
}
