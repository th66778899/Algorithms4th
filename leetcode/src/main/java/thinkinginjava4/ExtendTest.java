package thinkinginjava4;
class Basic{
    private String s = "Basic";
    public void append(String s){this.s+=s;}
    public void dilute(){append("dilute");}
    public void apply(){append("apply");}

    @Override
    public String toString() {
        return "Basic{" +
                "s='" + s + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Basic basic = new Basic();
        basic.dilute();
        basic.apply();
        System.out.println(basic);
    }
}
public class ExtendTest extends Basic{
    public void scrub(){

        super.append("extend test");
    }

    public static void main(String[] args) {
        ExtendTest extendTest = new ExtendTest();
        extendTest.scrub();
        System.out.println(extendTest);
        Basic.main(args);
    }
}
