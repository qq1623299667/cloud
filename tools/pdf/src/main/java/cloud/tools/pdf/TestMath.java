package cloud.tools.pdf;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;

/**
 * @author 石佳
 * @date 2021/4/15 20:29
 */
@Slf4j
public class TestMath {
    public static void main(String[] args) {
        // ... 创建并初始化输入数据：
        double[] x = new double[]{1,2,3,4,5};
        double[] y = new double[]{16.2,16.3,16.5,17.1,17.5};
        double v = calculateFunctionResult(x, y, 1, 6);
        log.info(String.valueOf(v));
    }


    // 计算出公式结果
    private static double calculateFunctionResult(double[] x, double[] y, int degree, double n) {
        double[] factor = calculateFunctionFactor(x, y, degree);
        double result = 0;
        for(int i=0;i<degree;i++){
            result = result+factor[i]*Math.pow(n, i);
        }
        return result;
    }

    // 计算出公式系数
    private static double[] calculateFunctionFactor(double[] x, double[] y,int degree) {
        // 将原始的x-y数据序列合成带权重的观察点数据序列：
        WeightedObservedPoints points = new WeightedObservedPoints();
        // 将x-y数据元素调用points.add(x[i], y[i])加入到观察点序列中
        for(int i=0;i<x.length;i++){
            points.add(x[i],y[i]);
        }
        // ...
        PolynomialCurveFitter fitter = PolynomialCurveFitter.create(degree);   // degree 指定多项式阶数
        double[] result = fitter.fit(points.toList());   // 曲线拟合，结果保存于双精度数组中，由常数项至最高次幂系数排列
        return result;
    }
}
