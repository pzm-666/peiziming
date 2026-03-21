/**
 * 温度转换工具类
 * 功能：实现摄氏温度与华氏温度的互相转换，支持交互式输入与命令行参数
 */
/**
 * 将摄氏度转换为华氏度
 * @param c 摄氏温度值（float类型）
 * @return 对应的华氏温度值（float类型）
 */
public static float celsiusToFahrenheit(float c) {
    return c * 9.0F / 5.0F + 32.0F;
}

/**
 * 将华氏度转换为摄氏度
 * @param f 华氏温度值（float类型）
 * @return 对应的摄氏温度值（float类型）
 */
public static float fahrenheitToCelsius(float f) {
    return (f - 32.0F) * 5.0F / 9.0F;
}

/**
 * 主方法：程序入口
 * @param args 命令行参数（可选，格式：java TemperatureConverter 36.6 C）
 */
void main(String[] args) {
    if (args.length >= 2) {
        // 命令行参数模式
        try {
            float value = Float.parseFloat(args[0]);
            String unit = args[1].toUpperCase();
            convertAndPrint(value, unit);
        } catch (Exception e) {
            IO.println("命令行参数解析失败，请按示例输入：java TemperatureConverter 36.6 C");
        }
        return;
    }

    // 交互式输入模式
    Scanner scanner = new Scanner(System.in);
    IO.println("===== 温度转换程序 =====");
    IO.print("请输入要转换的温度与单位（例如 36.6 C 或 97 F）：");
    String s = scanner.nextLine().strip();

    if (s.isEmpty()) {
        IO.println("输入为空，程序退出。");
        scanner.close();
        return;
    }

    String[] parts = s.split("\\s+");
    try {
        float value = Float.parseFloat(parts[0]);
        String unit = parts.length > 1 ? parts[1].toUpperCase() : "C";
        convertAndPrint(value, unit);
    } catch (Exception e) {
        IO.println("输入解析失败，请按示例输入数值与单位，例如：36.6 C");
    }
    scanner.close();
}

/**
 * 执行温度转换并打印结果
 * @param value 温度数值
 * @param unit 温度单位（C/F）
 */
private static void convertAndPrint(float value, String unit) {
    if (unit.startsWith("C")) {
        float f = celsiusToFahrenheit(value);
        System.out.printf("%.2f °C = %.2f °F%n", value, f);
    } else if (unit.startsWith("F")) {
        float c = fahrenheitToCelsius(value);
        System.out.printf("%.2f °F = %.2f °C%n", value, c);
    } else {
        IO.println("未知单位，请使用 C 或 F。");
    }
}
