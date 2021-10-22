package thinkinginjava4;

import java.text.NumberFormat;
import java.util.HashSet;

//枚举类型测试
public class EnumTest {

    public enum Type{
        MIDDLE,MINIMUM,MAXIMUM;


    }

    public static void main(String[] args) {

        // 测试hashset
        /*HashSet<String> hashSet = new HashSet<String>();
        hashSet.add("2");
        hashSet.add("3");
        hashSet.add("2");
        System.out.println(hashSet.size());*/

        double a = 15;
        double b = 15;
        double c = a/b;
        //获取百分百实列
        NumberFormat nf =   NumberFormat.getPercentInstance();
        //nf.setMinimumFractionDigits(2);//设置保留小数位
        // nf.setRoundingMode(RoundingMode.HALF_UP); //设置舍入模式
        String percent = nf.format(c);
        System.out.println(percent);
        /*Type a = Type.MAXIMUM;
        Type[] values = Type.values();
            switch (a){
                case MIDDLE:{
                    System.out.println("miiddle");
                }break;
                case MAXIMUM:{
                    System.out.println("maximum");
                }break;
                case MINIMUM:{
                    System.out.println("minimum");
                };break;
            }*/

    }
}

